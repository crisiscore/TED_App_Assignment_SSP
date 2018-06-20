package xyz.traver.tedtalks.events;

import java.util.List;

import xyz.traver.tedtalks.data.vos.TalkVO;

public class SuccessGetTalksEvent {

    private List<TalkVO> talks;

    public SuccessGetTalksEvent(List<TalkVO> talks) {
        this.talks = talks;
    }

    public List<TalkVO> getTalks(){
        return talks;
    }
}
