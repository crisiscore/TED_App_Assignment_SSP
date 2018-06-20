package xyz.traver.tedtalks.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TalkVO {

    @SerializedName("talk_id")
    private int talkId;
    @SerializedName("title")
    private String talkTitle;
    @SerializedName("speaker")
    private SpeakerVO speaker;
    @SerializedName("imageUrl")
    private String talkImageUrl;
    @SerializedName("durationInSec")
    private int talkDurationInSecond;
    @SerializedName("description")
    private String talkDescription;
    @SerializedName("tag")
    private List<TagVO> talkTags;

    public int getTalkId() {
        return talkId;
    }

    public String getTalkTitle() {
        return talkTitle;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public String getTalkImageUrl() {
        return talkImageUrl;
    }

    public int getTalkDurationInSecond() {
        return talkDurationInSecond;
    }

    public String getTalkDescription() {
        return talkDescription;
    }

    public List<TagVO> getTalkTags() {
        return talkTags;
    }
}
