package runner;

import config.CredentialsProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;

public class ProjectSteps {
    @Given("an user API token provided by Todo.ly")
    public void anUserAPITokenProvidedByTodoLy() throws FileNotFoundException {
        CredentialsProvider cp = new CredentialsProvider();
        System.out.println(cp.getUser());
    }

    @When("a {} request is sent to {string} with body")
    public void aPOSTRequestIsSentToWithBody(String arg0) {
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int arg0) {
    }

    @And("the field {string} should be {string}")
    public void theFieldShouldBe(String arg0, String arg1) {
    }

    @And("the field {string} is saved in the variable {string}")
    public void theFieldIsSavedInTheVariable(String arg0, String arg1) {
    }
}
