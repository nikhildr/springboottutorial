package com.skyfall.tutorial.cucumberglue;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class DeptStepDefination {

    @LocalServerPort
    String port;

    ResponseEntity lastResponse;

    String url = "http://localhost:";

    @When("the client calls the endpoint {string}")
    public void theClientCallsTheEndpoint(String endpoint) {
        url = url + port +endpoint;
        System.out.println("calling end point: "+url);
        lastResponse = new RestTemplate().getForEntity(url, List.class);
    }

    @Then("response status code is {int}")
    public void responseStatusCodeIs(int status) {
        assertThat("status code is " + status, lastResponse.getStatusCodeValue() == status);
    }

    @And("return the list of department more than {int}")
    public void returnTheListOfDepartmentMoreThan(int size) {
        List<Department> departments = (List<Department>) lastResponse.getBody();
        assertThat("departments size is:" + departments.size(), departments.size() > size);
    }


    @When("the client calls the endpoint to get department {string}")
    public void theClientCallsTheEndpointToGetDepartment(String endpoint) {
        url = url + port +endpoint;
        lastResponse = new RestTemplate().getForEntity(url, Department.class);
    }

    @And("return the department id {int}")
    public void returnTheDepartmentId(int id) {
        Department department = (Department) lastResponse.getBody();
        assertThat("Got department by id :" + department.getDepartmentId(), department.getDepartmentId()== id);


    }
}
