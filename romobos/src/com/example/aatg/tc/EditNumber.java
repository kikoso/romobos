package com.example.aatg.tc;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * 
 */

/**
 * @author ami
 *
 */
public class EditNumber extends EditText {
	private static final String DEFAULT_FORMAT = "%.2f";

	/**
	 * @param context
	 */
	public EditNumber(Context context) {
		super(context);
		// TODO Auto-generated yconstructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public EditNumber(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public EditNumber(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void clear() {
		setText("");
	}


	public void setNumber(double f) {super.setText(
		String.format(DEFAULT_FORMAT, f));
	}

	public double getNumber() {
		Log.d("EditNumber", "getNumber() returning value of '" + getText().toString() + "'");
		return Double.valueOf(getText().toString());
	}

}
