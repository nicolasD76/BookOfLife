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
import android.widget.TextView; 
import android.widget.Toast;

import com.example.bookoflifev0.R;

public class ListCountersAdapter extends ArrayAdapter<CounterEntity> {

	private CountersEntityDAO countersDAO;


	public ListCountersAdapter(Context context, int textViewResourceId,
			ArrayList<CounterEntity> countryList) {
		super(context, textViewResourceId, countryList);
		countersDAO = new CountersEntityDAO(context);
	}

	private class ViewHolder {
		TextView code;
		CheckBox name;
	}
	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		 
		   if (convertView == null) {
		   LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
		     Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.listview_counters_layout, null);
		 
		   holder = new ViewHolder();
		   holder.code = (TextView) convertView.findViewById(R.id.label_counters);
		   holder.name = (CheckBox) convertView.findViewById(R.id.check_box_counters);
		   convertView.setTag(holder);

		 
		    holder.name.setOnClickListener( new View.OnClickListener() {  
		     public void onClick(View v) {   
		    	 ModeleCounters counters = null;
					try {
						counters = ModeleCounters.getInstance();
					} catch (MiteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      if(counters.getCountersList().get(position).isSelected()){
			      counters.getCountersList().get(position).setSelected(false);
			      try {
					ModeleCounters.getInstance().updateCounter(counters.getCountersList().get(position));
					notifyDataSetChanged();
				} catch (MiteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      
			      
		      }else {
			      counters.getCountersList().get(position).setSelected(true);
			      try {
					ModeleCounters.getInstance().updateCounter(counters.getCountersList().get(position));
					notifyDataSetChanged();
				} catch (MiteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		     }  
		    });   
		   } 
		   else {
		    holder = (ViewHolder) convertView.getTag();
		   }
		 
		   CounterEntity counter = null;
			try {
				counter = ModeleCounters.getInstance().getCountersList().get(position);
			} catch (MiteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		   holder.name.setText(counter.getName());
		   holder.name.setChecked(counter.isSelected());
		 
		   return convertView;
	}

}