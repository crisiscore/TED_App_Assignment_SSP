package xyz.traver.tedtalks.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import xyz.traver.tedtalks.events.ErrorApiEvent;
import xyz.traver.tedtalks.events.SuccessGetTalksEvent;
import xyz.traver.tedtalks.network.responses.GetTalksResponse;
import xyz.traver.tedtalks.utils.TedTalksConstants;

public class HttpUrlConnectionAgentImpl implements TalksDataAgent {

    private static HttpUrlConnectionAgentImpl urlConnectionAgent;

    private HttpUrlConnectionAgentImpl() {

    }

    public static HttpUrlConnectionAgentImpl getUrlConnectionAgent(){
        if (urlConnectionAgent == null) urlConnectionAgent = new HttpUrlConnectionAgentImpl();
        return urlConnectionAgent;
    }


    @Override
    public void loadTalks(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                URL url;
                BufferedReader reader = null;
                StringBuilder builder;
                try {
                    url = new URL(TedTalksConstants.BASE_URL + TedTalksConstants.GET_TALKS);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(15 * 1000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    List<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair(TedTalksConstants.ACCESS_TOKEN , accessToken));
                    params.add(new BasicNameValuePair(TedTalksConstants.PAGE , String.valueOf(page)));
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();
                    connection.connect();
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    builder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null){
                        builder.append(line + "\n");
                    }

                    String responseString = builder.toString();

                    return responseString;

                }catch (Exception e){
                    Log.e("" , e.getMessage());
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Gson gson = new Gson();
                GetTalksResponse talksResponse = gson.fromJson(result , GetTalksResponse.class);

                if (talksResponse.isResponseOk()){
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getTalksList());
                    EventBus.getDefault().post(event);
                }else {
                    ErrorApiEvent event = new ErrorApiEvent(talksResponse.getMessage());
                    EventBus.getDefault().post(event);
                }

            }
        }.execute();
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
