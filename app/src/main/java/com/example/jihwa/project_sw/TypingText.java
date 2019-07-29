package com.example.jihwa.project_sw;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by YM on 2018-12-03.
 */

public class TypingText extends android.support.v7.widget.AppCompatTextView{

    private int typingSpeed =100;
    private int precision = 5;
    private String animateTextTyping = "";

    private Handler handler;
    private int counter=0;
    private boolean executed = false;
    private ScrollView scroll;

    public TypingText(Context context) {
        super(context);
    }

    public TypingText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AutoTypeTextView);
        try {
            animateTextTyping = ta.getString(R.styleable.AutoTypeTextView_animateTextTypeWithoutMistakes);
            typingSpeed = ta.getInt(R.styleable.AutoTypeTextView_typingSpeed, 100);
            precision = ta.getInt(R.styleable.AutoTypeTextView_typingPrecision, precision);
        } catch(Exception e) {

        }
        setupAttributes();
        ta.recycle();
    }

    public TypingText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AutoTypeTextView);
        try {
            animateTextTyping = ta.getString(R.styleable.AutoTypeTextView_animateTextTypeWithoutMistakes);
            typingSpeed = ta.getInt(R.styleable.AutoTypeTextView_typingSpeed, 100);
            precision = ta.getInt(R.styleable.AutoTypeTextView_typingPrecision, precision);
        } catch(Exception e) {

        }

        setupAttributes();
        ta.recycle();
    }

    private void setupAttributes() {
        if(animateTextTyping!=null)
            setTextAutoTyping(animateTextTyping, null);

    }

    public void setTextAutoTyping(final String text, final ScrollView scroll) {
        this.scroll=scroll;
        if(!executed) {
            executed = true;
            counter = 0;
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setText(text.substring(0, counter));
                    scroll.fullScroll(ScrollView.FOCUS_DOWN);
                    counter++;
                    if (text.length() >= counter) {
                        postDelayed(this, getTypingSpeed());
                    } else {
                        executed = false;
                    }
                }
            }, getTypingSpeed());
        }
    }

    public int getTypingSpeed() {
        return typingSpeed;
    }

    public void setTypingSpeed(int typingSpeed) {
        this.typingSpeed = typingSpeed;
    }

    public boolean isRunning() {
        return executed;
    }
}
