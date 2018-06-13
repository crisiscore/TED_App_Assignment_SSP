package xyz.traver.tedtalks.data.models;

import xyz.traver.tedtalks.network.HttpUrlConnectionAgentImpl;
import xyz.traver.tedtalks.network.TalksDataAgent;

public class TalksModel {

    private static final String ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private static TalksModel objInstance;

    private TalksDataAgent talksDataAgent;

    private TalksModel() {
        talksDataAgent = HttpUrlConnectionAgentImpl.getUrlConnectionAgent();
    }

    public static TalksModel getObjInstance(){
        if (objInstance == null) objInstance = new TalksModel();
        return objInstance;
    }

    public void loadTalks(){
        talksDataAgent.loadTalks(1 , ACCESS_TOKEN );
    }
}
