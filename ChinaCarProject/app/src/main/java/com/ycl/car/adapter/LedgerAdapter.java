package com.ycl.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ycl.car.R;
import com.ycl.car.model.Ledger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun0002 on 2017/3/6.
 */

public class LedgerAdapter extends RecyclerView.Adapter <LedgerAdapter.LedgerViewHolder>{
    List<Ledger.BBean> list ;
    Context context;

    public LedgerAdapter(List<Ledger.BBean> list, Context context) {
        if (list.size() != 0 && list !=null ){
            this.list = list;
        }else {
            return;
        }

        this.context = context;
    }

    @Override
    public LedgerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_ledger,parent, false);
        LedgerViewHolder holder= new LedgerViewHolder(view);
        return holder;

    }
    public void clearData(){
        list.clear();
    }

    public void addData(List<Ledger.BBean> beanList){
        clearData();
        list.addAll(beanList);
        notifyDataSetChanged();
    }

    /**
     * {"eqno":"IM5","eqname":"名称15","eqspeci":"型号5","eqfactory":"厂家5","eqdepartment":"部门",
     * "installocation":"安装点","useless":"情况","eqtype":"类型","eqwksp":"车间","eqsystem":"系统2"}
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(LedgerViewHolder holder, int position) {
        Ledger.BBean bBean = list.get(position);

        if (bBean != null){
            holder.tv_bianhao.setText(bBean.getEqno());
            holder.tv_mingcheng.setText(bBean.getEqname());
            holder.tv_xinghao.setText(bBean.getEqspeci());
            holder.tv_changjia.setText(bBean.getEqfactory());
            holder.tv_bumen.setText(bBean.getEqdepartment());
            holder.tv_anzhuang.setText(bBean.getInstallocation());
            holder.tv_shiyong.setText(bBean.getUseless());
            holder.tv_leixing.setText(bBean.getEqtype());
            holder.tv_chejian.setText(bBean.getEqwksp());
            holder.tv_xitong.setText(bBean.getEqsystem());
        }else {
            Log.e("LedgerAdapter", "bBean为: "+bBean);
            return;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class LedgerViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_bianhao,tv_mingcheng,tv_changjia,tv_bumen,tv_anzhuang,tv_shiyong,tv_leixing,
                tv_chejian,tv_xitong,tv_xinghao;

        public LedgerViewHolder(View itemView) {
            super(itemView);
            tv_bianhao = (TextView) itemView.findViewById(R.id.tv_bianhao);
            tv_mingcheng = (TextView) itemView.findViewById(R.id.tv_mingcheng);
            tv_changjia = (TextView) itemView.findViewById(R.id.tv_changjia);
            tv_bumen = (TextView) itemView.findViewById(R.id.tv_bumen);
            tv_anzhuang = (TextView) itemView.findViewById(R.id.tv_anzhuang);
            tv_shiyong = (TextView) itemView.findViewById(R.id.tv_shiyong);
            tv_leixing = (TextView) itemView.findViewById(R.id.tv_leixing);
            tv_chejian = (TextView) itemView.findViewById(R.id.tv_chejian);
            tv_xitong = (TextView) itemView.findViewById(R.id.tv_xitong);
            tv_xinghao = (TextView) itemView.findViewById(R.id.tv_xinghao);


        }
    }
}
