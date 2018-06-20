package xyz.traver.tedtalks.network;

import android.os.AsyncTask;
import android.widget.TabHost;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import xyz.traver.tedtalks.events.ErrorApiEvent;
import xyz.traver.tedtalks.events.SuccessGetTalksEvent;
import xyz.traver.tedtalks.network.responses.GetTalksResponse;
import xyz.traver.tedtalks.utils.TedTalksConstants;

public class OkHttpConnectionAgentImpl implements TalksDataAgent {

    private static OkHttpConnectionAgentImpl objInstance;

    private OkHttpClient okHttpClient;

    private OkHttpConnectionAgentImpl() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15  , TimeUnit.SECONDS)
                .writeTimeout(15 , TimeUnit.SECONDS)
                .readTimeout(60 , TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpConnectionAgentImpl getObjInstance() {
        if (objInstance == null) objInstance = new OkHttpConnectionAgentImpl();
        return objInstance;
    }

    @Override
    public void loadTalks(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                RequestBody requestBody = new FormBody.Builder()
                        .add(TedTalksConstants.ACCESS_TOKEN , accessToken)
                        .add(TedTalksConstants.PAGE , String.valueOf(page))
                        .build();

                Request request = new Request.Builder()
                        .url(TedTalksConstants.BASE_URL + TedTalksConstants.GET_TALKS)
                        .post(requestBody)
                        .build();

                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()){
                        String responseString = response.body().string();
                        return responseString;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                GetTalksResponse talksResponse = gson.fromJson(s , GetTalksResponse.class);
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
}
