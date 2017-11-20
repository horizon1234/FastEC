package com.unionman.fastec;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.unionman.latte.activities.ProxyActivity;
import com.unionman.latte.app.Latte;
import com.unionman.latte.delegates.LatteDelegate;
import com.unionman.latte_ec.Sign.SignInDelegate;
import com.unionman.latte_ec.Sign.SignUpDelegate;
import com.unionman.latte_ec.launcher.LauncherDelegate;
import com.unionman.latte_ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignInDelegate();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }
}
