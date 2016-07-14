package zz.reader.service.remoteServer;

import zz.reader.constant.ServerConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zz on 2016-07-05.
 */
public interface CommonServer {

    default String requestServer(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setConnectTimeout(500);
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", ServerConstant.USER_AGENT);
        urlConnection.connect();
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
