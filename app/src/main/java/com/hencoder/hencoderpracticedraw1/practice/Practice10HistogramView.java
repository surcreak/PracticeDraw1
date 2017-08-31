package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Map;

public class Practice10HistogramView extends View {

    Point originalPoint = new Point(50, 450);
    Map<String, Float> data;

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        data = new ArrayMap<String, Float>();
        data.put("Froyo", Float.valueOf(0.02f));
        data.put("GB", Float.valueOf(0.08f));
        data.put("ICS", Float.valueOf(0.10f));
        data.put("JB", Float.valueOf(0.15f));
        data.put("KitKat", Float.valueOf(0.20f));
        data.put("L", Float.valueOf(0.30f));
        data.put("M", Float.valueOf(0.15f));

        int lengthX = 600;
        int lengthY = 450;
        int histogramWidth = 70;
        int histogramGap = 10;

        Paint axisPaint = new Paint();
        axisPaint.setColor(Color.WHITE);
        axisPaint.setStrokeWidth(2);
        canvas.drawLine(originalPoint.x, originalPoint.y, originalPoint.x+lengthX, originalPoint.y, axisPaint);
        canvas.drawLine(originalPoint.x, originalPoint.y, originalPoint.x, originalPoint.y - lengthY, axisPaint);

        Paint histogramPaint = new Paint();
        histogramPaint.setColor(Color.LTGRAY);
        histogramPaint.setStyle(Paint.Style.FILL);

        axisPaint.setTextSize(20);

        int i=0;
        for (Map.Entry<String, Float> entry : data.entrySet()) {
            Log.d("gaol", "key =" + entry.getKey() +"     value = " + entry.getValue());
            canvas.drawRect(originalPoint.x + histogramGap*(i+1) + histogramWidth*i,
                    originalPoint.y-lengthY*entry.getValue().floatValue()*2,
                    originalPoint.x + histogramGap*(i+1) + histogramWidth*(i+1),
                    originalPoint.y,
                    histogramPaint);
            canvas.drawText(entry.getKey(),
                    originalPoint.x + histogramGap*(i+1) + histogramWidth*i,
                    originalPoint.y + 20,
                    axisPaint);
            i++;
        }
    }
}
