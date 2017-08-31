package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        Paint arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(200, 200, 400, 400, 180, 70, false, arcPaint);

        arcPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(200, 200, 400, 400, 270, 90, true, arcPaint);

        canvas.drawArc(200, 200, 400, 400, 40, 100, false, arcPaint);
    }
}
