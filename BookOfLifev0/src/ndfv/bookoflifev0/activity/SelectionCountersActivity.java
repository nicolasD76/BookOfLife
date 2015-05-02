package ndfv.bookoflifev0.activity;

import java.util.ArrayList;
import java.util.Collection;

import ndfv.bookoflifev0.adapter.ListCountersAdapter;
import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.loader.CountersEntityDAO;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookoflifev0.R;

public class SelectionCountersActivity extends ListActivity implements
		OnClickListener {

	private ArrayList<CounterEntity> listOfCOunter = new ArrayList<CounterEntity>();
	private Button buttonAddCounter = null;
	private EditText valueAddCounter = null;
	private CountersEntityDAO countersDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection_counters);
		
		countersDAO = new CountersEntityDAO(this);
		
		buttonAddCounter = new Button(getApplicationContext());
		buttonAddCounter = (Button) findViewById(R.id.add_button);
		buttonAddCounter.setOnClickListener(this);
		valueAddCounter = (EditText) findViewById(R.id.add_value);

		listOfCOunter.addAll((Collection<? extends CounterEntity>) countersDAO.getAllCounterEntity());
		ListCountersAdapter adapter = new ListCountersAdapter(this,
				android.R.layout.simple_list_item_1, listOfCOunter);
		adapter.notifyDataSetChanged();
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, mStrings);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selection_counters, menu);
		return true;
	}
 
	@Override
	public void onClick(View v) {
		@SuppressWarnings("unchecked")
		ListCountersAdapter adapter = (ListCountersAdapter) getListAdapter();
		CounterEntity counterEntity = null;
		switch (v.getId()) {
		case R.id.add_button:
			counterEntity = new CounterEntity();
			counterEntity.setName(valueAddCounter.getText().toString());
			// enregistrer le nouveau commentaire dans la base de données
			counterEntity = countersDAO.createCounterEntity(counterEntity);
			adapter.add(counterEntity);
			break;
		}
		adapter.notifyDataSetChanged();

	}

	 @Override
	  protected void onResume() {
		 countersDAO.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
		  countersDAO.close();
	    super.onPause();
	  }
}
