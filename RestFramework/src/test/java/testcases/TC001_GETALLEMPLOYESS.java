package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC001_GETALLEMPLOYESS extends TestBase {

	@BeforeClass
	public void getEmplyess() throws InterruptedException {

		RestAssured.baseURI = ("http://dummy.restapiexample.com/api/v1");
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

		Thread.sleep(3000);

	}

	@Test

	public void responseBody() {

		String responseBody = response.getBody().asString();
		logger.info("Response body is:" + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	public void statucCode() {

		int statusCode = response.getStatusCode();
		logger.info("Status code is:" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test

	public void responseTime() {

		long responsetime = response.getTime();
		logger.info("response time is:" + responsetime);

		if (responsetime > 2000) {
			logger.info("warning: response time is more than 2000");

		}

		Assert.assertTrue(responsetime < 10000);

	}

	@Test
	public void checkStatusLine() {

		String statusLine = response.getStatusLine();
		logger.info("Status line is:" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

}
