package ru.novitskiy.lab_2_questionnaire_v10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity
{
    public static boolean q1_pass = false;
    public static int q1_size = -1;
    public static int q1_true_answers = -1;
    public static int q1_result_score = -1;
    public static int q1_max_score = -1;

    public static boolean q2_pass = false;
    public static int q2_size = -1;
    public static int q2_true_answers = -1;
    public static int q2_result_score = -1;
    public static int q2_max_score = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        SetQ1(intent);
        SetQ2(intent);
    }

    public void SetQ1(Intent intent)
    {
        q1_pass = intent.getBooleanExtra("q1_pass", MainActivity.q1_pass);
        q1_size = intent.getIntExtra("q1_size", MainActivity.q1_size);
        q1_true_answers = intent.getIntExtra("q1_true_answers", MainActivity.q1_true_answers);
        q1_result_score = intent.getIntExtra("q1_result_score", MainActivity.q1_result_score);
        q1_max_score = intent.getIntExtra("q1_max_score", MainActivity.q1_max_score);

        TextView textView = (TextView) findViewById(R.id.Q1PassResult_textView);
        if(q1_pass)
        {
            textView.setText("Да");

            textView = (TextView) findViewById(R.id.Q1TrueAnswersResult_textView);
            textView.setVisibility(View.VISIBLE);

            textView = (TextView) findViewById(R.id.Q1ScoresResult_textView);
            textView.setVisibility(View.VISIBLE);
        }
        else
        {
            textView.setText("Нет");

            textView = (TextView) findViewById(R.id.Q1TrueAnswersResult_textView);
            textView.setVisibility(View.INVISIBLE);

            textView = (TextView) findViewById(R.id.Q1ScoresResult_textView);
            textView.setVisibility(View.INVISIBLE);
        }

        textView = (TextView) findViewById(R.id.Q1TrueAnswersResult_textView);
        textView.setText(String.valueOf(q1_true_answers) + "/" + String.valueOf(q1_size));

        textView = (TextView) findViewById(R.id.Q1ScoresResult_textView);
        textView.setText(String.valueOf(q1_result_score) + "/" + String.valueOf(q1_max_score));
    }

    public void SetQ2(Intent intent)
    {
        q2_pass = intent.getBooleanExtra("q2_pass", MainActivity.q2_pass);
        q2_size = intent.getIntExtra("q2_size", MainActivity.q2_size);
        q2_true_answers = intent.getIntExtra("q2_true_answers", MainActivity.q2_true_answers);
        q2_result_score = intent.getIntExtra("q2_result_score", MainActivity.q2_result_score);
        q2_max_score = intent.getIntExtra("q2_max_score", MainActivity.q2_max_score);

        TextView textView = (TextView) findViewById(R.id.Q2PassResult_textView);
        if(q2_pass)
        {
            textView.setText("Да");

            textView = (TextView) findViewById(R.id.Q2TrueAnswersResult_textView);
            textView.setVisibility(View.VISIBLE);

            textView = (TextView) findViewById(R.id.Q2ScoresResult_textView);
            textView.setVisibility(View.VISIBLE);
        }
        else
        {
            textView.setText("Нет");

            textView = (TextView) findViewById(R.id.Q2TrueAnswersResult_textView);
            textView.setVisibility(View.INVISIBLE);

            textView = (TextView) findViewById(R.id.Q2ScoresResult_textView);
            textView.setVisibility(View.INVISIBLE);
        }

        textView = (TextView) findViewById(R.id.Q2TrueAnswersResult_textView);
        textView.setText(String.valueOf(q2_true_answers) + "/" + String.valueOf(q2_size));

        textView = (TextView) findViewById(R.id.Q2ScoresResult_textView);
        textView.setText(String.valueOf(q2_result_score) + "/" + String.valueOf(q2_max_score));
    }

    public void Click_back(View view)
    {
        finish();
    }
}