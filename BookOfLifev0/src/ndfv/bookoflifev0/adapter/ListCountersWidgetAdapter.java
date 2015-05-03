package ndfv.bookoflifev0.adapter;

import java.util.ArrayList;

import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import ndfv.bookoflifev0.exception.MiteException;
import ndfv.bookoflifev0.loader.CountersEntityDAO;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ndfv.bookoflifev0.bookoflifev0.R;

public class ListCountersWidgetAdapter extends ArrayAdapter<CounterEntity> {

	private int positionForWidget = -1; 
	public ListCountersWidgetAdapter(Context context, int textViewResourceId, ArrayList<CounterEntity> countryList) {
		super(context, textViewResourceId, countryList);
	}

	private class ViewHolder {
		TextView code;
		CheckBox name;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.listview_counters_widget_layout, null);

			holder = new ViewHolder();
			holder.code = (TextView) convertView.findViewById(R.id.label_counters_widget);
			holder.name = (CheckBox) convertView.findViewById(R.id.check_box_counters_widget);
			convertView.setTag(holder);			

			holder.name.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CounterEntity counterToActivateForWidget = null;
					ModeleCounters counters = null;
					try {
						counters = ModeleCounters.getInstance();
						counterToActivateForWidget = counters.getCountersList().get(position);
						if(counterToActivateForWidget.isWidgetCounter()){
							counterToActivateForWidget.setWidgetCounter(false);
						}else{
							counterToActivateForWidget.setWidgetCounter(true);
							counters.disableOthersBooleanWidget(counterToActivateForWidget);
						}
					} catch (MiteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						ModeleCounters.getInstance().updateCounter(counterToActivateForWidget);
						notifyDataSetChanged();
					} catch (MiteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CounterEntity counter = null;
		try {
			counter = ModeleCounters.getInstance().getCountersList().get(position);
		} catch (MiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] separated = counter.getStringCreationDate().split(" ");
		holder.code.setText(" (" + separated[0] + ")");
		holder.name.setText(counter.getName());
		holder.name.setChecked(counter.isWidgetCounter());

		return convertView;
	}
	
	
}