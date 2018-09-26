package com.raduetsya.game2048;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by raduetsya on 3/18/14.
 */

public interface TileView {
    void draw( Canvas c, RectF rect, int rang, float howCloseToDissapear );
}