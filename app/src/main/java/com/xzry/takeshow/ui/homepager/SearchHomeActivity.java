package com.xzry.takeshow.ui.homepager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.StartActivityUtil;
import com.xzry.takeshow.R;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.entity.request.SearchRequest;
import com.xzry.takeshow.entity.response.SearchDataResponse;
import com.xzry.takeshow.ui.homepager.adapter.SearchHistoryAdapter;
import com.xzry.takeshow.ui.homepager.adapter.SearchItemAdapter;
import com.xzry.takeshow.ui.homepager.contract.SearchContract;
import com.xzry.takeshow.ui.homepager.contract.component.DaggerSearchComponent;
import com.xzry.takeshow.ui.homepager.module.SearchModule;
import com.xzry.takeshow.ui.homepager.presenter.SearchPresenter;
import com.xzry.takeshow.widget.AutoNewLineLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 *  首页搜索
 */

public class SearchHomeActivity extends BaseActivity implements SearchContract.View,View.OnClickListener,TextWatcher,AdapterView.OnItemSelectedListener {
    String TAG = "search_home";
    @BindView(R.id.status_bar_view)
    View status_bar;
    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.search_home_spinner)
    Spinner sp_spinner;
    @BindView(R.id.home_search_frame)
    EditText et_searchFrame;        //搜索框



    @BindView(R.id.search_hot_autonewlayout)
    AutoNewLineLayout hot_autoNewLineLayout;//热门搜索

    @BindView(R.id.home_search_history)
    RecyclerView rv_history;//历史记录

    @BindView(R.id.home_search_record)
    LinearLayout ll_record;           //记录
    @BindView(R.id.home_search_item)
    RecyclerView rv_searchItem;       //搜索的自动提示
    @BindView(R.id.search_clear)
    TextView tv_clear;                //清除历史记录

    private List<String> hotSearchLists = new ArrayList<>();
    private List<String> historyLists;
    @Inject
    SearchPresenter mPresenter;

    //SearchData searchData;

    RecyclerView.Adapter history_Adapter;
    RecyclerView.Adapter search_adapter;
    //private SearchHomePresenter mPresenter;
    int id = 0;

    public static void initActivity(Context context){
        Intent intent = new Intent(context, SearchHomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search_home;
    }

    @Override
    protected void initView() {
        init();
       // mPresenter = new SearchHomePresenter(this);
       // id = SpUtil.getInstance().getInt(USERID,0);
        if (id == 0) {
            return;
        }
    }

    @Override
    protected void initData() {

        //加载自动提示
        loadSearchItem();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //加载热门搜索
        loadSearchHot();
        //加载历史记录
        loadSearchHistory();
    }

    @Override
    protected void initListener() {
        tv_back.setOnClickListener(this);
        et_searchFrame.addTextChangedListener(this);
        sp_spinner.setOnItemSelectedListener(this);
        et_searchFrame.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                     //添加搜索事件
                    toSearchData();



                }
                return false;
            }
        });
    }
    public void toSearchData(){
        hideInput(SearchHomeActivity.this);//隐藏软键盘
        if (getContent().length()<=0) {
            showShortToast("请输入搜索内容");
            return;
        }
        StartActivityUtil.toActivity(this,SearchDataActivity.class,getContent());
    }
    /**
     *  隐藏软键盘
     */
    public static InputMethodManager manager;// 输入法管理器 用户隐藏软键盘
    public void hideInput(Context context) {
        if(manager==null){
             manager = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        manager.hideSoftInputFromWindow(((Activity) context)
                .getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public boolean isInPictureInPictureMode() {
        return super.isInPictureInPictureMode();
    }
    public void init(){
        DaggerSearchComponent.builder()
                .appComponent(getAppComponent())
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
        ViewTreeObserver viewTreeObserver = status_bar.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)status_bar.getLayoutParams();
                lp.height = BaseValue.STATUS_HEIGHT;
                status_bar.setLayoutParams(lp);
                return true;
            }
        });
        List<String> data_list = new ArrayList<>();
        data_list.add("商品");
        data_list.add("店铺");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.adapter_mytopactionbar_spinner,data_list){
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    //设置spinner展开的Item布局
                    convertView = getLayoutInflater().inflate(R.layout.adapter_mytopactionbar_spinner_item, parent, false);
                }
                TextView spinnerText = (TextView) convertView.findViewById(R.id.spinner_textView);
                spinnerText.setText(getItem(position));
                return convertView;
            }
        };
        sp_spinner.setAdapter(adapter);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_back:
                finish();
                break;

            case R.id.search_clear:   //清除
                mPresenter.clearHistoryData(BaseValue.USER_ID);
                break;
            default:
                break;
        }

    }
    //热门搜索
    private void loadSearchHot(){
        hotSearchLists.add("口红");
        hotSearchLists.add("春季套装");
        hotSearchLists.add("短靴");
        hotSearchLists.add("钱夹");
        hotSearchLists.add("单鞋");
        hotSearchLists.add("高跟鞋");
        hotSearchLists.add("打底裤");
        for (int i = 0; i < hotSearchLists.size(); i++) {
            final TextView tv = new TextView(this);
            tv.setTag(i);
            tv.setText(hotSearchLists.get(i));
            tv.setPadding(40,10,40,10);
            tv.setBackgroundResource(R.drawable.search_text_border);
            hot_autoNewLineLayout.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(SearchHomeActivity.this,SearchDataActivity.class);
//                    intent.putExtra("goodsName",tv.getText().toString());
//                    startActivity(intent);
                }
            });
        }

    }
    //历史记录
    private void loadSearchHistory(){

        mPresenter.toHistoryData(BaseValue.USER_ID);
    }
    //加载默认搜索条目，后面可以直接刷新
    private void loadSearchItem(){

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (getContent().length() > 0) {
            ll_record.setVisibility(View.GONE);
            SearchRequest request = new SearchRequest();
            Log.i(TAG,"----------"+BaseValue.USER_ID);
            request.setUserId(BaseValue.USER_ID);
            request.setKeyWord(getContent());
            request.setPage("1");
            mPresenter.toSearch(request);
        }else {
            ll_record.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public String getContent(){
        return  et_searchFrame.getText().toString().trim();
    }


    @Override
    public void getSearchData(SearchDataResponse dada) {
        List<SearchDataResponse.DataBean> dataBeenList = dada.itemList;
        Log.i(TAG,"---data:"+dataBeenList.size());
        SearchItemAdapter adapter = new SearchItemAdapter(dataBeenList);
        rv_searchItem.setLayoutManager(new LinearLayoutManager(this));
        rv_searchItem.setAdapter(adapter);

//        rv_searchItem.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(SearchHomeActivity.this,SearchDataActivity.class);
//                intent.putExtra("goodsName",searchData.getItemList().get(position).getGoodsName());
//                startActivity(intent);
//            }
//        }));



    }

    @Override
    public void getHistoryData(List<String> itemList) {
        historyLists = itemList;
        history_Adapter = new SearchHistoryAdapter(historyLists);
        rv_history.setLayoutManager(new LinearLayoutManager(this));
        rv_history.setAdapter(history_Adapter);
        //rv_history.setAdapter();
//        rv_history.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(SearchHomeActivity.this,SearchDataActivity.class);
//                intent.putExtra("goodsName",historyLists.get(position));
//                startActivity(intent);
//            }
//        }));
    }

    @Override
    public void getClearHistoryData(String str) {
        if (str.equals("success")) {
            historyLists.clear();
            history_Adapter.notifyDataSetChanged();
            showShortToast("清除成功");
        }else {
            showShortToast("清除失败");
        }
    }



}
