package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.AlarmRealTimeAdapter;
import com.ycl.car.contract.AlarmRealTimeContract;
import com.ycl.car.databinding.FragmentAlarmRealTimeBinding;
import com.ycl.car.model.AlarmRealTime;
import com.ycl.car.model.C;
import com.ycl.car.model.D;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.presenter.AlarmRealTimePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 实时报警
 * Created by y11621546 on 2017/2/13.
 */

public class AlarmRealTimeFragment extends BasePageFragment implements AlarmRealTimeContract.View {
    private AlarmRealTimePresenter presenter;
    private KProgressHUD kProgressHUD;
    private Bundle bundle;
    private LoginResponse.BBean userInfo;
    private int level_id, zone_id;

    public static AlarmRealTimeFragment newInstance(Bundle bundle) {
        AlarmRealTimeFragment fragment = new AlarmRealTimeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private FragmentAlarmRealTimeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AlarmRealTimePresenter(this);
        bundle = getArguments();
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", "{}"), LoginResponse.BBean.class);
    }

    @Override
    public void fetchData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm_real_time, container, false);
        binding.rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.query(String.valueOf(userInfo.getId()), String.valueOf(level_id), String.valueOf(zone_id));
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getInfo(String.valueOf(userInfo.getId()));
        if (bundle == null) return;
        ((Next1Activity) getActivity()).getBinding().toolbarRight.setText("");
        ((Next1Activity) getActivity()).getBinding().toolbarTitle.setText(bundle.getString("title"));
    }

    @Override
    public void showLoading() {
        kProgressHUD = KProgressHUD.create(getContext())
                .setLabel("正在加载...")
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
    public void onSuccess(List<AlarmRealTime> alarmRealTimeList) {
        final AlarmRealTimeAdapter alarmRealTimeAdapter = new AlarmRealTimeAdapter(alarmRealTimeList);
        binding.rvAlarmReal.setAdapter(alarmRealTimeAdapter);
        binding.rvAlarmReal.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initSpinnerList(List<C> cList, List<D> dList) {
        List<String> cStringList = new ArrayList<>();
        List<String> dStringList = new ArrayList<>();
        final List<Integer> cIntList = new ArrayList<>();
        final List<Integer> dIntList = new ArrayList<>();

        for (C c : cList) {
            cStringList.add(c.getLname());
            cIntList.add(c.getId());
        }
        for (D d : dList) {
            dStringList.add(d.getZname());
            dIntList.add(d.getId());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, cStringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerArea.setAdapter(arrayAdapter);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, dStringList);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerLevel.setAdapter(arrayAdapter1);
        if (cIntList.isEmpty()) return;
        if (dIntList.isEmpty()) return;
        level_id = cIntList.get(binding.spinnerArea.getSelectedItemPosition());
        zone_id = dIntList.get(binding.spinnerLevel.getSelectedItemPosition());
        binding.spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                zone_id = dIntList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                level_id = cIntList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    public void onError() {

    }

    @Override
    public void setPresenter(AlarmRealTimeContract.Presenter presenter) {

    }
}
