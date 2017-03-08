package com.ycl.car.fragment;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ycl.car.MyApp;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.databinding.FragmentInfoBinding;
import com.ycl.car.databinding.FragmentNickBinding;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.view.SexDialog;

/**
 * 个人资料
 * Created by y11621546 on 2017/1/18.
 */

public class UserInfoFragment extends BasePageFragment implements View.OnClickListener, SexDialog.ResultCallback {
    FragmentInfoBinding binding;
    LoginResponse.BBean user;
    SharedPreferences sharedPreferences;

    public static UserInfoFragment newInstance() {

        Bundle args = new Bundle();

        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = MyApp.getInstance().getSharedPreferences();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        binding.llNick.setOnClickListener(this);
        binding.llCompanyName.setOnClickListener(this);
        binding.llSex.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NextActivity) getActivity()).getBinding().toolbarTitle.setText("个人资料");
        ((NextActivity) getActivity()).getBinding().toolbarRight.setText("");
        if (sharedPreferences.contains("user")) {
            user = JSON.parseObject(sharedPreferences.getString("user", ""), LoginResponse.BBean.class);
            Log.d("UserInfoFragment", user.toString());

        }
        binding.setUser(user);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        if (user == null) return;
        bundle.putParcelable("user", user);
        switch (v.getId()) {
            case R.id.ll_nick:
                bundle.putString("title", "昵称");
                ((NextActivity) getActivity()).addFragment(NickFragment.newInstance(bundle), true);
                break;
            case R.id.ll_company_name:
                bundle.putString("title", "公司名称");
                ((NextActivity) getActivity()).addFragment(NickFragment.newInstance(bundle), true);
                break;
            case R.id.ll_sex:
                SexDialog.newInstance(user, this).show(getFragmentManager(), "");

                break;
        }
    }

    @Override
    public void fetchData() {
        System.out.println("UserInfoFragment.fetchData");

    }

    @Override
    public void onSuccess() {
        if (sharedPreferences.contains("user")) {
            user = JSON.parseObject(sharedPreferences.getString("user", ""), LoginResponse.BBean.class);
            Log.d("SexDialog111", user.toString());

        }
        binding.setUser(user);
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "性别修改失败", Toast.LENGTH_SHORT).show();
    }
}
