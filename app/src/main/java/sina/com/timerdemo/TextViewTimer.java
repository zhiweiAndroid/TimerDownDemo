package sina.com.timerdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Juzi
 *
 */
public class TextViewTimer extends TextView implements Runnable {

	// 天 , 小时 ,分钟, 秒
	private int day, hour, minute, second;
	// 当前计时器是否运行
	private boolean isRun = false;

	public TextViewTimer(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public TextViewTimer(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TextViewTimer(Context context) {
		this(context, null);
	}

	/**
	 * 传入 你要计时的时间 即可
	 * @param time
	 */
	public void setTimes(TextView timer,String time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		//目标时间
		Date target = null;
		try {
			target = dateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//获得时间差
		long diff = System.currentTimeMillis()- target.getTime();
		Log.d("mytest","timer>>>>>>>>>>>>>"+"System"+System.currentTimeMillis()+"----------"+"target"+target.getTime()+"==="+"diff"+diff);

		if (diff>60*60*1000||diff<0){
			timer.setText("00:00:00");
			return;
		}
		long lastTime=60*60*1000-diff;
		// 将毫秒数转化为时间
		this.second = (int) (lastTime / 1000) % 60;
		this.minute = (int) (lastTime / (60 * 1000) % 60);

		/**
		 * 开始倒计时
		 */
		if(!this.isRun()){
			this.start();
		}
	}


	/**
	 * 显示当前时间
	 *
	 * @return
	 */
	public String showTime() {
		StringBuilder time = new StringBuilder();

		time.append("00");
		time.append(":");
		if (minute<10){
			time.append("0"+minute);
		}else {
			time.append(minute);
		}
		time.append(":");
		if (second<10){
			time.append("0"+second);
		}else {
			time.append(second);
		}
		return time.toString();
	}

	/**
	 * 实现倒计时
	 */
	private void countdown() {
		second--;
		if (second < 0) {
			minute--;
			second = 59;
			if (minute < 0) {
				minute = 59;
			}

		}
		if (second==0&&minute==0){
			stop();
		}
	}

	public boolean isRun() {
		return isRun;
	}

	/**
	 * 开始计时
	 */
	public void start() {
		isRun = true;
		run();
	}

	/**
	 * 结束计时
	 */
	public void stop() {
		isRun = false;
	}

	/**
	 * 实现计时循环
	 */
	@Override
	public void run() {
		if (isRun) {
			countdown();
			this.setText(showTime());
			postDelayed(this, 1000);
		} else {
			removeCallbacks(this);
		}
	}

}
