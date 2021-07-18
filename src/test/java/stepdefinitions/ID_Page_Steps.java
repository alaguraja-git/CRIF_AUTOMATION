package stepdefinitions;

 

import java.io.BufferedReader;

 

import com.pages.ID_Page;

import com.qa.factory.DriverFactory;

 

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;

 

public class ID_Page_Steps {

             

              String line = "";

              String splitBy = "\\|";

              BufferedReader br;       

              String ID_filePath = "C:\\Users\\hp\\Desktop\\ala.txt";
              String CI_filePath = "C:\\Users\\hp\\Desktop\\ala.txt";
              String CN_filePath = "C:\\Users\\hp\\Desktop\\ala.txt";             

              public ID_Page idpage = new ID_Page(DriverFactory.getDriver());

             

              @Given("CRIF File is available")

              public void crif_id_file_is_available() {

                  System.out.println("File is available");

              }

 

             

              @When("I read the ID_TXT file by line by line")

              public void i_read_the_id_txt_file_by_line_by_line() {                 

                             br = idpage.readCSVfile(ID_filePath);

              }

             

              @When("I read the CI_TXT file by line by line")

              public void i_read_the_ci_txt_file_by_line_by_line() {                  

                             br = idpage.readCSVfile(CI_filePath);

              }

             

              @When("I read the CN_TXT file by line by line")

              public void i_read_the_cn_txt_file_by_line_by_line() {                

                             br = idpage.readCSVfile(CN_filePath);

              }

             

             

              @Then("I validate the {int} field is Not Null in the TXT file and {string}")

              public void i_validate_field_is_not_null_in_the_txt_file_and_testcasename(Integer columnNo, String TestCaseName) {

                             idpage.validation_Empty(br, columnNo, TestCaseName);

                             System.out.println("Not Null Validation is Completed.");

              }

 

              @Then("I validate the {int} field is date format validation in the TXT file and {string}")

              public void i_validate_field_is_date_format_validation_in_the_txt_file_and_testcasename(Integer columnNo, String TestCaseName) {

                             idpage.validation_Date(br, columnNo, TestCaseName);

                             System.out.println("Date Format Validation is Completed.");

              }          

             

}