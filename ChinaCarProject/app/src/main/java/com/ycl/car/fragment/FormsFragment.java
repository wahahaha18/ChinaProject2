package com.ycl.car.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycl.car.Next1Activity;
import com.ycl.car.R;
import com.ycl.car.databinding.FragmentFormsBinding;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by sun0002 on 2017/3/7.
 */

public class FormsFragment extends BasePageFragment {
   private FragmentFormsBinding binding;
    private static final String TITLE = "title";
    private Bundle bundle;
    public final static String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun",};
//    @BindView(R.id.chart_top)
//    LineChartView chartTop;

    private LineChartData lineData;

    public static FormsFragment newInstance(Bundle bundle) {


        FormsFragment fragment = new FormsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void fetchData() {
        System.out.println("FormsFragment.fetchData");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_forms, container, false);
//
//
//        init1(view);
//        ButterKnife.bind(this, view);
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_forms,container,false);


        generateInitialLineData();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Next1Activity)getActivity()).getBinding().toolbarTitle.setText("报表");
        ((Next1Activity)getActivity()).getBinding().toolbarRight.setText("");
    }

    private void init1(View view) {

//        chartTop = (LineChartView) view.findViewById(R.id.chart_top);

    }
    private void generateInitialLineData() {
        int numValues = 7;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        List<AxisValue> axisValues1 = new ArrayList<AxisValue>();
        List<PointValue> values1 = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            values.add(new PointValue(i, (int) (Math.random()*100)));
            axisValues.add(new AxisValue(i).setLabel(days[i]));
        }
        for (int i = 0; i < numValues; ++i) {
            values1.add(new PointValue(i, 1+2));
            axisValues1.add(new AxisValue(i).setLabel(days[i]));
        }

        Line line = new Line(values);
        Line line1 = new Line(values1);
        line.setColor(ChartUtils.COLOR_GREEN).setCubic(true);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        lines.add(line1);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));

        binding.chartTop.setLineChartData(lineData);

        // For build-up animation you have to disable viewport recalculation.
        binding.chartTop.setViewportCalculationEnabled(false);

        // And set initial max viewport and current viewport- remember to set viewports after data.
        Viewport v = new Viewport(0, 110, 6, 0);
        binding.chartTop.setMaximumViewport(v);
        binding.chartTop.setCurrentViewport(v);

        binding.chartTop.setZoomType(ZoomType.HORIZONTAL);
    }

}
