package com.unionman.latte.net;

import android.content.Context;

import com.unionman.latte.net.callback.IError;
import com.unionman.latte.net.callback.IFailure;
import com.unionman.latte.net.callback.IRequest;
import com.unionman.latte.net.callback.ISuccess;
import com.unionman.latte.net.callback.RequestCallbacks;
import com.unionman.latte.net.download.DownloaderHander;
import com.unionman.latte.ui.LatteLoader;
import com.unionman.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by zyh on 2017/11/7.
 */

public class RestClient {

    private final String Url;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final Context CONTEXT;
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      String dir,
                      String extension,
                      String name,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      Context context,
                      File file,
                      LoaderStyle loaderStyle) {
        this.Url = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.DOWNLOAD_DIR = dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT = context;
        this.FILE = file;
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
            case POST_RAW:
                call = service.postRaw(Url,BODY);
                break;
            case PUT:
                call = service.put(Url,PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(Url,BODY);
                break;
            case DELETE:
                call = service.delete(Url,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file",FILE.getName());
                call = service.upload(Url,body);
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
        if(BODY == null){
            request(HttpMethod.POST);
        }else{
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put(){
        if(BODY == null){
            request(HttpMethod.PUT);
        }else{
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void upload(){
        request(HttpMethod.UPLOAD);
    }

    public final void download(){
        new DownloaderHander(Url,REQUEST,DOWNLOAD_DIR,
                EXTENSION,NAME,SUCCESS,FAILURE,ERROR).handleDownload();
    }
}
