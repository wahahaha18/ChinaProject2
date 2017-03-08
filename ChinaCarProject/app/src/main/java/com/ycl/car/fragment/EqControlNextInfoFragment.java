package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.FragmentAdapter;
import com.ycl.car.databinding.FragmentControlNextInfoBinding;
import com.ycl.car.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备管理Next
 * Created by y11621546 on 2017/2/23.
 */

public class EqControlNextInfoFragment extends BasePageFragment {
    private Bundle bundle;
    private FragmentControlNextInfoBinding binding;
    private List<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX.ChildsBean> childsBeanList;
    private String[] titles;

    public static EqControlNextInfoFragment newInstance(Bundle bundle) {
        EqControlNextInfoFragment fragment = new EqControlNextInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        childsBeanList = (List<LoginResponse.CBean.ChildsBeanXX.ChildsBeanX.ChildsBean>) bundle.getSerializable("childs");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_control_next_info, container, false);
        List<Fragment> fragmentList = new ArrayList<>();
        if (childsBeanList.isEmpty()) return binding.getRoot();
        titles = new String[childsBeanList.size()];
        for (int i = 0; i < childsBeanList.size(); i++) {
            titles[i] = childsBeanList.get(i).getName_();
            fragmentList.add(EqControlItemFragment.newInstance(childsBeanList.get(i).getParam()));
        }
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList);
        binding.vp.setAdapter(adapter);
        binding.tl.setViewPager(binding.vp, titles);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Next1Activity) getActivity()).getBinding().toolbarTitle.setText(bundle.getString("title"));
        ((Next1Activity) getActivity()).getBinding().toolbarRight.setText("");
    }
}
