package xyz.traver.tedtalks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.traver.tedtalks.R;
import xyz.traver.tedtalks.data.vos.TalkVO;
import xyz.traver.tedtalks.delegates.TalkDelegate;
import xyz.traver.tedtalks.viewholders.TalkViewHolder;

public class TalksAdapter extends RecyclerView.Adapter<TalkViewHolder> {

    private TalkDelegate mTalkDelegate;
    private List<TalkVO> mTalksList;

    public TalksAdapter(TalkDelegate talkDelegate) {
        mTalkDelegate = talkDelegate;
        mTalksList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TalkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_talk, parent, false);
        return new TalkViewHolder(view, mTalkDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull TalkViewHolder holder, int position) {
        holder.setTalkData(mTalksList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTalksList.size();
    }

    public void setTalksData(List<TalkVO> talks) {
        mTalksList = talks;
        notifyDataSetChanged();
    }
}
