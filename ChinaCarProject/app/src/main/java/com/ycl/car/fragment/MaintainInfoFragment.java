package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.FragmentAdapter;
import com.ycl.car.databinding.FragmentMaintainInfoBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 维修资料
 * Created by y11621546 on 2017/2/14.
 */

public class MaintainInfoFragment extends BasePageFragment {
    private Bundle bundle;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<CustomTabEntity>();

    public static MaintainInfoFragment newInstance(Bundle bundle) {
        MaintainInfoFragment fragment = new MaintainInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Override
    public void fetchData() {

    }

    FragmentMaintainInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maintain_info, container, false);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(EquipmentFragment.newInstance());
        fragmentList.add(SparePartFragment.newInstance());
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        binding.pager.setAdapter(adapter);
        binding.tl1.setViewPager(binding.pager, new String[]{"设备资料", "备件资料"});
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle == null) return;
        ((Next1Activity) getActivity()).getBinding().toolbarRight.setText("");
        ((Next1Activity) getActivity()).getBinding().toolbarTitle.setText(bundle.getString("title"));
    }

}
