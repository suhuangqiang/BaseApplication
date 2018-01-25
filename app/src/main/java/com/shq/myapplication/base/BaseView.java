package com.shq.myapplication.base;

/**
 * Created by hp on 2016/8/13.
 */
public interface BaseView {
    public abstract void showLoadingDialog(Boolean isCanClose);
    public abstract void hideLoadingDialog();
}
