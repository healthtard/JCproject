package com.janacare.janacareproject;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private JanaCareApplication app;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app = JanaCareApplication.getInstance();
        progressDialog = new Dialog(this, R.style.CustomDialogAnim);
        progressDialog.setContentView(R.layout.dialog_progress);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        NowListAdapter mNowAdapter = new NowListAdapter(this, getSampleArrayList());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        TextView mNoNow = (TextView) findViewById(R.id.now_text_view);
        RecyclerView mNowListView = (RecyclerView) findViewById(R.id.now_list_view);
        mNowListView.setLayoutManager(linearLayoutManager);
        mNowListView.setItemAnimator(new DefaultItemAnimator());
        mNowListView.setAdapter(mNowAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "All is well!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new Blog("Jana care introduction", "A revolutionizing way to reverse type 2 diabetes by lifestyle changes as guided by this smart app. Click to learn more!", R.drawable.janacare_lg, ""));
        items.add(new Blog("Measure blood glucose", "Check sugar levels in five minutes! Click to start", R.drawable.jc_intro, ""));
        items.add("glucoselevel");
        items.add(new Blog("Pedometer", "Check daily walk steps report, number of calories burnt and progress!", R.drawable.steps, ""));
        items.add("glucoselevel");
        items.add(new Blog("New life", "Mental well being life style for health and immunity.",R.drawable.wallpaper , ""));
        return items;
    }

    /**
     * creates and shows the progress dialog
     */
    public void showProgressDialog() {
        try {
            if (!progressDialog.isShowing() && !this.isFinishing()) {
                progressDialog.show();
            } else {
                progressDialog.dismiss();
                if (!isFinishing()) {
                    progressDialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dismiss the progress dialog if showing
     */
    public void dismissProgressDialog() {
        try {
            if (progressDialog.isShowing() && !this.isFinishing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JanaCareApplication getApp() {
        return JanaCareApplication.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
