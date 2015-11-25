package com.diagoalley.android.viewpagerbannertest;

/**
 * banner界面小圆点View
 * @author MYH
 * Created at  2015/11/24 16:49
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;



public class PageIndicatorView extends View {
    private int mCurrentPage = -1;
    private int mTotalPage = 0;
    private String[] textValue = null;
    private int iconWidth;
    private int iconHeight;
    private int textSize;
    private int px;

    public PageIndicatorView(Context context) {
        super(context);
    }

    public PageIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTotalPage(int nPageNum) {
        mTotalPage = nPageNum;
        if (mCurrentPage >= mTotalPage)
            mCurrentPage = mTotalPage - 1;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int nPageIndex) {
        if (nPageIndex < 0 || nPageIndex >= mTotalPage)
            return;

        if (mCurrentPage != nPageIndex) {
            mCurrentPage = nPageIndex;
            this.invalidate();
        }
    }

    public void setRectSize(int width, int height, int px) {
        iconWidth = width;
        iconHeight = height;
        this.px = px;
    }

    public void setTextSize(int size) {
        textSize = size;
    }

    public void setTextValue(String[] value) {
        textValue = value;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG);
        paint.setStyle(Paint.Style.FILL);

        Rect r = new Rect();
        this.getDrawingRect(r);

        int space = 12;

        int x = (r.width() - (iconWidth * mTotalPage + space * (mTotalPage - 1)))
                - px;
        int y = (r.height() - iconHeight) / 2;

        int x2 = (r.width() / 2 - iconWidth) - px;

        for (int i = 0; i < mTotalPage; i++) {
            int resid = R.drawable.dot_black;
            String tt = "";
            if (i == mCurrentPage) {
                resid = R.drawable.dot_white;
                if (textValue != null) {
                    tt = textValue[i];
                    if (tt.length() > 13) {
                        tt = tt.substring(0, 12) + "...";
                    }
                    Rect r1 = new Rect();
                    r1.left = x;
                    r1.top = y;
                    r1.right = x + iconWidth;
                    r1.bottom = y + iconHeight;

                    paint.setColor(Color.WHITE);
                    paint.setTextSize(textSize);
                    canvas.drawText(tt, 20, r1.bottom + 2, paint);
                }
            }
            if (textValue != null) {
                Rect r1 = new Rect();
                r1.left = x;
                r1.top = y;
                r1.right = x + iconWidth;
                r1.bottom = y + iconHeight;

                paint.setColor(Color.BLACK);
                Bitmap bmp = BitmapFactory
                        .decodeResource(getResources(), resid);
                canvas.drawBitmap(bmp, null, r1, paint);
            } else {
                Rect r1 = new Rect();
                r1.left = x2;
                r1.top = y;
                r1.right = x2 + iconWidth;
                r1.bottom = y + iconHeight;

                paint.setColor(Color.BLACK);
                Bitmap bmp = BitmapFactory
                        .decodeResource(getResources(), resid);
                canvas.drawBitmap(bmp, null, r1, paint);
            }
            x += iconWidth + space;
            x2 += iconWidth + space;
        }

    }

    public void DrawImage(Canvas canvas, Bitmap mBitmap, int x, int y, int w,
                          int h, int bx, int by) {
        Rect src = new Rect();
        Rect dst = new Rect();
        src.left = bx;
        src.top = by;
        src.right = bx + w;
        src.bottom = by + h;

        dst.left = x;
        dst.top = y;
        dst.right = x + w;
        dst.bottom = y + h;

        src = null;
        dst = null;
    }

}
