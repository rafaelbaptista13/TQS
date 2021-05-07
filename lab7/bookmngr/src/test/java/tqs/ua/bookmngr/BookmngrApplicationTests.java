package tqs.ua.bookmngr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
class BookmngrApplicationTests {

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer()
		.withUsername("duke")
		.withPassword("password")
		.withDatabaseName("test");

	@Autowired
	private BookRepository bookRepository;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Test
	void contextLoads() {
		Book book = new Book();
		book.setTitle("Testcontainers");

		bookRepository.save(book);

		System.out.println("Context loads!");
	}

	@Test
	@Order(1)
	void testCreateBook() {
		Book book = new Book();
		book.setTitle("Os lusiadas");
		bookRepository.save(book);
		System.out.println("Book Created!");
	}

	@Test
	@Order(2)
	void testUpdateBook() {
		Book book = bookRepository.findByTitle("Os lusiadas");
		book.setTitle("Os Lusiadas");
		bookRepository.save(book);
		System.out.println("Book updated!");
	}

	@Test
	@Order(3)
	void testReadBook() {
		Book book = bookRepository.findByTitle("Os Lusiadas");
		assert book != null;
		System.out.println("Book Read!");
	}

	@Test
	@Order(4)
	void testDeleteBook() {
		Book book = bookRepository.findByTitle("Os Lusiadas");
		bookRepository.delete(book);
		Book book_deleted = bookRepository.findByTitle("Os Lusiadas");
		assert book_deleted == null;
		System.out.println("Book deleted!");
	}
}
