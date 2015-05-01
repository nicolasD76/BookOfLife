package ndfv.bookoflifev0;

import com.example.bookoflifev0.R;
import com.example.bookoflifev0.R.layout;
import com.example.bookoflifev0.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SelectionCountersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection_counters);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selection_counters, menu);
		return true;
	}

}
