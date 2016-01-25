package com.janacare.janacareproject;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pradeep on 17/1/16.
 */
public class NowFragment extends BaseFragment implements FragmentLifeCycle {
    private View rootView;
    private RecyclerView mNowListView;
    private NowListAdapter mNowAdapter;
    private TextView mNoNow;
    private int totalNumberCards = 0;
    private Dialog dialog=null;
    private LinearLayoutManager linearLayoutManager;
    private int index=0;
    private int top=0;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity!=null) {
            setLocalActivity((FragmentActivity)activity);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_now, container, false);
            init();
        } else {
            if (rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Method name", "On Resume Invoked");
    }

    @Override
    public void init() {
        initViews();
        initListeners();
        initObjects();
//        getData(false);

        if (totalNumberCards == 0) {
            mNoNow.setVisibility(View.VISIBLE);
        } else {
            mNoNow.setVisibility(View.GONE);
        }
    }

    @Override
    public void initViews() {
        mNoNow = (TextView) rootView.findViewById(R.id.now_text_view);
        mNowListView = (RecyclerView) rootView.findViewById(R.id.now_list_view);
    }

    @Override
    public void initListeners() {
    }

    @Override
    public void initObjects() {
    }

    @Override
    public void getData(boolean onRefresh) {
        getNowData();
    }

    private void getNowData() {
        ((MainActivity) getLocalActivity()).showProgressDialog();
        for (int i=0; i<10000; i++);
        ((MainActivity) getLocalActivity()).dismissProgressDialog();
    }

    @Override
    public void onPauseFragment() {
    }

    @Override
    public void onResumeFragment() {

    }


}

