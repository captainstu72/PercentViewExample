package co.uk.captainstu72.example.percentview;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import co.uk.captainstu72.example.percentview.PercentView;

public class MainActivity extends Activity {
	
	static PercentView pv = null;
	
	String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    pv = (PercentView) findViewById (R.id.pv);
	    pv.setOutlineSize(20);
	    

		EditText et = (EditText) findViewById(R.id.editText1);
		et.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
					//vf.setDisplayedChild(vf.getDisplayedChild()+1);
					setPercs(getCurrentFocus());
				}
				return false;
			}
        });
		
		RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);          
		rg.setOnCheckedChangeListener(
		      new RadioGroup.OnCheckedChangeListener() {
		            @Override
		            public void onCheckedChanged(RadioGroup radioGroup, int i) {
		                setPercs(getCurrentFocus());
		            }
		      });
	     
	}

	public void setPercs (View v) {
		Log.i(TAG,"setPercs()");
		int perc = 0;
		EditText et = (EditText) findViewById(R.id.editText1);
		if (et.getText().length() != 0) {
			
			// get selected value from the listview
			RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
			int rbID = rg.getCheckedRadioButtonId();	
			
			switch (rbID) {
			case R.id.rbOutline:
				pv.setOutlineOnly(true);
				break;

			default:
				pv.setOutlineOnly(false);
				break;
			}
			
			perc = (int) Integer.valueOf(et.getText().toString());			
			pv.setPercentage(perc);
		} else {
			Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.action_github:
	        	//Open link GitHib repo.
	        	Intent browserIntent = new Intent(Intent.ACTION_VIEW,
	        			Uri.parse("https://github.com/captainstu72/PercentViewExample"));
	        	startActivity(browserIntent);
	        	
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
