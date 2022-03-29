package com.duckbox.pequerisegura;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Anim_Card extends RecyclerView {



    /**
     * @author Leo on 2015/09/03
     */

        private boolean mScrollable;

        public Anim_Card(Context context) {
            this(context, null);
        }

        public Anim_Card(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public Anim_Card(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            mScrollable = false;
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            return !mScrollable || super.dispatchTouchEvent(ev);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            for (int i = 0; i < getChildCount(); i++) {
                animate(getChildAt(i), i);

                if (i == getChildCount() - 1) {
                    getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mScrollable = true;
                        }
                    }, i * 100);
                }
            }
        }

        public void animate(View view, final int pos) {
            view.animate().cancel();
            view.setTranslationY(200);
            view.setAlpha(0);
            view.animate().alpha(1.0f).translationY(0).setDuration(500).setStartDelay(pos * 70);
        }

    public void animate2(View view, final int pos) {

    }
    }

