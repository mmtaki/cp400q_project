package com.chaumoha.a01;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	// Buttons
	private Button mButtonTrue, mButtonFalse;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
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
		if (mQuestionBank[mCurrentIndex].isTrueQuestion() == a) {
			s = R.string.correct_toast;
		} else {
			s = R.string.incorrect_toast;
		}
		Toast.makeText(QuizActivity.this, s, Toast.LENGTH_SHORT).show();
	}
	
	private void moveNextQuestion (boolean b) {
		if (b) {
			mCurrentIndex++;
		} else {
			if (mCurrentIndex - 1 >= 0)
				mCurrentIndex--;
		}
		mCurrentIndex = mCurrentIndex % mQuestionBank.length;
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mTextViewQuestion.setText(question);
	}
}
