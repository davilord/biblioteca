package br.fjn.edu.biblioteca.repository;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Restrictions;

import br.fjn.edu.biblioteca.connection.Connection;
import br.fjn.edu.biblioteca.model.Book;

public class BookRepository {
	public void insert(Book book) {
		EntityManager manager = Connection.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(book);
		manager.getTransaction().commit();
		manager.clear();
		manager.close();
	}

	public boolean bookExists(String isbn) {
		Book bookfind = (Book) Connection.getSession()
				.createCriteria(Book.class)
				.add(Restrictions.eq("isbn", isbn))
				.uniqueResult();
		return bookfind != null;
	}

	public Book getBook(String isbn) {

		return (Book) Connection.getSession().createCriteria(Book.class)
				.add(Restrictions.eq("isbn", isbn))
				.uniqueResult();

	}
}
