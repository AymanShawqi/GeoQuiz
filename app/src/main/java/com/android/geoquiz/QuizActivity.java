package com.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "QuizActivity";
    private static final String CURRENT_INDEX="com.android.geoquize.current_index";
    private static final String CURRENT_GRADE="current_grade";
    private Button mTrueBtn;
    private Button mFalseBtn;
    private Button mShowGradeBtn;
    private Button mRestBtn;
    private ImageButton mNextBtn;
    private ImageButton mPrevBtn;
    private TextView mQuestionTextView;
    private List<Question> mQuestionList;
    private int mCurrentIndex;
    private int mGrade;
    private Question mQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState !=null){
            mCurrentIndex=savedInstanceState.getInt(CURRENT_INDEX,0);
            mGrade=savedInstanceState.getInt(CURRENT_GRADE,0);
        }

        mTrueBtn=findViewById(R.id.true_button);
        mFalseBtn=findViewById(R.id.false_button);
        mNextBtn=findViewById(R.id.next_button);
        mPrevBtn=findViewById(R.id.previous_button);
        mShowGradeBtn=findViewById(R.id.grade_id);
        mRestBtn=findViewById(R.id.reset_id);
        mQuestionTextView=findViewById(R.id.question_text_view);
        mQuestionList=QuestionBank.getQuestionBank().getQuestions();
        displayQuestions();
        mTrueBtn.setOnClickListener(this);
        mFalseBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mPrevBtn.setOnClickListener(this);
        mShowGradeBtn.setOnClickListener(this);
        mRestBtn.setOnClickListener(this);

    }
    void checkAnswerBtn(){
        if (mQuestion.isUserAnswered()){
            mTrueBtn.setEnabled(false);
            mFalseBtn.setEnabled(false);
        }
        else {
            mTrueBtn.setEnabled(true);
            mFalseBtn.setEnabled(true);
        }
    }

    void displayQuestions(){
        mQuestion=mQuestionList.get(mCurrentIndex);
        mQuestionTextView.setText(mQuestion.getTextResId());
        checkAnswerBtn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.next_button:
                mCurrentIndex=(mCurrentIndex+1)%mQuestionList.size();
                displayQuestions();
                break;
            case R.id.previous_button:
                mCurrentIndex=(mCurrentIndex+mQuestionList.size()-1)% mQuestionList.size();
                displayQuestions();
                break;
            case R.id.grade_id:
                showGrade();
                break;
            case R.id.reset_id:
                reset();
                break;
        }
    }

    void checkAnswer(Boolean userAAnswer){
        int msg;
        if(mQuestion.isAnswerTrue()==userAAnswer){
            msg=R.string.correct_toast;
            mGrade++;
        }
        else {
            msg=R.string.incorrect_toast;
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        mQuestion.setUserAnswered(true);
        checkAnswerBtn();
    }

    void showGrade(){
        Toast.makeText(this,"Your Grade is : "+mGrade,Toast.LENGTH_SHORT).show();
    }

    void reset(){
        mCurrentIndex=0;
        mGrade=0;
        for(Question question : mQuestionList){
            question.setUserAnswered(false);
        }
        displayQuestions();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_INDEX,mCurrentIndex);
        outState.putInt(CURRENT_GRADE,mGrade);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"onRestoreInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"on start called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"on Resumed is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy is called");
    }
}
