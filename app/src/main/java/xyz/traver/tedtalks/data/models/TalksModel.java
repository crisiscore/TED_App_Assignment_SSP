package xyz.traver.tedtalks.data.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import xyz.traver.tedtalks.data.vos.TalkVO;
import xyz.traver.tedtalks.events.SuccessGetTalksEvent;
import xyz.traver.tedtalks.network.HttpUrlConnectionAgentImpl;
import xyz.traver.tedtalks.network.OkHttpConnectionAgentImpl;
import xyz.traver.tedtalks.network.TalksDataAgent;
import xyz.traver.tedtalks.network.retrofit.RetrofitDataAgent;

public class TalksModel {

    private static final String ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private static TalksModel objInstance;

    private TalksDataAgent talksDataAgent;

    private HashMap<String, TalkVO> talksHashMap;

    private TalksModel() {

       // talksDataAgent = HttpUrlConnectionAgentImpl.getUrlConnectionAgent();

      //  talksDataAgent = OkHttpConnectionAgentImpl.getObjInstance();

        talksDataAgent = RetrofitDataAgent.getObjInstance();

        talksHashMap = new HashMap<>();

        EventBus.getDefault().register(this);

    }

    public static TalksModel getObjInstance() {
        if (objInstance == null) objInstance = new TalksModel();
        return objInstance;
    }

    public void loadTalks() {
        talksDataAgent.loadTalks(1, ACCESS_TOKEN);
    }

    public TalkVO getTalkById(String talkId){
        return talksHashMap.get(talkId);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoadedTalks(SuccessGetTalksEvent event) {
        for (TalkVO talkVO : event.getTalks()) {
            talksHashMap.put(String.valueOf(talkVO.getTalkId()), talkVO);
        }
    }
}
