package com.ycl.car.fragment;

import com.ycl.car.BasePresenter;
import com.ycl.car.BaseView;

/**
 * Created by y11621546 on 2017/2/23.
 */

public interface DrawContract {
    interface Presenter extends BasePresenter {
        void drawRect();

    }

    interface View extends BaseView<Presenter> {

    }

}
