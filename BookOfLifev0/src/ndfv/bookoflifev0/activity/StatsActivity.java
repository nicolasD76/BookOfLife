package ndfv.bookoflifev0.activity;

import ndfv.bookoflifev0.adapter.ListStatsAdapter;
import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.bookoflifev0.R;

public class StatsActivity extends ListActivity  {
	private ModeleCounters modeleCounters;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		
		//Récupération du modèle
		modeleCounters = ModeleCounters.getInstance();
		
		ListStatsAdapter adapter = new ListStatsAdapter(this,
				android.R.layout.simple_list_item_1, modeleCounters.getCountersList());
		adapter.notifyDataSetChanged();
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stats, menu);
		return true;
	}

}
