package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05_mostBorrowedBooksGenreStepDefs {

    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {

        String queryOfMostBorrowedGenreBook = "select bc.name, count(*)\n" +
                "from book_borrow bb\n" +
                "         inner join books b on bb.book_id = b.id\n" +
                "         inner join book_categories bc on b.book_category_id = bc.id\n" +
                "group by name\n" +
                "order by 2 desc;";

        DB_Util.runQuery(queryOfMostBorrowedGenreBook);

    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedFromUser) {

        String actualFromDB = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualFromDB = " + actualFromDB);
        System.out.println("expectedFromUser = " + expectedFromUser);

        Assert.assertEquals(expectedFromUser, actualFromDB);

    }
}
