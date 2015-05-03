package ndfv.bookoflifev0.activity;

import java.util.Collection;

import ndfv.bookoflifev0.adapter.ListCountersAdapter;
import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bookoflifev0.R;

public class SelectionCountersActivity extends ListActivity implements OnClickListener {

	private ModeleCounters modeleCounters = null;
	private Button buttonAddCounter = null;
	private EditText valueAddCounter = null;
	private Button buttonDeleteCounter = null;
	private ListView listviewCounters = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection_counters);

		modeleCounters = ModeleCounters.getInstance(this);

		buttonAddCounter = (Button) findViewById(R.id.add_button);
		buttonAddCounter.setOnClickListener(this);

		buttonDeleteCounter = (Button) findViewById(R.id.delete_button);
		buttonDeleteCounter.setOnClickListener(this);
		valueAddCounter = (EditText) findViewById(R.id.add_value);
		
		listviewCounters = (ListView) findViewById(android.R.id.list);

		ListCountersAdapter adapter = new ListCountersAdapter(this, android.R.layout.simple_list_item_1, modeleCounters.getCountersList());
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
			String name = valueAddCounter.getText().toString();
			if (canCreatedCounterByName(name)) {
				counterEntity = new CounterEntity();
				Log.d("d", counterEntity.getStringLastUpdateDate());
				counterEntity.setName(name);
				// enregistrer le nouveau commentaire dans la base de données
				modeleCounters.insertCounter(counterEntity);
				adapter.notifyDataSetChanged();
				setListAdapter(adapter);
				valueAddCounter.setText(null);
			}
			break;
//		case R.id.delete_button:
//			System.out.println("size: " + modeleCounters.getCountersList().size());
//			int size = modeleCounters.getCountersList().size();
//			int alreadySuppressed = 0;
//			for (int i = 0; i < size; i++) {
//				System.out.println("name: " + modeleCounters.getCountersList().get(i - alreadySuppressed).getName() + " selected: "
//						+ modeleCounters.getCountersList().get(i - alreadySuppressed).isSelected());
//
//				if (modeleCounters.getCountersList().get(i - alreadySuppressed).isSelected()) {
//					System.out.println("deleted!");
//					modeleCounters.deleteCounter(modeleCounters.getCountersList().get(i - alreadySuppressed));
//					alreadySuppressed = alreadySuppressed + 1;
//					adapter.notifyDataSetChanged();
//					setListAdapter(adapter);
//				}
//			}
//			break;
		}
		adapter.notifyDataSetChanged();
	}

	public boolean canCreatedCounterByName(String name) {
		boolean can = true;
		int index = 0;
		int size = modeleCounters.getCountersList().size();
		while (can && index < size) {
			if (modeleCounters.getCountersList().get(index).getName().equals(name)) {
				can = false;
			}
			index++;
		}

		return can;
	}

	
}
