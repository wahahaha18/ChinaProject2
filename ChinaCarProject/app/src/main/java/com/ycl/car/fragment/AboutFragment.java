package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.MessageAdapter;
import com.ycl.car.contract.MessageContract;
import com.ycl.car.databinding.FragmentAboutBinding;
import com.ycl.car.databinding.FragmentMssageBinding;
import com.ycl.car.presenter.MessagePresenter;

import java.util.Arrays;

/**
 * 关于中汽
 * Created by y11621546 on 2017/1/18.
 */

public class AboutFragment extends BasePageFragment {
    private FragmentAboutBinding binding;
    private static final String text = "中国汽车工业工程有限公司由原机械工业部第四,第五两个设计元合并而成.现为中国机械工业集团全资子公司";

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);
        binding.setText(text);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NextActivity) getActivity()).getBinding().toolbarTitle.setText("关于中汽");
        ((NextActivity) getActivity()).getBinding().toolbarRight.setText("");
    }

    @Override
    public void fetchData() {
        System.out.println("MessageFragment.fetchData");
    }


}
