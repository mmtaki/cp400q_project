package com.example.stickfight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	public void createGame(View v){
		Intent i = new Intent();
		i = null;
	}
	
	public void addRound(View v){
		TextView round = (TextView)findViewById(R.id.text_rounds);
		String s = (String) round.getText();
		int i = Integer.parseInt(s.substring(0, 1));
		
		if (i > 0 && i < 5){
			round.setText(++i+" Rounds");
		}
		Button bp = (Button)v;
		Button bm = (Button)findViewById(R.id.button_minus);
		bm.setEnabled(true);
		if (i == 5)
			bp.setEnabled(false);
	}
	
	public void subRound(View v){
		TextView round = (TextView)findViewById(R.id.text_rounds);
		String s = (String) round.getText();
		int i = Integer.parseInt(s.substring(0, 1));
		
		if (i > 1 && i < 6){
			i--;
			if(i == 1)
				round.setText(i+" Round ");
			else
				round.setText(i+" Rounds");
		}
		Button bm = (Button)v;
		Button bp = (Button)findViewById(R.id.button_plus);
		bp.setEnabled(true);

		if (i == 1)
			bm.setEnabled(false);
	}

}
