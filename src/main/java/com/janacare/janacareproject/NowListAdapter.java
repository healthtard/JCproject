package com.janacare.janacareproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;

/**
 * Created by pradeep on 24/1/16.
 */
public class NowListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final int BLOG = 0, GLUCOSE_READING = 2;
    private Context context;
    ArrayList<Object> nowCards;


    public NowListAdapter(Context context,ArrayList<Object> nowCards) {
        this.nowCards = nowCards;
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        //return position % 2 * 2;
            if (nowCards.get(position) instanceof Blog)
                return BLOG;
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

   /* private void bindDataToAdapter() {
        // Bind adapter to recycler view object
        mNowListView.setAdapter(new NowListAdapter(getLocalActivity(), getSampleArrayList()));
    }*/

    private void configureViewHolder1(final ViewHolder1 vh1, final int position) {
            Blog blog = (Blog) nowCards.get(position);
            if (blog != null) {
                vh1.getTitle().setText(blog.title);
                vh1.getDescription().setText(blog.description);
                vh1.getBlog_image().setImageResource(blog.getImage_url());
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

    private class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout blog_layout;
        private ImageView blog_image;
        private TextView title;
        private TextView description;

        public ViewHolder1(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            blog_image = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            blog_layout = (LinearLayout) itemView.findViewById(R.id.blog_card);
        }

        public void onClick(View v) {
            context.startActivity(new Intent(context,BannerDetailActivity.class));
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



    private class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private GraphView graph;
        private LinearLayout glucase_level;

        public ViewHolder2(View itemView) {
            super(itemView);
            graph = (GraphView) itemView.findViewById(R.id.graph);
            glucase_level = (LinearLayout) itemView.findViewById(R.id.glucose_level);
            graph.setOnClickListener(this);
            glucase_level.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            context.startActivity(new Intent(context, BannerDetailActivity.class));
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