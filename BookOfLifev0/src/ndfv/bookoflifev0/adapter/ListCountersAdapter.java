package ndfv.bookoflifev0.adapter;

import java.util.ArrayList;

import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;

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


	public ListCountersAdapter(Context context, int textViewResourceId,
			ArrayList<CounterEntity> countryList) {
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
		   LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
		     Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.listview_counters_layout, null);
		 
		   holder = new ViewHolder();
		   holder.code = (TextView) convertView.findViewById(R.id.label_counters);
		   holder.name = (CheckBox) convertView.findViewById(R.id.check_box_counters);
		   convertView.setTag(holder);

		 
		    holder.name.setOnClickListener( new View.OnClickListener() {  
		     public void onClick(View v) {   
		      CheckBox cb = (CheckBox) v ; 
		      System.out.println("pass in clicklistener before: " + ModeleCounters.getInstance().getCountersList().get(position).isSelected() + " position: " + position);
		      if(ModeleCounters.getInstance().getCountersList().get(position).isSelected()){
			      ModeleCounters.getInstance().getCountersList().get(position).setSelected(false);
		      }else {
			      ModeleCounters.getInstance().getCountersList().get(position).setSelected(true);
		      }
		      System.out.println("pass in clicklistener after: " + ModeleCounters.getInstance().getCountersList().get(position).isSelected());
		     }  
		    });  
		   } 
		   else {
		    holder = (ViewHolder) convertView.getTag();
		   }
		 
		   CounterEntity counter = ModeleCounters.getInstance().getCountersList().get(position);
		   holder.name.setText(counter.getName());
		   holder.name.setChecked(counter.isSelected());
		 
		   return convertView;
	}

}
