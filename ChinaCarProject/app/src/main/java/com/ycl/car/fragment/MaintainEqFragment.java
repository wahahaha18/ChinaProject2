package com.ycl.car.fragment;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.FragmentAdapter;
import com.ycl.car.databinding.FragmentMaintainEqBinding;
import com.ycl.car.databinding.FragmentMaintainInfoBinding;
import com.ycl.car.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备维修
 * Created by y11621546 on 2017/2/14.
 */

public class MaintainEqFragment extends BasePageFragment {
    private Bundle bundle;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<CustomTabEntity>();
    private SharedPreferences sharedPreferences;
    private LoginResponse.BBean userInfo;

    public static MaintainEqFragment newInstance(Bundle bundle) {
        MaintainEqFragment fragment = new MaintainEqFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
        if (!sharedPreferences.contains("user")) return;
        userInfo = JSON.parseObject(sharedPreferences.getString("user", "{}"), LoginResponse.BBean.class);
    }

    @Override
    public void fetchData() {

    }

    FragmentMaintainEqBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maintain_eq, container, false);
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(EqHandleNoFragment.newInstance(String.valueOf(userInfo.getId()), null, "0"));
        fragmentList.add(EqHandleFragment.newInstance(String.valueOf(userInfo.getId()), null, "1"));
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        binding.pager.setAdapter(adapter);
        binding.tl1.setViewPager(binding.pager, new String[]{"未处理", "已处理"});
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle == null) return;
        ((Next1Activity) getActivity()).getBinding().toolbarRight.setText("");
        ((Next1Activity) getActivity()).getBinding().toolbarTitle.setText(bundle.getString("title"));
    }

}
