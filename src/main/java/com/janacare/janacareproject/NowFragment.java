package com.janacare.janacareproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;

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
            getData(false);
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
        totalNumberCards = 10;
        mNowAdapter = new NowListAdapter(getLocalActivity(), getSampleArrayList());
        linearLayoutManager=new LinearLayoutManager(getLocalActivity());
        mNowListView.setLayoutManager(linearLayoutManager);
        mNowListView.setItemAnimator(new DefaultItemAnimator());
        mNowListView.setAdapter(mNowAdapter);
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

    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Blog("Jana care introduction", "A revolutionizing way to reverse type 2 diabetes by lifestyle changes as guided by this smart app. Click to learn more!", "", ""));
        items.add(new Blog("Jana care introduction", "A revolutionizing way to reverse type 2 diabetes by lifestyle changes as guided by this smart app. Click to learn more!\", \"\", \"\"", "", ""));
        items.add("glucoselevel");
        items.add(new Blog("Jana care introduction", "A revolutionizing way to reverse type 2 diabetes by lifestyle changes as guided by this smart app. Click to learn more!\", \"\", \"\"", "", ""));
        items.add("glucoselevel");
        items.add(new Blog("Jana care introduction", "A revolutionizing way to reverse type 2 diabetes by lifestyle changes as guided by this smart app. Click to learn more!\", \"\", \"\"","", ""));
        return items;
    }


    private class NowListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        private final int BLOG = 0, GLUCOSE_READING = 1;
        private Context context;
        ArrayList<Object> nowCards;


        public NowListAdapter(Context context,ArrayList<Object> nowCards) {
            this.nowCards = nowCards;
            this.context=context;
        }

        @Override
        public int getItemViewType(int position) {
            if (nowCards.get(position) instanceof Blog)
                return BLOG;
            else if (nowCards.get(position) instanceof String)
                return GLUCOSE_READING;
            else
                return GLUCOSE_READING;
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getItemCount() {
            return this.nowCards.size();
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            switch (holder.getItemViewType()) {
                case BLOG:
                    ViewHolder1 vh1 = (ViewHolder1) holder;
                    configureViewHolder1(vh1, position);
                    break;

                case GLUCOSE_READING:
                    ViewHolder2 vh2 = (ViewHolder2) holder;
                    configureViewHolder2(vh2, position);
                    break;
            }
        }

        private void bindDataToAdapter() {
            // Bind adapter to recycler view object
            mNowListView.setAdapter(new NowListAdapter(getLocalActivity(), getSampleArrayList()));
        }

        private void configureViewHolder1(final ViewHolder1 vh1, final int position) {
            Blog blog = (Blog) nowCards.get(position);
            if (blog != null) {
                vh1.getTitle().setText(blog.title);
                vh1.getDescription().setText(blog.description);
                vh1.getBlog_image().setImageResource(0);
            }
        }

        private void configureViewHolder2(ViewHolder2 vh2, int position) {
            vh2.getGraph();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            switch (viewType) {
                case BLOG:
                    View v1 = inflater.inflate(R.layout.blog_card, parent, false);
                    viewHolder = new ViewHolder1(v1);
                    break;

                case GLUCOSE_READING:
                    View v2 = inflater.inflate(R.layout.glucose_level_graph_card, parent, false);
                    viewHolder = new ViewHolder2(v2);
                    break;
            }

            return viewHolder;
        }



        private class ViewHolder1 extends RecyclerView.ViewHolder {
            private LinearLayout blog_layout;
            private ImageView blog_image;
            private TextView title;
            private TextView description;

            public ViewHolder1(View itemView) {
                super(itemView);

                blog_image = (ImageView) itemView.findViewById(R.id.thumbnail);
                title = (TextView) itemView.findViewById(R.id.title);
                description = (TextView) itemView.findViewById(R.id.description);
                blog_layout = (LinearLayout) itemView.findViewById(R.id.blog_card);
            }

            public LinearLayout getBlog_layout() {
                return blog_layout;
            }

            public void setBlog_layout(LinearLayout blog_layout) {
                this.blog_layout = blog_layout;
            }

            public ImageView getBlog_image() {
                return blog_image;
            }

            public void setBlog_image(int blog_image) {
                //get image from server
            }

            public TextView getTitle() {
                return title;
            }

            public void setTitle(TextView title) {
                this.title = title;
            }

            public TextView getDescription() {
                return description;
            }

            public void setDescription(TextView description) {
                this.description = description;
            }
        }



        private class ViewHolder2 extends RecyclerView.ViewHolder
        {
            private GraphView graph;
            private LinearLayout glucase_level;

            public ViewHolder2(View itemView) {
                super(itemView);
                graph = (GraphView) itemView.findViewById(R.id.graph);
                glucase_level = (LinearLayout) itemView.findViewById(R.id.glucose_level);
            }

            public GraphView getGraph() {
                return graph;
            }

            public void setGraph(GraphView graph) {
                this.graph = graph;
            }

            public LinearLayout getGlucase_level() {
                return glucase_level;
            }

            public void setGlucase_level(LinearLayout glucase_level) {
                this.glucase_level = glucase_level;
            }
        }


    }
}

