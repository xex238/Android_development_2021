package ru.novitskiy.lab_2_questionnaire_v10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    public final static int QA1_REQUEST_CODE = 1;
    public final static String Q_TYPE = "QUESTIONARY_TYPE";
    public final static int Q_BLEACH = 1;
    public final static int Q_MILLIONAIRE = 2;

    public static boolean q1_pass = false; // Пройден ли тест или нет
    public static int q1_size = -1; // Количество вопросов в опроснике
    public static int q1_true_answers = -1; // Количество правильных ответов от пользователя
    public static int q1_result_score = -1; // Результат (количество набранных бвллов)
    public static int q1_max_score = -1; // Максимальное количество баллов в тесте

    public final static int QA2_REQUEST_CODE = 2;

    public static boolean q2_pass = false;
    public static int q2_size = -1;
    public static int q2_true_answers = -1;
    public static int q2_result_score = -1;
    public static int q2_max_score = -1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Click_questionaire1(View view)
    {
        Intent intent = new Intent(this, QA_1.class);
/*        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) q1);*/
        intent.putExtra(Q_TYPE, Q_BLEACH);
        startActivityForResult(intent, QA1_REQUEST_CODE);
    }

    public void Click_questionaire2(View view)
    {
        Intent intent = new Intent(this, QA_1.class);
/*        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) q2);*/
        intent.putExtra(Q_TYPE, Q_MILLIONAIRE);
        startActivityForResult(intent, QA2_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_CANCELED)
        {
            return;
        }

        switch (requestCode)
        {
            case QA1_REQUEST_CODE:
                q1_pass = data.getBooleanExtra("q_pass", q1_pass);
                q1_true_answers = data.getIntExtra("q_true_answers", q1_true_answers);
                q1_result_score = data.getIntExtra("q_result_score", q1_result_score);
                q1_size = data.getIntExtra("q_size", q1_size);
                q1_max_score = data.getIntExtra("q_max_score", q1_max_score);
                break;
            case QA2_REQUEST_CODE:
                q2_pass = data.getBooleanExtra("q_pass", q2_pass);
                q2_true_answers = data.getIntExtra("q_true_answers", q2_true_answers);
                q2_result_score = data.getIntExtra("q_result_score", q2_result_score);
                q2_size = data.getIntExtra("q_size", q2_size);
                q2_max_score = data.getIntExtra("q_max_score", q2_max_score);
        }
    }

    public void Click_results(View view)
    {
        Intent intent = new Intent(this, ResultsActivity.class);

        intent.putExtra("q1_pass", q1_pass);
        intent.putExtra("q1_size", q1_size);
        intent.putExtra("q1_true_answers", q1_true_answers);
        intent.putExtra("q1_result_score", q1_result_score);
        intent.putExtra("q1_max_score", q1_max_score);

        intent.putExtra("q2_pass", q2_pass);
        intent.putExtra("q2_size", q2_size);
        intent.putExtra("q2_true_answers", q2_true_answers);
        intent.putExtra("q2_result_score", q2_result_score);
        intent.putExtra("q2_max_score", q2_max_score);

        startActivity(intent);
    }
}