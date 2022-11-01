package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.LoadRequest;
import result.LoadResult;
import service.LoadService;

import java.io.*;
import java.net.HttpURLConnection;



public class LoadHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {

            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                // Check to see if an "Authorization" header is present

                // Extract the auth token from the "Authorization" header

                // Extract the JSON string from the HTTP request body

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();
                String req = readString(reqBody);
                Gson gson = new Gson();
                LoadRequest request = (LoadRequest)gson.fromJson(req, LoadRequest.class);
                LoadService service = new LoadService();
                LoadResult result = service.load(request);
                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                }
                OutputStream resBody = exchange.getResponseBody();
                String res = gson.toJson(result);
                writeString(res,resBody);
                System.out.println(gson.toJson(result));
                exchange.getResponseBody().close();


            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
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
    private void writeString(String res, OutputStream output) throws IOException {
        OutputStreamWriter write = new OutputStreamWriter(output);
        write.write(res);
        write.flush();
    }
}