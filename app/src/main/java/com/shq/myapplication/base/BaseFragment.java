package com.shq.myapplication.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.shq.myapplication.R;
import com.shq.myapplication.rxbus.RxBus;
import com.shq.myapplication.utils.ImageUtil;
import com.shq.myapplication.utils.TUtil;
import com.shq.myapplication.view.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/18.
 */

public abstract class BaseFragment<P extends BasePresenter,M extends BaseModel> extends com.trello.rxlifecycle2.components.support.RxFragment implements BaseView {
    private View view;
    protected LoadingDialog dialog;
    public P presenter;
    public M model;

    public Context context;
    public ImageUtil imageUtil;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getContext();
        imageUtil = new ImageUtil(context);
        presenter = TUtil.getT(this,0);
        model = TUtil.getT(this,1);
        if(this instanceof BaseView) presenter.setVM(context,this,model);
        RxBus.get().register(this);
        initView();
        presenter.onStart();
        return view;
    }

    public abstract int getLayoutId();
    public abstract void initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
