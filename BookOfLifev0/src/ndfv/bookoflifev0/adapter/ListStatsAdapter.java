package ndfv.bookoflifev0.adapter;

import java.util.List;

import com.example.bookoflifev0.R;

import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import ndfv.bookoflifev0.exception.MiteException;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListStatsAdapter extends ArrayAdapter<CounterEntity>{

	public ListStatsAdapter(Context context, int textViewResourceId,
			List<CounterEntity> statsList) {
		super(context, textViewResourceId, statsList);
		// TODO Auto-generated constructor stub
	}
	
	private class ViewHolder {
		TextView libelle_Stat;
		TextView stat_value;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		
		if (convertView == null) {
		   LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
				   Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.listview_stats_layout, null);
		 
		   holder = new ViewHolder();
		   holder.libelle_Stat = (TextView) convertView.findViewById(R.id.libelle_stats);
		   holder.stat_value = (TextView) convertView.findViewById(R.id.stat_value);
		   convertView.setTag(holder);
	 
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
	}
	   holder.libelle_Stat.setText(counter.getName());
	   holder.stat_value.setText(String.valueOf(counter.getValue()));
	 
	   return convertView;
	}


}


