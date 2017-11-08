package com.unionman.latte.net;

import android.content.Context;

import com.unionman.latte.net.callback.IError;
import com.unionman.latte.net.callback.IFailure;
import com.unionman.latte.net.callback.IRequest;
import com.unionman.latte.net.callback.ISuccess;
import com.unionman.latte.net.callback.RequestCallbacks;
import com.unionman.latte.ui.LatteLoader;
import com.unionman.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by zyh on 2017/11/7.
 */

public class RestClient {

    private final String Url;
    private static WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.Url = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }



    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if(REQUEST != null){
            REQUEST.onRequestStart();
        }

        if(LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method){
            case GET:
                call = service.get(Url,PARAMS);
                break;
            case POST:
                call = service.post(Url,PARAMS);
                break;
            case PUT:
                call = service.put(Url,PARAMS);
                break;
            case DELETE:
                call = service.delete(Url,PARAMS);
                break;
            default:
                break;
        }

        if(call != null){
            call.enqueue(getRequsetCallbacks());
        }
    }

    private Callback<String> getRequsetCallbacks(){
        return new RequestCallbacks(REQUEST, SUCCESS,FAILURE,ERROR,LOADER_STYLE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
