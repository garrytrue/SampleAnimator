package com.hiqes.sampleanimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SampleAnimatorActivity extends Activity implements ValueAnimator.AnimatorUpdateListener {
    private static final String         TAG = SampleAnimatorActivity.class.getName();

    private Button         mAnimLogButton;
    private ValueAnimator  mAnim;

    private ObjectAnimator mAnimFadeOut;
    private ObjectAnimator mAnimFadeIn;
    private Button         mAnimFadeButton;
    private TextView       mAnimFadeText;

    private Animator       mAnimFlip;
    private Button         mAnimFlipButton;
    private TextView       mAnimFlipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_animator);

        mAnimLogButton = (Button)findViewById(R.id.btn_anim_log);
        mAnim = ValueAnimator.ofFloat(0.0f, 1.0f);
        mAnim.setDuration(2000);
        mAnim.addUpdateListener(this);
        mAnimLogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAnim.start();
            }
        });

        mAnimFadeButton = (Button)findViewById(R.id.btn_anim_fade);
        mAnimFadeText = (TextView)findViewById(R.id.tv_fade_in_out);
        mAnimFadeOut =
                ObjectAnimator.ofFloat(mAnimFadeText, "alpha", 1.0f, 0.0f);
        mAnimFadeIn =
                ObjectAnimator.ofFloat(mAnimFadeText, "alpha", 0.0f, 1.0f);
        mAnimFadeOut.setDuration(1000);
        mAnimFadeIn.setDuration(1000);
        mAnimFadeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float curAlpha = mAnimFadeText.getAlpha();
                if (curAlpha > 0.5f) {
                    Log.d(TAG, "Starting fade OUT");
                    mAnimFadeOut.start();
                } else {
                    Log.d(TAG, "Starting fade IN");
                    mAnimFadeIn.start();
                }
            }
        });

        mAnimFlipButton = (Button)findViewById(R.id.btn_anim_flip);
        mAnimFlipText = (TextView)findViewById(R.id.tv_flip);
        mAnimFlip = AnimatorInflater.loadAnimator(this, R.animator.flip_over);
        mAnimFlip.setTarget(mAnimFlipText);
        mAnimFlipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimFlip.start();
            }
        });
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Log.d(TAG,
                "Update fraction: " +
                        animation.getAnimatedFraction() +
                        " value: " +
                        animation.getAnimatedValue());
    }
}
