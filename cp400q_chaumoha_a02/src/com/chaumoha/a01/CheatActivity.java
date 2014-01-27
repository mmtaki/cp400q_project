package com.chaumoha.a01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity{
	private static final String TAG = "CHEAT_ACTIVITY";
	public static final String EXTRA_ANSWER_IS_TRUE = "answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "answer_shown";
	private boolean mAnswerIsTrue;
	private boolean mHasCheated;
	
	private TextView mTextAnswer;
	private Button mButtonCheat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mHasCheated = getIntent().getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
		
		//Cheat
		mButtonCheat = (Button)findViewById(R.id.showAnswerButton);
		mButtonCheat.setOnClickListener( new View.OnClickListener() {		
		
			@Override
			public void onClick(View v) {
				setAnswerText();
				mHasCheated = true;
				setAnswerShownResult(mHasCheated);
			}
		});
		
		if (savedInstanceState != null){
			mHasCheated = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN);
			if (mHasCheated){
				setAnswerText();
			}
		}
		setAnswerShownResult(mHasCheated);
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle b){
		super.onSaveInstanceState(b);
		Log.i(TAG,"save");
		b.putBoolean(EXTRA_ANSWER_SHOWN, mHasCheated);
	}
	private void setAnswerShownResult(boolean isAnswerShown){
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	private void setAnswerText(){
		mTextAnswer = (TextView)findViewById(R.id.answerTextView);
		if (mAnswerIsTrue)
			mTextAnswer.setText(R.string.true_button);
		else
			mTextAnswer.setText(R.string.false_button);
	}
}
