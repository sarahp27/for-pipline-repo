package afn.xloop.booktracker_cnsd23;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository repo;

    public BookController(BookRepository repo){
        this.repo = repo;
    }

    //localhost:8080/books
    @PostMapping("")
    public String createBook(@RequestBody Book book){
        this.repo.save(book);
        return book.getTitle() + " Book is added in book list " ;
        
    }

    @GetMapping("/all")
    public Collection<Book> getAllBooks(){
        return this.repo.getAllBook();
    }

    @DeleteMapping("/delete/{id}")
    public  String  deleteBook(@RequestBody Book book){
           this.repo.delete(book);
            return book.getTitle() + " Book is deleted from booklist " ;
    }
    @PutMapping("update/{id}")
    public String updateBook(@RequestBody Book book ){
         this.repo.Update(book);
     
         return book.getTitle() +" Book  is Updated in booklist "  ;
        }
}
