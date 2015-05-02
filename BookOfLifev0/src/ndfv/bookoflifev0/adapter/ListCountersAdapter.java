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

	private ArrayList<CounterEntity> countersList;

	public ListCountersAdapter(Context context, int textViewResourceId,
			ArrayList<CounterEntity> countryList) {
		super(context, textViewResourceId, countryList);
		this.countersList = new ArrayList<CounterEntity>();
		this.countersList.addAll(countryList);
	}

	private class ViewHolder {
		TextView code;
		CheckBox name;
	}
	
	@Override
	public void add(CounterEntity counter){
		super.add(counter);
		this.countersList.add(counter);
	}
	
	@Override
	public void remove(CounterEntity counter){
		super.remove(counter);
		this.countersList.remove(counter);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		 
		   if (convertView == null) {
		   LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
		     Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.listview_counters_layout, null);
		 
		   holder = new ViewHolder();
		   holder.code = (TextView) convertView.findViewById(R.id.label_counters);
		   holder.name = (CheckBox) convertView.findViewById(R.id.check_box_counters);
		   convertView.setTag(holder);
		      final int currentPosition = position;

		 
		    holder.name.setOnClickListener( new View.OnClickListener() {  
		     public void onClick(View v) {   
		      CheckBox cb = (CheckBox) v ; 
		      System.out.println("pass in clicklistener before: " + ModeleCounters.getInstance().getCountersList().get(currentPosition).isSelected());
		      if(ModeleCounters.getInstance().getCountersList().get(currentPosition).isSelected()){
			      ModeleCounters.getInstance().getCountersList().get(currentPosition).setSelected(false);
		      }else {
			      ModeleCounters.getInstance().getCountersList().get(currentPosition).setSelected(true);
		      }
		      System.out.println("pass in clicklistener after: " + ModeleCounters.getInstance().getCountersList().get(currentPosition).isSelected());
		     }  
		    });  
		   } 
		   else {
		    holder = (ViewHolder) convertView.getTag();
		   }
		 
		   CounterEntity counter = countersList.get(position);
		   holder.name.setText(counter.getName());
		   holder.name.setChecked(counter.isSelected());
		 
		   return convertView;
	}

}
