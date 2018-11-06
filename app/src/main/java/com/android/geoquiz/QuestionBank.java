package com.android.geoquiz;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    private static QuestionBank mQuestionBank;
    private List<Question> questions;

    private QuestionBank(){
        questions=new ArrayList<>();
       questions.add(new Question(R.string.question_australia,true));
       questions.add(new Question(R.string.question_oceans,true));
       questions.add(new Question(R.string.question_mideast,false));
       questions.add(new Question(R.string.question_africa,false));
       questions.add(new Question(R.string.question_americas,true));
       questions.add(new Question(R.string.question_asia,true));

   }

   public static QuestionBank getQuestionBank(){
        if (mQuestionBank==null){
            mQuestionBank=new QuestionBank();
        }
        return mQuestionBank;
   }

    public List<Question> getQuestions() {
        return questions;
    }
}
