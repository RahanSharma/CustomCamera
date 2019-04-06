package com.example.android.camera2basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.hardware.camera2.params.Face;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class CustomView extends SurfaceView {

    private final Paint paint;
    private final SurfaceHolder mHolder;
    private final Context context;
    private Boolean faceDetected;
    private Face currentFace;
    public CustomView(Camera2BasicFragment context)
    {
        super(context.getActivity().getBaseContext());
        mHolder = getHolder();
        mHolder.setFormat(PixelFormat.TRANSPARENT);
        this.setZOrderOnTop(true);
        SurfaceHolder sfhTrackHolder = this.getHolder();
        sfhTrackHolder.setFormat(PixelFormat.TRANSPARENT);
        this.context = context.getActivity().getBaseContext();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            invalidate();
            if (mHolder.getSurface().isValid()) {
//                if(faceDetected==true)
//                {
//                    Paint paint=new Paint();
//                    paint.setStrokeWidth(2);
//                    paint.setColor(Color.WHITE);
//
//
//                    faceDetected=false;
//                }
                final Canvas canvas = mHolder.lockCanvas();
                Log.d("touch", "touchRecieved by camera");
                if (canvas != null) {
                    Log.d("touch", "touchRecieved CANVAS STILL Not Null");
                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    canvas.drawColor(Color.TRANSPARENT);
                    canvas.drawCircle(event.getX(), event.getY(), 120, paint);
                    canvas.drawCircle(event.getX(), event.getY(), 20, paint);
//                    canvas.drawRect(currentFace.getBounds(),paint);
                    mHolder.unlockCanvasAndPost(canvas);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Canvas canvas1 = mHolder.lockCanvas();
                            if(canvas1 !=null){
                                canvas1.drawColor(0, PorterDuff.Mode.CLEAR);
                               mHolder.unlockCanvasAndPost(canvas1);
                            }

                        }
                    }, 2000);

                }
//                mHolder.unlockCanvasAndPost(canvas);


            }
        }


        return false;
    }
public  void faceRect(Face face)
{
  faceDetected=true;
  currentFace=face;


}
    public void drawFaceRect(Face[] faces) {
      if(faces.length>0)
        faceRect(faces[0]);
    }
}
