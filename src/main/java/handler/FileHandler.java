package handler;

import java.io.*;
import java.net.*;
import java.io.File;
import java.nio.file.Files;

import com.sun.net.httpserver.*;

/*
	The ListGamesHandler is the HTTP handler that processes
	incoming HTTP requests that contain the "/games/list" URL path.
	
	Notice that ListGamesHandler implements the HttpHandler interface,
	which is define by Java.  This interface contains only one method
	named "handle".  When the HttpServer object (declared in the Server class)
	receives a request containing the "/games/list" URL path, it calls 
	ListGamesHandler.handle() which actually processes the request.
*/
public class FileHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {


    try {
      // Determine the HTTP request type (GET, POST, etc.).
      // Only allow GET requests for this operation.
      // This operation requires a GET request, because the
      // client is "getting" information from the server, and
      // the operation is "read only" (i.e., does not modify the
      // state of the server).
      if (exchange.getRequestMethod().toLowerCase().equals("get")) {
        String uri = exchange.getRequestURI().toString();
        if (uri == null || uri.equals("/")) {
          uri = "/index.html";
        }
        String filePath = "web" + uri;
        File file = new File(filePath);
        if (file.exists()) {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
          OutputStream out = exchange.getResponseBody();
          Files.copy(file.toPath(),out);
          out.close();
        }
        else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
          exchange.getResponseBody().close();
        }
      }

    }
    catch (IOException e) {
      // Some kind of internal error has occurred inside the server (not the
      // client's fault), so we return an "internal server error" status code
      // to the client.
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      // Since the server is unable to complete the request, the client will
      // not receive the list of games, so we close the response body output stream,
      // indicating that the response is complete.
      exchange.getResponseBody().close();

      // Display/log the stack trace
      e.printStackTrace();
    }
  }

  /*
      The writeString method shows how to write a String to an OutputStream.
  */
  private void writeString(String str, OutputStream os) throws IOException {
    OutputStreamWriter sw = new OutputStreamWriter(os);
    sw.write(str);
    sw.flush();
  }
}