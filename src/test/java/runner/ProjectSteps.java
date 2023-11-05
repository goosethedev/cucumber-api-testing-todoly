package runner;

import config.CredentialsProvider;
import factory.request.FactoryRequest;
import factory.request.RequestInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

public class ProjectSteps {

    CredentialsProvider cp = new CredentialsProvider();
    Response response;
    RequestInfo requestInfo = new RequestInfo();
    Map<String,String> variables = new HashMap<>();

    public ProjectSteps() throws FileNotFoundException {
    }

    @Given("an user API token provided by Todo.ly")
    public void setTokenHeader() {
        String tokenRequestURL = cp.getHost() + "/api/authentication/token.json";

        String credentials = Base64.getEncoder()
                .encodeToString((cp.getUser() + ":" + cp.getPassword()).getBytes());
        requestInfo.setUrl(tokenRequestURL)
                .setHeader("Authorization", "Basic " + credentials);

        // Request token
        response = FactoryRequest.make("get").send(requestInfo);
        String token = response.then().extract().path("TokenString");
        requestInfo = new RequestInfo();
        requestInfo.setHeader("Token", token);
    }

    @When("a {} request is sent to {string} with body")
    public void sendRequest(String method, String url, String body) {
        String requestURL = cp.getHost() + this.replaceValues(url);
        requestInfo.setUrl(requestURL).setBody(body);
        response = FactoryRequest.make(method).send(requestInfo);
    }

    @Then("the response code should be {int}")
    public void checkResponseCode(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @And("the field {string} should be {string}")
    public void checkFieldValue(String field, String expectedValue) {
        response.then().body(field, equalTo(expectedValue));
    }

    @And("the field {string} is saved in the variable {string}")
    public void saveVariable(String field, String varName) {
        variables.put(varName, response.then().extract().path(field) + "");
    }

    private String replaceValues(String value){
        for (String key: variables.keySet() ) {
            value = value.replace(key,variables.get(key));
        }
        return value;
    }
}
