package com.ycl.car.contract;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;

/**
 * Created by sun0002 on 2017/3/7.
 */

public interface FormsContrct {
    interface Presenter extends BasePresenter{
        void getData(String s);
    }
    interface View extends BaseView<Presenter>{
        void showLoading();
    }
}
