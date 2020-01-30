package com.sdc.aisat.annajijo.lightup.light_upclasses;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdc.aisat.annajijo.lightup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {
    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_COLOR = "arg_color";
    private View mContent;
    private TextView mTextView;
    TextView profilename;
    TextView mailId;

    public SummaryFragment() {
        // Required empty public constructor
    }

    public static SummaryFragment newInstance(String text, int color) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        if(fm.getBackStackEntryCount()>0) {
            fm.popBackStack();
        }
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_summary, container, false);
        return  view;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
;
        // retrieve text and color from bundle or savedInstanceState
        // initialize views
        mContent = view.findViewById(R.id.summary_menu_fragment);
        // set text and background color
        profilename = (TextView)view.findViewById(R.id.profilename);
        mailId = (TextView)view.findViewById(R.id.mailid);
        profilename.setText(com.sdc.aisat.annajijo.lightup.StaticClass.personalRecord.getFirstName()+" "+com.sdc.aisat.annajijo.lightup.StaticClass.personalRecord.getLastName());
        mailId.setText(com.sdc.aisat.annajijo.lightup.StaticClass.personalRecord.getEmail());

        mContent.setBackground(getResources().getDrawable(R.drawable.b3));
        com.jjoe64.graphview.GraphView graph = (com.jjoe64.graphview.GraphView) view.findViewById(R.id.graph);
com.jjoe64.graphview.series.BarGraphSeries<com.jjoe64.graphview.series.DataPoint> series = new com.jjoe64.graphview.series.BarGraphSeries<com.jjoe64.graphview.series.DataPoint>(new com.jjoe64.graphview.series.DataPoint[] {
          new com.jjoe64.graphview.series.DataPoint(0, 1),
          new com.jjoe64.graphview.series.DataPoint(1, 5),
          new com.jjoe64.graphview.series.DataPoint(2, 3),
          new com.jjoe64.graphview.series.DataPoint(3, 2),
          new com.jjoe64.graphview.series.DataPoint(4, 6)
});
graph.addSeries(series);
com.jjoe64.graphview.series.LineGraphSeries<com.jjoe64.graphview.series.DataPoint> seriesline = new com.jjoe64.graphview.series.LineGraphSeries<com.jjoe64.graphview.series.DataPoint>(new com.jjoe64.graphview.series.DataPoint[] {
          new com.jjoe64.graphview.series.DataPoint(0, 1),
          new com.jjoe64.graphview.series.DataPoint(1, 5),
          new com.jjoe64.graphview.series.DataPoint(2, 3),
          new com.jjoe64.graphview.series.DataPoint(3, 2),
          new com.jjoe64.graphview.series.DataPoint(4, 6)
});
graph.addSeries(seriesline);


        // mContent.setBackgroundColor(mColor);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

}
