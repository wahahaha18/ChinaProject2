package com.ycl.car;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ycl.car.databinding.ActivityNextBinding;
import com.ycl.car.fragment.AlarmRealTimeFragment;
import com.ycl.car.fragment.CategoryNextFragment;
import com.ycl.car.fragment.EqControlNextInfoFragment;
import com.ycl.car.fragment.FormsFragment;
import com.ycl.car.fragment.HistoryAlarmFragment;
import com.ycl.car.fragment.LedgerFragment;
import com.ycl.car.fragment.MaintainEqFragment;
import com.ycl.car.fragment.MaintainInfoFragment;
import com.ycl.car.fragment.MaintainPlanFragment;
import com.ycl.car.fragment.QrCodeEqDetailFragment;
import com.ycl.car.fragment.SparePartLibraryFragment;
import com.ycl.car.fragment.TpmCheckFragment;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 管理二级内页
 */
public class Next1Activity extends AutoLayoutActivity {
    ActivityNextBinding binding;
    private static final String BUNDLE = "bundle";
    private static final String TITLE = "title";
    Bundle bundle;
    Fragment fragment;


    public static void start(Context context, Bundle bundle) {
        Intent starter = new Intent(context, Next1Activity.class);
        starter.putExtra(BUNDLE, bundle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getBundleExtra(BUNDLE);
        Log.d("NextActivity", "aaaa:" + bundle.getString("code", ""));
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
        Log.d("Next1Activity", ":" + bundle.getInt("position", 0));
        switch (bundle.getInt("position", 0)) {

            case 9:
                fragment = CategoryNextFragment.newInstance(bundle.getString(TITLE, TITLE));
                break;
            case 34:
                fragment = AlarmRealTimeFragment.newInstance(bundle);
                break;
            case 58:
                fragment = HistoryAlarmFragment.newInstance(bundle);
                break;
            case 36:
                fragment = MaintainInfoFragment.newInstance(bundle);
                break;
            case 60:
                fragment = MaintainEqFragment.newInstance(bundle);
                break;
            case 59:
                fragment = LedgerFragment.newInstance(bundle);
                break;
            case 37:
                fragment = SparePartLibraryFragment.newInstance(bundle);
                break;
            case 40:
                fragment = FormsFragment.newInstance(bundle);
                break;
            case 38:
                fragment = MaintainPlanFragment.newInstance(bundle);
                break;
            case 39:
                fragment = TpmCheckFragment.newInstance(bundle);
                break;
            case -1:
                fragment = QrCodeEqDetailFragment.newInstance(bundle);
                break;
            case 18:
                fragment = EqControlNextInfoFragment.newInstance(bundle);
                break;
            default:
                fragment = CategoryNextFragment.newInstance(bundle.getString(TITLE, TITLE));

        }

        addFragment(fragment, false);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        BusProvider.getInstance().post(new MessageEvent(""));
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


}
