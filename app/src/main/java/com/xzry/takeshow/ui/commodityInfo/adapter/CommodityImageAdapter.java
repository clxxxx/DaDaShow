package com.xzry.takeshow.ui.commodityInfo.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.util.ScreenUtil;
import com.xzry.takeshow.R;

import java.util.List;

/**
 * Created by 周东阳 on 2017/8/10 0010.
 */

public class CommodityImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CommodityImageAdapter(List<String> data) {
        super(R.layout.item_image, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {
        ImageView imageView = ((ImageView) helper.getView(R.id.img));
        int screenWidth = ScreenUtil.getScreenWidth(mContext);
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = screenWidth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        imageView.setLayoutParams(lp);
        imageView.setMaxWidth(screenWidth);
        imageView.setMaxHeight(screenWidth * 5);
        switch (position)
        {
            case 0:
//                imageView.setImageResource(R.mipmap.as);
//                imageView.setImageURI(
//                        "http://img30.360buyimg.com/popWaterMark/jfs/t6028/155/7052653619/358316/b94cd9b4/5979bcf8N05184001.jpg");
                break;
            case 1:
//                imageView.setImageURI(
//                        "http://img30.360buyimg.com/popWaterMark/jfs/t6010/152/7070837609/497809/c1a311c9/5979bcf7N5a5ad8ad.jpg");
                break;
            case 2:
//                imageView.setImageURI(
//                        "http://img.hb.aicdn.com/25f7cc8f32882079b3f4f4740a5ffb12e9622523b94c-QlhxD6_fw658");
                break;
            case 3:
//                imageView.setImageURI(
//                        "http://img30.360buyimg.com/popWaterMark/jfs/t6028/155/7052653619/358316/b94cd9b4/5979bcf8N05184001.jpg");
                break;
            case 4:
//                imageView.setImageURI(
//                        "http://img30.360buyimg.com/popWaterMark/jfs/t6010/152/7070837609/497809/c1a311c9/5979bcf7N5a5ad8ad.jpg");
                break;
            case 5:
//                imageView.setImageURI(
//                        "http://img30.360buyimg.com/popWaterMark/jfs/t6028/155/7052653619/358316/b94cd9b4/5979bcf8N05184001.jpg");
                break;
        }
    }
}
