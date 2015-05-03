package ndfv.bookoflifev0.adapter;

import java.util.ArrayList;

import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import ndfv.bookoflifev0.exception.MiteException;
import ndfv.bookoflifev0.loader.CountersEntityDAO;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView; 
import android.widget.Toast;

import com.example.bookoflifev0.R;

public class ListCountersActivatedAdapter extends ArrayAdapter<CounterEntity> {

	private CountersEntityDAO countersDAO;


	public ListCountersActivatedAdapter(Context context, int textViewResourceId,
			ArrayList<CounterEntity> countryList) {
		super(context, textViewResourceId, countryList);
		countersDAO = new CountersEntityDAO(context);
	}

	private class ViewHolder {
		TextView counter;
		TextView counterName;
		ImageButton incremente;
	}
	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		   CounterEntity counter = null;
			try {
				counter = ModeleCounters.getInstance().getCountersActivatedList().get(position);
			} catch (MiteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   if (convertView == null) {
		   LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
		     Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.listview_counters_activated_layout, null);
		 
		   holder = new ViewHolder();
		   holder.counter = (TextView) convertView.findViewById(R.id.value_counters_activated);
		   holder.counterName = (TextView) convertView.findViewById(R.id.label_counters_activated);
		   holder.incremente = (ImageButton) convertView.findViewById(R.id.button_incremente_value);
		   convertView.setTag(holder);

		 
		    holder.incremente.setOnClickListener( new View.OnClickListener() {  
		     public void onClick(View v) {  
		    	 ModeleCounters counters = null;
		    	 CounterEntity counter = null;
		    	 try {
		    		 counters = ModeleCounters.getInstance();
		    		 counter = ModeleCounters.getInstance().getCountersActivatedList().get(position);
		 		} catch (MiteException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		    	 counters.getCountersActivatedList().get(position).setValue(counters.getCountersActivatedList().get(position).getValue() + 1);
		    	 ListCountersActivatedAdapter.this.notifyDataSetChanged();
			 
		    	 counters.updateCounter(counters.getCountersActivatedList().get(position));
		     }  
		    });   
		   } 
		   else {
		    holder = (ViewHolder) convertView.getTag();
		   }

			   holder.counterName.setText(counter.getName());
			   holder.counter.setText(String.valueOf(counter.getValue()));

		   
		 
		   return convertView;
	}

}