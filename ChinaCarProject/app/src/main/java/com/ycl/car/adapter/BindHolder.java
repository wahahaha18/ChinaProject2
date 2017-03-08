package com.ycl.car.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.ycl.car.R;

/**
 * Created by y11621546 on 2017/2/18.
 */
public class BindHolder extends BaseViewHolder {
    public BindHolder(View view) {
        super(view);
    }

    public ViewDataBinding getBinding() {
        return (ViewDataBinding) getConvertView().getTag(R.id.BaseQuickAdapter_databinding_support);
    }
}
