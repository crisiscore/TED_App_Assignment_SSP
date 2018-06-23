package xyz.traver.tedtalks.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.traver.tedtalks.R;
import xyz.traver.tedtalks.adapters.WatchNextAdapter;
import xyz.traver.tedtalks.data.models.TalksModel;
import xyz.traver.tedtalks.data.vos.TalkVO;
import xyz.traver.tedtalks.utils.Helper;

public class TalkDetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_watch_next)
    RecyclerView watchNextRV;
    @BindView(R.id.iv_talk_detail_image)
    ImageView ivTalkDetailsImage;
    @BindView(R.id.tv_talk_details_speaker)
    TextView tvTalkDetailsName;
    @BindView(R.id.tv_talk_details_title)
    TextView tvTalkDetailsTitle;
    @BindView(R.id.tv_talk_details_duration)
    TextView tvTalkDetailsDuration;
    @BindView(R.id.tv_talk_details_description)
    TextView tvTalkDetailDescription;
    @BindView(R.id.iv_talk_detail_speaker_image)
    ImageView ivTalkDetailSpeakerImage;
    @BindView(R.id.tv_talk_speaker_name)
    TextView tvTalkSpeakerName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        watchNextRV.setLayoutManager(linearLayoutManager);
        watchNextRV.setAdapter(new WatchNextAdapter());

        int talkId = getIntent().getIntExtra("talk_id", -1);

        TalkVO talkVO = TalksModel.getObjInstance().getTalkById(String.valueOf(talkId));
        setupProfile(talkVO);
    }

    private void setupProfile(TalkVO talkVO) {

        Glide.with(getApplicationContext())
                .load(talkVO.getTalkImageUrl())
                .into(ivTalkDetailsImage);

        tvTalkDetailsName.setText(talkVO.getSpeaker().getSpeakerName());
        tvTalkDetailsTitle.setText(talkVO.getTalkTitle());
        tvTalkDetailsDuration.setText(Helper.getDurationInMin(talkVO.getTalkDurationInSecond()).concat("  |  "));
        tvTalkDetailDescription.setText(talkVO.getTalkDescription());

        Glide.with(getApplicationContext())
                .load(talkVO.getTalkImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(ivTalkDetailSpeakerImage);

        tvTalkSpeakerName.setText(talkVO.getSpeaker().getSpeakerName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
