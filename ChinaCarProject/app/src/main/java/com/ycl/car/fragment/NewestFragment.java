package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ycl.car.DetailActivity;
import com.ycl.car.MainActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.PostAdapter;
import com.ycl.car.contract.PostContract;
import com.ycl.car.databinding.FragmentNewestBinding;
import com.ycl.car.model.Post;
import com.ycl.car.presenter.PostPresenter;

import java.util.List;

/**
 * 最新贴
 * Created by y11621546 on 2017/1/18.
 */

public class NewestFragment extends BasePageFragment implements PostContract.View {
    FragmentNewestBinding binding;
    PostPresenter postPresenter;
    List<Post.BBean> list;

    public static NewestFragment newInstance() {

        Bundle args = new Bundle();

        NewestFragment fragment = new NewestFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_newest, container, false);
        binding.rvNewsNewest.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvNewsNewest.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postPresenter.getPostInfo(0);
            }
        });
        binding.rvNewsNewest.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "帖子详情");
                DetailActivity.start(getContext(), bundle);
            }
        });
        binding.rvNewsNewest.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_zan:
                        if (list.isEmpty()) return;
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

        return binding.getRoot();
    }

    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override
    public void fetchData() {
        postPresenter.getPostInfo(0);
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
    public void onSuccess(List<Post.BBean> list) {
        this.list = list;
        PostAdapter adapter = new PostAdapter(list);
        binding.rvNewsNewest.setAdapter(adapter);

    }


    @Override
    public void onError() {

    }

    @Override
    public void setPresenter(PostContract.Presenter presenter) {

    }
}
