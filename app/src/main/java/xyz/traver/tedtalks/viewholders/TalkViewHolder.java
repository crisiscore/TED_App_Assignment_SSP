package xyz.traver.tedtalks.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.traver.tedtalks.R;
import xyz.traver.tedtalks.data.vos.TalkVO;
import xyz.traver.tedtalks.delegates.TalkDelegate;
import xyz.traver.tedtalks.utils.Helper;

public class TalkViewHolder extends RecyclerView.ViewHolder {

    private TalkDelegate mTalkDelegate;

    @BindView(R.id.iv_talk_photo)
    ImageView talkImage;
    @BindView(R.id.tv_talk_title)
    TextView talkTitle;
    @BindView(R.id.tv_talk_duration)
    TextView talkDuration;
    @BindView(R.id.tv_talk_speaker)
    TextView talkSpeaker;
    private TalkVO mTalkVO;

    public TalkViewHolder(View itemView, TalkDelegate talkDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mTalkDelegate = talkDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTalkDelegate.onTapTalk(mTalkVO);
            }
        });
    }

    public void setTalkData(TalkVO talkVO) {
        mTalkVO = talkVO;
        Glide.with(talkImage.getContext()).load(talkVO.getTalkImageUrl()).into(talkImage);
        talkTitle.setText(talkVO.getTalkTitle());
        talkDuration.setText(Helper.getDurationInMin(talkVO.getTalkDurationInSecond()));
        talkSpeaker.setText(talkVO.getSpeaker().getSpeakerName());
    }
}
