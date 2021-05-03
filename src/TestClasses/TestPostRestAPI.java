package TestClasses;
import static org.junit.Assert.*;
import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import Secret.SECRETS;
import Utils.JSONUtils;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;


public class TestPostRestAPI {
	

	@Test
	public void TestCreateClientDomain() throws Exception {
		
		String url = "https://provisioner.bkathon.com/register?client=ahmad-obeid";
		HttpURLConnection connection = RestClientHandler.connectServer(url, "POST",HTTPRequestsContentTypes.JSON);
        connection.setRequestProperty("Authorization", SECRETS.authHeaderValue);
		RestClientHandler.sendPost(connection,HTTPRequestsContentTypes.JSON);
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		assertTrue(jsonObject.get("client").equals("ahmad-obeid"));
		assertTrue(jsonObject.get("status").equals("pending"));
		assertTrue(connection.getResponseCode()==201);

	}

	@Test
	public void noAuthTestCreateClientDomain() throws Exception {
		
		String url = "https://provisioner.bkathon.com/register?client=test";
		HttpURLConnection connection = RestClientHandler.connectServer(url, "POST",HTTPRequestsContentTypes.JSON);
		assertTrue(connection.getResponseCode()==401);

	}
	@Ignore
	@Test
	public void testCreateClientDomainFormat() throws Exception {
		
		String url = "https://provisioner.bkathon.com/register?client=Test-Test   ";
		HttpURLConnection connection = RestClientHandler.connectServer(url, "POST",HTTPRequestsContentTypes.JSON);
        connection.setRequestProperty("Authorization", SECRETS.authHeaderValue);
		RestClientHandler.sendPost(connection,HTTPRequestsContentTypes.JSON);
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		assertTrue(jsonObject.get("client").equals("test-test"));
		assertTrue(connection.getResponseCode()==201);

	}
	
	@Test
	public void testCreateDuplicateClientDomain() throws Exception {
		
		String url = "https://provisioner.bkathon.com/register?client=autodns";
		HttpURLConnection connection = RestClientHandler.connectServer(url, "POST",HTTPRequestsContentTypes.JSON);
        connection.setRequestProperty("Authorization", SECRETS.authHeaderValue);
		assertTrue(connection.getResponseCode()==403);

	}
	
	


}
