package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import result.EventResult;
import service.EventService;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                Headers headers = exchange.getRequestHeaders();
                String url=exchange.getRequestURI().toString().substring(1);
                String[] params=url.split("/");
                String auth = headers.getFirst("Authorization");
                EventService eventService = new EventService();
                EventResult eventResult = eventService.getEvent(auth,params);
                Gson gson = new Gson();
                if (eventResult.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
                OutputStream output = exchange.getResponseBody();
                String res = gson.toJson(eventResult);
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
