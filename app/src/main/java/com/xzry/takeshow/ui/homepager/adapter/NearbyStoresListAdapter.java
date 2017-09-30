package com.xzry.takeshow.ui.homepager.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.NearbyStoresEntity;
import com.xzry.takeshow.ui.homepager.BrandShopActivity;
import com.xzry.takeshow.ui.homepager.StoreActivity;

import java.util.List;

import static com.xzry.takeshow.R.id.item_ns_toshop;

/**
 * @author: luosy
 * @date: 2017-9-11
 * @description:
 */


public class NearbyStoresListAdapter extends BaseQuickAdapter<NearbyStoresEntity.row,BaseViewHolder> implements BaseQuickAdapter.OnItemChildClickListener{
    public List<NearbyStoresEntity.row> rowList;
    public NearbyStoresListAdapter(List<NearbyStoresEntity.row> rowList){
        super(R.layout.item_nearby_stores,rowList);
        this.rowList = rowList;
    }
    @Override
    protected void convert(BaseViewHolder helper, NearbyStoresEntity.row item, int position) {
        ((ExpandImageView)helper.getView(R.id.item_ns_img)).setImageURI(item.storeImg);
        helper.setText(R.id.item_ns_name,item.storeName);
        helper.setText(R.id.item_ns_address,item.address);
        helper.setText(R.id.item_ns_phone,item.mobile);
        helper.setText(R.id.item_ns_km,item.distance);



        helper.addOnClickListener(R.id.item_ns_tomap);
        helper.addOnClickListener(R.id.item_ns_tophone);
        helper.addOnClickListener(R.id.item_ns_toshop);
        setOnItemChildClickListener(this);

    }



    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_ns_tomap:
                StoreActivity.intentActivity(mContext,rowList.get(position));
                break;
            case R.id.item_ns_tophone:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + rowList.get(position).mobile);
                intent.setData(data);
                mContext.startActivity(intent);
                break;
            case item_ns_toshop:
                BrandShopActivity.toStartActivity(mContext,rowList.get(position).storeID);
                break;
        }
        return false;
    }
}
