package com.example.administrator.demolistapplication.demomoduls.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.demolistapplication.R;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Administrator on 2018/2/22.
 */

public class StepView extends View {

    private int defaultNormalLineColor;
    private int defaultPassLineColor;
    private int defaultTextColor;
    private int defaultLineStikeWidth;
    private int defaultTextSize;
    private int defaultText2DotMargin;
    private int defalutMargin;
    private int defaultLine2TopMargin;
    private int defaultText2BottomMargin;
    private Bitmap normal_pic;
    private Bitmap target_pic;
    private Bitmap passed_pic;

    private int defaultDotCount ;
    private int dotcount;

    private int defaultStepNum ;
    private int stepNum ;

    private int defaultLineLength;
    private int lineLength;

    private int defaultTextLocation;
    private int maxDotCount;

    private int defaultMaxDotCount;
    private int textLocation;

    private boolean isTextBelowLine;

    private int normalLineColor;
    private int passLineColor;
    private int textColor;
    private float textSize;
    private float text2LineMargin;
    private int margin;
    private float line2TopMargin;
    private float text2BottomMargin;
    private boolean defaultViewClickable;
    private boolean clickable;
    private float line2BottomMargin;
    private float text2TopMargin;
    private Paint linePaint;
    private float lineStikeWidth;
    private Paint textPaint;
    private Rect bounds;
    private int width;
    private int height;
    private int perLineLength;
    private int[] passWH;
    private int[] normalWH;
    private int[] targetWH;


    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        defaultNormalLineColor = Color.parseColor("#545454");
        defaultPassLineColor =  Color.WHITE ;
        defaultTextColor = Color.WHITE;
        defaultLineStikeWidth = dp2px(context, 1);
        defaultTextSize = sp2px(context, 80);
        defaultText2DotMargin = dp2px(context, 15);
        defalutMargin = dp2px(context, 100);
        defaultLine2TopMargin = dp2px(context, 30);
        defaultText2BottomMargin = dp2px(context, 20);

        normal_pic = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_normal);
        target_pic = BitmapFactory.decodeResource(getResources(), R.mipmap.target_pic);
        passed_pic = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_passed);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.stepview, defStyleAttr, 0);
        dotcount = a.getInt(R.styleable.stepview_count  , defaultDotCount );
        if (dotcount < 2) {
            throw new IllegalArgumentException("Steps can't be less than 2");
        }
        stepNum = a.getInt(R.styleable.stepview_step, defaultStepNum);
        lineLength = a.getInt(R.styleable.stepview_line_length, defaultLineLength);
        maxDotCount = a.getInt(R.styleable.stepview_max_dot_count, defaultMaxDotCount);
        if (maxDotCount < dotcount ) {//当最多点小于设置点数量时，设置线条长度可变
            lineLength = defaultLineLength;
        }
        textLocation = a.getInt(R.styleable.stepview_text_location, defaultTextLocation);
        isTextBelowLine = textLocation == defaultTextLocation;

        normalLineColor = a.getColor(R.styleable.stepview_normal_line_color, defaultNormalLineColor);
        passLineColor = a.getColor(R.styleable.stepview_passed_line_color, defaultPassLineColor);
        textColor = a.getColor(R.styleable.stepview_text_color, defaultTextColor);
        textSize = a.getDimension(R.styleable.stepview_text_size , defaultTextSize);
        text2LineMargin = a.getDimension(R.styleable.stepview_text_to_line_margin, defaultText2DotMargin);
        margin = (int) a.getDimension(R.styleable.stepview_margin, defalutMargin);
        line2TopMargin = a.getDimension(R.styleable.stepview_line_to_top_margin, defaultLine2TopMargin);
        text2BottomMargin = a.getDimension(R.styleable.stepview_text_to_bottom_margin, defaultText2BottomMargin);
        clickable = a.getBoolean(R.styleable.stepview_is_view_clickable, defaultViewClickable);
        a.recycle();//todo
        //当文字在线条上面时，参数倒置
        if (!isTextBelowLine) {
            line2BottomMargin = line2TopMargin;
            text2TopMargin = text2BottomMargin;
        }
        //线条画笔
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(lineStikeWidth);
        //文字画笔
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        //存放说明文字的矩形
        bounds = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w - margin * 2;
        height = h;
        //线条长度是否可变
        if (lineLength == defaultLineLength) {//可变
            perLineLength = width / (dotcount - 1);
        } else {//固定
            perLineLength = width / (maxDotCount - 1);
        }
        passWH = calculateWidthAndHeight(passed_pic);
        normalWH = calculateWidthAndHeight(normal_pic);
        targetWH = calculateWidthAndHeight(target_pic);

    }



    /******************************************************************************************/

    private int dp2px(Context context, int value) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * value + 0.5f);
    }

    private int sp2px(Context context, int value) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / scaledDensity + 0.5f);
    }

    /*计算bitmap宽高*/
    private int[] calculateWidthAndHeight(Bitmap bitmap) {
        int[] wh = new int[2];
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        wh[0] = width;
        wh[1] = height;
        return wh;
    }





}
