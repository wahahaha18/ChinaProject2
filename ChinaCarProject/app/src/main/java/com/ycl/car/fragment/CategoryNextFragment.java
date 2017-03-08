package com.ycl.car.fragment;

import android.animation.Animator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.ycl.car.Next1Activity;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.CateAdapter;
import com.ycl.car.databinding.FragmentCateBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y11621546 on 2017/2/9.
 */

public class CategoryNextFragment extends BasePageFragment {
    FragmentCateBinding binding;
    private static final String TITLE = "title";
    private String title = TITLE;
    private CateAdapter adapter;

    public static CategoryNextFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(TITLE, title);
        CategoryNextFragment fragment = new CategoryNextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(TITLE, TITLE);
    }

    @Override
    public void fetchData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cate, container, false);
        binding.rvCate.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvCate.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        ((Next1Activity) getActivity()).getBinding().toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(getActivity()).initiateScan();
            }
        });
        binding.rvCate.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", 9);
                Next1Activity.start(getActivity(), bundle);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Next1Activity) getActivity()).getBinding().toolbarTitle.setText(title);
        ((Next1Activity) getActivity()).getBinding().toolbarRight.setText("");
        List<String> list = new ArrayList<>();
    }
}
