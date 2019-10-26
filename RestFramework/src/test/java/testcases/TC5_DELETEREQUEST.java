package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC5_DELETEREQUEST extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void deleteRequest() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// capture id
		String empID = jsonPathEvaluator.get("[0].id");

		response = httpRequest.request(Method.DELETE, "/delete/"+ empID); // Pass ID to delete record

		Thread.sleep(3000);
	}

	@Test
	void checkResposeBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);

	}
		
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
		
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

}
