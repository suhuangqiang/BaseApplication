package com.shq.myapplication.base;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yukai on 2016/11/12.
 */

public class BaseResult<M> {


    private String msg;
    private int code;
    private boolean error;
    private long timestamp;
    private M data;

    public static BaseResult objectFromData(String str) {

        return new Gson().fromJson(str, BaseResult.class);
    }

    public static List<BaseResult> arrayBaseResultFromData(String str) {

        Type listType = new TypeToken<ArrayList<BaseResult>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getMsg() {
//        if (getCode() == 401){
//            //删除极光推送别名
//            int sequence = 1;
//            String alias = SpUtil.getUser().getId();
//            TagAliasOperatorHelper.TagAliasBean bean = new TagAliasOperatorHelper.TagAliasBean();
//            bean.setAliasAction(true);
//            bean.setAlias(alias);
//            bean.setAction(TagAliasOperatorHelper.ACTION_DELETE);
//            TagAliasOperatorHelper.getInstance().handleAction(APP.getAppContext(),sequence,bean);
//            SpUtil.setUser(null);
//            //通知推送
//            RxBus.get().send(EventCode.LOGIN_OUT);
//        }
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }
}
