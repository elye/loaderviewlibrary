package com.elyeproj.loaderviewlibrary;

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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

public class LoaderTextView extends AppCompatTextView implements LoaderView {

    private LoaderController loaderController;
    private int defaultColorResource;
    private int darkerColorResource;
    private int loaderGravity;

    public LoaderTextView(Context context) {
        super(context);
        init(null);
    }

    public LoaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoaderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        loaderController = new LoaderController(this);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.loader_view, 0, 0);
        loaderController.setWidthWeight(typedArray.getFloat(R.styleable.loader_view_width_weight, LoaderConstant.MAX_WEIGHT));
        loaderController.setHeightWeight(typedArray.getFloat(R.styleable.loader_view_height_weight, LoaderConstant.MAX_WEIGHT));
        loaderController.setUseGradient(typedArray.getBoolean(R.styleable.loader_view_use_gradient, LoaderConstant.USE_GRADIENT_DEFAULT));
        loaderController.setCorners(typedArray.getInt(R.styleable.loader_view_corners, LoaderConstant.CORNER_DEFAULT));
        defaultColorResource = typedArray.getColor(R.styleable.loader_view_custom_color, ContextCompat.getColor(getContext(), R.color.default_color));
        darkerColorResource = typedArray.getColor(R.styleable.loader_view_custom_color, ContextCompat.getColor(getContext(), R.color.darker_color));
        typedArray.recycle();
        loaderGravity = getGravity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) { // prefer text alignment when available.
            final int verticalGravity = loaderGravity & Gravity.VERTICAL_GRAVITY_MASK;
            switch (getTextAlignment()) {
                case android.view.View.TEXT_ALIGNMENT_CENTER:
                    loaderGravity = verticalGravity | Gravity.CENTER_HORIZONTAL;
                    break;
                case android.view.View.TEXT_ALIGNMENT_TEXT_END:
                case android.view.View.TEXT_ALIGNMENT_VIEW_END:
                    loaderGravity = verticalGravity | Gravity.END;
                    break;
                case android.view.View.TEXT_ALIGNMENT_TEXT_START:
                case android.view.View.TEXT_ALIGNMENT_VIEW_START:
                    loaderGravity = verticalGravity | Gravity.START;
                    break;
            }
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        loaderController.onSizeChanged();
    }

    public void resetLoader() {
        if (!TextUtils.isEmpty(getText())) {
            super.setText(null);
            loaderController.startLoading();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        loaderController.onDraw(canvas, getCompoundPaddingLeft(),
                getCompoundPaddingTop(),
                getCompoundPaddingRight(),
                getCompoundPaddingBottom());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (loaderController != null) {
            loaderController.stopLoading();
        }
    }

    @Override
    public void setRectColor(Paint rectPaint) {
        final Typeface typeface = getTypeface();
        if (typeface != null && typeface.getStyle()== Typeface.BOLD ) {
            rectPaint.setColor(darkerColorResource);
        } else {
            rectPaint.setColor(defaultColorResource);
        }
    }

    @Override
    public boolean valueSet() {
        return !TextUtils.isEmpty(getText());
    }

    @Override
    public int getLoaderGravity() {
        return loaderGravity;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        loaderController.removeAnimatorUpdateListener();
    }
}
