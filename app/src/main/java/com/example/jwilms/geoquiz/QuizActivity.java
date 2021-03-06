package com.example.jwilms.geoquiz;
//jan wilms
//CSC200
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String MYTAG = "quizactivity";
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank=new Question[]{
      new Question(R.string.question_australia,true),
      new Question(R.string.question_oceans,true),
      new Question(R.string.question_mideast,false),
      new Question(R.string.question_africa,false),
      new Question(R.string.question_americas,true),
      new Question(R.string.question_asia,true)
    };
    private int mCurrentIndex=0;

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(MYTAG,"called onstart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(MYTAG,"called onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(MYTAG,"called onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(MYTAG,"called onstop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(MYTAG,"called onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null) mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        Log.d(MYTAG,"called onCreate");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueButton= (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        System.out.println("true button was clicked");
//                        Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_LONG).show();
                        checkAnswer(true);
                        setButtons(false);;
                    }
                }
        );
        mFalseButton= (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        System.out.println("true button was clicked");
/*                        Toast mytoast= Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_LONG);//.show();
                        mytoast.setGravity(Gravity.TOP,0,10);
                        mytoast.show();
*/
                        setButtons(false);;
                        checkAnswer(false);
                        ;
                    }
                }

        );
        mNextButton= (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
//                        if (mCurrentIndex==mQuestionBank.length) { mCurrentIndex=0; }
                        updateQuestion();
//                        System.out.println("true button was clicked");
//                        Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_LONG).show();
                        setButtons(true);
                    }
                }
        );

    }

    private void updateQuestion(){
        int question= mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void setButtons(boolean newstate) {
            mTrueButton.setEnabled(newstate);
            mFalseButton.setEnabled(newstate);

    }

    private void checkAnswer(boolean userPressedTrue){
        if(userPressedTrue==mQuestionBank[mCurrentIndex].isAnswerTrue())
            Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_LONG).show();

    }
}
