package com.arvin.pmenu.frame;

import android.view.View;

import com.arvin.pmenu.R;
import com.arvin.pmenu.dialog.LoadingDialog;
import com.arvin.pmenu.response.callback.RequestCallback;

import cn.arvin.framework.activity.FrameActivity;
import cn.arvin.framework.core.net.RequestModel;
import cn.arvin.framework.core.net.RequestParams;
import cn.arvin.framework.core.net.RequestSender;


public abstract class CommonActivity extends FrameActivity {


    protected boolean isLoadErrorViewShowing;
    protected boolean isLoadingViewShowing;

    private LoadingDialog mLoadingDialog;

    protected RequestModel mRequest;


    @Override
    protected int getHeadViewId() {
        return 0;
    }

    @Override
    protected void onBodyOverlayShown(int overlayId, View overlayView) {
        switch (overlayId) {
            case R.layout.common_layout_loading:
                break;
            case R.layout.common_layout_load_error:
                getView(R.id.body_reload_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestSender.sendRequest(mRequest);
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 显示Loading界面
     */
    public void showLoadingView() {
        if (!isLoadingViewShowing) {
            showBodyOverlay(R.layout.common_layout_loading);
            isLoadingViewShowing = true;
        }
        isLoadErrorViewShowing = false;
    }

    /**
     * 显示加载失败界面
     */
    public void showLoadErrorView() {
        if (!isLoadErrorViewShowing) {
            showBodyOverlay(R.layout.common_layout_load_error);
            isLoadErrorViewShowing = true;
        }
        isLoadingViewShowing = false;
    }

    /**
     * 隐藏覆盖的界面
     */
    @Override
    protected void dismissBodyOverlay() {
        super.dismissBodyOverlay();
        isLoadingViewShowing = false;
        isLoadErrorViewShowing = false;
    }

    // --------------------------------------------------------------------------------

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dismissLoadingDialog();
    }

    /**
     * 显示正在加载的提示
     */
    public void showLoadingDialog(String msg) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.show();
        mLoadingDialog.setMsg(msg);
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 隐藏正在加载的提示
     */
    public void dismissLoadingDialog() {
        if (null != mLoadingDialog && mLoadingDialog.isShowing() == true) {
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBack();
    }

    protected void onBack() {
        CommonActivity.this.finish();
    }


    public void objectGetRequest(String url, Class<?> clazz, RequestCallback<?> callback) {
        objectGetRequest(url, null, clazz, callback);
    }

    public void objectGetRequest(String url, RequestParams paramMap, Class<?> clazz, RequestCallback<?> callback) {
        RequestSender.objectGetRequest(url, paramMap, clazz, callback);
    }


    public void objectPostRequest(String url, RequestParams paramMap, Class<?> clazz, RequestCallback<?> callback) {
        RequestSender.objectPostRequest(url, paramMap, clazz, callback);
    }

}
