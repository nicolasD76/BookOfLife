package ndfv.bookoflifev0.fragment;

import ndfv.bookoflifev0.activity.StatsActivity;
import ndfv.bookoflifev0.adapter.ListCountersActivatedAdapter;
import ndfv.bookoflifev0.bookoflifev0.R;
import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import ndfv.bookoflifev0.entity.ViewPagerInterface;
import ndfv.bookoflifev0.tool.DateTool;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ListOfCountersFragment extends ListFragment implements OnClickListener,ViewPagerInterface{
	private Button compteursButton = null;
	private Button statsButton = null;
	private ListCountersActivatedAdapter adapter;

	private ModeleCounters counters;


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
	 	View view = inflater.inflate(R.layout.activity_list_of_counters_fragment, container, false);
		statsButton = (Button) view.findViewById(R.id.stats_button);
		statsButton.setOnClickListener(this);

		counters = ModeleCounters.getInstance(getActivity());

		if (counters.getCountersList().size() > 0) {
			CounterEntity entity = counters.getCountersList().get(0);
			Log.d("d init", DateTool.getStringFullDate(entity.getLastUpdateDate()));
		}

		adapter = new ListCountersActivatedAdapter(getActivity(), android.R.layout.simple_list_item_1, counters.getCountersActivatedList());
		setListAdapter(adapter);        
		adapter.notifyDataSetChanged();
		return view;
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
		
		AlertDialog.Builder dialogDelete = new AlertDialog.Builder(getActivity());
		dialogDelete.setTitle("Suppression/modification de compteur");
		dialogDelete.setMessage("Quel compteur pour le widget?");
		
		//set de la listView
		ListView input = new ListView(getActivity().getBaseContext());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//		ListCountersWidgetAdapter adapter = null;
//		try {
//			adapter = new ListCountersWidgetAdapter(getActivity(), android.R.layout.simple_list_item_1, ModeleCounters.getInstance().getCountersFromDataBase());
//			adapter.notifyDataSetChanged();
//
//		} catch (MiteException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		input.setAdapter(adapter);

		input.setLayoutParams(lp);
		dialogDelete.setView(input);
		dialogDelete.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.stats_button:
			onButtonStatsClick();
			break;
		}
	}

	@Override
	public void onResume() {
//		ListCountersActivatedAdapter adapter = new ListCountersActivatedAdapter(getActivity(), android.R.layout.simple_list_item_1, counters.getCountersActivatedList());
		adapter.notifyDataSetChanged();
		setListAdapter(adapter);
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void fragmentBecameVisible() {
		adapter = new ListCountersActivatedAdapter(getActivity(), android.R.layout.simple_list_item_1, counters.getCountersActivatedList());
		adapter.notifyDataSetChanged();
		setListAdapter(adapter);
	}

	public void onButtonStatsClick() {
		Intent activityStats = new Intent(getActivity(), StatsActivity.class);
		startActivity(activityStats);
	}
}
