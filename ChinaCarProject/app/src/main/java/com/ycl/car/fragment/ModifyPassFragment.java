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
import com.ycl.car.databinding.FragmentPassFindBinding;
import com.ycl.car.databinding.FragmentPassModifyBinding;

/**
 * 修改密码
 * Created by y11621546 on 2017/1/18.
 */

public class ModifyPassFragment extends BasePageFragment {
    FragmentPassModifyBinding binding;

    public static ModifyPassFragment newInstance() {

        Bundle args = new Bundle();

        ModifyPassFragment fragment = new ModifyPassFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pass_modify, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NextActivity) getActivity()).getBinding().toolbarTitle.setText("修改密码");
        ((NextActivity) getActivity()).getBinding().toolbarRight.setText("确认");
    }

    @Override
    public void fetchData() {
        System.out.println("ModifyPassFragment.fetchData");
    }
}
