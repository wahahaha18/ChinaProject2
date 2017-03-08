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
import com.ycl.car.model.ToolbarContent;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区
 * <p>
 * Created by y11621546 on 2017/1/16.
 */

public class CommunityFragment extends BasePageFragment {
    ToolbarContent content = new ToolbarContent();
    FragmentFourBinding binding;

    public static CommunityFragment newInstance() {

        Bundle args = new Bundle();

        CommunityFragment fragment = new CommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_four, container, false);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(NewestFragment.newInstance());
        fragmentList.add(HotFragment.newInstance());
        fragmentList.add(EssenceFragment.newInstance());
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        binding.pager.setAdapter(adapter);
        binding.tl1.setViewPager(binding.pager, new String[]{"最新帖", "热门帖", "精华帖"});


        return binding.getRoot();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getBinding().toolbarTitle.setCompoundDrawables(null, null, null, null);
//        content.title.set("社区");
//        content.right.set("");
//        getBinding().setToolbar(content);
//    }

    @Override
    public void fetchData() {
        System.out.println("CommunityFragment.fetchData");
    }
}
