package com.example.aatg.tc;
/**
 * @author ami
 *
 */
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.TextView;

import com.example.aatg.tc.TemperatureConverter.OP;

public class TemperatureConverterActivity extends Activity {
	private EditNumber mCelsius;
	private EditNumber mFahrenheit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mCelsius = (EditNumber) findViewById(R.id.celsius);
		mFahrenheit = (EditNumber) findViewById(R.id.fahrenheit);

		mCelsius.addTextChangedListener(
				new TemperatureChangedWatcher(OP.C2F));
		mFahrenheit.addTextChangedListener(
				new TemperatureChangedWatcher(OP.F2C));
		
		setTextFonts();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temperature_converter, menu);
		return true;
	}
	
	private void setTextFonts() {
		String fontPath = "fonts/Roboto-LightItalic.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

		((TextView) findViewById(R.id.textTypeTemperature)).setTypeface(tf);
		((TextView) findViewById(R.id.celsius_label)).setTypeface(tf);
		((TextView) findViewById(R.id.fahrenheit_label)).setTypeface(tf);
	    
	}

	public class TemperatureChangedWatcher implements TextWatcher {
		private final EditNumber mSource;
		private final EditNumber mDest;
		private OP mOp;

		/**
		 * @param mDest
		 * @param convert
		 * @throws NoSuchMethodException
		 * @throws SecurityException
		 */
		public TemperatureChangedWatcher(TemperatureConverter.OP op) {
			if ( op == OP.C2F ) {
				this.mSource = mCelsius;
				this.mDest = mFahrenheit;
			}
			else {
				this.mSource = mFahrenheit;
				this.mDest = mCelsius;
			}
			this.mOp = op;
		}


		/* (non-Javadoc)
		 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
		 */
		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence, int, int, int)
		 */
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence, int, int, int)
		 */
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (!mDest.hasWindowFocus() || mDest.hasFocus() || s == null )
			{
				return; }
			final String str = s.toString();
			if ( "".equals(str) ) {
				mDest.setText("");
				return;
			}
			try {
				final double temp = Double.parseDouble(str);
				final double result = (mOp == OP.C2F) ?
						TemperatureConverter.celsiusToFahrenheit(temp) :
							TemperatureConverter.fahrenheitToCelsius(temp);
						final String resultString = String.format("%.2f", result);
						mDest.setNumber(result);
						mDest.setSelection(resultString.length());
			} catch (NumberFormatException e) {
				// WARNING
				// this is generated while a number is entered,
				// for example just a '-'
				// so we don't want to show the error
			} catch (Exception e) {
				mSource.setError("ERROR: " + e.getLocalizedMessage());
			}


		}

	}

}
