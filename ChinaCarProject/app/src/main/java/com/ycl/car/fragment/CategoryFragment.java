package com.ycl.car.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.CateAdapter;
import com.ycl.car.contract.EqControlListContract;
import com.ycl.car.databinding.FragmentCateBinding;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.presenter.EqControlListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理一级内页
 * Created by y11621546 on 2017/2/9.
 */

public class CategoryFragment extends BasePageFragment implements EqControlListContract.View {
    private EqControlListPresenter presenter;
    FragmentCateBinding binding;
    private static final String TITLE = "title";
    //    private String title = TITLE;
    private CateAdapter adapter;
    private KProgressHUD kProgressHUD;
    ArrayList<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX> list;
    private Bundle bundle;
    private LoginResponse.BBean userInfo;

    public static CategoryFragment newInstance(Bundle bundle) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        presenter = new EqControlListPresenter(this);
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", "{}"), LoginResponse.BBean.class);
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
        ((NextActivity) getActivity()).getBinding().toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(getActivity()).initiateScan();
//                Bundle bundle = new Bundle();
//                bundle.putInt("position", -1);
//                bundle.putString("code", "eq_01");
//                Next1Activity.start(getActivity(), bundle);
            }
        });
        binding.rvCate.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", list.get(position).getId());
                bundle.putString("title", list.get(position).getName_());
                bundle.putSerializable("childs", list.get(position).getChilds());
                Next1Activity.start(getActivity(), bundle);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NextActivity) getActivity()).getBinding().toolbarTitle.setText(bundle.getString("title"));
        if ("维修管理".equals(bundle.getString("title"))) {
            ((NextActivity) getActivity()).getBinding().toolbarRight.setText("扫一扫");
            list = bundle.getParcelableArrayList("child");
            adapter = new CateAdapter(list);
            binding.rvCate.setAdapter(adapter);
        } else if ("设备管理".equals(bundle.getString("title"))) {
            presenter.getData("sb", String.valueOf(userInfo.getId()));
            ((NextActivity) getActivity()).getBinding().toolbarRight.setText("");
        } else {
            ((NextActivity) getActivity()).getBinding().toolbarRight.setText("");
            list = bundle.getParcelableArrayList("child");
            adapter = new CateAdapter(list);
            binding.rvCate.setAdapter(adapter);
        }

    }


    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getActivity())
                .setLabel("正在加载...")
                .show();
    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void onSuccess(ArrayList<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX> childsBeanXList) {
        this.list = childsBeanXList;
        adapter = new CateAdapter(childsBeanXList);
        binding.rvCate.setAdapter(adapter);
    }


    public void onError() {

    }

    @Override
    public void setPresenter(EqControlListContract.Presenter presenter) {

    }
}
