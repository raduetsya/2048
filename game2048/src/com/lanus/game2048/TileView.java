package com.lanus.game2048;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by lanus on 3/18/14.
 */

public interface TileView {
    void draw( Canvas c, RectF rect, int rang, float howCloseToDissapear );
}
