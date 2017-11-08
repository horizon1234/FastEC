package com.unionman.latte.net;

import android.content.Context;

import com.unionman.latte.net.callback.IError;
import com.unionman.latte.net.callback.IFailure;
import com.unionman.latte.net.callback.IRequest;
import com.unionman.latte.net.callback.ISuccess;
import com.unionman.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by zyh on 2017/11/7.
 */

public class RestClientBuilder {

    private  String mUrl;
    private  static final Map<String,Object> PARAMS = RestCreator.getParams();
    private  IRequest mIRequest;
    private  ISuccess mISuccess;
    private  IFailure mIFailure;
    private  IError mIError;
    private  RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    RestClientBuilder(){

    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(Map<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError){
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallPulseIndicator;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIFailure,mIError,mBody,mContext,mLoaderStyle);
    }
}
