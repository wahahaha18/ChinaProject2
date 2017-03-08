package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ycl.car.DetailActivity;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.PostItemAdapter;
import com.ycl.car.databinding.FragmentPostBinding;
import com.ycl.car.databinding.FragmentPostDetailBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的帖子
 * Created by y11621546 on 2017/1/18.
 */

public class PostDetailFragment extends BasePageFragment {
    private FragmentPostDetailBinding binding;
    private static final String text = "中国汽车工业工程有限公司由原机械工业部第四,第五两个设计元合并而成.现为中国机械工业集团全资子公司";

    public static PostDetailFragment newInstance() {

        Bundle args = new Bundle();

        PostDetailFragment fragment = new PostDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_detail, container, false);
        binding.rvPost.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.rvPost.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPost.setNestedScrollingEnabled(false);
        binding.scrollView.smoothScrollTo(0, 0);
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("sadfsaf" + i);
        }
        PostItemAdapter adapter = new PostItemAdapter(list);
        binding.rvPost.setAdapter(adapter);
        binding.rvPost.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void fetchData() {
        System.out.println("MessageFragment.fetchData");
    }


}
