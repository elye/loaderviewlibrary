package com.elyeproj.loaderviewlibrary;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

/*
 * Copyright 2016 Elye Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class LoaderController {

    private LoaderView loaderView;
    private Paint rectPaint;
    private float progress;
    private ValueAnimator valueAnimator;
    private float widthWeight = LoaderConstant.MAX_WEIGHT;
    private float heightWeight = LoaderConstant.MAX_WEIGHT;

    private final static int MAX_COLOR_CONSTANT_VALUE = 255;
    private final static int ANIMATION_CYCLE_DURATION = 750; //milis

    public LoaderController(LoaderView view) {
        loaderView = view;
        init();
    }

    private void init() {
        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        loaderView.setRectColor(rectPaint);
        setValueAnimator(0.5f, 1, ObjectAnimator.INFINITE);
    }

    public void onDraw(Canvas canvas) {
        float margin_height = canvas.getHeight() * (1-heightWeight)/2;
        rectPaint.setAlpha((int)(progress * MAX_COLOR_CONSTANT_VALUE));
        canvas.drawRect(0, margin_height, canvas.getWidth() * widthWeight, canvas.getHeight() - margin_height, rectPaint);
    }

    public void onSizeChanged() {
        if (valueAnimator != null && !loaderView.valueSet()) {
            valueAnimator.start();
        }
    }

    public void setHeightWeight(float heightWeight) {
        this.heightWeight = validateWeight(heightWeight);
    }

    public void setWidthWeight(float widthWeight) {
        this.widthWeight = validateWeight(widthWeight);
    }

    private float validateWeight(float weight) {
        if (weight > LoaderConstant.MAX_WEIGHT)
            return LoaderConstant.MAX_WEIGHT;
        if (weight < LoaderConstant.MIN_WEIGHT)
            return LoaderConstant.MIN_WEIGHT;
        return weight;
    }

    public void stopLoading() {
        valueAnimator.cancel();
        setValueAnimator(progress, 0, 0);
        valueAnimator.start();
    }

    private void setValueAnimator(float begin, float end, int repeatCount) {
        valueAnimator = ValueAnimator.ofFloat(begin, end);
        valueAnimator.setRepeatCount(repeatCount);
        valueAnimator.setDuration(ANIMATION_CYCLE_DURATION);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                loaderView.invalidate();
            }
        });
    }
}
