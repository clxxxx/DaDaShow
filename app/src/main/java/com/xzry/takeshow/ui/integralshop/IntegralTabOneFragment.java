package com.xzry.takeshow.ui.integralshop;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxjs.common.base.BaseFragment;
import com.xzry.takeshow.R;
import com.xzry.takeshow.dagger.component.DaggerIntegralShopComponent;
import com.xzry.takeshow.dagger.module.IntegralShopModule;
import com.xzry.takeshow.entity.homeEntity.IntegralCompEntity;
import com.xzry.takeshow.entity.homeEntity.IntegralEntity;
import com.xzry.takeshow.ui.integralshop.adapter.IntegralFragmentAdapter;
import com.xzry.takeshow.ui.integralshop.contract.IntegralShopContract;
import com.xzry.takeshow.ui.integralshop.presenter.IntegralShopPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class IntegralTabOneFragment extends BaseFragment implements IntegralShopContract.View{
    @BindView(R.id.integral_shop_recyclerview)
    RecyclerView recyclerView;
    public int categoryID;
    public int page = 1;
    @Inject
    IntegralShopPresenter mPresenter;
    public IntegralTabOneFragment(int categoryID){
        this.categoryID = categoryID;
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_one;
    }

    @Override
    protected void initView() {
        DaggerIntegralShopComponent.builder()
                .appComponent(getAppComponent())
                .integralShopModule(new IntegralShopModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        if (categoryID <= 0) {
            return;
        }
        mPresenter.getAllInfo(categoryID,page);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getResult(List<IntegralCompEntity> list) {

    }

    @Override
    public void getResultChild(IntegralEntity entity) {
        // 设置布局管理器

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        List<IntegralEntity.item> list = entity.rows;
        recyclerView.setAdapter(new IntegralFragmentAdapter(list));
    }
}
