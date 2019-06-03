package br.fjn.edu.biblioteca.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.fjn.edu.biblioteca.components.RegisterLog;
import br.fjn.edu.biblioteca.components.UserSession;
import br.fjn.edu.biblioteca.model.Operations;
import br.fjn.edu.biblioteca.repository.ServiceRepository;

@Controller
public class IndexController {

	@Inject
	private UserSession userSession;
	@Inject
	private ServiceRepository ServiceRep;
	@Inject
	private Result result;
	@Inject
	private RegisterLog log;

	@Get("/")
	public void index() {

		log.registrationLog(Operations.READ, "Service");
		result.include("user", userSession.getUser());
		result.include("services", ServiceRep.ListServices());
		result.of(ServiceController.class).list();
	}

}
