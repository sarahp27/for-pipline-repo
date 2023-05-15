package afn.xloop.booktracker_cnsd23;

import java.util.ArrayList;
import java.util.Collection;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class BooktrackerCnsd23ApplicationTests {

	private MockMvc mvc;

	@Mock
	private BookRepository bookrepository;

	@InjectMocks
	private BookController bookcontroller;

	private JacksonTester<Book> jsonBook;
	private JacksonTester<Collection<Book>> jsonBooks;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(bookcontroller).build();
	}

	// When I enter the Title, author, year of publication, and lenght of book into
	// the UI an hit Submit, my book will save to the list.
	@Test
	public void canCreateANewBook() throws Exception {
		Book book = new Book(1, "HTML for Babies", "Some Kid", 1999, 26);
		mvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBook.write(book).getJson()))
				.andExpect(status().isOk());
	}

	// View all book
	@Test
	public void canGetAllBooks() throws Exception {
		Book book1 = new Book(1, "HTML for Babies", "Some Kid", 1999, 26);
		Book book2 = new Book(2, "C# Expert", "Rox", 2006, 260);
		Collection<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		when(bookrepository.getAllBook()).thenReturn(books);
		mvc.perform(get("/books/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonBooks.write(books).getJson()));

	}

	// @Test 
	// public void CanDeletebook() throws Exception{
	// 	Book book1 = new Book(1, "HTML for Babies", "Some Kid", 1999, 26);
	// 	Book book2 = new Book(2, "C# Expert", "Rox", 2006, 260);
	// 	Collection<Book> books = new ArrayList<Book>();
	// 	books.add(book1);
	// 	books.add(book2);

	// 	books.remove(book1);
     
	// 	when(bookrepository.getAllBook()).thenReturn(books);
	// 	mvc.perform(delete("/books/delete/id")
	// 			.contentType(MediaType.APPLICATION_JSON));
	// 			// .andExpect(status().isOk());
				
	// 		// assertTrue(books.containsOnly(book2));
	// 		assertTrue(books.size() == 1 && books.contains(book2));
	// }

	@Test
	public void canDeleteBook() throws Exception {
		Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 1937, 420);
		mvc.perform(delete("/books/delete/1")
		.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBook.write(book).getJson()))
				
				.andExpect(status().isOk());
	}



	
@Test
	public void canUpdateBook() throws Exception {
		Book book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 1937, 420);
		mvc.perform(put("/books/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBook.write(book).getJson()))
				.andExpect(status().isOk());
	}
	// @Test
	// public void canUpdateBook() throws Exception {
	// 	Book book1 = new Book(1, "HTML for Babies", "Some Kid", 1999, 26);
	// 	Book updatedBook = new Book(1, "HTML for Babies - 2nd Edition", "Some Kid", 2021, 32);
	
	// 	Collection<Book> books = new ArrayList<Book>();
	// 	books.add(book1);
	
	// 	// Stub the repository to return the book with ID 1 when getById() is called
	// 	when(bookrepository.getAllBook()).thenReturn(books);
	
	// 	MockMvc mockMvc;
	// 	// MockMvc mockMvc;
	// 	// Call the controller method with the updated book
	// 	mockMvc.perform(put("/books/update/id")
	// 			.contentType(MediaType.APPLICATION_JSON)
	// 			.content(jsonBook.write(updatedBook).getJson()))
	// 			// .andExpect(status().isOk());
	
	// 	// Check that the book was updated
	// 	verify(bookrepository, times(1)).save(updatedBook);
	// 	assertFalse(books.contains(book1));
	// 	assertTrue(books.contains(updatedBook));
	// }
	







}
