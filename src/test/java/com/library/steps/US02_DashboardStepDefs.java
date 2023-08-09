package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02_DashboardStepDefs {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedNumber;


    @Given("the {string} on the home page")
    public void the_on_the_home_page(String librarian) {

        loginPage.login(librarian);

    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        actualBorrowedNumber = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedNumber = " + actualBorrowedNumber);
    }


    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        String query = "select count(*) from book_borrow where is_returned = 0";
        DB_Util.runQuery(query);

        String expectedBorrowedNumber = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedNumber = " + expectedBorrowedNumber);

        Assert.assertEquals(expectedBorrowedNumber, actualBorrowedNumber);

    }
}
