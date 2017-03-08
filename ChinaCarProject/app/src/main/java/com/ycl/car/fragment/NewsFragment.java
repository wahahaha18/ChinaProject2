package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycl.car.R;
import com.ycl.car.adapter.FragmentAdapter;
import com.ycl.car.databinding.FragmentFourBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻
 * <p>
 * Created by y11621546 on 2017/1/16.
 */

public class NewsFragment extends BasePageFragment {

    FragmentFourBinding binding;

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_four, container, false);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(NewsMainFragment.newInstance());
        fragmentList.add(NewsDynamicFragment.newInstance());
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        binding.pager.setAdapter(adapter);
        binding.tl1.setViewPager(binding.pager, new String[]{"研发中心要闻", "研发动态"});

        return binding.getRoot();
    }

    @Override
    public void fetchData() {
        System.out.println("NewsFragment.fetchData");
    }
}
