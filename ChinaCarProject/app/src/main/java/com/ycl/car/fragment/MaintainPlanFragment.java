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
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.MaintainPlanAdapter;
import com.ycl.car.adapter.PMAdapter;
import com.ycl.car.contract.MaintainPlanContract;
import com.ycl.car.model.PM;
import com.ycl.car.databinding.FragmentMaintainPlanBinding;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.MaintainPlan;
import com.ycl.car.presenter.MaintainPlanPresenter;

import java.util.List;

/**
 * PM 可行性维修计划
 * Created by y11621546 on 2017/2/15.
 */

public class MaintainPlanFragment extends BasePageFragment implements MaintainPlanContract.View {
    private KProgressHUD kProgressHUD;
    private MaintainPlanPresenter planPresenter;
    private Bundle bundle;
    private LoginResponse.BBean userInfo;
    private List<MaintainPlan> maintainPlanList;
    private List<PM> pmList;

    public static MaintainPlanFragment newInstance(Bundle bundle) {


        MaintainPlanFragment fragment = new MaintainPlanFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private FragmentMaintainPlanBinding binding;

    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", ""), LoginResponse.BBean.class);
        planPresenter = new MaintainPlanPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maintain_plan, container, false);
        binding.rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.rvAlarmReal.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (bundle.getInt("type", 0) == 1) {
                    Bundle bundle = new Bundle();
                    switch (view.getId()) {
                        case R.id.tv_note:
                            bundle.putString("pmid", String.valueOf(pmList.get(position).getId()));
                            ((Next1Activity) getActivity()).addFragment(PMRecordFragment.newInstance(bundle), true);
                            break;
                        case R.id.tv_deal:
                            bundle.putString("date", pmList.get(position).getPlandate());
                            bundle.putString("name", pmList.get(position).getEqname());
                            bundle.putString("content", pmList.get(position).getPmcontent());
                            bundle.putString("flag", "pm");
                            bundle.putString("id", String.valueOf(pmList.get(position).getId()));
                            ((Next1Activity) getActivity()).addFragment(HandleFragment.newInstance(bundle), true);
                            break;
                    }
                } else {
                    Bundle bundle = new Bundle();
                    switch (view.getId()) {
                        case R.id.tv_note:
                            bundle.putString("pmid", String.valueOf(maintainPlanList.get(position).getO().getPmid()));
                            ((Next1Activity) getActivity()).addFragment(PMRecordFragment.newInstance(bundle), true);
                            break;
                        case R.id.tv_deal:
                            bundle.putString("date", maintainPlanList.get(position).getO().getPlandate());
                            bundle.putString("name", maintainPlanList.get(position).getEquip().getEqname());
                            bundle.putString("content", maintainPlanList.get(position).getO().getPmcontent());
                            bundle.putString("flag", "pm");
                            bundle.putString("id", String.valueOf(maintainPlanList.get(position).getO().getPmid()));
                            ((Next1Activity) getActivity()).addFragment(HandleFragment.newInstance(bundle), true);
                            break;
                    }
                }

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle == null) return;
        ((Next1Activity) getActivity()).getBinding().toolbarRight.setText("");
        ((Next1Activity) getActivity()).getBinding().toolbarTitle.setText(bundle.getString("title"));
        if (bundle.getInt("type", 0) == 1) {
            planPresenter.initToDoList(String.valueOf(userInfo.getId()));
        } else {
            planPresenter.initData(String.valueOf(userInfo.getId()), bundle.getString("eqno", null));
        }

    }

    @Override
    public void showLoading() {
        if (kProgressHUD == null) {
            kProgressHUD = KProgressHUD.create(getContext())
                    .setLabel("正在加载...")
                    .show();
        }

    }

    @Override
    public void dismissLoading() {
        if (kProgressHUD.isShowing() && kProgressHUD != null) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void onSuccess(List<MaintainPlan> maintainPlanList) {
        this.maintainPlanList = maintainPlanList;
        MaintainPlanAdapter adapter = new MaintainPlanAdapter(maintainPlanList);
        binding.rvAlarmReal.setAdapter(adapter);
    }

    @Override
    public void onToDoSuccess(List<PM> pmList) {
        this.pmList = pmList;
        PMAdapter adapter = new PMAdapter(pmList);
        binding.rvAlarmReal.setAdapter(adapter);
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MaintainPlanContract.Presenter presenter) {

    }
}
