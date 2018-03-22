package com.lsnotes.spring5webapp.bootstrap;

import com.lsnotes.spring5webapp.model.Author;
import com.lsnotes.spring5webapp.model.Book;
import com.lsnotes.spring5webapp.model.Publisher;
import com.lsnotes.spring5webapp.repositories.AuthorRepository;
import com.lsnotes.spring5webapp.repositories.BookRepository;
import com.lsnotes.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}

	private void initData() {

		Publisher publisher = new Publisher();
		publisher.setName("Libero");

		publisherRepository.save(publisher);

		Author pratchett = new Author("Terry", "Pratchett");
		Book guard = new Book("Guard! Guard!", "1324-32432", publisher);
		pratchett.getBooks().add(guard);
		guard.getAuthors().add(pratchett);

		authorRepository.save(pratchett);
		bookRepository.save(guard);

		Author bob = new Author("Robert", "Martin");
		Book cleanCode = new Book("Clean Code", "45332-3434", publisher);
		cleanCode.getAuthors().add(bob);

		authorRepository.save(bob);
		bookRepository.save(cleanCode);
	}
}
