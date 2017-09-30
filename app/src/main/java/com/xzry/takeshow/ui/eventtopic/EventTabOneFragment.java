package com.xzry.takeshow.ui.eventtopic;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sxjs.common.base.BaseFragment;
import com.xzry.takeshow.R;
import com.xzry.takeshow.dagger.component.DaggerEventTopicComponent;
import com.xzry.takeshow.dagger.module.EventTopicModule;
import com.xzry.takeshow.entity.homeEntity.EventTopicComp;
import com.xzry.takeshow.entity.homeEntity.EventTopicEntity;
import com.xzry.takeshow.ui.eventtopic.adapter.EventTopicAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class EventTabOneFragment extends BaseFragment implements EventtopicContract.View{
    @BindView(R.id.tv_big_title)
    TextView tv_bigTitle;
    @BindView(R.id.event_recyclerview)
    RecyclerView recyclerView;
    @Inject
    EventTopicPresenter mPresenter;
    public int channelID;
    public int subjectID;
    public int page = 1;
    public EventTabOneFragment(int channelID,int subjectID){
        this.channelID = channelID;
        this.subjectID = subjectID;
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_event_tab_one;
    }

    @Override
    protected void initView() {
        DaggerEventTopicComponent.builder()
                .appComponent(getAppComponent())
                .eventTopicModule(new EventTopicModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        if (channelID <= 0 || subjectID <= 0) {
            return;
        }
        mPresenter.getAllInfo(channelID,page,subjectID);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getResult(List<EventTopicComp> list) {

    }

    @Override
    public void getResultChild(EventTopicEntity entity) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        List<EventTopicEntity.item> list = entity.rows;
        recyclerView.setAdapter(new EventTopicAdapter(list));
    }
}
