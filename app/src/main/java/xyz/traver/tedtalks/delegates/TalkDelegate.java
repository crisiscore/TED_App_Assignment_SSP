package xyz.traver.tedtalks.delegates;

import xyz.traver.tedtalks.data.vos.TalkVO;

public interface TalkDelegate {
    void onTapTalk(TalkVO talkVO);
    void onTapMenu(TalkVO talkVO);
}
