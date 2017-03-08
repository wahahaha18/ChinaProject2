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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.AlarmRealTimeAdapter;
import com.ycl.car.adapter.PartLibraryAdapter;
import com.ycl.car.contract.AlarmRealTimeContract;
import com.ycl.car.contract.PartLibraryContract;
import com.ycl.car.databinding.FragmentAlarmRealTimeBinding;
import com.ycl.car.databinding.FragmentSpareLibraryBinding;
import com.ycl.car.model.AlarmRealTime;
import com.ycl.car.model.PartLibrary;
import com.ycl.car.presenter.AlarmRealTimePresenter;
import com.ycl.car.presenter.PartLibraryPresenter;

import java.util.Arrays;
import java.util.List;

/**
 * 实时报警
 * Created by y11621546 on 2017/2/13.
 */

public class SparePartLibraryFragment extends BasePageFragment implements PartLibraryContract.View {
    private static final String TAG = "SparePartLibraryFragment";
    private KProgressHUD kProgressHUD;

    private Bundle bundle;
    private PartLibraryPresenter presenter;

    public static SparePartLibraryFragment newInstance(Bundle bundle) {
        SparePartLibraryFragment fragment = new SparePartLibraryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private FragmentSpareLibraryBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PartLibraryPresenter(this);

        bundle = getArguments();
    }

    @Override
    public void fetchData() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_spare_library, container, false);
        binding.rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hint1 = binding.etNo.getText().toString();
                String hint2 = binding.etName.getText().toString();
                presenter.queryData(hint1, hint2);
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
        presenter.initData();
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
    public void onSuccess(List<PartLibrary> partLibraryList) {
        PartLibraryAdapter adapter = new PartLibraryAdapter(partLibraryList);
        binding.rvAlarmReal.setAdapter(adapter);
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PartLibraryContract.Presenter presenter) {

    }
}
