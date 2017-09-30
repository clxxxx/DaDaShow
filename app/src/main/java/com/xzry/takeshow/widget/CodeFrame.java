package com.xzry.takeshow.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xzry.takeshow.R;

/**  自定义验证码框
 * @author: luosy
 * @date: 2017-9-5
 * @description:
 */


public class CodeFrame extends RelativeLayout implements TextWatcher {
    EditText editText;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;

    public CodeFrame(Context context) {
        super(context);
        init(context);
    }

    public CodeFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CodeFrame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CodeFrame(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_code_frame,this);
        editText = (EditText) findViewById(R.id.code_edittext);
        tv1 = (TextView) findViewById(R.id.code_text1);
        tv2 = (TextView) findViewById(R.id.code_text2);
        tv3 = (TextView) findViewById(R.id.code_text3);
        tv4 = (TextView) findViewById(R.id.code_text4);
        tv5 = (TextView) findViewById(R.id.code_text5);
        tv6 = (TextView) findViewById(R.id.code_text6);
        editText.addTextChangedListener(this);
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        for (int i = 0; i < getCode().length(); i++) {
            if (i == 0) {
                tv1.setText(getCode().substring(0));
                tv2.setText("");
                tv1.setTextColor(Color.parseColor("#FF365D"));

            }else if (i == 1) {
                tv2.setText(getCode().substring(1));
                tv3.setText("");
                tv2.setTextColor(Color.parseColor("#FF365D"));
                tv1.setTextColor(Color.parseColor("#151517"));
            }else if (i == 2) {
                tv3.setText(getCode().substring(2));
                tv4.setText("");
                tv3.setTextColor(Color.parseColor("#FF365D"));
                tv2.setTextColor(Color.parseColor("#151517"));
            }else if (i == 3) {
                tv4.setText(getCode().substring(3));
                tv5.setText("");
                tv4.setTextColor(Color.parseColor("#FF365D"));
                tv3.setTextColor(Color.parseColor("#151517"));
            }else if (i == 4) {
                tv5.setText(getCode().substring(4));
                tv6.setText("");
                tv5.setTextColor(Color.parseColor("#FF365D"));
                tv4.setTextColor(Color.parseColor("#151517"));
            }else if (i == 5) {
                tv6.setText(getCode().substring(5));
                tv6.setTextColor(Color.parseColor("#FF365D"));
                tv5.setTextColor(Color.parseColor("#151517"));
            }
        }
        if (getCode().length()==0) {
            tv1.setText("");
        }
    }
    public String getCode(){
        return editText.getText().toString().trim();
    }
}
