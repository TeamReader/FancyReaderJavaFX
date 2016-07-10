package zz.reader.service;

import zz.reader.constant.ServerConstant;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zz on 2016-07-05.
 */
public interface CommonServer {

    default String requestServer(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", ServerConstant.USER_AGENT);
        urlConnection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
        dataOutputStream.flush();
        dataOutputStream.close();
        return readResponseBody(urlConnection.getInputStream());

    }
    default String readResponseBody(InputStream inputStream) throws IOException {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(inputStream));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
