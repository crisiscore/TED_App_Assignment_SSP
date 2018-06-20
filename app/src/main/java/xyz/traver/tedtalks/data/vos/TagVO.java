package xyz.traver.tedtalks.data.vos;

import com.google.gson.annotations.SerializedName;

public class TagVO {

    @SerializedName("tag_id")
    private int tagId;
    @SerializedName("tag")
    private String tagName;
    @SerializedName("description")
    private String tagDescription;

    public int getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }
}
