package com.ycl.car.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ycl.car.ContantValue;
import com.ycl.car.MyApp;
import com.ycl.car.Next1Activity;
import com.ycl.car.NextActivity;
import com.ycl.car.R;
import com.ycl.car.contract.FirstContract;
import com.ycl.car.databinding.FragmentFirstBinding;
import com.ycl.car.model.DrawPic;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.Message;
import com.ycl.car.model.ToolbarContent;
import com.ycl.car.model.Weather;
import com.ycl.car.presenter.FirstPresenter;
import com.ycl.car.utils.HttpManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 首页
 * Created by y11621546 on 2017/1/16.
 */

public class FirstFragment extends BasePageFragment implements View.OnClickListener, FirstContract.View {
    FragmentFirstBinding binding;
    ToolbarContent content = new ToolbarContent();
    private LoginResponse.BBean userInfo;
    SharedPreferences sharedPreferences;
    Weather weather;
    private KProgressHUD kProgressHUD;
    private FirstPresenter firstPresenter;

    public static FirstFragment newInstance() {

        Bundle args = new Bundle();

        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = MyApp.getInstance().getSharedPreferences();
//        if (sharedPreferences.contains("user")) return;
        userInfo = JSON.parseObject(MyApp.getInstance().getSharedPreferences().getString("user", "{}"), LoginResponse.BBean.class);
        firstPresenter = new FirstPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false);
        binding.pic.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(getContext()).load(R.mipmap.scene).into(binding.pic);
        firstPresenter.getWeatherInfo();
        binding.getRoot().findViewById(R.id.layout_weather).findViewById(R.id.tv_more1).setOnClickListener(this);
        binding.getRoot().findViewById(R.id.layout_weather).findViewById(R.id.tv_more2).setOnClickListener(this);
        binding.getRoot().findViewById(R.id.layout_weather).findViewById(R.id.tv_more3).setOnClickListener(this);
        binding.getRoot().findViewById(R.id.layout_weather).findViewById(R.id.tv_more4).setOnClickListener(this);
        binding.getRoot().findViewById(R.id.layout_weather).findViewById(R.id.tv_more5).setOnClickListener(this);
        binding.getRoot().findViewById(R.id.layout_weather).findViewById(R.id.tv_more6).setOnClickListener(this);
        if (sharedPreferences.contains("user")) {
            firstPresenter.getNotificationInfo(String.valueOf(userInfo.getId()));
        }

        return binding.getRoot();
    }

    //    @Override
//    public void onResume() {
//        super.onResume();
//        Drawable drawable = getResources().getDrawable(R.mipmap.qiyewenhua1_2x);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        getBinding().toolbarTitle.setCompoundDrawables(drawable, null, null, null);
//        content.title.set("中汽工程");
//        content.right.set("");
//        getBinding().setToolbar(content);
//    }


    @Override
    public boolean prepareFetchData(boolean forceUpdate) {
        return super.prepareFetchData(true);
    }

    @Override

    public void fetchData() {

    }

    @Override
    public void onClick(View view) {
        if (!sharedPreferences.contains("user")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setTitle("请先登录")
                    .setCancelable(false)
                    .setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("position", 7);
                            bundle.putString(ContantValue.TOOLBAR_TITLE, "管理登录");
                            bundle.putString(ContantValue.TOOLBAR_RIGHT, "登录");
                            NextActivity.start(getContext(), bundle);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            builder.show();
            return;
        }
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_more1:
                bundle.putInt("position", 3);
                bundle.putInt("type", 1);
                NextActivity.start(getContext(), bundle);
                break;
            case R.id.tv_more2:
                bundle.putInt("position", 34);
                bundle.putString("title", "实时报警");
//                bundle.putInt("type", 1);
//                bundle.putSerializable("childs", list.get(position).getChilds());
                Next1Activity.start(getActivity(), bundle);
                break;
            case R.id.tv_more3:

                bundle.putInt("position", 38);
                bundle.putString("title", "PM预防性维修计划");
                bundle.putInt("type", 1);
//                bundle.putSerializable("childs", list.get(position).getChilds());
                Next1Activity.start(getActivity(), bundle);
                break;
            case R.id.tv_more4:

                bundle.putInt("position", 39);
                bundle.putString("title", "TPM点检");
                bundle.putInt("type", 1);
//                bundle.putSerializable("childs", list.get(position).getChilds());
                Next1Activity.start(getActivity(), bundle);
                break;
            case R.id.tv_more5:
                Toast.makeText(getActivity(), "维修信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more6:
                bundle.putInt("position", 3);
                bundle.putInt("type", 2);
                NextActivity.start(getContext(), bundle);
                break;
        }

    }

    @Override
    public void setPresenter(FirstContract.Presenter presenter) {

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
        if (kProgressHUD != null && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }
    }

    @Override
    public void onWeatherSucceess(Weather weather) {
        binding.setWeather(weather);
        TextView tvWeather = (TextView) binding.getRoot().findViewById(R.id.layout_weather).findViewById(R.id.tv_weather);
        if (weather.getWeather().contains("晴")) {
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_16);
            drawable.setBounds(0, 0, drawable.getMinimumWidth() / 2, drawable.getMinimumHeight() / 2);
            tvWeather.setCompoundDrawables(drawable, null, null, null);
        }
    }

    @Override
    public void onNotifiCationSucceess(Weather.WeatherBean weatherBean) {
        if (weather == null) {
            weather = new Weather();
        }
        weather.setWeatherBean(weatherBean);
        binding.setWeather(weather);


    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "数据加载失败,请稍候重试", Toast.LENGTH_SHORT).show();
    }
}
