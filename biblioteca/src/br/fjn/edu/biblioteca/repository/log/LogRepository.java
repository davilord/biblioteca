package br.fjn.edu.biblioteca.repository.log;

import javax.persistence.EntityManager;

import br.fjn.edu.biblioteca.connection.ConnectionLog;
import br.fjn.edu.biblioteca.model.log.Log;

public class LogRepository {
	
	
	public void register(Log log){
		
		EntityManager manager = ConnectionLog.getEntityManager();

		manager.getTransaction().begin();

		try {
			manager.persist(log);
			manager.getTransaction().commit();
		} catch (NullPointerException nullPointerException) {
			manager.getTransaction().rollback();
		} catch (Exception exception) {
			manager.getTransaction().rollback();
		} finally {
			manager.clear();
			manager.close();
		}
	} 
}
