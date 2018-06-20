package xyz.traver.tedtalks.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import xyz.traver.tedtalks.data.vos.TalkVO;

public class GetTalksResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("apiVersion")
    private String apiVersion;
    @SerializedName("page")
    private String page;
    @SerializedName("ted_talks")
    private List<TalkVO> talksList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<TalkVO> getTalksList() {
        if (talksList == null) return new ArrayList<>();
        return talksList;
    }

    public boolean isResponseOk() {
        return (code == 200 && talksList != null);
    }
}
