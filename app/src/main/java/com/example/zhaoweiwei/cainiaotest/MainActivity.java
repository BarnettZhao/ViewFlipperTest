package com.example.zhaoweiwei.cainiaotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

	private ViewFlipper flipper;
	private float startX;
	private boolean isChange;

	private MyGestureListener myGestureListener;
	private GestureDetector gestureDetector;
	private final static int MIN_MOVE = 200;   //滑动距离大于该值时，进行相应操作

	private int[]resId = {R.drawable.launcher_img_guide_1,R.drawable.launcher_img_guide_2,R.drawable.launcher_img_guide_3,R.drawable.launcher_img_guide_4};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		flipper = (ViewFlipper) findViewById(R.id.view_filpper);
		for (int i = 0 ;i < resId.length; i++) {
			flipper.addView(getImageView(resId[i]));
		}
		flipper.startFlipping();
		myGestureListener = new MyGestureListener();
		gestureDetector = new GestureDetector(this, myGestureListener);
	}

	private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			if(e1.getX() - e2.getX() > MIN_MOVE){
				flipper.setInAnimation(MainActivity.this,R.anim.launch_right_in);
				flipper.setOutAnimation(MainActivity.this,R.anim.launch_right_out);
				flipper.showNext();
			}else if(e2.getX() - e1.getX() > MIN_MOVE){
				flipper.setInAnimation(MainActivity.this,R.anim.launch_left_in);
				flipper.setOutAnimation(MainActivity.this,R.anim.launch_left_out);
				flipper.showPrevious();
			}
			return false;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/*switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = event.getX();
				isChange = true;
				break;
			case MotionEvent.ACTION_MOVE:
				if (isChange) {
					if (event.getX() > startX) {
						flipper.setInAnimation(this,R.anim.launch_left_in);
						flipper.setOutAnimation(this,R.anim.launch_left_out);
						flipper.showPrevious();
					} else if (startX > event.getX()){
						flipper.setInAnimation(this,R.anim.launch_right_in);
						flipper.setOutAnimation(this,R.anim.launch_right_out);
						flipper.showNext();
					}
				}
				isChange = false;
				break;
		}*/
		return gestureDetector.onTouchEvent(event);
	}

	private ImageView getImageView(int resId){
		ImageView img = new ImageView(this);
		img.setImageResource(resId);
		return img;
	}
}
