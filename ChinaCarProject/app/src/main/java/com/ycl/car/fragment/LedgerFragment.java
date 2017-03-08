package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.adapter.LedgerAdapter;
import com.ycl.car.contract.LedgerContract;
import com.ycl.car.databinding.FragmentLedgerBinding;
import com.ycl.car.model.C;
import com.ycl.car.model.D;
import com.ycl.car.model.Ledger;
import com.ycl.car.presenter.LedgerPresenter;
import com.ycl.car.utils.HttpManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备台账
 * Created by y11621546 on 2017/3/4.
 */

public class LedgerFragment extends BasePageFragment implements LedgerContract.View {
    private FragmentLedgerBinding binding;
    private KProgressHUD kProgressHUD;
    private Bundle bundle;
    private int sPinnerInt1,sPinnerInt2;
    private int sPinnerInt3;

    private LedgerPresenter presenter;
    private int sPinnerInt2Position;
    private List<Ledger.DBean.SysBean> sys;
    private static String s2;
    private static String s3;
    private static String s1;
    private LedgerAdapter ledgerAdapter;

    public static LedgerFragment newInstance(Bundle bundle) {


        LedgerFragment fragment = new LedgerFragment();
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
        presenter = new LedgerPresenter(this);
        presenter.getLedgerList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ledger, container, false);

        binding.rvAlarmReal.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvAlarmReal.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        binding.tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("LedgerFragment", "onSuccess: "+"s1" +s1);

                if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2) && !TextUtils.isEmpty(s3)){
                    presenter.queryLedgerList(null,s1, s3, s2);
                } else {
                    Toast.makeText(getActivity(), "请全部选中", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void setPresenter(LedgerContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {
        if(kProgressHUD == null){
            kProgressHUD = KProgressHUD.create(getContext())
                    .setLabel("正在加载...")
                    .setCancellable(false)
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
    public void onError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onSuccess(final Ledger ledger) {

        ledgerAdapter = new LedgerAdapter(ledger.getB(),getContext());
        binding.rvAlarmReal.setAdapter(ledgerAdapter);

        List<String> cStringList = new ArrayList<>();
        List<String> dStringList = new ArrayList<>();
        final List<Integer> cIntList = new ArrayList<>();
        final List<Integer> dIntList = new ArrayList<>();


        for (Ledger.CBean cBean : ledger.getC()) {
            cStringList.add(cBean.getTpname());
            cIntList.add(cBean.getId());
        }
        Log.e("LedgerFragment", "onSuccess: "+"cStringList" +cStringList.size());

        final String[][] aa = new String[ledger.getD().size()][];
        for (int i = 0; i < ledger.getD().size(); i++) {
            Ledger.DBean dBean = ledger.getD().get(i);
            sys = dBean.getSys();
            dStringList.add(dBean.getWname());
            dIntList.add(dBean.getId());
            aa[i] = new String[sys.size()];
            for (int j = 0; j < sys.size(); j++) {
                aa[i][j] = sys.get(j).getSname();
                Log.e("LedgerFragment", "onSuccess:   "+"aa[i][j]" +aa[i][j]);
            }
        }

        if (cStringList != null){
            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, cStringList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner1.setAdapter(arrayAdapter);
        }else {
            Toast.makeText(getActivity(), "网络连接异常", Toast.LENGTH_SHORT).show();
        }

        if (dStringList != null){
            final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, dStringList);
            arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner2.setAdapter(arrayAdapter1);
        }else {
            Toast.makeText(getActivity(), "网络连接异常", Toast.LENGTH_SHORT).show();
        }
        if (aa != null){
            final int selectedItemPosition = binding.spinner2.getSelectedItemPosition();
            final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, aa[selectedItemPosition]);
            arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner3.setAdapter(arrayAdapter2);
        }else {
            Toast.makeText(getActivity(), "网络连接异常", Toast.LENGTH_SHORT).show();
        }


        sPinnerInt1 = cIntList.get(binding.spinner1.getSelectedItemPosition());
        s1 = String.valueOf(sPinnerInt1);
        sPinnerInt2 = dIntList.get(binding.spinner2.getSelectedItemPosition());
        s2 = String.valueOf(sPinnerInt2);
        sPinnerInt3 = ledger.getD().get(binding.spinner2.getSelectedItemPosition()).getSys().get(binding.spinner3.getSelectedItemPosition()).getEqwksp_id();
        s3 = String.valueOf(sPinnerInt3);
        Log.e("LedgerFragment", "onSuccess:   "+"s1" +sPinnerInt1+sPinnerInt2+sPinnerInt3);



        binding.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sPinnerInt1 = cIntList.get(i);
                s1 = String.valueOf(sPinnerInt1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sPinnerInt2Position = i;
                sPinnerInt2 = dIntList.get(i);

                s2 = String.valueOf(sPinnerInt2);

                Log.e("LedgerFragment", "onSuccess:   "+"sPinnerInt2    "+sPinnerInt2);
                final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getContext(), R.layout.adapter_mytopactionbar_spinner, aa[i]);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spinner3.setAdapter(arrayAdapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("LedgerFragment", "onSuccess:   "+"sPinnerInt3"+i);
                sPinnerInt3 = ledger.getD().get(sPinnerInt2Position).getSys().get(i).getEqwksp_id();
                s3 = String.valueOf(sPinnerInt3);
                Log.e("LedgerFragment", "onSuccess:   "+"sPinnerInt3"+aa[sPinnerInt2Position][i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//


    }

    @Override
    public void onSuccess1(Ledger ledger) {

        Log.e("LedgerFragment", "onSuccess:   "+ledger.getA());
        Log.e("LedgerFragment", "onSuccess:   "+ledger.getB().size());

        if (ledger.getB().size() == 0){
            ledgerAdapter.clearData();
            ledgerAdapter.notifyDataSetChanged();
        }else {
            ledgerAdapter.addData(ledger.getB());
        }

    }


}
