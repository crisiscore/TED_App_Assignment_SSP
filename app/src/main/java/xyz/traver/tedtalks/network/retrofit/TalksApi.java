package xyz.traver.tedtalks.network.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.traver.tedtalks.network.TalksDataAgent;
import xyz.traver.tedtalks.network.responses.GetTalksResponse;
import xyz.traver.tedtalks.utils.TedTalksConstants;

public interface TalksApi {

    @FormUrlEncoded
    @POST(TedTalksConstants.GET_TALKS)
    Call<GetTalksResponse> loadTalks(@Field(TedTalksConstants.ACCESS_TOKEN) String accessToken ,
                                     @Field(TedTalksConstants.PAGE) int page);

}
