package handler;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import request.RegisterRequest;
import result.RegisterResult;
import result.RegisterResult;
import service.FillService;
import service.RegisterService;
import com.google.gson.Gson;


/*
	The ClaimRouteHandler is the HTTP handler that processes
	incoming HTTP requests that contain the "/routes/claim" URL path.

	Notice that ClaimRouteHandler implements the HttpHandler interface,
	which is define by Java.  This interface contains only one method
	named "handle".  When the HttpServer object (declared in the Server class)
	receives a request containing the "/routes/claim" URL path, it calls
	ClaimRouteHandler.handle() which actually processes the request.
*/
public class RegisterHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    boolean success = false;
    try {
      if (exchange.getRequestMethod().toLowerCase().equals("post")) {
        String url=exchange.getRequestURI().toString().substring(1);
        Gson gson = new Gson();
        String data = readString(exchange.getRequestBody());
        RegisterRequest params = gson.fromJson(data,RegisterRequest.class);
        RegisterService registerService = new RegisterService();
        RegisterResult registerResult = registerService.register(params);
        success = registerResult.isSuccess();
        if (success) {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        }
        else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        OutputStream output = exchange.getResponseBody();
        String res = gson.toJson(registerResult);
        writeString(res,output);
        exchange.getResponseBody().close();
      }
    }
    catch(IOException e) {
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
      exchange.getResponseBody().close();
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
  private void writeString(String str, OutputStream os) throws IOException {
    OutputStreamWriter sw = new OutputStreamWriter(os);
    sw.write(str);
    sw.flush();
  }
}