package com.shq.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.shq.myapplication.R;
import com.shq.myapplication.rxbus.RxBus;
import com.shq.myapplication.utils.ImageUtil;
import com.shq.myapplication.utils.StatusBarLightMode;
import com.shq.myapplication.utils.StatusBarUtil;
import com.shq.myapplication.utils.TUtil;
import com.shq.myapplication.view.LoadingDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/6/14.
 */

public abstract class BaseActivity<P extends BasePresenter,M extends BaseModel> extends RxAppCompatActivity implements BaseView {
    public P presenter;
    public M model;
    public Context context;
    protected LoadingDialog dialog;
    public ImageUtil imageUtil;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = TUtil.getT(this,0);
        model = TUtil.getT(this,1);
        context = this;
        imageUtil = new ImageUtil(context);
        if(this instanceof BaseView) {
            presenter.setVM(context,this,model);

        }
        RxBus.get().register(this);
        StatusBarLightMode.StatusBarLightMode(this);
        StatusBarUtil.setColor(this, ContextCompat.getColor(context, R.color.colorPrimary),0);
        initView();
    }
    public abstract int getLayoutId();
    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        RxBus.get().unRegister(this);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void showLoadingDialog(Boolean isCanClose) {

        if (dialog == null){
            dialog = new LoadingDialog(context, R.style.dialogBase);
            dialog.setCancelable(isCanClose);
            dialog.setTipText("加载中");
        }
        if (!dialog.isShowing()){
            dialog.show();
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

}
