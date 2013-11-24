package com.sun.progressbutton;

import com.example.progressbutton.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ProgressButton extends ImageButton {

	private boolean _shouldDisplayLoadingAnimation = false;
	private Drawable _loadingAnimation;
	private TextPaint _textPaint;
	private Rect _textBounds;
	private String _defaultText;

	/**
	 * {@link ProgressButton} can be used to display a simple rotating {@link Drawable} to give the user 
	 * the effect of a loading button. The {@link Drawable} will be displayed once the user clicks the button and will have to be
	 * manually dismissed using the {@link #stopLoadingAnimation()} method.
	 * 
	 * @param context_
	 * 			the current {@link Context}
	 * @param attrs_
	 * 			the {@link AttributeSet} to retrieve data from compiled xml files
	 * @param defStyle_
	 * 			the default style to apply
	 */
	public ProgressButton(Context context_, AttributeSet attrs_, int defStyle_) {
		super(context_, attrs_, defStyle_);	
		
	    final TypedArray a = context_.obtainStyledAttributes(attrs_, R.styleable.ProgressButton,
	            R.attr.progressButtonStyle, R.style.ProgressButton_attrs);
		
	    this.setBackgroundColor(a.getInteger(R.styleable.ProgressButton_defaultBackgroundColor, Color.WHITE));
	    
		_loadingAnimation = getDrawable();
		_loadingAnimation.setAlpha(0);
		_defaultText = a.getString(R.styleable.ProgressButton_defaultText);
	    _textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
	    _textPaint.density = getResources().getDisplayMetrics().density;
	    
	    _textPaint.setColor(a.getInteger(R.styleable.ProgressButton_defaultFontColor, Color.BLACK));
	    _textPaint.setTextAlign(Align.CENTER);
	    _textPaint.setTextSize(a.getInteger(R.styleable.ProgressButton_defaultFontSize, 40));
	    _textPaint.setFakeBoldText(true);
	    _textBounds = new Rect();
	    
	    a.recycle();
	}

	public ProgressButton(Context context_, AttributeSet attrs_) {
		this(context_, attrs_, 0);
	}

	public ProgressButton(Context context_) {
		this(context_, null);
	}

	/**
	 * Ensures that the loading animation will be displayed when the user clicks the button
	 */
	@Override
	public boolean performClick() {
		boolean isClicked = super.performClick();
		if (isClicked) {
			_shouldDisplayLoadingAnimation = true;
			this.invalidate();
		}

		return isClicked;
	};


	public void startLoadingAnimation() {
		_shouldDisplayLoadingAnimation = true;
		this.invalidate();
	}

	public void stopLoadingAnimation() {
		_shouldDisplayLoadingAnimation = false;
		this.invalidate();
	}

	/**
	 * Display a loading animation if the user has clicked the button or hide it if {@link #stopLoadingAnimation()} 
	 * has been called. 
	 * 
	 */
	@Override
	protected void onDraw(Canvas canvas_) {
		if (_shouldDisplayLoadingAnimation) {
				shouldShowAnimation(true);
		} else {
			_textPaint.getTextBounds(_defaultText, 0, _defaultText.length(), _textBounds);
			canvas_.drawText( _defaultText , getWidth()/2, (getHeight()/2)+((_textBounds.bottom-_textBounds.top)/2) , _textPaint);
			shouldShowAnimation(false);
			_loadingAnimation.setVisible(false, false);
		}
		super.onDraw(canvas_);

	}

	/**
	 * Start or stop the current {@link Animatable} instance
	 * 
	 * @param shouldShow_
	 * 			to indicate whether {@link android.graphics.drawable.Animatable#stop()} or 
	 * 			{@link android.graphics.drawable.Animatable#start()} should be invoked on the current {@link Animatable} instance
	 */
	private void shouldShowAnimation(boolean shouldShow_) {
		if (_loadingAnimation instanceof Animatable) {
			if (shouldShow_) {
				_loadingAnimation.setAlpha(255);
				((Animatable) _loadingAnimation).start();
			} else {
				_loadingAnimation.setAlpha(0);
				((Animatable) _loadingAnimation).stop();
			}
		}
	}

}
