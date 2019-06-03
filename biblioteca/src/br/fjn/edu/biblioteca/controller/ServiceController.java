package br.fjn.edu.biblioteca.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.fjn.edu.biblioteca.components.RegisterLog;
import br.fjn.edu.biblioteca.components.UserSession;
import br.fjn.edu.biblioteca.model.Operations;
import br.fjn.edu.biblioteca.model.Service;
import br.fjn.edu.biblioteca.model.User;
import br.fjn.edu.biblioteca.model.Book;
import br.fjn.edu.biblioteca.repository.ServiceRepository;
import br.fjn.edu.biblioteca.repository.BookRepository;

@Controller
@Path("service")
public class ServiceController {

	private UserSession userSession;
	private Result result;
	private BookRepository bookRep;
	private ServiceRepository servRep;
	private RegisterLog log;
	private Validator validator;
	private final String MODEL = "Service";

	@Inject
	public ServiceController(UserSession session, Result result, BookRepository bookRep,
			ServiceRepository servRep, RegisterLog log, Validator validator) {
		this.userSession = session;
		this.result = result;
		this.bookRep = bookRep;
		this.servRep = servRep;
		this.log = log;
		this.validator = validator;
	}

	/**
	 * @deprecated for CDI
	 */
	ServiceController() {
	}

	@Get("/")
	public void index() {

		result.include("user", userSession.getUser());

	}

	@Post("newService")
	public void newService(Service service, Book book) {

		User user = userSession.getUser();

		if (bookRep.bookExists(book.getIsbn())) {
			Book v = bookRep.getBook(book.getIsbn());
			service.setBook(v);
		} else {
			service.setBook(book);
		}

		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");

		service.setUser(user);
		service.setCreated(formatador.format(data));
		service.setDateTimeEntry(Calendar.getInstance());

		servRep.insert(service);
		log.registrationLog(Operations.CREATE, MODEL);

		validator.add(new SimpleMessage("success", "Serviço realizado!"));
		validator.onErrorRedirectTo(IndexController.class).index();

	}

	@Get("services")
	public void list() {

		log.registrationLog(Operations.READ, MODEL);
		result.include("user", userSession.getUser());
		result.include("services", servRep.ListServices());
	}

	@Get("search")
	public void search(String isbn) {

		if (bookRep.bookExists(isbn)) {

			log.registrationLog(Operations.READ, MODEL);
			result.use(Results.json()).withoutRoot().from(bookRep.getBook(isbn)).serialize();

		}

	}

	@Get("busca")
	public void busca(String day) {

		log.registrationLog(Operations.READ, MODEL);
		result.include("services", servRep.ListServicesByDay(day));
		result.of(this).list();
	}

	@Get("checkout/{id}")
	public void checkout(int id) {
		if (servRep.serviceExist(id)) {

			log.registrationLog(Operations.UPDATE, MODEL);
			Service checkOut = servRep.getService(id);
			checkOut.setDateTimeOut(Calendar.getInstance());

			String[] tempo = this.Stay(checkOut);

			int hora = Integer.parseInt(tempo[0]) * 60;
			int min = Integer.parseInt(tempo[1]);
			int tempoTotal = hora + min;
			checkOut.setStay(tempoTotal);
			double vDef = 3.0;
			double vStay = tempoTotal * 0.03;

			double totalPrice = vDef + vStay;
			checkOut.setAmount(totalPrice);

			servRep.update(checkOut);
			validator.add(new SimpleMessage("success", "Livro devolvido com sucesso!"));
			validator.onErrorRedirectTo(IndexController.class).index();
		}
	}

	private String[] Stay(Service service) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		long start = service.getDateTimeEntry().getTimeInMillis();
		long out = service.getDateTimeOut().getTimeInMillis();

		long time = out - start;

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		cal.add(Calendar.SECOND, Integer.parseInt("" + (time / 1000)));
		System.out.println(time / 1000);

		return sdf.format(cal.getTime()).split(":");

	}

}
