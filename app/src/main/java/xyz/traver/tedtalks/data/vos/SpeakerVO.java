package xyz.traver.tedtalks.data.vos;

import com.google.gson.annotations.SerializedName;

public class SpeakerVO {

    @SerializedName("speaker_id")
    private int speakerId;
    @SerializedName("name")
    private String speakerName;

    public int getSpeakerId() {
        return speakerId;
    }

    public String getSpeakerName() {
        return speakerName;
    }
}
