package xyz.traver.tedtalks.data.vos;

import java.util.List;

public class TalkVO {

    private int talkId;
    private String talkTitle;
    private SpeakerVO speaker;
    private String talkImageUrl;
    private int talkDurationInSecond;
    private String talkDescription;
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
