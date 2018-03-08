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
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

public class LoaderTextView extends AppCompatTextView implements LoaderView {

    private static final int NO_PLACEHOLDER = -1;
    private LoaderController loaderController;
    private String placeholderText;

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
        typedArray.recycle();

        checkPlaceholderAttributes(attrs);
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
            rectPaint.setColor(LoaderConstant.COLOR_DARKER_GREY);
        } else {
            rectPaint.setColor(LoaderConstant.COLOR_DEFAULT_GREY);
        }
    }

    @Override
    public boolean valueSet() {
        return !TextUtils.isEmpty(getText());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        loaderController.removeAnimatorUpdateListener();
    }

    private void checkPlaceholderAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LoaderTextView, 0, 0);
        int placeholderTextRes =
                typedArray.getResourceId(R.styleable.LoaderTextView_placeholder_resource, NO_PLACEHOLDER);
        String stringArgument =
                typedArray.getNonResourceString(R.styleable.LoaderTextView_placeholder_string_argument);
        int intArgument =
                typedArray.getInt(R.styleable.LoaderTextView_placeholder_int_argument, NO_PLACEHOLDER);
        float floatPlaceholder = (float) NO_PLACEHOLDER;
        float floatArgument =
                typedArray.getFloat(R.styleable.LoaderTextView_placeholder_float_argument, floatPlaceholder);

        if (placeholderTextRes != NO_PLACEHOLDER) {
            Object argument = null;
            if (stringArgument != null) {
                argument = stringArgument;
            }
            else if (intArgument != NO_PLACEHOLDER) {
                argument = intArgument;
            }
            else if (floatArgument != floatPlaceholder) {
                argument = floatArgument;
            }

            placeholderText = getResources().getString(placeholderTextRes, argument);
            measurePlaceholderTextAndSetMinWidth();
        }

        typedArray.recycle();
    }

    private void measurePlaceholderTextAndSetMinWidth() {
        if (placeholderText == null) {
            setMinimumWidth(0);
            return;
        }

        float measuredWidth = getPaint().measureText(placeholderText);
        setMinimumWidth((int) measuredWidth);
    }

    /**
     * @return The text that is used to measure the required minimum width.
     */
    public String getPlaceholderText() {
        return placeholderText;
    }

    /**
     * Setting a placeholder text will not set any text on this {@code TextView}, but measure the minimum width
     * based on this text, making the shimmer effect as large as the {@code placeholderText} width.
     *
     * @param placeholderText A text that will most likely be the actual text of this {@code TextView} later, so the
     *                        shimmer effect has a width matching the text.
     */
    public void setPlaceholderText(final String placeholderText) {
        this.placeholderText = placeholderText;
        measurePlaceholderTextAndSetMinWidth();
    }
}
