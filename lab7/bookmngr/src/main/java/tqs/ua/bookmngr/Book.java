package tqs.ua.bookmngr;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String title;
 
    public Book() {
    }

	public Book(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

    public void setTitle(String title) {
		this.title = title;
	}

}