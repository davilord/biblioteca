package br.fjn.edu.biblioteca.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.fjn.edu.biblioteca.anotations.Public;
import br.fjn.edu.biblioteca.components.UserSession;
import br.fjn.edu.biblioteca.model.User;
import br.fjn.edu.biblioteca.repository.UserRepository;

@Controller
public class LoginController {
	@Inject
	private UserSession userSession;

	@Inject
	private Result result;

	@Inject
	private Validator validator;

	@Public
	@Get("login")
	public void loginForm() {

	}

	@Post("loginUser")
	@Public
	public void loginUser(User user) {

		UserRepository userRep = new UserRepository();

		if (userRep.AuthenticationUser(user)) {

			userSession.setUser(userRep.getUser(user));
			result.redirectTo(IndexController.class).index();

		} else {
			validator.add(new SimpleMessage("danger", "Usuário ou senha inválido!"));
			validator.onErrorRedirectTo(this).loginForm();
		}

	}

	public void logout() {
		userSession.logout();
		result.redirectTo(this).loginForm();
	}

}
