package com.shq.myapplication.net;

import android.accounts.NetworkErrorException;
import android.content.Context;


import com.shq.myapplication.App;
import com.shq.myapplication.base.BaseResult;
import com.shq.myapplication.base.BaseView;
import com.shq.myapplication.utils.T;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/8/26.
 */
public abstract class BaseObserver<M> implements Observer<BaseResult<M>> {
    /*protected Context mContext;
    private BaseView view;

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }
    public BaseObserver(BaseView baseView){
        view = baseView;
    }*/

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();

    }

    @Override
    public void onNext(BaseResult<M> tBaseEntity) {
        onRequestEnd();
        if (!tBaseEntity.isError()) {
            try {
                onSuccees(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        onComplete();
//        try {
//            onSuccees(tBaseEntity);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onError(Throwable e) {
//        Log.w(TAG, "onError: ", );这里可以打印错误信息
        e.printStackTrace();
        onRequestEnd();
        try {
            if (e instanceof ConnectException){
                T.show("连接错误");
            }else if (e instanceof TimeoutException){
                T.show("请求超时");
            }else if (e instanceof NetworkErrorException){
                T.show("请检查您网络");
            }else if (e instanceof UnknownHostException){
                if (NetWorkUtil.isNetConnected(App.getAppContext())){
                    T.show("请检查您网络");
                }else {
                    T.show("无法连接服务器");
                }
            }else {
                 onFailure(e, false);
            }
//            if (e instanceof ConnectException
//                    || e instanceof TimeoutException
//                    || e instanceof NetworkErrorException
//                    || e instanceof UnknownHostException) {
////                onFailure(e, true);
//                T.show("请检查您的网络");
//            }
        } catch (Exception e1) {
            T.show(e1.getMessage());
            e1.printStackTrace();
        }finally {
            onComplete();
        }

    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseResult<M> t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(BaseResult<M> t) throws Exception {
        T.show(t.getMsg());
    };


    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception{
        T.show(e.getMessage());
    };

    protected void onRequestStart() {
    }

    protected void onRequestEnd() {
    }



}
