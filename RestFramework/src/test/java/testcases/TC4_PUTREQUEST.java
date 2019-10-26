package testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RestUtils;

public class TC4_PUTREQUEST extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	String uid = RestUtils.iD();
	String userId = RestUtils.userid();
	String title = RestUtils.title();
	String body = RestUtils.body();

	@BeforeClass

	public void putData() throws InterruptedException {

		
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", uid);
		requestParams.put("userid", userId);
		requestParams.put("title", title);
		requestParams.put("body", body);
		
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.PUT, "/posts/" +id);
		
			Thread.sleep(5000);
		
		}
	
	@Test
	public void checkValidations() {
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body is:" + responseBody);
		Assert.assertEquals(responseBody.contains(id), true);
		Assert.assertEquals(responseBody.contains(body), true);
		Assert.assertEquals(responseBody.contains(title), true);
	}
	
	@Test
	public void checkStatus() {
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@Test
	public void serverStatus() {
		
		String checkServer = response.header("Server");
		logger.info("Server type is:" + checkServer);
				
	}
	
	@Test
	public void checkContent() {
	String contentType	= response.header("Content-Type");
	logger.info("Content type is:" + contentType);
	
	}
	

}