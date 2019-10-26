package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC2_singleEMPLOYESS extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void getData() throws InterruptedException {
        
		logger.info("******Test has started*******");
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/4" + empID);

		Thread.sleep(5000);
	}

	@Test
	public void checkValidation() {

		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);

	}

	@Test

	public void statusCode() {

		int status = response.getStatusCode();
		Assert.assertEquals(status, 200);

	}

	@Test

	public void content() {

		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

	}
	@AfterClass
	public void tearDown() {
		
		logger.info("******Test has ended********");
	}

}
