package com.xzry.takeshow.ui.homepager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.request.SearchRequest;
import com.xzry.takeshow.entity.response.SearchDataResponse;
import com.xzry.takeshow.ui.homepager.adapter.SearchDataAdapter;
import com.xzry.takeshow.ui.homepager.contract.SearchDataContract;
import com.xzry.takeshow.ui.homepager.contract.component.DaggerSearchDataComponent;
import com.xzry.takeshow.ui.homepager.module.SearchDataModule;
import com.xzry.takeshow.ui.homepager.presenter.SearchDataPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;



/**
 *  搜索内容
 */
public class SearchDataActivity extends BaseActivity implements SearchDataContract.View,View.OnClickListener{
    String TAG = "searchdata";
    @BindView(R.id.se_da_back)
    LinearLayout ll_back;
    @BindView(R.id.se_da_search_lay)
    LinearLayout ll_searchFram;
    @BindView(R.id.se_da_search_text)
    TextView ll_searchText;
    @BindView(R.id.se_da_more)
    LinearLayout ll_more;
    @BindView(R.id.se_da_button_btn1)
    LinearLayout btn_sales;
    @BindView(R.id.se_da_button_btn2)
    LinearLayout btn_news;
    @BindView(R.id.se_da_button_btn3)
    LinearLayout btn_price;
    @BindView(R.id.se_da_price_state)
    ImageView iv_priceState;
    @BindView(R.id.se_da_recyclerview)
    RecyclerView rv_RecyclerView;

    @Inject
    SearchDataPresenter mPresenter;
    private LinearLayout[] mTabs;
    private int currentTabIndex = 0;
    private int isFirst = 0;

    public static void toActivity(Context context, String key) {
        Intent intent = new Intent(context, SearchDataActivity.class);
        intent.putExtra("str",key);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search_data;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        ll_back.setOnClickListener(this);
        btn_sales.setOnClickListener(this);
        btn_news.setOnClickListener(this);
        btn_price.setOnClickListener(this);
    }
    public void init(){
        DaggerSearchDataComponent.builder()
                .appComponent(getAppComponent())
                .searchDataModule(new SearchDataModule(this))
                .build()
                .inject(this);
        String str = getIntent().getStringExtra("str");
        SearchRequest request = new SearchRequest();
        Log.i(TAG,"----------"+BaseValue.USER_ID);
        request.setUserId(BaseValue.USER_ID);
        request.setKeyWord(str);
        request.setPage("1");
        mPresenter.toSearch(request);

        mTabs = new LinearLayout[3];
        mTabs[0] = btn_sales;
        mTabs[1] = btn_news;
        mTabs[2] = btn_price;

        mTabs[0].setSelected(true);
    }

    @Override
    public void getSearchData(SearchDataResponse dada) {


        List<SearchDataResponse.DataBean> dataBeenList = dada.itemList;
        Log.i(TAG,"---data:"+dataBeenList.size());
        rv_RecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        rv_RecyclerView.setAdapter(new SearchDataAdapter(dataBeenList));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.se_da_back:
                finish();
                break;
            case R.id.se_da_button_btn1:
                loadSales();
                break;
            case R.id.se_da_button_btn2:
                loadNews();
                break;
            case R.id.se_da_button_btn3:
                loadPrice();
                break;
            default:
                break;
        }
    }
    public void loadSales(){
        currentTab(0);

    }
    public void loadNews(){
        currentTab(1);
    }
    public void loadPrice(){
        if (currentTabIndex != 2) {
            isFirst = 0;
        }
        if (isFirst == 0) {
            iv_priceState.setImageResource(R.mipmap.ico_price);
            isFirst = 1;
        }else {
            iv_priceState.setImageResource(R.mipmap.ico_price_2);
            isFirst = 0;
        }
        Log.i(TAG," --- isfirst:"+isFirst);
        currentTab(2);
    }
    private void currentTab(int position){
        if (currentTabIndex != position) {
            mTabs[currentTabIndex].setSelected(false);
            mTabs[position].setSelected(true);
            currentTabIndex = position;
        }
        Log.i(TAG," --- isfirst:"+isFirst);
    }
}
