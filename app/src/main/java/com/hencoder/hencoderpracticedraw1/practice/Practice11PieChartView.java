package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Practice11PieChartView extends View {

    Point originalPoint = new Point(400,250);
    int radius = 200;
    Map<String, Float> data;

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        data = new ArrayMap<String, Float>();
        data.put("Froyo", Float.valueOf(0.02f));
        data.put("GB", Float.valueOf(0.08f));
        data.put("ICS", Float.valueOf(0.10f));
        data.put("JB", Float.valueOf(0.15f));
        data.put("KitKat", Float.valueOf(0.20f));
        data.put("L", Float.valueOf(0.30f));
        data.put("M", Float.valueOf(0.15f));

        List<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.LTGRAY);
        colors.add(Color.GREEN);
        colors.add(Color.BLACK);
        colors.add(0XFFEE82EE);

        Paint circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);

        Paint tipPaint = new Paint();
        tipPaint.setStyle(Paint.Style.FILL);
        tipPaint.setStrokeWidth(2);
        tipPaint.setTextSize(20);
        tipPaint.setAntiAlias(true);
        tipPaint.setColor(Color.LTGRAY);

        float startAngle = 0;
        float sweepAngle = 0;
        float gapAngle = 2;
        float middleAngle = 0;
        int offset = 10;

        String selected = "L";

        int i = 0;
        for (Map.Entry<String, Float> entry : data.entrySet()) {
            circlePaint.setColor(colors.get(i));
            sweepAngle = entry.getValue().floatValue()*(360-gapAngle*data.size());
            middleAngle = startAngle+sweepAngle/2;
            int flag = (int) ((middleAngle/90)%4);
            boolean moveLeft = (flag==1 || flag==2) ? true : false;
            boolean moveup = (flag==2 || flag==3) ? true : false;

            int originalX = originalPoint.x;
            int originalY = originalPoint.y;
            if (selected.equals(entry.getKey())) {
                originalX += (moveLeft?-offset:offset);
                originalY += (moveup?-offset:offset);
            }

            canvas.drawArc(originalX - radius,
                    originalY - radius,
                    originalX + radius,
                    originalY + radius,
                    startAngle,
                    sweepAngle,
                    true,
                    circlePaint);

            // quadrent isn't match math quadrent.
            float middleEdgeX = (float) (originalX + (moveLeft?-1:1)*Math.abs(Math.cos(Math.toRadians(middleAngle))*radius));
            float middleEdgeY = (float) (originalY + (moveup?-1:1)*Math.abs(Math.sin(Math.toRadians(middleAngle))*radius));
            float middleEdgeExtendX = (float) (originalX + (moveLeft?-1:1)*Math.abs(Math.cos(Math.toRadians(middleAngle))*(radius+20)));
            float middleEdgeExtendY = (float) (originalY + (moveup?-1:1)*Math.abs(Math.sin(Math.toRadians(middleAngle))*(radius+20)));

            canvas.drawLine(middleEdgeX, middleEdgeY, middleEdgeExtendX, middleEdgeExtendY, tipPaint);
            canvas.drawLine(middleEdgeExtendX, middleEdgeExtendY, middleEdgeExtendX + (moveLeft?-50:50), middleEdgeExtendY, tipPaint);

            canvas.drawText(entry.getKey(),middleEdgeExtendX + (moveLeft?-60:50), middleEdgeExtendY, tipPaint);

            startAngle += sweepAngle + gapAngle;
            i++;
        }

    }
}
