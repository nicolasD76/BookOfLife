package ndfv.bookoflifev0.activity;

import ndfv.bookoflifev0.adapter.ListCountersActivatedAdapter;
import ndfv.bookoflifev0.adapter.ListCountersAdapter;
import ndfv.bookoflifev0.adapter.ListCountersWidgetAdapter;
import ndfv.bookoflifev0.bookoflifev0.R;
import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import ndfv.bookoflifev0.exception.MiteException;
import ndfv.bookoflifev0.tool.DateTool;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MenuMainActivity extends ListActivity implements OnClickListener {
	private Button compteursButton = null;
	private Button statsButton = null;

	private ModeleCounters counters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_main);
		compteursButton = (Button) findViewById(R.id.counters_button);
		compteursButton.setOnClickListener(this);
		statsButton = (Button) findViewById(R.id.stats_button);
		statsButton.setOnClickListener(this);

		counters = ModeleCounters.getInstance(this);

		if (counters.getCountersList().size() > 0) {
			CounterEntity entity = counters.getCountersList().get(0);
			Log.d("d init", DateTool.getStringFullDate(entity.getLastUpdateDate()));
		}

		ListCountersActivatedAdapter adapter = new ListCountersActivatedAdapter(this, android.R.layout.simple_list_item_1, counters.getCountersActivatedList());
		adapter.notifyDataSetChanged();
		setListAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_choose_counters_label:
			openActivityChooseWidget();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void openActivityChooseWidget() {
		System.out.println("enterInPoPUp");
		int positionForWidget = -1;
		
		AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
		dialogDelete.setTitle("Suppression/modification de compteur");
		dialogDelete.setMessage("Quel compteur pour le widget?");
		
		//set de la listView
		ListView input = new ListView(this.getBaseContext());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		ListCountersWidgetAdapter adapter = null;
		try {
			adapter = new ListCountersWidgetAdapter(this, android.R.layout.simple_list_item_1, ModeleCounters.getInstance().getCountersFromDataBase());
			adapter.notifyDataSetChanged();

		} catch (MiteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		input.setAdapter(adapter);

		input.setLayoutParams(lp);
		dialogDelete.setView(input);
		dialogDelete.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
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
		ListCountersActivatedAdapter adapter = new ListCountersActivatedAdapter(this, android.R.layout.simple_list_item_1, counters.getCountersActivatedList());
		adapter.notifyDataSetChanged();
		setListAdapter(adapter);
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	public void onButtonCompteursClick() {
		Intent activitySelectionCounters = new Intent(this, SelectionCountersActivity.class);
		startActivity(activitySelectionCounters);
	}

	public void onButtonStatsClick() {
		Intent activityStats = new Intent(this, StatsActivity.class);
		startActivity(activityStats);
	}

}
