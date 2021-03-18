package ru.novitskiy.lab_2_questionnaire_v10.QA;

import java.util.LinkedList;

public class Question
{
    int number = -1; // Номер вопроса
    String name = ""; // Формулировка вопроса

    LinkedList<String> answers = new LinkedList<String>(); // Варианты ответов

    int score = -1; // Количество баллов за правильный ответ
    int true_answer = -1; // Верный вариант ответа
    int answer = -1; // Ответ пользователя

    public Question(int number, String name, LinkedList<String> answers, int true_answer, int score)
    {
        this.number = number;
        this.name = name;
        this.answers = answers;
        this.true_answer = true_answer;
        this.score = score;
    }

    public int Get_number()
    {
        return number;
    }

    public String Get_name()
    {
        return name;
    }

    public LinkedList<String> Get_answers()
    {
        return answers;
    }

    public int Get_true_answer()
    {
        return true_answer;
    }

    public int Get_answer()
    {
        return answer;
    }

    public void Set_answer(int answer)
    {
        this.answer = answer;
    }

    public int Get_score()
    {
        return score;
    }
}
