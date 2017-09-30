package com.xzry.takeshow.ui.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseFragment;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.dagger.component.DaggerCollectComponent;
import com.xzry.takeshow.dagger.module.CollectModule;
import com.xzry.takeshow.entity.CollectGoods;
import com.xzry.takeshow.entity.CollectMerchant;
import com.xzry.takeshow.ui.mine.adapter.CollectMerchantAdapter;
import com.xzry.takeshow.ui.mine.contract.CollectContract;
import com.xzry.takeshow.ui.mine.presenter.CollectPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


public class CollectMerchantFragment extends BaseFragment implements CollectContract.View{
    @Inject
    CollectPresenter mPresenter;
    @BindView(R.id.fg_merchant_recycler)
    RecyclerView recyclerView;
    private int page = 1;
    public static CollectMerchantFragment newInstance(){
        return new CollectMerchantFragment();
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_collect_merchant;
    }

    @Override
    protected void initView() {
        DaggerCollectComponent.builder()
                .appComponent(getAppComponent())
                .collectModule(new CollectModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        Map mp = new HashMap();
        mp.put("token", BaseValue.TOKEN);
        mp.put("pageNo",page);
        mPresenter.getMerchantList(mp);
    }

    @Override
    protected void initListener() {

    }



    @Override
    public void resultGoodsSuccess(CollectGoods goods) {

    }

    @Override
    public void resultMerchantSuccess(CollectMerchant merchant) {
        if (merchant == null) {
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CollectMerchantAdapter adapter = new CollectMerchantAdapter(merchant.rows);
        recyclerView.setAdapter(adapter);

    }
}
