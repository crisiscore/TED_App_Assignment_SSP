package xyz.traver.tedtalks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.traver.tedtalks.R;
import xyz.traver.tedtalks.delegates.TalkDelegate;
import xyz.traver.tedtalks.viewholders.TalkViewHolder;

public class TalksAdapter extends RecyclerView.Adapter {

    private TalkDelegate mTalkDelegate;

    public TalksAdapter(TalkDelegate talkDelegate) {
        mTalkDelegate = talkDelegate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_talk, parent, false);
        return new TalkViewHolder(view , mTalkDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
