package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.CollectionAdapter;
import com.ycl.car.databinding.FragmentCollectionBinding;
import com.ycl.car.model.Collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的收藏
 * Created by y11621546 on 2017/1/18.
 */

public class CollectionFragment extends BasePageFragment {
    private FragmentCollectionBinding binding;

    public static CollectionFragment newInstance() {

        Bundle args = new Bundle();

        CollectionFragment fragment = new CollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_collection, container, false);
        binding.rvCollection.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.rvCollection.setLayoutManager(new LinearLayoutManager(getContext()));
        final List<Collection> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new Collection("title" + i, "content"));
        }
        CollectionAdapter adapter = new CollectionAdapter(list);
        binding.rvCollection.setAdapter(adapter);
        binding.rvCollection.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                list.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NextActivity) getActivity()).getBinding().toolbarTitle.setText("我的收藏");
        ((NextActivity) getActivity()).getBinding().toolbarRight.setText("");
    }

    @Override
    public void fetchData() {
        System.out.println("MessageFragment.fetchData");
    }


}
