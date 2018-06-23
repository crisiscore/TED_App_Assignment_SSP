package xyz.traver.tedtalks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.traver.tedtalks.R;
import xyz.traver.tedtalks.adapters.TalksAdapter;
import xyz.traver.tedtalks.data.models.TalksModel;
import xyz.traver.tedtalks.data.vos.TalkVO;
import xyz.traver.tedtalks.delegates.TalkDelegate;
import xyz.traver.tedtalks.events.SuccessGetTalksEvent;
import xyz.traver.tedtalks.network.responses.GetTalksResponse;

public class MainActivity extends BaseActivity implements TalkDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rv_talks)
    RecyclerView recyclerView;

    private TalksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new TalksAdapter(this);
        recyclerView.setAdapter(adapter);

        TalksModel.getObjInstance().loadTalks();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Menu", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapTalk(TalkVO talkVO) {
        Intent intent = new Intent(getApplicationContext(), TalkDetailsActivity.class);
        intent.putExtra("talk_id" , talkVO.getTalkId());
        startActivity(intent);
    }

    @Override
    public void onTapMenu(TalkVO talkVO) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadedTalks(SuccessGetTalksEvent event) {
        adapter.setTalksData(event.getTalks());
    }


}
