package com.chaumoha.a01;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Assignment 01 main class for android app
 * @author Bryan Chau &amp; Mohamed Mohamedtaki
 * @version 01/26/2014
 *
 */
public class QuizActivity extends Activity {
	
	private static final String TAG = "QUIZ_ACTIVITY";
	private static final String USER_CHEATED = "USER_CHEATED";
	private static final String CURRENT_INDEX = "CURRENT_INDEX";
	
	// Buttons
	private Button mButtonTrue, mButtonFalse, mButtonCheat;
	private ImageButton mButtonPrevious, mButtonNext;
	
	// TextView
	private TextView mTextViewQuestion;
	
	// current index
	private int mCurrentIndex = 0;
	
	// Questions
	private TrueFalse[] mQuestionBank = {
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
	};
	private boolean[] mIsCheater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		// Set Cheater
		mIsCheater = new boolean[mQuestionBank.length];
		Arrays.fill(mIsCheater, false);
		//Retrieve instance state
		if (savedInstanceState != null){
			
			mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX,0);
			mIsCheater = savedInstanceState.getBooleanArray(USER_CHEATED);
		}
		
		// Create the buttons, then add onClick listeners
		mButtonTrue = (Button) findViewById(R.id.true_button);
		mButtonTrue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				questionTest(true);
			}
		});
		mButtonFalse = (Button) findViewById(R.id.false_button);
		mButtonFalse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				questionTest(false);
			}
		});
		mButtonPrevious = (ImageButton) findViewById(R.id.previous_button);
		mButtonPrevious.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				moveNextQuestion(false);
			}
		});
		mButtonNext = (ImageButton) findViewById(R.id.next_button);
		mButtonNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				moveNextQuestion(true);
			}
		});
		
		// create textview
		mTextViewQuestion = (TextView)findViewById(R.id.question_text);
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mTextViewQuestion.setText(question);
		mTextViewQuestion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				moveNextQuestion(true);
			}
		});
		
		//cheat button
		mButtonCheat = (Button)findViewById(R.id.cheat_button);
		mButtonCheat.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(QuizActivity.this,CheatActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				i.putExtra(CheatActivity.EXTRA_ANSWER_SHOWN, mIsCheater[mCurrentIndex]);
				startActivityForResult(i,0);
			}
		});
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode,int resultCode, Intent data){
		if (data == null)
			return;
		mIsCheater[mCurrentIndex] = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
	}
	
	@Override
	public void onSaveInstanceState(Bundle b){
		super.onSaveInstanceState(b);
		Log.i(TAG,"save");
		b.putBooleanArray(USER_CHEATED, mIsCheater);
		b.putInt(CURRENT_INDEX, mCurrentIndex);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}
	
	/**
	 * Test if a boolean answer matches the question's answer
	 * @param a A boolean answer
	 */
	private void questionTest(boolean a) {
		int s;
		if (mIsCheater[mCurrentIndex]){
			s = R.string.judgment_toast;
		}else{
			if (mQuestionBank[mCurrentIndex].isTrueQuestion() == a) {
				s = R.string.correct_toast;
			} else {
				s = R.string.incorrect_toast;
			}
		}
		Toast.makeText(QuizActivity.this, s, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Move to the next or previous question
	 * @param b A boolean value indicating direction: true=next, false=previous</b>
	 */
	private void moveNextQuestion (boolean b) {
		if (b) {
			mCurrentIndex++;
		} else {
			// check to avoid indexoutofbound exception
			if (mCurrentIndex - 1 >= 0)
				mCurrentIndex--;
		}
		mCurrentIndex = mCurrentIndex % mQuestionBank.length;
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mTextViewQuestion.setText(question);
	}
}
