package ndfv.bookoflifev0.fragment;

import ndfv.bookoflifev0.adapter.ListCountersAdapter;
import ndfv.bookoflifev0.bookoflifev0.R;
import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.ModeleCounters;
import ndfv.bookoflifev0.entity.ViewPagerInterface;
import ndfv.bookoflifev0.tool.DateTool;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SelectionCountersFragment extends ListFragment implements OnClickListener,ViewPagerInterface{

	private ModeleCounters modeleCounters = null;
	private Button buttonAddCounter = null;
	private EditText valueAddCounter = null;
	private ListView listviewCounters = null;
	private TextView title;
	private TextView label1;
	private ListCountersAdapter adapter;

		
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                             Bundle savedInstanceState) {
		 	View view = inflater.inflate(R.layout.activity_selection_counters_fragment, container, false);
		 	//On charge les textView pour setter la police
			title = (TextView) view.findViewById(R.id.title_counters);
			label1 = (TextView) view.findViewById(R.id.add_label);
			setFont();

			//début du code
			modeleCounters = ModeleCounters.getInstance(this.getActivity());
			buttonAddCounter = (Button) view.findViewById(R.id.add_button);
			buttonAddCounter.setOnClickListener(this);
			valueAddCounter = (EditText) view.findViewById(R.id.add_value);

			listviewCounters = (ListView) view.findViewById(android.R.id.list);

			adapter = new ListCountersAdapter(this.getActivity(), android.R.layout.simple_list_item_1, modeleCounters.getCountersList());
			setListAdapter(adapter);
			adapter.notifyDataSetChanged();
	        return view;
	    }
	 
	 private void setFont(){
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "book_of_life_font.ttf");
		title.setTypeface(font);
		label1.setTypeface(font);
	 }
		@Override
		public void onClick(View v) {
			@SuppressWarnings("unchecked")
			CounterEntity counterEntity = null;
			switch (v.getId()) {
			case R.id.add_button:
				String name = valueAddCounter.getText().toString();
				if (canCreatedCounterByName(name)) {
					counterEntity = new CounterEntity();
					Log.d("d", DateTool.getStringFullDate(counterEntity.getLastUpdateDate()));
					counterEntity.setName(name);
					// enregistrer le nouveau commentaire dans la base de données
					modeleCounters.insertCounter(counterEntity);
					
					CounterEntity e = modeleCounters.getCountersList().get(modeleCounters.getCountersList().size() - 1);
					
//					e.getHistoric().add(new HistoricDay(e.getId(), e.getLastUpdateDate(), e.getValue()));
					
					
					adapter.notifyDataSetChanged();
					setListAdapter(adapter);
					valueAddCounter.setText(null);
				}
				break;
			}
			adapter.notifyDataSetChanged();
		}

		public boolean canCreatedCounterByName(String name) {
			boolean can = true;
			int index = 0;
			int size = modeleCounters.getCountersList().size();
			while (can && index < size) {
				if (modeleCounters.getCountersList().get(index).getName().equals(name)) {
					can = false;
				}
				index++;
			}

			return can;
		}
	    
	    @Override
		public void fragmentBecameVisible() {
			if(adapter != null){
				adapter.notifyDataSetChanged();
			}
		}
}
