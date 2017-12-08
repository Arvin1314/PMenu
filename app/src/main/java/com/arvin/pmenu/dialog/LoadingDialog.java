package com.arvin.pmenu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.arvin.pmenu.R;


public class LoadingDialog extends Dialog {
    private Context mContext;
    private TextView mMsgTextView;
    private String msg = "";

    public LoadingDialog(Context context) {
        //super(mContext, R.style.PopDialog);
        super(context);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        View ContentView = View.inflate(mContext, R.layout.common_dialog_loading_progress, null);

        setContentView(ContentView);

        mMsgTextView = (TextView) ContentView.findViewById(R.id.dialog_loading_text_msg);

        //设置Dialog周围不变暗
        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = 0f;
        window.setAttributes(params);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        if (!TextUtils.isEmpty(msg)) {
            mMsgTextView.setText(msg);
        }

        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    public void setMsg(String msg) {
        this.msg = msg;
        if (!isShowing()) {
            show();
        }
        mMsgTextView.setText(msg);
    }
}