package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.contract.EqInfoContract;
import com.ycl.car.databinding.FragmentEqInfoBinding;
import com.ycl.car.model.EqInfo;
import com.ycl.car.presenter.EqInfoPresenter;

import java.util.List;

/**
 * 设备详情
 * Created by y11621546 on 2017/2/16.
 */

public class EqInfoFragment extends BasePageFragment implements EqInfoContract.View {
    private EqInfoPresenter presenter;
    private Bundle bundle;
    private KProgressHUD kProgressHUD;

    public static EqInfoFragment newInstance(Bundle bundle) {

        EqInfoFragment fragment = new EqInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private FragmentEqInfoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EqInfoPresenter(this);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_eq_info, container, false);
        presenter.init(bundle.getString("eqno"));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Next1Activity) getActivity()).getBinding().toolbarRight.setText("");
        ((Next1Activity) getActivity()).getBinding().toolbarTitle.setText(bundle.getString("title"));
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getContext())
                .setLabel("正在加载...").show();
    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void onSuccess(EqInfo eqInfo) {
        binding.setEqinfo(eqInfo);
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(EqInfoContract.Presenter presenter) {

    }
}
