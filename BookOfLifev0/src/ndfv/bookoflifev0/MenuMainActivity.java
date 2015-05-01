package ndfv.bookoflifev0;

import ndfv.bookoflifev0.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuMainActivity extends Activity implements OnClickListener{
	private Button compteurButton = null;
	private int numberOfCompteur = 0;
	private String compteurLabel = "Compteur";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		compteurButton = (Button) findViewById(R.id.counters_button);
        compteurButton.setOnClickListener(this);

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
		}
	}
	
	public void onButtonCompteursClick(){
		numberOfCompteur = numberOfCompteur + 1;
		compteurButton.setText(compteurLabel + "\n" + numberOfCompteur);
	}

}
