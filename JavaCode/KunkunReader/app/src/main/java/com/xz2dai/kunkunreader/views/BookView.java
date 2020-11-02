package com.xz2dai.kunkunreader.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

class BookView extends View {
    int bookId;
    Bitmap bookImg;
    public BookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BookView(Context context) {
        super(context);
    }
}
