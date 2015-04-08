package com.ss.view;

import com.ss.membercenter.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomAlertNumberView extends View {
	
	private int mNum=0;//default num is 0
	private int mCircleColor=Color.GREEN;//default color is green
	
	private final static String TAG=CustomAlertNumberView.class.getSimpleName();
	
	public CustomAlertNumberView(Context context) {
		this(context,null,0);
	}

	public CustomAlertNumberView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public CustomAlertNumberView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.CustomAlertNumberView);
		mCircleColor=a.getInt(R.styleable.CustomAlertNumberView_circleColor, mCircleColor);
		mNum=a.getInteger(R.styleable.CustomAlertNumberView_number, mNum);
		a.recycle();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int height=getMeasuredHeight();
		int width=getMeasuredWidth();
		
		Log.i(TAG,"height"+height+"width:"+width);
		String text=String.valueOf(mNum);
		
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		//paint.setColor(mCircleColor);
		paint.setStrokeWidth(1.0f);
		
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(mCircleColor);
		
		//paint.getTextBounds(text,0,text.length(),textRect);
		
		int circleX=width/2;
		int circleY=height/2;
		
		int radius=Math.min(circleX, circleY);
		
		Paint backPaint=new Paint();
		backPaint.setColor(Color.WHITE);
		
		canvas.drawCircle(circleX,circleY, radius, paint);
		
		canvas.drawCircle(circleX, circleY, radius, backPaint);
		//将文本在圆心绘制
		
		TextPaint textP=new TextPaint();
		textP.setAntiAlias(true);
		textP.setColor(mCircleColor);
		textP.setTextSize(30f);
		
		Rect textRect=new Rect();
		
		textP.getTextBounds(text, 0, text.length(), textRect);
		
		float textX=circleX-textRect.width()/2;
		float textY=circleY+textRect.height()/2;
		
		canvas.drawText(text, textX, textY, textP);
		
	}
	
	public void set(int num){
		this.mNum=num;
		this.postInvalidate();
	}
	
	/**
	 * reset num=0
	 */
	public void clearTip(){
		this.mNum=0;
		this.postInvalidate();
	}
	
}
