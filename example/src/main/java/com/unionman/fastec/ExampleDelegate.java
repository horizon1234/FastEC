package com.unionman.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.unionman.latte.delegates.LatteDelegate;
import com.unionman.latte.net.RestClient;
import com.unionman.latte.net.callback.IError;
import com.unionman.latte.net.callback.IFailure;
import com.unionman.latte.net.callback.ISuccess;

/**
 * Created by zyh on 2017/11/3.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
             .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i("sss",response);
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.i("sss","shibai");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.i("sss","error");
                    }
                })
                .build()
                .get();
    }
}
