import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.*;

public class APITest {

    @Test
    public void whenListingAllTodos_thenStatusCodeOk() {
        get("https://jsonplaceholder.typicode.com/todos/").then().statusCode(200); 
    }

    @Test
    public void whenGettingId4_thenCorrectResult() {
        String title = "et porro tempora";
        get("https://jsonplaceholder.typicode.com/todos/4").then().statusCode(200).assertThat()
            .body("title", equalTo(title)); 
    }

    @Test
    public void whenGettingAllTodos_thenId198and199isReturned() {
        get("https://jsonplaceholder.typicode.com/todos/").then().statusCode(200).assertThat()
            .body("id", hasItems(198, 199)); 
    }



}