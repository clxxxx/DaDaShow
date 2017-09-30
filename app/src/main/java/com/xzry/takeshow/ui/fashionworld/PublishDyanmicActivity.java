package com.xzry.takeshow.ui.fashionworld;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.adapter.GridImageAdapter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 周东阳 on 2017/8/25 0025.
 */

public class PublishDyanmicActivity extends BaseActivity {

    @BindView(R.id.titlebar_view)
    TitleBarView mtitle;
    @BindView(R.id.edt_fashion_publish_content)
    EditText edt_fashion_publish_content;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private GridImageAdapter adapter;

    private List<LocalMedia> selectList = new ArrayList<>();

    public static void intentPublishDyanmicActivity(Context activity) {
        Intent intent = new Intent(activity, PublishDyanmicActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_publish_dyanmic;
    }

    @Override
    protected void initView() {

        FullyGridLayoutManager manager = new FullyGridLayoutManager(PublishDyanmicActivity.this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(PublishDyanmicActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(BaseValue.DYANMIC_MAX_IMAGE_SIZE);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            PictureSelector.create(PublishDyanmicActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(PublishDyanmicActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(PublishDyanmicActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(PublishDyanmicActivity.this);
                } else {
                    Toast.makeText(PublishDyanmicActivity.this,
                            getString(R.string.setting_access_hint), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            PictureSelector.create(PublishDyanmicActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_white_style)
                .maxSelectNum(BaseValue.DYANMIC_MAX_IMAGE_SIZE)
                .minSelectNum(1)
                .selectionMode(PictureConfig.MULTIPLE)
//                        .previewImage(cb_preview_img.isChecked())
                .compressGrade(Luban.THIRD_GEAR)
                .isCamera(true)
                .compress(true)
                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
                .glideOverride(160, 160)
                .previewEggs(true)
//                        .isGif(cb_isGif.isChecked())
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
