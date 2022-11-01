package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import result.FillResult;
import service.FillService;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    boolean success = false;
    try {
      if (exchange.getRequestMethod().toLowerCase().equals("post")) {
        String url=exchange.getRequestURI().toString().substring(1);
        String[] params=url.split("/");
        FillService fillService = new FillService();
        FillResult fillResult = fillService.fill(params);
        Gson gson = new Gson();
        if (fillResult.isSuccess()) {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        }
        else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        OutputStream output = exchange.getResponseBody();
        String res = gson.toJson(fillResult);
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
  private void writeString(String res, OutputStream output) throws IOException {
    OutputStreamWriter write = new OutputStreamWriter(output);
    write.write(res);
    write.flush();
  }

}
