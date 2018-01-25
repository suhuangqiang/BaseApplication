package com.shq.myapplication.utils;

import java.lang.reflect.ParameterizedType;

/**
 * 泛型管理类
 * Created by hp on 2016/8/13.
 */
public class TUtil {
    /**
     * 返回泛型参数中实际实现类的对象
     * @param o
     * @param i
     * @param <T>
     * @return
     */
    public static <T> T getT(Object o, int i){
        try {
            return ((Class<T>)((ParameterizedType)(o.getClass()
                    //获得带有泛型的父类
                    .getGenericSuperclass()))
                    .getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据类名返回类
     * @param className
     * @return
     */
    public static Class<?> forName(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
