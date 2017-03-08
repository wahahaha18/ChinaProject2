package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.TpmAdapter;
import com.ycl.car.adapter.TpmCheckAdapter;
import com.ycl.car.contract.TpmCheckContract;
import com.ycl.car.databinding.FragmentTpmCheckBinding;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.TPM;
import com.ycl.car.model.TpmCheck;
import com.ycl.car.presenter.TpmCheckPresenter;

import java.util.List;

/**
 * Created by y11621546 on 2017/2/15.
 */

public class TpmCheckFragment extends BasePageFragment implements TpmCheckContract.View {
    public static TpmCheckFragment newInstance(Bundle bundle) {

        TpmCheckFragment fragment = new TpmCheckFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private FragmentTpmCheckBinding binding;
    private KProgressHUD kProgressHUD;
    private TpmCheckPresenter presenter;
    private Bundle bundle;
    private LoginResponse.BBean userInfo;
    private List<TpmCheck> tpmCheckList;
    private List<TPM> tpmList;

    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        presenter = new TpmCheckPresenter(this);
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", ""), LoginResponse.BBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle == null) return;
        ((Next1Activity) getActivity()).getBinding().toolbarRight.setText("");
        ((Next1Activity) getActivity()).getBinding().toolbarTitle.setText(bundle.getString("title"));
        if (bundle.getInt("type", 0) == 1) {
            presenter.initTPMAToDoList(String.valueOf(userInfo.getId()));
        } else {
            presenter.initData(String.valueOf(userInfo.getId()), bundle.getString("eqno", null));
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tpm_check, container, false);
        binding.rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.rvAlarmReal.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putString("id", String.valueOf(tpmCheckList.get(position).getTpmid()));
//                ((Next1Activity) getActivity()).addFragment(HandleFragment.newInstance(bundle), true);
            }
        });
        binding.rvAlarmReal.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (bundle.getInt("type", 0) == 1) {
                    Bundle bundle = new Bundle();
                    switch (view.getId()) {
                        case R.id.tv_note:
                            bundle.putString("pmid", String.valueOf(tpmList.get(position).getId()));
                            ((Next1Activity) getActivity()).addFragment(TPMRecordFragment.newInstance(bundle), true);
                            break;
                        case R.id.tv_deal:
                            bundle.putString("date", tpmList.get(position).getPlandate());
                            bundle.putString("content", tpmList.get(position).getWorkinfo());
                            bundle.putString("name", tpmList.get(position).getSystemname());
                            bundle.putString("flag", "tpm");
                            bundle.putString("id", String.valueOf(tpmList.get(position).getId()));
                            ((Next1Activity) getActivity()).addFragment(HandleFragment.newInstance(bundle), true);
                            break;
                    }
                } else {
                    Bundle bundle = new Bundle();
                    switch (view.getId()) {
                        case R.id.tv_note:
                            bundle.putString("pmid", String.valueOf(tpmCheckList.get(position).getTpmid()));
                            ((Next1Activity) getActivity()).addFragment(TPMRecordFragment.newInstance(bundle), true);
                            break;
                        case R.id.tv_deal:
                            bundle.putString("date", tpmCheckList.get(position).getPlandate());
                            bundle.putString("content", tpmCheckList.get(position).getWorkinfo());
                            bundle.putString("name", tpmCheckList.get(position).getSystemname());
                            bundle.putString("flag", "tpm");
                            bundle.putString("id", String.valueOf(tpmCheckList.get(position).getTpmid()));
                            ((Next1Activity) getActivity()).addFragment(HandleFragment.newInstance(bundle), true);
                            break;
                    }
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getContext())
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
    public void onSuccess(List<TpmCheck> tpmCheckList) {
        this.tpmCheckList = tpmCheckList;
        TpmCheckAdapter adapter = new TpmCheckAdapter(tpmCheckList);
        binding.rvAlarmReal.setAdapter(adapter);
    }

    @Override
    public void onToDoSuccess(List<TPM> tpmList) {
        this.tpmList = tpmList;
        TpmAdapter adapter = new TpmAdapter(tpmList);
        binding.rvAlarmReal.setAdapter(adapter);
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(TpmCheckContract.Presenter presenter) {

    }
}
