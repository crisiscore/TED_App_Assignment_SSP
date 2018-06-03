package xyz.traver.tedtalks.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import xyz.traver.tedtalks.delegates.TalkDelegate;

public class TalkViewHolder extends RecyclerView.ViewHolder {

    private TalkDelegate mTalkDelegate;

    public TalkViewHolder(View itemView , TalkDelegate talkDelegate) {
        super(itemView);
        mTalkDelegate = talkDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTalkDelegate.onTapTalk();
            }
        });
    }
}
