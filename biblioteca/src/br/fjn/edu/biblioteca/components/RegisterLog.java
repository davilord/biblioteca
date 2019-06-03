package br.fjn.edu.biblioteca.components;

import java.util.Calendar;

import javax.inject.Inject;

import br.fjn.edu.biblioteca.model.Operations;
import br.fjn.edu.biblioteca.model.log.Log;
import br.fjn.edu.biblioteca.repository.log.LogRepository;

public class RegisterLog {

	
	private Log log;
	private LogRepository logRep;
	private UserSession userSession;
	
	@Inject
	public RegisterLog(Log log, LogRepository logRep, UserSession userSession) {
		this.log = log;
		this.logRep = logRep;
		this.userSession = userSession;
	}

	public void registrationLog(Operations operations, String model) {

		log.setUser_id(userSession.getUser().getId());
		log.setModel(model);
		log.setOperation(operations);
		log.setCreated(Calendar.getInstance());

		logRep.register(log);
	}
}
