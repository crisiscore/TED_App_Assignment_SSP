package xyz.traver.tedtalks.data.vos;

import java.util.List;

public class PlayListVO {

    private int playlistId;
    private String playlistTitle;
    private String playlistImageUrl;
    private int playlistTotalTalks;
    private String playlistDescription;
    private List<TalkVO> playlistTalks;

    public int getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public String getPlaylistImageUrl() {
        return playlistImageUrl;
    }

    public int getPlaylistTotalTalks() {
        return playlistTotalTalks;
    }

    public String getPlaylistDescription() {
        return playlistDescription;
    }

    public List<TalkVO> getPlaylistTalks() {
        return playlistTalks;
    }
}
