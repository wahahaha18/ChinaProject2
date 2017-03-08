package com.ycl.car;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ycl.car.databinding.ActivityNextBinding;
import com.ycl.car.fragment.AboutFragment;
import com.ycl.car.fragment.CategoryFragment;
import com.ycl.car.fragment.CollectionFragment;
import com.ycl.car.fragment.LoginFragment;
import com.ycl.car.fragment.MessageFragment;
import com.ycl.car.fragment.PostFragment;
import com.ycl.car.fragment.PostInputFragment;
import com.ycl.car.fragment.SettingFragment;
import com.ycl.car.fragment.UserInfoFragment;
import com.zhy.autolayout.AutoLayoutActivity;

public class NextActivity extends AutoLayoutActivity {
    ActivityNextBinding binding;
    private static final String BUNDLE = "bundle";
    private static final String TITLE = "title";
    Bundle bundle;
    Fragment fragment;


    public static void start(Context context, Bundle bundle) {
        Intent starter = new Intent(context, NextActivity.class);
        starter.putExtra(BUNDLE, bundle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getBundleExtra(BUNDLE);
        Log.d("NextActivity", "aaaa:" + bundle.getInt("position", 0));
        if (bundle == null) return;
//        binding.toolbarTitle.setText(getIntent().getBundleExtra(BUNDLE).getString(TITLE, TITLE));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_next);
        binding.toolbar.setTitle("");
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationIcon(R.mipmap.fanhui1_2x);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getFragments().size() < 2) {
                    finish();
                } else {
                    onBackPressed();
                }
            }
        });
        switch (bundle.getInt("position", 0)) {
            case 0:
                fragment = UserInfoFragment.newInstance();
                break;
            case 1:
                fragment = PostFragment.newInstance();
                break;
            case 2:
                fragment = CollectionFragment.newInstance();
                break;
            case 3:
                fragment = MessageFragment.newInstance(bundle);
                break;
            case 5:
                fragment = AboutFragment.newInstance();
                break;
            case 6:
                fragment = SettingFragment.newInstance();
                break;
            case 7:
                fragment = LoginFragment.newInstance();
                break;
            case 8:
                fragment = CategoryFragment.newInstance(bundle);
                break;
            case 9:
                fragment = PostInputFragment.newInstance();
                break;
            default:
                fragment = LoginFragment.newInstance();

        }

        addFragment(fragment, false);

    }

    public ActivityNextBinding getBinding() {
        return binding;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getFragments().size() < 2) {
                finish();
            } else {
                onBackPressed();
            }
        }
        return true;
    }

    //添加fragment
    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        if (fragment == null) return;
        if (isAddToBackStack) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_next, fragment).addToBackStack("").commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_next, fragment).commit();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            bundle.putInt("position", -1);
            bundle.putString("code", scanResult.getContents());
            Next1Activity.start(NextActivity.this, bundle);
        }

    }
}
