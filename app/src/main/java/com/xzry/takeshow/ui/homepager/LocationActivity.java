package com.xzry.takeshow.ui.homepager;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.sxjs.common.util.PinyinUtils;
import com.xzry.takeshow.R;
import com.xzry.takeshow.db.DBManager;
import com.xzry.takeshow.entity.City;
import com.xzry.takeshow.ui.homepager.adapter.LocationAdapter;
import com.xzry.takeshow.ui.homepager.widget.PinnedHeaderExpandableListView;
import com.xzry.takeshow.ui.homepager.widget.SideLetterBar;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/** 定位页面
 * Created by zdy on 2017/6/9 0009.
 */

public class LocationActivity extends BaseActivity implements View.OnClickListener, TitleBarView.BtnClickListener,
        ExpandableListView.OnChildClickListener,ExpandableListView.OnGroupClickListener,PinnedHeaderExpandableListView.OnHeaderUpdateListener{

    @BindView(R.id.title_bar)
    TitleBarView titleBarView;

    @BindView(R.id.side_letter_bar)
    SideLetterBar mLetterBar;

    @BindView(R.id.et_search)
    EditText searchBox;

    @BindView(R.id.all_city)
    PinnedHeaderExpandableListView mAllCityListView;

//    @BindView(R.id.search_result)
//    PinnedHeaderExpandableListView mResultListView;

    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;

    @BindView(R.id.empty_view)
    ViewGroup emptyView;

    private static final String TAG = "LocationActivity";
    private DBManager dbManager;
    LocationAdapter mAdapter;
    //adapter数据
    private List<Map<String, String>> letterList  = new ArrayList<Map<String, String>>();
    private List<List<Map<String, String>>> cityList  = new ArrayList<List<Map<String, String>>>();


    private static LocationActivity insatnce;

    public static void initActivity(Context context){
        Intent intent = new Intent(context, LocationActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_location;
    }

    @Override
    protected void initView() {
        titleBarView.setTitle("选择城市");

        initLetterBar();
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
//                    clearBtn.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
//                    mResultListView.setVisibility(View.GONE);
                } else {
//                    clearBtn.setVisibility(View.VISIBLE);
//                    mResultListView.setVisibility(View.VISIBLE);
                    List<City> result = dbManager.searchCity(keyword);
                    if (result == null || result.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
//                        mResultAdapter.changeData(result);
                    }
                }
            }
        });

        View hotView = (ViewGroup) getLayoutInflater().inflate(R.layout.location_headview, null);
        hotView.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

//        RecyclerView.Adapter a = new RecyclerView.Adapter() {
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return 0;
//            }
//        }

        mAllCityListView.addHeaderView(hotView);

        mAdapter = new LocationAdapter(this);
        mAllCityListView.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {
//        fetchUsersFromDatabase().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onSubscribe(Subscription s) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        mAdapter.setCityList(letterList, cityList);
//                        // 展开所有group,因为添加了headview，所以要-1
//                        for (int i = 0, count = mAllCityListView.getCount()-1; i < count; i++) {
//                            mAllCityListView.expandGroup(i);
//                        }
//                    }
//                });
    }

    @Override
    protected void initListener() {
        titleBarView.setTitlebarListener(this);

        mAllCityListView.setOnHeaderUpdateListener(this);
        mAllCityListView.setOnChildClickListener(this);
    }

    private void initLetterBar() {
        mLetterBar.setOverlay(tvSideBarHint);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = -1;
                for (int i = 0; i<letterList.size(); i++)
                {
                    if (TextUtils.equals(letterList.get(i).get("letter"), letter))
                        position = i;
                }
                mAllCityListView.setSelectedGroup(position);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }

    @Override
    public View getPinnedHeader() {
        View headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.item_location_letter, null);
        headerView.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        TextView tv_letter = (TextView) headerView.findViewById(R.id.tv_letter);
        if (firstVisibleGroupPos == -1){
            tv_letter.setVisibility(View.INVISIBLE);
        }else {
            tv_letter.setVisibility(View.VISIBLE);
            tv_letter.setText(letterList.get(firstVisibleGroupPos).get("letter"));
        }

    }



//    public Observable<String> fetchUsersFromDatabase() {
//        return Observable.create(new Observable.OnSubscribe<String>(){
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext(getCitys());
//                subscriber.onCompleted();
//            }
//        });
//    }

    public String getCitys()
    {
        dbManager = new DBManager(this);
        dbManager.copyDBFile();
        List<City> mAllCities = dbManager.getAllCities();
        for (int i = 'A'; i <= 'Z'; i++) {
            //提供父列表的数据
            Map<String, String> gmap = new HashMap<String, String>();
            gmap.put("letter", (char)(i) + "");
            letterList.add(gmap);
            //提供当前父列的子列数据
            List<Map<String, String>> citys = new ArrayList<Map<String, String>>();
            for (int j = 0; j < mAllCities.size(); j++) {
                //当前城市拼音首字母
                String currentLetter = PinyinUtils.getFirstLetter(mAllCities.get(j).getPinyin());
                if (TextUtils.equals(currentLetter, ((char)(i) + "")))
                {
                    Map<String, String> cmaps = new HashMap<String, String>();
                    cmaps.put("city", mAllCities.get(j).getName());
                    citys.add(cmaps);
                }
            }
            cityList.add(citys);
        }

        return "成功";
    }

}
