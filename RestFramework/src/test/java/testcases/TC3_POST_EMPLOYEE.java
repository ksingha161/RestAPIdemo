package testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RestUtils;

public class TC3_POST_EMPLOYEE extends TestBase {

	RequestSpecification httpRequest;
	Response response;
	
	String userId = RestUtils.userid();
	String id = RestUtils.iD();
	String title = RestUtils.title();
	String body = RestUtils.body();
	
	@BeforeClass
	public void postEmployee() throws InterruptedException {
		
		logger.info("********Test Started*******");
		
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		httpRequest = RestAssured.given();
		
		
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("userId", userId);
		requestParams.put("id", id);
		requestParams.put("title", title);
		requestParams.put("body", body);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.POST, "/posts");
	
		Thread.sleep(5000);
	}
	
	@Test
	public void checkValidations() {
		
		String responseBdy = response.getBody().asString();
		logger.info("Response body is:" +responseBdy );
		Assert.assertEquals(responseBdy.contains(userId), true);
		Assert.assertEquals(responseBdy.contains(id), true);
		Assert.assertEquals(responseBdy.contains(title), true);
		Assert.assertEquals(responseBdy.contains(body), true);
		
		
	}
	
	@Test
	public void checkStatus () {
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
		
		}
	@Test
	public void checkContent() {
		
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@AfterClass
	public void tearDown() {
		
		logger.info("******Test has ended*****");
	}
	
}
