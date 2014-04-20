package com.lanus.game2048;

import java.util.Map;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lanus.game2048.view.Animation;
//import com.lanus.game2048.view.Context;
import com.lanus.game2048.view.GameViewMetrics;
import com.lanus.game2048.view.GestureRecognizer;
import com.lanus.game2048.view.UIDraw;
import com.lanus.game2048.view.UISettings;

/**
 * Created by lanus on 3/14/14.
 */


public class GameView extends View implements com.lanus.game2048.view.Context {

    Handler animHandler = new Handler();
    final static int ANIM_FRAMERATE = 33; // 1000/30, 30fps
    
    Runnable animationInvalidator = new Runnable() {
        @Override
        public void run() {
            invalidate();
            animHandler.postDelayed(this, ANIM_FRAMERATE);
        }
    };

    UISettings uisett;
    GameGrid model;
    UIDraw uidraw;
    GameViewMetrics metrics;
    GestureRecognizer gest;
    Random rand = new Random();

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        uisett = new UISettings();
        model = new GameGrid(4,4);
        uidraw = new UIDraw(this);
        metrics = new GameViewMetrics(this);
        gest = new GestureRecognizer(this);
    }
    
    public boolean onUserAction(String action) {
    	if (action == "NEW_GAME") {
    		model.doNewGame(null);
    		return true;
    	}
    	return false;
    }
    
    public Map<String, String> saveState() {
		return null;
    }
    
    public void loadState(Map<String, String> state) {
    	
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        //canvas.drawColor( Color.rgb( rand.nextInt(255), rand.nextInt(255), rand.nextInt(255) ) );
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (model == null) return false;


        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }

        else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }

        else if (ev.getAction() == MotionEvent.ACTION_UP) {
            return true;
        }

        return super.onTouchEvent(ev);
    }

	@Override
	public GameViewMetrics getMetrics() {
		return metrics;
	}

	@Override
	public GestureRecognizer getGest() {
		return gest;
	}

	@Override
	public UISettings getUiSettings() {
		return uisett;
	}

	@Override
	public GameGrid getModel() {
		return model;
	}

	@Override
	public void startAnim(Animation anim) {
		// TODO Auto-generated method stub
		
	}

}
