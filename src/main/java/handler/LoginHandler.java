package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.LoginRequest;
import result.LoginResult;
import service.LoginService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;


/*
	The ClaimRouteHandler is the HTTP handler that processes
	incoming HTTP requests that contain the "/routes/claim" URL path.

	Notice that ClaimRouteHandler implements the HttpHandler interface,
	which is define by Java.  This interface contains only one method
	named "handle".  When the HttpServer object (declared in the Server class)
	receives a request containing the "/routes/claim" URL path, it calls
	ClaimRouteHandler.handle() which actually processes the request.
*/
public class LoginHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {



    try {
      // Determine the HTTP request type (GET, POST, etc.).
      // Only allow POST requests for this operation.
      // This operation requires a POST request, because the
      // client is "posting" information to the server for processing.
      if (exchange.getRequestMethod().toLowerCase().equals("post")) {

        // Get the HTTP request headers
        Headers reqHeaders = exchange.getRequestHeaders();
        // Check to see if an "Authorization" header is present

          // Extract the auth token from the "Authorization" header

            // Extract the JSON string from the HTTP request body

            // Get the request body input stream
            InputStream reqBody = exchange.getRequestBody();

            // Read JSON string from the input stream
            String reqData = readString(reqBody);

            // Display/log the request JSON data
            System.out.println(reqData);

            // TODO: Claim a route based on the request data

            Gson gson = new Gson();
            LoginRequest request = (LoginRequest)gson.fromJson(reqData, LoginRequest.class);

            LoginService service = new LoginService();
            LoginResult result = service.login(request);
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            OutputStream resBody = exchange.getResponseBody();
//            gson.toJson(result, resBody);
            System.out.println(gson.toJson(result));
            resBody.close();


            // Start sending the HTTP response to the client, starting with
            // the status code and any defined headers.
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            // We are not sending a response body, so close the response body
            // output stream, indicating that the response is complete.
            exchange.getResponseBody().close();


      }
      else {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
        exchange.getResponseBody().close();
      }
    }
    catch (IOException e) {
      // Some kind of internal error has occurred inside the server (not the
      // client's fault), so we return an "internal server error" status code
      // to the client.
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);

      // We are not sending a response body, so close the response body
      // output stream, indicating that the response is complete.
      exchange.getResponseBody().close();

      // Display/log the stack trace
      e.printStackTrace();
    }
  }

  /*
      The readString method shows how to read a String from an InputStream.
  */
  private String readString(InputStream is) throws IOException {
    StringBuilder sb = new StringBuilder();
    InputStreamReader sr = new InputStreamReader(is);
    char[] buf = new char[1024];
    int len;
    while ((len = sr.read(buf)) > 0) {
      sb.append(buf, 0, len);
    }
    return sb.toString();
  }
}