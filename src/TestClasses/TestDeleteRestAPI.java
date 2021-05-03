package TestClasses;
import static org.junit.Assert.*;
import java.net.HttpURLConnection;
import org.junit.Test;
import Secret.SECRETS;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;

public class TestDeleteRestAPI {
	
	@Test
	public void noAuthtTestDeleteClientDomain() throws Exception
	{
		String url = "https://provisioner.bkathon.com/destroy/ahmad-obeid";
		HttpURLConnection connection= RestClientHandler.connectServer(url, "DELETE" , HTTPRequestsContentTypes.JSON);
		assertTrue(connection.getResponseCode()==401);

	}
	
	@Test
	public void testDeleteClientDomain() throws Exception
	{
		String url = "https://provisioner.bkathon.com/destroy/ahmad-obeid";
		HttpURLConnection connection= RestClientHandler.connectServer(url, "DELETE" , HTTPRequestsContentTypes.JSON);
        connection.setRequestProperty("Authorization",SECRETS.authHeaderValue);
		RestClientHandler.sendDelete(connection, " ", HTTPRequestsContentTypes.JSON);
		assertTrue(connection.getResponseCode()==204);

	}
	
	@Test
	public void testGetClientStatusNotExist() throws Exception {
		String url = "https://provisioner.bkathon.com/destroy/ahmad";
		HttpURLConnection connection = RestClientHandler.connectServer(url, "DELETE",HTTPRequestsContentTypes.JSON);
        connection.setRequestProperty("Authorization", SECRETS.authHeaderValue);
		assertTrue(connection.getResponseCode()==404);
	}

}
