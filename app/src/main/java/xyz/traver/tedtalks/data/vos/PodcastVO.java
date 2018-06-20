package xyz.traver.tedtalks.data.vos;

import java.util.List;

import xyz.traver.tedtalks.data.vos.SegmentVO;

public class PodcastVO {


    private int podcastId;
    private String podcastTitle;
    private String podcastImageUrl;
    private String podcastDescription;
    private List<SegmentVO> podcastSegments;

    public int getPodcastId() {
        return podcastId;
    }

    public String getPodcastTitle() {
        return podcastTitle;
    }

    public String getPodcastImageUrl() {
        return podcastImageUrl;
    }

    public String getPodcastDescription() {
        return podcastDescription;
    }

    public List<SegmentVO> getPodcastSegments() {
        return podcastSegments;
    }
}
