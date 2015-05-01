package ndfv.bookoflifev0.activity;

import java.util.ArrayList;
import java.util.Collection;

import ndfv.bookoflifev0.adapter.ListCountersAdapter;
import ndfv.bookoflifev0.entity.CounterEntity;

import com.example.bookoflifev0.R;
import com.example.bookoflifev0.R.layout;
import com.example.bookoflifev0.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class SelectionCountersActivity extends ListActivity  {

	private ArrayList<CounterEntity> listOfCOunter = new ArrayList<CounterEntity>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection_counters);

		listOfCOunter.add(new CounterEntity("clope",false));
		listOfCOunter.add(new CounterEntity("biere",false));
		listOfCOunter.add(new CounterEntity("verres d'eau(20cl)",false));
		listOfCOunter.add(new CounterEntity("pet",false));
		ListCountersAdapter adapter = new ListCountersAdapter(this, android.R.layout.simple_list_item_1, listOfCOunter);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings);
	    setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selection_counters, menu);
		return true;
	}

}
