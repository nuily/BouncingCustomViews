package nyc.c4q.huilin.jj_1_09;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huilin on 1/9/17.
 */

public class BounceBounce extends View {
    private float radius;
    private float xSpeed;
    private float ySpeed;
    private float xDir = 1;
    private float yDir = 1;
    private String cirColor;
    private Paint dotPaint;

    public BounceBounce(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BounceBounce,
                0, 0);

        try {
            radius = a.getDimension(R.styleable.BounceBounce_radius, 50);
            xSpeed = a.getDimension(R.styleable.BounceBounce_xSpeed, 200);
            ySpeed = a.getDimension(R.styleable.BounceBounce_ySpeed, 100);
            cirColor = a.getString(R.styleable.BounceBounce_cirColor);
            if (cirColor == null)  {
                cirColor = "#FFF11223";
            }
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dotPaint.setColor(Color.parseColor(cirColor));
        dotPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(xSpeed, ySpeed, radius, dotPaint);
        if (xSpeed > this.getRight() - radius || xSpeed < this.getLeft() + radius) {
            xDir *= -1;
        }
        xSpeed += xSpeed * xDir;
        invalidate();
    }
}
