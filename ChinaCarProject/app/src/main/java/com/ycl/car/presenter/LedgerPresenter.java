package com.ycl.car.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ycl.car.contract.LedgerContract;
import com.ycl.car.model.AlarmRealTime;
import com.ycl.car.model.C;
import com.ycl.car.model.D;
import com.ycl.car.model.Ledger;
import com.ycl.car.utils.HttpManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by y11621546 on 2017/3/4.
 */

public class LedgerPresenter implements LedgerContract.Presenter {
    private final String TAG = LedgerPresenter.class.getName();
    private LedgerContract.View view;

    public LedgerPresenter(LedgerContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getLedgerList() {
        view.showLoading();
        HttpManager.getInstance().getHttpService().getLedgerInfo(null, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());


                        Ledger ledger=JSON.parseObject(jsonObject.toJSONString(),Ledger.class);
                        if (ledger.getA().equals("0")){
                            view.onSuccess(ledger);
                        }else {
                            String s = "网络请求异常";
                            view.onError(s);
                        }

//
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        String throwableS = throwable.toString();
                        view.dismissLoading();
                        view.onError(throwableS);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.dismissLoading();
                    }
                });
    }

    @Override
    public void queryLedgerList(String eqno, String eqtype_id, String eqwksp_id, String eqsystem_id) {
        view.showLoading();
        Log.d(TAG, "queryLedgerList() called with: eqno = [" + eqno + "], eqtype_id = [" + eqtype_id + "], eqwksp_id = [" + eqwksp_id + "], eqsystem_id = [" + eqsystem_id + "]");
        HttpManager.getInstance().getHttpService().getLedgerInfo(eqno, eqtype_id, eqwksp_id, eqsystem_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(responseBody.string());
                        Ledger ledger=JSON.parseObject(jsonObject.toJSONString(),Ledger.class);
//                        Log.e("LedgerPresenter", "getLedgerList: "+"ledger" +ledger);
                        if (ledger.getA().equals("0")){
                            view.onSuccess1(ledger);
                        }else {
                            String s = "网络请求异常";
                            view.onError(s);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        String throwableS = throwable.toString();
                        view.dismissLoading();
                        view.onError(throwableS);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.dismissLoading();
                    }
                });
    }
}
