package com.shq.myapplication.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;


/**
 * Created by hp on 2016/8/13.
 */
public abstract class BasePresenter<M,V> {

    public Context context;
    public M model;
    public V view;
    protected Dialog mDialog;
    protected ProgressDialog progressDialog;


    public void setVM(Context context, V v, M m){
        this.context = context;
        this.view = v;
        this.model = m;
        this.onStart();
    }

    protected LifecycleProvider<ActivityEvent>
    getActivityLifecycleProvider() {

        LifecycleProvider<ActivityEvent> provider = null;
        if (null != context &&
                context instanceof LifecycleProvider) {
            provider =  (LifecycleProvider<ActivityEvent>)context;
        }
        return provider;
    }
    protected LifecycleProvider<FragmentEvent>
    getFragmentLifecycleProvider() {
        LifecycleProvider<FragmentEvent> provider = null;
        if (null != context &&
                context instanceof LifecycleProvider) {
            provider =  (LifecycleProvider<FragmentEvent>)context;
        }
        return provider;
    }


    public void onStart(){};

    public void onDestroy(){
        this.context = null;
    }

}
