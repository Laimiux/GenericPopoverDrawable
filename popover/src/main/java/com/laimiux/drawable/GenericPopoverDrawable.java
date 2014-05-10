package com.laimiux.drawable;

/**
 * Created by laimonasturauskas on 5/9/14.
 */
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by laimonasturauskas on 5/7/14.
 */
public class GenericPopoverDrawable extends Drawable {
    private static final int STROKE_WIDTH = 2; // In pixels
    private int mDrawableWidth = -1;
    private int mDrawableHeight = -1;

    final private int mBackgroundColor, mBorderColor;


    public GenericPopoverDrawable(int backgroundColor, int borderColor) {
        mBackgroundColor = backgroundColor;
        mBorderColor = borderColor;
    }

    @Override
    public void draw(Canvas canvas) {
        int mArrowSize = getIntrinsicHeight() / 6;

        Rect bounds = getBounds();

        Rect backgroundRect = new Rect(bounds);
        backgroundRect.bottom -= mArrowSize;

        // Draw background
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(mBackgroundColor);
        canvas.drawRect(backgroundRect, paint);

        int centerX = (int) (bounds.left + getIntrinsicWidth() / 2f);
        int halfArrowSize = (int) (mArrowSize / 2f);

        Point leftTrianglePoint = new Point(centerX - halfArrowSize, backgroundRect.bottom);
        Point bottomTrianglePoint = new Point(centerX, backgroundRect.bottom + mArrowSize);
        Point rightTrianglePoint = new Point(centerX + halfArrowSize, backgroundRect.bottom);

        // Draw triangle background
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(leftTrianglePoint.x, leftTrianglePoint.y);
        path.lineTo(bottomTrianglePoint.x, bottomTrianglePoint.y);
        path.lineTo(rightTrianglePoint.x, rightTrianglePoint.y);

        paint.setColor(mBackgroundColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);

        // Draw the border
        int borderOffset = STROKE_WIDTH;

        Path borderPath = new Path();
        borderPath.moveTo(bounds.left + borderOffset, bounds.top + borderOffset);
        borderPath.lineTo(bounds.left + borderOffset, bounds.bottom - mArrowSize);
        borderPath.lineTo(leftTrianglePoint.x + borderOffset, leftTrianglePoint.y);
        borderPath.lineTo(bottomTrianglePoint.x, bottomTrianglePoint.y - borderOffset);
        borderPath.lineTo(rightTrianglePoint.x - borderOffset, rightTrianglePoint.y);
        borderPath.lineTo(bounds.right - borderOffset, rightTrianglePoint.y);
        borderPath.lineTo(bounds.right - borderOffset, bounds.top + borderOffset);
        borderPath.close();

        paint.setColor(mBorderColor);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(borderPath, paint);
    }


    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        mDrawableWidth = bounds.right - bounds.left;
        mDrawableHeight = bounds.bottom - bounds.top;
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public int getIntrinsicWidth() {
        return mDrawableWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mDrawableHeight;
    }
}
