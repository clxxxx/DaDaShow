package com.xzry.takeshow.ui.mine;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.sxjs.common.base.BaseActivity;
import com.xzry.takeshow.R;
import com.xzry.takeshow.widget.TitleBarView;

import butterknife.BindView;

/**
 *  意见与反馈
 */
public class OpinionsAndFeedbackActivity extends BaseActivity implements TextWatcher{
    @BindView(R.id.title_bar)
    TitleBarView tv_titleBar;
    @BindView(R.id.feedback_content)
    EditText et_content;
    @BindView(R.id.feedback_text1)
    TextView tv_currentTxLength;
    @Override
    public int getLayout() {
        return R.layout.activity_opinions_and_feedback;
    }

    @Override
    protected void initView() {
        tv_titleBar.setTitle("意见反馈");
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {
        tv_titleBar.setTitlebarListener(new TitleBarView.BtnClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        et_content.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (getFeedbackContent().length() > 0) {
            tv_currentTxLength.setText(""+getFeedbackContent().length());
        }else {
            tv_currentTxLength.setText("0");
        }
    }
    public String getFeedbackContent(){
        return et_content.getText().toString().trim();
    }
}
