package com.ycl.car.fragment;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.contract.LoginContract;
import com.ycl.car.databinding.FragmentLoginBinding;
import com.ycl.car.presenter.LoginPresenter;

/**
 * 登录
 * Created by y11621546 on 2017/1/18.
 */

public class LoginFragment extends BasePageFragment implements LoginContract.View {
    FragmentLoginBinding binding;
    LoginPresenter presenter;
    ProgressDialog progressDialog;
    private KProgressHUD kProgressHUD;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        presenter = new LoginPresenter(this);

        binding.tvPassForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NextActivity) getActivity()).addFragment(FindPassFragment.newInstance(), true);
            }
        });
        ((NextActivity) getActivity()).getBinding().toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(binding.getName(), binding.getPwd());
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NextActivity) getActivity()).getBinding().toolbarTitle.setText("管理登录");
        ((NextActivity) getActivity()).getBinding().toolbarRight.setText("登录");
    }

    @Override
    public void fetchData() {
        System.out.println("LoginFragment.fetchData");
    }

    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getContext())
                .setLabel("登录中...")
                .setAnimationSpeed(2)
                .setCancellable(false)
                .show();

    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }

    }

    @Override
    public void onSuccess() {
        getActivity().finish();
    }

    @Override
    public void onError() {

    }

    @Override
    public void showWarnMsg(String msg) {

        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}
