package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class UserStepDefs {

    //US01 - 1 - 2
    String actualUserCount;
    List<String> actualAllColumns;

    //US02
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedNumber;


    //US01 - 1
    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        //Make a conn with library
        //DB_Util.createConnection();
        System.out.println("--------------------------------------------------");
        System.out.println("--- CONNECTION WILL BE DONE WITH BEFORE HOOK -----");
        System.out.println("--------------------------------------------------");
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        String query = "select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualUserCount = " + actualUserCount);

    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {

        String query = "select count(distinct id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();

        System.out.println("expectedUserCount = " + expectedUserCount);

        Assert.assertEquals(expectedUserCount, actualUserCount);

        //Close Conn
        //DB_Util.destroy();
        System.out.println("--------------------------------------------------");
        System.out.println("--- CONNECTION WILL BE CLOSED WITH AFTER HOOK -----");
        System.out.println("--------------------------------------------------");
    }


    //US01 - 2
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        String query = "select * from users";
        DB_Util.runQuery(query);

        actualAllColumns = DB_Util.getAllColumnNamesAsList();
        System.out.println("actualAllColumns = " + actualAllColumns);

    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedAllColumns) {

        System.out.println("expectedAllColumns = " + expectedAllColumns);
        Assert.assertEquals(expectedAllColumns, actualAllColumns);

    }


    //US02
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
