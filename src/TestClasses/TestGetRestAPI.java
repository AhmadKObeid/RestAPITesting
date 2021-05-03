package TestClasses;
import static org.junit.Assert.*;
import java.net.HttpURLConnection;
import org.json.simple.JSONObject;
import org.junit.Test;
import Utils.JSONUtils;
import Secret.SECRETS;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;


public class TestGetRestAPI {
	
	@Test
	public void noAuthTestGetClient() throws Exception {
		String url = "https://provisioner.bkathon.com/check_status/autodns";
		HttpURLConnection connection = RestClientHandler.connectServer(url, "GET",HTTPRequestsContentTypes.JSON);
		assertTrue(connection.getResponseCode()==401);
	}
	
	@Test
	public void testGetClientStatus() throws Exception {
		String url = "https://provisioner.bkathon.com/check_status/autodns";
		HttpURLConnection connection = RestClientHandler.connectServer(url, "GET",HTTPRequestsContentTypes.JSON);
        connection.setRequestProperty("Authorization", SECRETS.authHeaderValue);
		RestClientHandler.sendGet(connection," ", HTTPRequestsContentTypes.JSON);
		String response = RestClientHandler.readResponse(connection);
		JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		assertTrue(jsonObject.get("status").equals("Complete"));
		assertTrue(connection.getResponseCode()==200);
	}

	@Test
	public void testGetClientStatusNotExist() throws Exception {
		String url = "https://provisioner.bkathon.com/check_status/auto";
		HttpURLConnection connection = RestClientHandler.connectServer(url, "GET",HTTPRequestsContentTypes.JSON);
        connection.setRequestProperty("Authorization", SECRETS.authHeaderValue);
		assertTrue(connection.getResponseCode()==404);
	}


}
