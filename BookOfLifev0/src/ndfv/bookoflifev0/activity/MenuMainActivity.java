package ndfv.bookoflifev0.activity;

import com.example.bookoflifev0.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuMainActivity extends Activity implements OnClickListener{
	private Button compteursButton = null;
	private Button statsButton = null;
//	private int numberOfCompteur = 0;
//	private String compteurLabel = "Compteur";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_main);
		compteursButton = (Button) findViewById(R.id.counters_button);
        compteursButton.setOnClickListener(this);
		statsButton = (Button) findViewById(R.id.stats_button);
		statsButton.setOnClickListener(this);


	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.counters_button:
			onButtonCompteursClick();
			break;
		case R.id.stats_button:
			onButtonStatsClick();
			break;
		}
	}
	
	public void onButtonCompteursClick(){
//		numberOfCompteur = numberOfCompteur + 1;
		Intent activitySelectionCounters = new Intent(this,SelectionCountersActivity.class);
		startActivity(activitySelectionCounters);
	}
	
	public void onButtonStatsClick(){
		Intent activityStats = new Intent(this,StatsActivity.class);
		startActivity(activityStats);
	}

}
