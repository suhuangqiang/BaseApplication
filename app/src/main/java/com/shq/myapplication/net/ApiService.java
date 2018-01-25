package com.shq.myapplication.net;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {

    /**
     * 注册
     * @param mobile
     * @param password
     * @param code
     * @return
     */
//    @POST("auth/register")
//    Observable<BaseResult<LoginResult>> register(@Query("mobile") String mobile, @Query("password") String password, @Query("code") String code);

}
