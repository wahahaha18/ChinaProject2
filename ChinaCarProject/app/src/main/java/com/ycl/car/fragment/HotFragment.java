package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.ycl.car.R;
import com.ycl.car.adapter.PostAdapter;
import com.ycl.car.contract.PostContract;
import com.ycl.car.databinding.FragmentHotBinding;
import com.ycl.car.databinding.FragmentNewestBinding;
import com.ycl.car.model.Post;
import com.ycl.car.presenter.PostPresenter;

import java.util.List;

/**
 * 热门贴
 * Created by y11621546 on 2017/1/18.
 */

public class HotFragment extends BasePageFragment implements PostContract.View {
    FragmentHotBinding binding;
    PostPresenter postPresenter;

    public static HotFragment newInstance() {

        Bundle args = new Bundle();

        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postPresenter = new PostPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hot, container, false);
        binding.rvNewsHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvNewsHot.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));


        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postPresenter.getPostInfo(1);
            }
        });
        return binding.getRoot();
    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void fetchData() {
        postPresenter.getPostInfo(1);
    }

    @Override
    public void showLoading() {
        binding.swipeRefresh.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onSuccess(final List<Post.BBean> list) {
        PostAdapter adapter = new PostAdapter(list);
        binding.rvNewsHot.setAdapter(adapter);
        binding.rvNewsHot.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_zan:
                        int zanNum = list.get(position).getZanNum();
                        list.get(position).setZan(!list.get(position).isZan());
                        list.get(position).setZanNum(list.get(position).isZan() ? ++zanNum : --zanNum);
                        adapter.notifyItemChanged(position);
                        break;
                    case R.id.tv_comment:
//                        Toast.makeText(getActivity(), "点击了回复", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


    @Override
    public void onError() {

    }

    @Override
    public void setPresenter(PostContract.Presenter presenter) {

    }
}
