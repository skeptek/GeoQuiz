package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	public static final String EXTRA_ANSWER_IS_TRUE = 
			"com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = 
			"com.bignerdranch.android.geoquiz.answer_shown";
	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	private boolean mShownAnswer = false;
	
	private void setAnswerShownResult(boolean isAnswerShown) {
		mShownAnswer = isAnswerShown;
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
		mAnswerIsTrue = getIntent().getBooleanExtra(this.EXTRA_ANSWER_IS_TRUE, false);
		
		// Answer will not be shown until the user
		// presses the button
		//setAnswerShownResult(false);
		if (savedInstanceState != null) {
			setAnswerShownResult(savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false));
			if(this.mShownAnswer) { 
				if (mAnswerIsTrue) {
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
			}
		} else {
			setAnswerShownResult(false);
		}
		
		mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mAnswerIsTrue) {
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
			}
		});
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, mShownAnswer);
	}
}
