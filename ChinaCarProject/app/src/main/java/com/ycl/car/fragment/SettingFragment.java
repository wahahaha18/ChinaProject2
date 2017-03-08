package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.databinding.FragmentInfoBinding;
import com.ycl.car.databinding.FragmentSettingBinding;
import com.ycl.car.utils.DataCleanManager;

/**
 * 个人资料
 * Created by y11621546 on 2017/1/18.
 */

public class SettingFragment extends BasePageFragment implements View.OnClickListener {
    FragmentSettingBinding binding;

    public static SettingFragment newInstance() {

        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        binding.llPassModify.setOnClickListener(this);
        binding.llClearCache.setOnClickListener(this);
        binding.llUpdateCheck.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NextActivity) getActivity()).getBinding().toolbarTitle.setText("设置");
        ((NextActivity) getActivity()).getBinding().toolbarRight.setText("");
        try {
            binding.setCache(String.valueOf(DataCleanManager.getTotalCacheSize(getContext())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_pass_modify:
                ((NextActivity) getActivity()).addFragment(ModifyPassFragment.newInstance(), true);
                break;
            case R.id.ll_clear_cache:
                DataCleanManager.clearAllCache(getContext());
                try {
                    binding.setCache(String.valueOf(DataCleanManager.getTotalCacheSize(getContext())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void fetchData() {
        System.out.println("SettingFragment.fetchData");
    }
}
