package com.janacare.janacareproject;

/**
 * Created by pradeep on 17/1/16.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseFragment extends Fragment implements AppConstants {

    protected Context mContext;
    protected Activity mActivity;
    private RelativeLayout mLayout;
    private TextView mErrorMessage;
    private TextView mErrorIcon;
    private TextView mTryAgain;
    private CircleProgressBar mProgressBar;

    public BaseFragment() {
        super();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    // abstract to method to initialize the components in class
    public abstract void init();

    // abstract to method to initialize the views in class
    public abstract void initViews();

    // abstract to method to initialize the listeners in class
    public abstract void initListeners();

    // abstract to method to initialize the object in class
    public abstract void initObjects();

    /**
     * abstract method to call the API methods
     *
     * @param onRefresh - update operation on refresh
     */
    public abstract void getData(boolean onRefresh);

    /**
     * getting the application context
     *
     * @return
     */
    public JanaCareApplication getApp() {
        return ((MainActivity) getLocalActivity()).getApp();
    }

    // public abstract void updateFilter(int scrollState);

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
        this.mContext = mActivity.getApplicationContext();
    }

    private FragmentActivity mFragmentActivity;

    /**
     * This method is used to get object of parent activity in current fragment.
     * @return FragmentActivity
     */
    public FragmentActivity getLocalActivity() {
        if (mFragmentActivity != null) {
            return mFragmentActivity;
        } else {
            return getActivity();
        }

    }

    /**
     * This is setter method for variable mFragmentActivity which is available in BaseFragment.java to keep object reference of parent activity.
     * @param mFragmentActivity
     */
    public void setLocalActivity(FragmentActivity mFragmentActivity) {
        this.mFragmentActivity = mFragmentActivity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
        mContext = null;
    }


    /**
     * visible/hiding the views with animation
     *
     * @param view
     * @param visible
     */
    public void slideUpDown(final View view, int visible) {
        if (visible == View.VISIBLE) {
            TranslateAnimation animation = animateView(0, 0, 100f, 0f, 500, true, new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.clearAnimation();
                    view.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            view.startAnimation(animation);
        } else {
            TranslateAnimation animation = animateView(0, 0, 0f, 100f, 500, true, new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.clearAnimation();
                    view.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            view.startAnimation(animation);
        }
    }

    /**
     * Hiding the softkeyboard on view context
     *
     * @param editText
     */
    public void hideSoftKeyboard(EditText editText) {
        InputMethodManager imgr = (InputMethodManager) getLocalActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (editText!=null&&imgr!=null&&imgr.isActive(editText))
            imgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * Showing the softkeyboard on view context
     *
     * @param editText
     */
    public void showSoftKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getLocalActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }


    /**
     * translate view from one position to another position
     *
     * @param fromX     - start x
     * @param toX       - end x
     * @param fromY     - start y
     * @param toY       - end y
     * @param duration  - duration of animation
     * @param fillAfter
     * @return
     */
    public TranslateAnimation animateView(float fromX, float toX, float fromY, float toY, long duration, boolean fillAfter, Animation.AnimationListener listener) {
        TranslateAnimation translateAnimation = null;
        translateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
        translateAnimation.setDuration(duration);
        translateAnimation.setFillAfter(fillAfter);
        translateAnimation.setAnimationListener(listener);
        return translateAnimation;
    }

    /**
     * showing the progressbar
     *
     * @param rootView
     */
    public void showProgressBar(View rootView) {
        if(rootView!=null){
            if (mProgressBar == null) {
                mProgressBar = (CircleProgressBar) rootView.findViewById(R.id.progressBar);
            }
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * dismiss the progressbar
     */
    public void dismissProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

}
