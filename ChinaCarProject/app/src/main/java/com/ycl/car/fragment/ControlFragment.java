package com.ycl.car.fragment;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ycl.car.ContantValue;
import com.ycl.car.MainActivity;
import com.ycl.car.MyApp;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.adapter.ControlAdapter;
import com.ycl.car.adapter.TabAdapter;
import com.ycl.car.databinding.FragmentThirdBinding;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.TabItem;

import java.util.ArrayList;
import java.util.List;


/**
 * 管理
 * <p>
 * Created by y11621546 on 2017/1/16.
 */

public class ControlFragment extends BasePageFragment {

    FragmentThirdBinding binding;

    private List<LoginResponse.CBean> cBeanList;

    public static ControlFragment newInstance() {
        Bundle args = new Bundle();
        ControlFragment fragment = new ControlFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false);
        binding.rvThird.setLayoutManager(new GridLayoutManager(getContext(), 2));
        final List<TabItem> tabItemList = new ArrayList<>();
        binding.rvThird.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", 8);
                bundle.putParcelableArrayList("child", cBeanList.get(0).getChilds().get(position).getChilds());
                bundle.putString("title", cBeanList.get(0).getChilds().get(position).getName_());
                NextActivity.start(getContext(), bundle);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void fetchData() {
        System.out.println("ControlFragment.fetchData");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("ControlFragment.onResume");
        getBinding().toolbarRight.setText("");
        getBinding().toolbarTitle.setText("管理");
        cBeanList = JSON.parseArray(MyApp.getInstance().getSharedPreferences().getString("control", "[]"), LoginResponse.CBean.class);
        if (cBeanList.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setTitle("请先登录")
                    .setCancelable(false)
                    .setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("position", 7);
                            bundle.putString(ContantValue.TOOLBAR_TITLE, "管理登录");
                            bundle.putString(ContantValue.TOOLBAR_RIGHT, "登录");
                            NextActivity.start(getContext(), bundle);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            builder.show();
            return;
        }
        ControlAdapter adapter = new ControlAdapter(cBeanList.get(0).getChilds());
        binding.rvThird.setAdapter(adapter);

    }
}
