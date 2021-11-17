package client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MyOKHttpClient {

    public static OkHttpClient client = new OkHttpClient();

    public static void main(String args[]) {


        String url = "http://localhost:8088/api/hello";
        try {
            String text = GetUrl(url);
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client = null;

    }


    static String GetUrl(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();


        }
    }

}
