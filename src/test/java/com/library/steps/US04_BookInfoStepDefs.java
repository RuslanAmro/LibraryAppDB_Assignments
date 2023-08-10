package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class US04_BookInfoStepDefs {

    BookPage bookPage = new BookPage();
    String globalBookName;

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {

        globalBookName = bookName;
        bookPage.bookSearch(bookName);
    }

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {

        bookPage.editBook(globalBookName).click();
    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        String query = "select * from books where name = 'Clean Code';";
        DB_Util.runQuery(query);

        Map<String, String> mapDataFromDB = DB_Util.getRowMap(1);
        System.out.println("mapDataFromDB = " + mapDataFromDB);

        //get the name from DB
        String name_db = mapDataFromDB.get("name");
        System.out.println("name_db = " + name_db);

        //get name from ui
        String name_ui = bookPage.getBookInfo("Book Name");
        System.out.println("name_ui = " + name_ui);
        Assert.assertEquals(name_ui, name_db);


        //get year from db
        String year_db = mapDataFromDB.get("year");
        System.out.println("year_db = " + year_db);

        //get year from ui
        String year_ui = bookPage.getBookInfo("Year");
        System.out.println("year_ui = " + year_ui);
        Assert.assertEquals(year_ui, year_db);

    }
}
