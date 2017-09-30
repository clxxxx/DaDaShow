package com.xzry.takeshow.ui.fashionworld.adapter;

import com.sxjs.common.base.baseadapter.BaseMultiItemQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.MyGridView;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.xzry.takeshow.R;
import com.xzry.takeshow.entity.RecommendEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周东阳 on 2017/8/24 0024.
 */

public class PersonageDyanmicAdapter  extends BaseMultiItemQuickAdapter<RecommendEntity, BaseViewHolder> {

    public PersonageDyanmicAdapter( List<RecommendEntity> data) {
        super(data);
        addItemType(RecommendEntity.IMG_SIZE_ONE, R.layout.item_recommend_img_one);
        addItemType(RecommendEntity.IMG_SIZE_TWO, R.layout.item_recommend_img_two);
        addItemType(RecommendEntity.IMG_SIZE_NINE, R.layout.item_recommend_img_nine);
    }


    @Override
    protected void convert(BaseViewHolder helper, RecommendEntity item, int position) {
        switch (helper.getItemViewType()) {
            case RecommendEntity.IMG_SIZE_ONE:
                bindContentOneImg(helper, item, position);
                break;
            case RecommendEntity.IMG_SIZE_TWO:
                bindContentTwoImg(helper, item, position);
                break;
            case RecommendEntity.IMG_SIZE_NINE:
                bindContentNineImg(helper, item, position);
                break;
        }
    }

    //一张图片展示
    private void bindContentOneImg(final BaseViewHolder helper, RecommendEntity item, int position) {
        ((ExpandImageView) helper.getView(R.id.one_img)).setImageURI("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1502714872&di=cbc9988f554eb43fc61da5bee1bb2524&src=http://attachments.gfan.com/forum/201510/13/122325e9vecvb0u3o9wqzw.jpg");
    }

    //两张，四张图片展示
    private void bindContentTwoImg(final BaseViewHolder helper, RecommendEntity item, int position) {
        MyGridView myGridView = (MyGridView) helper.getView(R.id.myGridView);
        List<String> list = new ArrayList<>();
        if (position == 1)
        {
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796067267&di=c1a64b07a461dc1dac8261505beed3ad&imgtype=0&src=http%3A%2F%2Fp19.qhimg.com%2Fbdr%2F__%2Ft01dcb868586171c13d.jpg");
        }else {
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796067267&di=c1a64b07a461dc1dac8261505beed3ad&imgtype=0&src=http%3A%2F%2Fp19.qhimg.com%2Fbdr%2F__%2Ft01dcb868586171c13d.jpg");
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796085285&di=963387008ad69a0bea11eeafc3f66f16&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201309%2F09%2F180637aa6zc5l056bbn7b4.jpg");
            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
        }
        MyGridViewAdapter adapter = new MyGridViewAdapter(mContext, list);
        myGridView.setAdapter(adapter);
    }

    //其他：3,5,6,7,8,9张图片展示
    private void bindContentNineImg(final BaseViewHolder helper, RecommendEntity item, int position) {
        MyGridView myGridView = (MyGridView) helper.getView(R.id.myGridView);
        List<String> list = new ArrayList<>();
        switch (position)
        {
            case 2:
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796106507&di=3e0553006aa54cbbefbcbd333fee7ed3&imgtype=0&src=http%3A%2F%2Ffile.mumayi.com%2Fforum%2F201311%2F15%2F172925ngux2agzgoau3kx2.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                break;
            case 4:
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796106507&di=3e0553006aa54cbbefbcbd333fee7ed3&imgtype=0&src=http%3A%2F%2Ffile.mumayi.com%2Fforum%2F201311%2F15%2F172925ngux2agzgoau3kx2.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796067267&di=c1a64b07a461dc1dac8261505beed3ad&imgtype=0&src=http%3A%2F%2Fp19.qhimg.com%2Fbdr%2F__%2Ft01dcb868586171c13d.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                break;
            case 5:
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796106507&di=3e0553006aa54cbbefbcbd333fee7ed3&imgtype=0&src=http%3A%2F%2Ffile.mumayi.com%2Fforum%2F201311%2F15%2F172925ngux2agzgoau3kx2.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796067267&di=c1a64b07a461dc1dac8261505beed3ad&imgtype=0&src=http%3A%2F%2Fp19.qhimg.com%2Fbdr%2F__%2Ft01dcb868586171c13d.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796085285&di=963387008ad69a0bea11eeafc3f66f16&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201309%2F09%2F180637aa6zc5l056bbn7b4.jpg");
                break;
            case 6:
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796106507&di=3e0553006aa54cbbefbcbd333fee7ed3&imgtype=0&src=http%3A%2F%2Ffile.mumayi.com%2Fforum%2F201311%2F15%2F172925ngux2agzgoau3kx2.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796067267&di=c1a64b07a461dc1dac8261505beed3ad&imgtype=0&src=http%3A%2F%2Fp19.qhimg.com%2Fbdr%2F__%2Ft01dcb868586171c13d.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796085285&di=963387008ad69a0bea11eeafc3f66f16&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201309%2F09%2F180637aa6zc5l056bbn7b4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                break;
            case 7:
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796106507&di=3e0553006aa54cbbefbcbd333fee7ed3&imgtype=0&src=http%3A%2F%2Ffile.mumayi.com%2Fforum%2F201311%2F15%2F172925ngux2agzgoau3kx2.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796067267&di=c1a64b07a461dc1dac8261505beed3ad&imgtype=0&src=http%3A%2F%2Fp19.qhimg.com%2Fbdr%2F__%2Ft01dcb868586171c13d.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796085285&di=963387008ad69a0bea11eeafc3f66f16&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201309%2F09%2F180637aa6zc5l056bbn7b4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                break;
            case 8:
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796106507&di=3e0553006aa54cbbefbcbd333fee7ed3&imgtype=0&src=http%3A%2F%2Ffile.mumayi.com%2Fforum%2F201311%2F15%2F172925ngux2agzgoau3kx2.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796067267&di=c1a64b07a461dc1dac8261505beed3ad&imgtype=0&src=http%3A%2F%2Fp19.qhimg.com%2Fbdr%2F__%2Ft01dcb868586171c13d.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796023342&di=b24dd5829431a957aece6a0035d4197c&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201307%2F24%2F090122enm9onakhm9kpen4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796085285&di=963387008ad69a0bea11eeafc3f66f16&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201309%2F09%2F180637aa6zc5l056bbn7b4.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502796093423&di=31c57474a09f732c38e6c22c77b5757c&imgtype=0&src=http%3A%2F%2F0.thumb.pc6.com%2Fthumb%2Fup%2F2011-12%2F20111212771455195624_460_380.jpg");
                break;
        }
        MyGridViewAdapter adapter = new MyGridViewAdapter(mContext, list);
        myGridView.setAdapter(adapter);
    }
}