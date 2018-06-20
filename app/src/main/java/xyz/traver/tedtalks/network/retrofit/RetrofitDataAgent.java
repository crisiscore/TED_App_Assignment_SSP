package xyz.traver.tedtalks.network.retrofit;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.traver.tedtalks.data.models.TalksModel;
import xyz.traver.tedtalks.events.ErrorApiEvent;
import xyz.traver.tedtalks.events.SuccessGetTalksEvent;
import xyz.traver.tedtalks.network.TalksDataAgent;
import xyz.traver.tedtalks.network.responses.GetTalksResponse;
import xyz.traver.tedtalks.utils.TedTalksConstants;

public class RetrofitDataAgent implements TalksDataAgent {

    private static RetrofitDataAgent objInstance;

    private TalksApi mApi;

    private RetrofitDataAgent() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TedTalksConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mApi = retrofit.create(TalksApi.class);

    }

    public static RetrofitDataAgent getObjInstance() {
        if (objInstance == null) objInstance = new RetrofitDataAgent();
        return objInstance;
    }


    @Override
    public void loadTalks(int page, String accessToken) {
        Call<GetTalksResponse> loadTalks = mApi.loadTalks(accessToken, page);
        loadTalks.enqueue(new Callback<GetTalksResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetTalksResponse> call, @NonNull Response<GetTalksResponse> response) {
                GetTalksResponse talksResponse = response.body();
                if (talksResponse != null && talksResponse.isResponseOk()) {
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getTalksList());
                    EventBus.getDefault().post(event);
                } else {
                    if (talksResponse == null) {
                        ErrorApiEvent event = new ErrorApiEvent("Empty response in network call");
                        EventBus.getDefault().post(event);
                    } else {
                        ErrorApiEvent event = new ErrorApiEvent(response.message());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetTalksResponse> call, @NonNull Throwable t) {

            }
        });

    }
}
