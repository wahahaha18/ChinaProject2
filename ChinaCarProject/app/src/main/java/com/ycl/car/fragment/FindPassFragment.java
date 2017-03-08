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
import com.ycl.car.databinding.FragmentLoginBinding;
import com.ycl.car.databinding.FragmentPassFindBinding;

/**
 * 找回密码
 * Created by y11621546 on 2017/1/18.
 */

public class FindPassFragment extends BasePageFragment {
    FragmentPassFindBinding binding;

    public static FindPassFragment newInstance() {

        Bundle args = new Bundle();

        FindPassFragment fragment = new FindPassFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pass_find, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NextActivity) getActivity()).getBinding().toolbarTitle.setText("密码找回");
        ((NextActivity) getActivity()).getBinding().toolbarRight.setText("下一步");
    }

    @Override
    public void fetchData() {
        System.out.println("FindPassFragment.fetchData");
    }
}
