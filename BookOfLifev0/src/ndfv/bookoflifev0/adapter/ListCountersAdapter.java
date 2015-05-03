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

import com.example.bookoflifev0.R;

public class ListCountersAdapter extends ArrayAdapter<CounterEntity> {

	private CountersEntityDAO countersDAO;
	private ListView listViewItem = null;

	public ListCountersAdapter(Context context, int textViewResourceId, ArrayList<CounterEntity> countryList) {
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
			convertView = vi.inflate(R.layout.listview_counters_layout, null);

			holder = new ViewHolder();
			holder.code = (TextView) convertView.findViewById(R.id.label_counters);
			holder.name = (CheckBox) convertView.findViewById(R.id.check_box_counters);
			convertView.setTag(holder);

			listViewItem = (ListView) parent;
			listViewItem.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			// parent.setOnLongClickListener(this);
			final ViewHolder holderForListenner = holder;
			convertView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					ModeleCounters counters = null;
					try {
						counters = ModeleCounters.getInstance();
					} catch (MiteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					showAlertDialogDelete(holderForListenner.name, counters.getCountersList().get(position));
					return false;
				}
			});

			holder.name.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					ModeleCounters counters = null;
					try {
						counters = ModeleCounters.getInstance();
					} catch (MiteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (counters.getCountersList().get(position).isSelected()) {
						CounterEntity counter = counters.getCountersList().get(position);
						counter.setSelected(false);
						// counters.updateCounter(counters.getCountersList().get(position));
						Log.d("d", "selected false");
						try {
							ModeleCounters.getInstance().updateCounter(counter);
							notifyDataSetChanged();
						} catch (MiteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						counters.getCountersList().get(position).setSelected(true);
						// counters.updateCounter(counters.getCountersList().get(position));
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
		holder.name.setChecked(counter.isSelected());

		return convertView;
	}

	private void showAlertDialogDelete(CheckBox boxOfCounterToDelete, final CounterEntity counterToDelete) {
		AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this.getContext());
		dialogDelete.setTitle("Suppression/modification de compteur");
		dialogDelete.setMessage("Voulez-vous supprimer ou modifier le compteur suivant: " + boxOfCounterToDelete.getText());
		dialogDelete.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					ModeleCounters.getInstance().deleteCounter(counterToDelete);
					notifyDataSetChanged();
				} catch (MiteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		final EditText input = new EditText(ListCountersAdapter.this.getContext());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		input.setLayoutParams(lp);
		dialogDelete.setView(input);
		dialogDelete.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					counterToDelete.setName(input.getText().toString());
					ModeleCounters.getInstance().updateCounter(counterToDelete);
					notifyDataSetChanged();
				} catch (MiteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		dialogDelete.show();
	}
}