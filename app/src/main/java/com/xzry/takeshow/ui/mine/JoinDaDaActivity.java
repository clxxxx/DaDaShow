package com.xzry.takeshow.ui.mine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.adapter.GridImageAdapter;
import com.xzry.takeshow.base.BaseValue;
import com.xzry.takeshow.widget.FullyGridLayoutManager;
import com.xzry.takeshow.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 *  加入搭搭秀
 */
public class JoinDaDaActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBarView titleBarView;
    @BindView(R.id.join_add_img)
    RecyclerView recyclerView;

    private static final int REQUEST_IMAGE = 2;
    private List<LocalMedia> selectList = new ArrayList<>();

    private GridImageAdapter mAdapter;//发布adapter
    @Override
    public int getLayout() {
        return R.layout.activity_join_da_da;
    }

    @Override
    protected void initView() {
        titleBarView.setTitle("加入搭搭");
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        mAdapter = new GridImageAdapter(this, onAddPicClickListener);
        mAdapter.setList(selectList);
        mAdapter.setSelectMax(BaseValue.ADD_MAX_IMAGE_SIZE);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
//                switch (mediaType) {
//                    case 1:
//                        // 预览图片
//                        PictureSelector.create(JoinDaDaActivity.this).externalPicturePreview(position, selectList);
//                        break;
//                    case 2:
//                        // 预览视频
//                        PictureSelector.create(JoinDaDaActivity.this).externalPictureVideo(media.getPath());
//                        break;
//                    case 3:
//                        // 预览音频
//                        PictureSelector.create(JoinDaDaActivity.this).externalPictureAudio(media.getPath());
//                        break;
//                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        titleBarView.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                mAdapter.setList(selectList);
                mAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePhoto();
        } else {
            //showShortToast(getString(R.string.setting_access_hint));
        }
    }
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            takePhoto();
           // openWESPermission();

        }
    };

    //申请使用相机和文件访问的权限
    public void openWESPermission() {
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 2);
        } else {
            takePhoto();
        }
    }
    public void takePhoto(){
        PictureSelector.create(JoinDaDaActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .enableCrop(true)
                .compress(true)
                .freeStyleCropEnabled(false)
                .withAspectRatio(1,1)
                .circleDimmedLayer(false)//// 是否圆形裁剪 true or false
                .showCropFrame(false)
                .showCropGrid(false)
                .forResult(PictureConfig.CHOOSE_REQUEST);


//        PictureSelector.create(JoinDaDaActivity.this)
//                .openGallery(PictureMimeType.ofImage())
//                .theme(R.style.picture_white_style)
//                .maxSelectNum(BaseValue.MAX_IMAGE_SIZE)
//                .minSelectNum(1)
//                .selectionMode(PictureConfig.MULTIPLE)
////                        .previewImage(cb_preview_img.isChecked())
//                .compressGrade(Luban.THIRD_GEAR)
//                .isCamera(true)
//                .compress(true)
//                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
//                .glideOverride(160, 160)
//                .previewEggs(true)
////                        .isGif(cb_isGif.isChecked())
//                .selectionMedia(selectList)
//                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
}
