package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ycl.car.DetailActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.TabAdapter;
import com.ycl.car.databinding.FragmentSecondBinding;
import com.ycl.car.model.TabItem;
import com.ycl.car.model.ToolbarContent;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业
 * <p>
 * Created by y11621546 on 2017/1/16.
 */

public class EnterpriseFragment extends BasePageFragment {
    ToolbarContent content = new ToolbarContent();
    FragmentSecondBinding binding;

    public static EnterpriseFragment newInstance() {

        Bundle args = new Bundle();

        EnterpriseFragment fragment = new EnterpriseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false);
        binding.rvSecond.setLayoutManager(new GridLayoutManager(getContext(), 2));
        final List<TabItem> tabItemList = new ArrayList<>();
        tabItemList.add(new TabItem("公司简介", R.mipmap.gongsijianjie1_3x));
        tabItemList.add(new TabItem("涂装工程院简介", R.mipmap.tuzhuanggongcheng1_3x));
        tabItemList.add(new TabItem("研发中心简介", R.mipmap.yanfazhongxin1_3x));
        tabItemList.add(new TabItem("企业文化", R.mipmap.qiyewenhua1_3x));
        tabItemList.add(new TabItem("业绩展示", R.mipmap.yejizhanshi1_3x));
        TabAdapter adapter = new TabAdapter(tabItemList);
        binding.rvSecond.setAdapter(adapter);
        binding.rvSecond.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title", tabItemList.get(position).getTitle());
                DetailActivity.start(getContext(), bundle);
            }
        });
        return binding.getRoot();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getBinding().toolbarTitle.setCompoundDrawables(null, null, null, null);
//        content.title.set("企业");
//        content.right.set("");
//        getBinding().setToolbar(content);
//    }

    @Override
    public void fetchData() {
        System.out.println("EnterpriseFragment.fetchData");
    }
}
