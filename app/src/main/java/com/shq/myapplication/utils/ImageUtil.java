package com.shq.myapplication.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shq.myapplication.R;


/**
 * Created by hp on 2016/8/21.
 */
public class ImageUtil {
    private Context mContext;
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    public ImageUtil(Context context) {
        this.mContext = context;
    }

    // 将资源ID转为Uri
    public Uri resourceIdToUri(int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + mContext.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    // 加载网络图片
    public void loadUrlImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.fail_img)
                .crossFade()
                .into(imageView);
    }
    public void loadUrlImage(String url, ImageView imageView, int defaultImg, int errorImg) {
        Glide.with(mContext)
                .load(url)
                .placeholder(defaultImg)
                .error(errorImg)
                .crossFade()
                .into(imageView);
    }

    //

    // 加载drawable图片
    public void loadResImage(int resId, ImageView imageView) {
        Glide.with(mContext)
                .load(resourceIdToUri(resId))
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.fail_img)
                .crossFade()
                .into(imageView);
    }

    // 加载本地图片
    public void loadLocalImage(String path, ImageView imageView) {
        Glide.with(mContext)
                .load("file://" + path)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.fail_img)
                .crossFade()
                .into(imageView);
    }
    // 加载本地图片
    public void loadLocalImage(Uri uri, ImageView imageView) {
        Glide.with(mContext)
                .load(uri)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.fail_img)
                .crossFade()
                .into(imageView);
    }

    // 加载网络圆型图片
    public void loadCircleImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.fail_img)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }
    public void loadRoundImage(String url, ImageView imageView, int radius) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.fail_img)
                .crossFade()
                .transform(new GlideRoundTransform(mContext,radius))
                .into(imageView);
    }
    public void loadCircleImage(String url, ImageView imageView, int defaultImg, int failImg) {
        Glide.with(mContext)
                .load(url)
                .placeholder(defaultImg)
                .error(failImg)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载drawable圆型图片
    public void loadCircleResImage(int resId, ImageView imageView) {
        Glide.with(mContext)
                .load(resourceIdToUri(resId))
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.fail_img)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载本地圆型图片
    public void loadCircleLocalImage(String path, ImageView imageView) {
        Glide.with(mContext)
                .load("file://" + path)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.fail_img)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
        //Glide.with(mContext).load(path).bitmapTransform(new RoundedCornersTransformation(this, 30, 0, RoundedCornersTransformation.CornerType.ALL)).crossFade(1000).into(imageView);
    }
    // 加载本地圆型图片
    public void loadCircleLocalImage(Uri uri, ImageView imageView) {
        Glide.with(mContext)
                .load(uri)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.fail_img)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

}
