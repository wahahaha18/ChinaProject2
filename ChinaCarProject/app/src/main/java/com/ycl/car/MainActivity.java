package com.ycl.car;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ycl.car.databinding.ActivityMainBinding;
import com.ycl.car.fragment.CommunityFragment;
import com.ycl.car.fragment.ControlFragment;
import com.ycl.car.fragment.EnterpriseFragment;
import com.ycl.car.fragment.FirstFragment;
import com.ycl.car.fragment.MyFragment;
import com.ycl.car.fragment.NewsFragment;
import com.ycl.car.model.TabEntity;
import com.ycl.car.model.ToolbarContent;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AutoLayoutActivity {
    private SharedPreferences sharedPreferences;
    private static MainActivity instance;
    ActivityMainBinding binding;
    private String[] mTitles_user = {"首页", "企业", "新闻", "社区", "管理", "我的"};
    private int[] mIconUnselectIds_user = {
            R.mipmap.shouye2_2x, R.mipmap.qiye2_2x, R.mipmap.xinwen2_2x, R.mipmap.shequ2_2x,
            R.mipmap.guanli2_2x, R.mipmap.wode2_2x};
    private int[] mIconSelectIds_user = {
            R.mipmap.shouye1_2x, R.mipmap.qiye1_2x, R.mipmap.xinwen1_2x, R.mipmap.shequ1_2x,
            R.mipmap.guanli1_2x, R.mipmap.wode1_2x};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<CustomTabEntity>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private onToolbarRightClickListenr onToolbarRightClickListenr;

    //toolbar右侧点击事件
    public void setOnToolbarRightClickListenr(MainActivity.onToolbarRightClickListenr onToolbarRightClickListenr) {
        this.onToolbarRightClickListenr = onToolbarRightClickListenr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        for (int i = 0; i < mTitles_user.length; i++) {
            mTabEntities.add(new TabEntity(mTitles_user[i], mIconSelectIds_user[i], mIconUnselectIds_user[i]));
        }
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
        initTab(0);
        fragmentList.add(FirstFragment.newInstance());
        fragmentList.add(EnterpriseFragment.newInstance());
        fragmentList.add(NewsFragment.newInstance());
        fragmentList.add(CommunityFragment.newInstance());
        fragmentList.add(ControlFragment.newInstance());
        fragmentList.add(MyFragment.newInstance());
//        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
//        binding.viewpager.setAdapter(adapter);
        binding.tlBottom.setTabData(mTabEntities);

        binding.toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onToolbarRightClickListenr != null) {
                    onToolbarRightClickListenr.onToolbarClick(v);
                }
            }
        });
        binding.tlBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                initTab(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", 9);
                NextActivity.start(MainActivity.this, bundle);

            }
        });
    }

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    //    初始化tab
    private void initTab(int index) {
//        binding.viewpager.setCurrentItem(index);
        ToolbarContent content = new ToolbarContent();

        switch (index) {
            case 0:
                content.title.set("中汽工程");
                Drawable drawable = getResources().getDrawable(R.mipmap.qiyewenhua1_2x);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                binding.toolbarTitle.setCompoundDrawables(drawable, null, null, null);
                binding.fab.setVisibility(View.GONE);
                addFragment(FirstFragment.newInstance(), false);
                break;
            case 1:
                content.title.set("企业");
                binding.toolbarTitle.setCompoundDrawables(null, null, null, null);
                binding.fab.setVisibility(View.GONE);
                addFragment(EnterpriseFragment.newInstance(), false);
                break;
            case 2:
                content.title.set("新闻");
                binding.toolbarTitle.setCompoundDrawables(null, null, null, null);
                binding.fab.setVisibility(View.GONE);
                addFragment(NewsFragment.newInstance(), false);
                break;
            case 3:
                content.title.set("社区");
                binding.toolbarTitle.setCompoundDrawables(null, null, null, null);
                binding.fab.setVisibility(View.VISIBLE);
                addFragment(CommunityFragment.newInstance(), false);
                break;
            case 4:
                content.title.set("管理");
                binding.toolbarTitle.setCompoundDrawables(null, null, null, null);
                binding.fab.setVisibility(View.GONE);
                addFragment(ControlFragment.newInstance(), false);
                break;
            case 5:
                content.title.set("");
                binding.fab.setVisibility(View.GONE);
                if (sharedPreferences.contains("user")) {
                    content.right.set("退出登录");
                } else {
                    content.right.set("管理登录");
                }
                binding.toolbarTitle.setCompoundDrawables(null, null, null, null);
                addFragment(MyFragment.newInstance(), false);
                break;
        }
        binding.setToolbar(content);
    }

    public ActivityMainBinding getBinding() {
        return binding;
    }

    public interface onToolbarRightClickListenr {
        void onToolbarClick(View view);
    }

    //添加fragment
    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        if (fragment == null) return;
        if (isAddToBackStack) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }

    }
}
