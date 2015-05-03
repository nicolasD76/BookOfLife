package ndfv.bookoflifev0.activity;

import java.util.Collection;

import ndfv.bookoflifev0.adapter.ListCountersActivatedAdapter;
import ndfv.bookoflifev0.adapter.ListCountersAdapter;
import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import ndfv.bookoflifev0.loader.CountersEntityDAO;

import com.example.bookoflifev0.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuMainActivity extends ListActivity implements OnClickListener{
	private Button compteursButton = null;
	private Button statsButton = null;
	private CountersEntityDAO countersDAO;
	
	private ModeleCounters counters;

//	private int numberOfCompteur = 0;
//	private String compteurLabel = "Compteur";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_main);
		compteursButton = (Button) findViewById(R.id.counters_button);
        compteursButton.setOnClickListener(this);
		statsButton = (Button) findViewById(R.id.stats_button);
		statsButton.setOnClickListener(this);
		
//		countersDAO = new CountersEntityDAO(this);

		counters = ModeleCounters.getInstance(this);
		
		if (counters.getCountersList().size() == 0) {
			counters.getCountersList().addAll(
					(Collection<? extends CounterEntity>) counters.getCountersActivatedList());
		}
		ListCountersActivatedAdapter adapter = new ListCountersActivatedAdapter(this,
				android.R.layout.simple_list_item_1,
				counters.getCountersActivatedList());
		adapter.notifyDataSetChanged();
		setListAdapter(adapter);

	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.counters_button:
			onButtonCompteursClick();
			break;
		case R.id.stats_button:
			onButtonStatsClick();
			break;
		}
	}
	
	@Override
	protected void onResume() {
//		countersDAO.open();
		if (counters.getCountersList().size() == 0) {
			counters.getCountersList().addAll(
					(Collection<? extends CounterEntity>) counters.getCountersActivatedList());
		}
		ListCountersActivatedAdapter adapter = new ListCountersActivatedAdapter(this,
				android.R.layout.simple_list_item_1,
				counters.getCountersActivatedList());
		adapter.notifyDataSetChanged();
		setListAdapter(adapter);
		super.onResume();
	}

	@Override
	protected void onPause() {
//		countersDAO.close();
		super.onPause();
	}
	
	
	public void onButtonCompteursClick(){
//		numberOfCompteur = numberOfCompteur + 1;
		Intent activitySelectionCounters = new Intent(this,SelectionCountersActivity.class);
		startActivity(activitySelectionCounters);
	}
	
	public void onButtonStatsClick(){
		Intent activityStats = new Intent(this,StatsActivity.class);
		startActivity(activityStats);
	}

}
