package com.chaumoha.a01;
/**
 * Taken from textbook
 * @author Android Programming: The Big Nerd Ranch Guide
 *
 */
public class TrueFalse {
	
	private int mQuestion;
	private boolean mTrueQuestion;

	public TrueFalse(int question, boolean trueQuestion) {
		mQuestion = question;
		mTrueQuestion = trueQuestion;
	}

	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}

	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}

	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}
}
