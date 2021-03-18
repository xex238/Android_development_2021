package ru.novitskiy.lab_2_questionnaire_v10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import ru.novitskiy.lab_2_questionnaire_v10.QA.Question;

public class QA_1 extends AppCompatActivity
{
    public final static String Q_TYPE = "QUESTIONARY_TYPE";
    public final static int Q_BLEACH = 1;
    public final static int Q_MILLIONAIRE = 2;

    ArrayList<Question> q1 = new ArrayList<Question>(); // Список вопросов первого опросника
    ArrayList<Question> q2 = new ArrayList<Question>(); // Список вопросов второго опросника
    ArrayList<Question> q; // Список вопросов первого опросника

    int current_question_number = -1;
    int count_of_questions = -1;
    boolean checked = false;

    int count_of_true_answers = 0;
    int result_score = 0;
    int max_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_a_1);

        Set_questionaire1();
        Set_questionaire2();

        Intent intent = getIntent();
        int q_type = intent.getIntExtra(Q_TYPE, -1);

        if(q_type == Q_BLEACH)
        {
            q = (ArrayList<Question>) q1.clone();
        }
        else if(q_type == Q_MILLIONAIRE)
        {
            q = (ArrayList<Question>) q2.clone();
        }

        Button back_button = (Button) findViewById(R.id.back_button);
        back_button.setEnabled(false);

        count_of_questions = q.size();
        current_question_number = 0;
        Set_current_question();
    }

    // Инициализация вопросов первого опросника
    public void Set_questionaire1()
    {
        int number;
        String name;
        LinkedList<String> answer1 = new LinkedList<String>();
        int true_answer;
        int score;

        number = 1;
        name = "Кто является капитаном шестого отряда?";
        answer1.add("Ямомото Генрюсай");
        answer1.add("Бякуя Кучики");
        answer1.add("Зараки Кенпачи");
        answer1.add("Тоширо Хицугая");
        true_answer = 2;
        score = 1;

        q1.add(new Question(number, name, (LinkedList<String>) answer1.clone(), true_answer, score));

        number = 2;
        name = "Как зовут плюшевого львёнка Ичиго?";
        answer1.clear();
        answer1.add("Кейго");
        answer1.add("Айзен");
        answer1.add("Кон");
        answer1.add("Мидзуиро");
        true_answer = 3;
        score = 1;

        q1.add(new Question(number, name, (LinkedList<String>)answer1.clone(), true_answer, score));

        number = 3;
        name = "Какое имя носит банкай Ичиго?";
        answer1.clear();
        answer1.add("Тенса Зангецу");
        answer1.add("Занка но Тачи");
        answer1.add("Минадзуки");
        answer1.add("Сенбонзакура Кагеёши");
        true_answer = 1;
        score = 1;

        q1.add(new Question(number, name, (LinkedList<String>)answer1.clone(), true_answer, score));
    }

    // Инициализация вопросов второго опросника
    public void Set_questionaire2()
    {
        int number;
        String name;
        LinkedList<String> answer1 = new LinkedList<String>();
        int true_answer;
        int score;

        number = 1;
        name = "Сколько раз в сутки подзаводят куранты Спасской башни Кремля?";
        answer1.add("Один");
        answer1.add("Два");
        answer1.add("Три");
        answer1.add("Четыре");
        true_answer = 2;
        score = 1;

        q2.add(new Question(number, name, (LinkedList<String>) answer1.clone(), true_answer, score));

        number = 2;
        name = "В какой из этих столиц бывших союзных республик раньше появилось метро?";
        answer1.clear();
        answer1.add("Тбилиси");
        answer1.add("Ереван");
        answer1.add("Баку");
        answer1.add("Минск");
        true_answer = 1;
        score = 2;

        q2.add(new Question(number, name, (LinkedList<String>)answer1.clone(), true_answer, score));

        number = 3;
        name = "Сколько морей омывают Балканский полуостров?";
        answer1.clear();
        answer1.add("3");
        answer1.add("4");
        answer1.add("5");
        answer1.add("6");
        true_answer = 4;
        score = 3;

        q2.add(new Question(number, name, (LinkedList<String>)answer1.clone(), true_answer, score));
    }

    private void Set_current_question()
    {
        TextView question_number = (TextView) findViewById(R.id.questionNumber_textView);
        question_number.setText("Вопрос №" + String.valueOf(q.get(current_question_number).Get_number()));
        TextView question_name = (TextView) findViewById(R.id.question_textView);
        question_name.setText(q.get(current_question_number).Get_name());

        CheckBox checkBox = (CheckBox) findViewById(R.id.answer_1_checkBox);
        checkBox.setText(q.get(current_question_number).Get_answers().get(0));
        if(q.get(current_question_number).Get_answer() == 1)
        {
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }

        checkBox = (CheckBox) findViewById(R.id.answer_2_checkBox);
        checkBox.setText(q.get(current_question_number).Get_answers().get(1));
        checkBox.setChecked(false);
        if(q.get(current_question_number).Get_answer() == 2)
        {
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }

        checkBox = (CheckBox) findViewById(R.id.answer_3_checkBox);
        checkBox.setText(q.get(current_question_number).Get_answers().get(2));
        checkBox.setChecked(false);
        if(q.get(current_question_number).Get_answer() == 3)
        {
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }

        checkBox = (CheckBox) findViewById(R.id.answer_4_checkBox);
        checkBox.setText(q.get(current_question_number).Get_answers().get(3));
        checkBox.setChecked(false);
        if(q.get(current_question_number).Get_answer() == 4)
        {
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }

        if(q.get(current_question_number).Get_answer() == -1)
        {
            checked = false;
        }
        else
        {
            checked = true;
        }
    }

    public void Finish(View view)
    {
        finish();
    }

    public void Forward(View view)
    {
        if(current_question_number + 1 < count_of_questions)
        {
            current_question_number++;
            Set_current_question();

            Button back_button = (Button) findViewById(R.id.back_button);
            back_button.setEnabled(true);
        }
        if(current_question_number + 1 == count_of_questions)
        {
            Button forward_button = (Button) findViewById(R.id.forward_button);
            forward_button.setEnabled(false);

            Button complete_button = (Button) findViewById(R.id.CompleteQuestionaire_button);
            complete_button.setVisibility(View.VISIBLE);
        }
    }

    public void Back(View view)
    {
        if(current_question_number - 1 >= 0)
        {
            current_question_number--;
            Set_current_question();

            Button forward_button = (Button) findViewById(R.id.forward_button);
            forward_button.setEnabled(true);

            Button complete_button = (Button) findViewById(R.id.CompleteQuestionaire_button);
            complete_button.setVisibility(View.INVISIBLE);
        }
        if(current_question_number - 1 < 0)
        {
            Button back_button = (Button) findViewById(R.id.back_button);
            back_button.setEnabled(false);
        }
    }

    public void Checked(View view)
    {
        int checked_id = view.getId();
        int answer = -1;

        switch (checked_id)
        {
            case R.id.answer_1_checkBox:
                answer = 1;
                break;
            case R.id.answer_2_checkBox:
                answer = 2;
                break;
            case R.id.answer_3_checkBox:
                answer = 3;
                break;
            case R.id.answer_4_checkBox:
                answer = 4;
                break;
        }

        CheckBox checkBox = (CheckBox) findViewById(checked_id);

        if(!checked)
        {
            checked = true;
            q.get(current_question_number).Set_answer(answer);
        }
        else if(checked && checkBox.isChecked())
        {
            checkBox.setChecked(false);
        }
        else if(checked && !checkBox.isChecked())
        {
            checked = false;
            q.get(current_question_number).Set_answer(-1);
        }
    }

    public void Computing_answers(View view)
    {
        for(int i = 0; i< q.size(); i++)
        {
            if(q.get(i).Get_true_answer() == q.get(i).Get_answer())
            {
                count_of_true_answers++;
                result_score = result_score + q.get(i).Get_score();
            }
            else if(q.get(i).Get_answer() == -1)
            {
                // Вывод окна с сообщением, что ответ дан не на все вопросы
                return;
            }

            max_score = max_score + q.get(i).Get_score();
        }

        Intent intent = new Intent();
        intent.putExtra("q_pass", true);
        intent.putExtra("q_size", q.size());
        intent.putExtra("q_true_answers", count_of_true_answers);
        intent.putExtra("q_result_score", result_score);
        intent.putExtra("q_max_score", max_score);
        setResult(RESULT_OK, intent);

        finish();
    }
}