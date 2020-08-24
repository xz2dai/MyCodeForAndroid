package com.xz2dai.kunkunreader.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
/**
@author yq
 @date 2020-8-24
 用于展示书架上书的自定义view
 **/
class BookView extends View {
    public BookView(Context context){   //new对象时调用
        super(context);
    }
    public BookView(Context context,AttributeSet attrs) {   //xml中调用使用
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
