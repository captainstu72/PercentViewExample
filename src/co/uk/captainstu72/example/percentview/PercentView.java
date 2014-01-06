package co.uk.captainstu72.example.percentview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

//modified version of the code provided on this SO thread.
//http://stackoverflow.com/questions/19731261/android-draw-circle-with-2-colors-pie-chart
//Answer originally by Sherif elKhatib.

public class PercentView extends View {
	
	final static String TAG = "PercentView";
    
    static Paint paint = new Paint();
    static Paint bgpaint = new Paint();
    static RectF rect = new RectF();
    static float percentage = 0;
    static int size = 0;
    static Boolean outlineOnly = false;
    static int width = 0;
    static int shortAxis = 0;
    static int longAxis = 0;
    static int left = 0;
    static int bgColour = 0;
    static int highColour = 0;
    static int medColour = 0;
    static int lowColour = 0;

    public PercentView (Context context) {
        super(context);
        init();
    }
    
    public PercentView (Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    public PercentView (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        
        bgpaint = new Paint();
        bgpaint.setAntiAlias(true);
        bgpaint.setStyle(Paint.Style.FILL);
        
        rect = new RectF();
    }
    
    //This is my edited version
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        //set out colours if they were not set by user
        if (bgColour == 0) {
        	bgColour = Color.parseColor("#ffe5e5e5");
        }        
        bgpaint.setColor(bgColour);
        if (highColour == 0) {
        	highColour = Color.parseColor("#ff57d78d");
        }
        if (medColour == 0) {
        	medColour = Color.parseColor("#fffcd12c");
        }
        if (lowColour == 0) {
        	lowColour = Color.parseColor("#fffb2a52");
        }   

        //work out what the long and short axis should be. This will be useful if the view is set to match
        //the size of a parent. This will help set the left so the circles are drawn in the middle
        if (getHeight() > getWidth()) {
            shortAxis = getWidth();
            longAxis = getHeight();
        } else {
            shortAxis = getHeight();
            longAxis = getWidth();
        }
        
        Log.i(TAG,"shortAxis: " + shortAxis + " / longAxis: " + longAxis);
        
        //get the left point - Take the short from the long and divide by 2. This will give an equal margin (hopefully)
        left = (longAxis - shortAxis) / 2;
        
        width = shortAxis;

        if (outlineOnly) {
        	drawOutlineMode(canvas);
        } else {
        	drawPieMode(canvas);
        }
        
    }
    
    private void drawOutlineMode(Canvas canvas) {
        //draw background circle anyway
        
        //make sure we have a size - This is the width of the percentage line.
        if (size == 0) {
            size = 10;
        }   
        
        Log.i(TAG,"left: " + left + "/ shortAxis: " + shortAxis + "/ longAxis: " + longAxis);
        
        
        int top = 0;
        
    	rect.set(left, top, left+width, top + width); 
        canvas.drawArc(rect, -90, 360, true, bgpaint); //and lets fill it right out to the edge.

        if(percentage != 0) {
            
        	int iPercentage = Float.valueOf(percentage * 100).intValue(); //(percentage * 100);
        	Log.i(TAG,"Percentage: " + iPercentage);
            if (iPercentage > 66)  {
                paint.setColor(highColour);        	
            } else if (iPercentage > 33) {
                paint.setColor(medColour);        	
            } else {
                paint.setColor(lowColour);        	
            }      
        	
            rect.set(left, top, left+width, top + width); 
            canvas.drawArc(rect, -90, (float) (360*percentage), true, paint);
        }
        
        left = left + size/2;
        top = size/2;
        width = width-size;
        rect.set(left, top, left+width, top + width); 
        canvas.drawArc(rect, -90, 360, true, bgpaint);
    }
    
    private void drawPieMode (Canvas canvas) {
	// This is the original SO answer with my edit.
    	
    	int iPercentage = Float.valueOf(percentage * 100).intValue(); //(percentage * 100);
    	Log.i(TAG,"Percentage: " + iPercentage);
        if (iPercentage > 66)  {
            paint.setColor(highColour);        	
        } else if (iPercentage > 33) {
            paint.setColor(medColour);        	
        } else {
            paint.setColor(lowColour);        	
        }    
        
		//draw background circle anyway
		int top = 0;
        rect.set(left, top, left+width, top + width); 
		canvas.drawArc(rect, -90, 360, true, bgpaint);
		if(percentage!=0) {
		    canvas.drawArc(rect, -90, (360*percentage), true, paint);
		}
    }   
     
    public void setPercentage(float percentage) {
        this.percentage = percentage / 100;
        invalidate();
    }
    
    public void setOutlineSize(int size) {
        this.size = size;
        invalidate();
    }
    
    public void setOutlineOnly(boolean outlineOnly) {
        this.outlineOnly = outlineOnly;
        invalidate();
    }
    
    public void setBackgroundColour(int bgColour) {
    	this.bgColour = bgColour;
        invalidate();
    }
    public void setHighColour(int highColour) {
    	this.highColour = highColour;
        invalidate();
    }
    public void setMedColour(int medColour) {
    	this.medColour = medColour;
        invalidate();
    }
    public void setLowColour(int lowColour) {
    	this.lowColour = lowColour;
        invalidate();
    }
}
