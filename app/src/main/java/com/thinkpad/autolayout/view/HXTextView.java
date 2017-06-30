package com.thinkpad.autolayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
/**
 * 带横线的TextView
 * @author lwx
 *
 */
public class HXTextView extends TextView{
	private Paint mPaint;
	private int width;
	private int height;
	public HXTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mPaint=new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		width=this.getWidth();
		height=this.getHeight();
		mPaint.setStrokeWidth(2);
		mPaint.setColor(Color.GRAY);
		mPaint.setAntiAlias(true);
		canvas.drawLine(0,height/2,width,height/2,mPaint);
	}
}
