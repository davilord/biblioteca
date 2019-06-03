package br.fjn.edu.biblioteca.test;

import br.fjn.edu.biblioteca.model.Service;
import br.fjn.edu.biblioteca.model.Book;
import br.fjn.edu.biblioteca.repository.ServiceRepository;
import br.fjn.edu.biblioteca.repository.BookRepository;

public class Main {

	public static void main(String[] args) {

		BookRepository bookRep = new BookRepository();

		Book book = new Book();
		book.setIsbn("nrd1353");

		Service service = new Service();
		service.setBook(bookRep.getBook(book.getIsbn()));
		ServiceRepository servRep = new ServiceRepository();
		servRep.insert(service);

	}
}
