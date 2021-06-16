package ru.novitskiy.calc_v_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{
    private static final String PI = "3.1416";
    private static final String E = "2.7183";

    int start_num_pos = 0;
    int end_num_pos = 0;

    ArrayList<Double> numbers = new ArrayList<Double>();
    ArrayList<String> signs = new ArrayList<String>();

    LinkedList<Integer> array_start_num_pos = new LinkedList<Integer>();
    LinkedList<Integer> array_end_num_pos = new LinkedList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mm_item_1:
                Intent intent1 = new Intent(this, Eng_calc.class);
                startActivity(intent1);
                break;
            case R.id.mm_item_2:
                Intent intent2 = new Intent(this, Reg_calc.class);
                startActivity(intent2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // Изменение отображения в текстовом окне значения start_num_pos
    public void Change_start_num(int value)
    {
        try
        {
            TextView view_result = (TextView)findViewById(R.id.start_text_number);
            view_result.setText(String.valueOf(value));
        }
        catch (Exception e)
        {
            TextView view_result = (TextView)findViewById(R.id.result_textView);
            view_result.setText("Problem with change start");
        }
    }

    // Изменение отображения в текстовом окне значения end_num_pos
    public void Change_end_num(int value)
    {
        try
        {
            TextView view_result = (TextView)findViewById(R.id.end_text_number);
            view_result.setText(String.valueOf(value));
        }
        catch (Exception e)
        {
            TextView view_result = (TextView)findViewById(R.id.result_textView);
            view_result.setText("Problem with change end");
        }
    }

    public void Change_numbers_size()
    {
        try
        {
            TextView view_result = (TextView)findViewById(R.id.NumberSizeTextNumber);
            view_result.setText(String.valueOf(numbers.size()));
        }
        catch (Exception e)
        {
            TextView view_result = (TextView)findViewById(R.id.result_textView);
            view_result.setText("Problem with change");
        }
    }

    // Кнопка "С" (Очистка поля ввода)
    public void Clear_results(View view)
    {
        TextView view_result = (TextView)findViewById(R.id.result_textView);
        view_result.setHint("0");
        view_result.setText("");

        start_num_pos = 0;
        end_num_pos = 0;

        Change_start_num(start_num_pos);
        Change_end_num(end_num_pos);

        while (array_start_num_pos.size() > 0)
        {
            array_start_num_pos.removeLast();
        }

        while (array_end_num_pos.size() > 0)
        {
            array_end_num_pos.removeLast();
        }

        while (numbers.size() > 0)
        {
            numbers.remove(numbers.size() - 1);
        }

        while (signs.size() > 0)
        {
            signs.remove(signs.size() - 1);
        }

        Change_numbers_size();
    }

    // Кнопка "DEL" (Удаление последнего символа)
    public void Delete_last(View view)
    {
        TextView view_result = (TextView)findViewById(R.id.result_textView);
        String buffer = (String) view_result.getText();
        String new_buffer = "";

        if(buffer.length() > 0)
        {
            // Если удаляем последнюю цифру числа
            if(end_num_pos - start_num_pos == 1)
            {
                numbers.remove(numbers.size() - 1);
                end_num_pos--;
                Change_end_num(end_num_pos);
            }
            // Если удаляем знак (но не mod)
            else if(end_num_pos - start_num_pos == 0)
            {
                signs.remove(signs.size() - 1);

                start_num_pos = array_start_num_pos.get(array_start_num_pos.size() - 1);
                end_num_pos = array_end_num_pos.get(array_end_num_pos.size() - 1);
                Change_start_num(start_num_pos);
                Change_end_num(end_num_pos);

                array_start_num_pos.remove(array_start_num_pos.size() - 1);
                array_end_num_pos.remove(array_end_num_pos.size() - 1);
            }
            // Если удаляем цифру из числа, но не последнюю
            else
            {
                numbers.remove(numbers.size() - 1);

                end_num_pos--;
                Change_end_num(end_num_pos);

                numbers.add(Double.valueOf(buffer.substring(start_num_pos, end_num_pos)));
            }

            new_buffer = buffer.substring(0, end_num_pos);

            view_result.setText(new_buffer);
        }
    }

    // Нажатие кнопки с цифрой
    public void Set_numeral(View view)
    {
        TextView view_result = (TextView)findViewById(R.id.result_textView);
        String buffer = (String) view_result.getText();

        int btn_id = view.getId();
        int start_end_num_pos = end_num_pos;

        switch (btn_id)
        {
            case R.id.button_0:
                if(end_num_pos - start_num_pos != 0)
                    buffer += "0";
                else
                {
                    end_num_pos--;
                    Change_end_num(end_num_pos);
                }
                break;
            case R.id.button_1:
                buffer += "1";
                break;
            case R.id.button_2:
                buffer += "2";
                break;
            case R.id.button_3:
                buffer += "3";
                break;
            case R.id.button_4:
                buffer += "4";
                break;
            case R.id.button_5:
                buffer += "5";
                break;
            case R.id.button_6:
                buffer += "6";
                break;
            case R.id.button_7:
                buffer += "7";
                break;
            case R.id.button_8:
                buffer += "8";
                break;
            case R.id.button_9:
                buffer += "9";
                break;
            default:
                return;
        }

        end_num_pos++;
        Change_end_num(end_num_pos);

        if(start_end_num_pos == end_num_pos)
        {

        }
        else if((end_num_pos - start_num_pos != 1))
        {
            numbers.remove(numbers.size() - 1);
            numbers.add(Double.valueOf(buffer.substring(start_num_pos, end_num_pos)));
        }
        else
        {
            numbers.add(Double.valueOf(buffer.substring(start_num_pos, end_num_pos)));
        }

        Change_numbers_size();
        view_result.setText(buffer);
    }

    // Вывод констант (пи, е)
    public void Set_number(View view)
    {
        TextView view_result = (TextView)findViewById(R.id.result_textView);
        String buffer = (String) view_result.getText();
        String new_buffer = buffer.substring(0, start_num_pos);

        int btn_id = view.getId();

        switch (btn_id)
        {
            case R.id.button_pi:
                new_buffer += PI;
                break;
            case R.id.button_e:
                new_buffer += E;
                break;
            default:
                return;
        }

        if(end_num_pos - start_num_pos != 0)
        {
            numbers.remove(numbers.size() - 1);
        }
        numbers.add(Double.valueOf(PI));

        end_num_pos = start_num_pos + 6;
        Change_end_num(end_num_pos);

        view_result.setText(new_buffer);
    }

    // Вывод точки на экран
    public void Set_point(View view)
    {
        TextView view_result = (TextView)findViewById(R.id.result_textView);
        String buffer = (String) view_result.getText();
        String new_buffer = "";

        int index_of_point = buffer.substring(start_num_pos, end_num_pos).indexOf(".");
        if((index_of_point == -1) && (end_num_pos - start_num_pos != 0))
        {
            new_buffer = buffer += ".";
            view_result.setText(new_buffer);
            end_num_pos++;
            Change_end_num(end_num_pos);
        }
    }

    // Вычисление значения для односторонней операции
    public void Set_uniteral_operation(View view)
    {
        TextView view_result = (TextView)findViewById(R.id.result_textView);
        String buffer = (String) view_result.getText();
        String new_buffer = buffer.substring(0, start_num_pos);

        int btn_id = view.getId();

        try
        {
            switch (btn_id)
            {
                case R.id.button_factorial:
                    int value1 = Integer.valueOf(buffer.substring(start_num_pos, end_num_pos));
                    int result1 = 1;
                    for(int i=1; i<=value1; i++)
                    {
                        result1 = result1 * i;
                    }

                    String result_string1 = String.valueOf(result1);
                    new_buffer += result_string1;

                    numbers.remove(numbers.size() - 1);
                    numbers.add(Double.valueOf(result1));

                    end_num_pos = start_num_pos + result_string1.length();
                    Change_end_num(end_num_pos);

                    break;
                case R.id.button_sqrt:
                    double value2 = Double.valueOf(buffer.substring(start_num_pos, end_num_pos));
                    double result2 = Math.sqrt(value2);

                    String result_string2;

                    if(result2 == (int)result2)
                    {
                        int result_int = (int)result2;
                        result_string2 = String.valueOf(result_int);
                    }
                    else
                    {
                        result_string2 = String.valueOf(result2);
                    }

                    new_buffer += result_string2;

                    numbers.remove(numbers.size() - 1);
                    numbers.add(Double.valueOf(result_string2));

                    end_num_pos = start_num_pos + result_string2.length();
                    Change_end_num(end_num_pos);
            }

            view_result.setText(new_buffer);
        }
        catch (Exception e)
        {

        }
    }

    // Вывод знака на экран
    public String Set_sign(String sign, String buffer)
    {
        if((end_num_pos - start_num_pos != 0) && (buffer.substring(buffer.length() - 1, buffer.length()) != "."))
            try
            {
                signs.add(sign);
                array_start_num_pos.add(start_num_pos);
                array_end_num_pos.add(end_num_pos);
                start_num_pos = end_num_pos + sign.length();
                end_num_pos = end_num_pos + sign.length();
                Change_start_num(start_num_pos);
                Change_end_num(end_num_pos);
                buffer += sign;
            }
            catch (Exception e)
            {

            }

        return buffer;
    }

    // Добавление знака двусторонней операции
    public void Set_bilateral_operation(View view)
    {
        TextView view_result = (TextView)findViewById(R.id.result_textView);
        String buffer = (String) view_result.getText();

        if((end_num_pos - start_num_pos != 0) && (buffer.substring(buffer.length() - 1, buffer.length()) != "."))
        {
            int btn_id = view.getId();

            switch (btn_id)
            {
                case R.id.button_plus:
                    buffer = Set_sign("+", buffer);
                    break;
                case R.id.button_minus:
                    buffer = Set_sign("-", buffer);
                    break;
                case R.id.button_devide:
                    buffer = Set_sign("/", buffer);
                    break;
                case R.id.button_multiply:
                    buffer = Set_sign("*", buffer);
                    break;
                case R.id.button_degree:
                    buffer = Set_sign("^", buffer);
                    break;
                case R.id.button_mod:
                    buffer = Set_sign("mod", buffer);
                    break;
                default:
                    return;
            }

            view_result.setText(buffer);
            Change_numbers_size();
        }
    }

    // Вспомогательный метод для Set_equals
    public void Shift(int i, double result)
    {
        numbers.remove(i+1);
        numbers.remove(i);
        numbers.add(i, result);
        signs.remove(i);
    }

    // Вычисление итогового значения
    public void Set_equals(View view)
    {
        TextView view_result = (TextView)findViewById(R.id.result_textView);
        String buffer = (String) view_result.getText();

        double result = 0.0;

        if(signs.size() == numbers.size())
        {
            signs.remove(signs.size() - 1);
        }

        try
        {
            for(int i=0; i<signs.size(); i++)
            {
                switch(signs.get(i))
                {
                    case "^":
                        result = Math.pow(numbers.get(i), numbers.get(i+1));
                        Shift(i, result);
                        i--;
                        break;
                }
            }

            for(int i=0; i<signs.size(); i++)
            {
                switch (signs.get(i))
                {
                    case "*":
                        result = numbers.get(i)*numbers.get(i+1);
                        Shift(i, result);
                        i--;
                        break;
                    case "/":
                        result = numbers.get(i)/numbers.get(i+1);
                        Shift(i, result);
                        i--;
                        break;
                    case "mod":
                        result = numbers.get(i)%numbers.get(i+1);
                        Shift(i, result);
                        i--;
                        break;
                }
            }

            for(int i=0; i<signs.size(); i++)
            {
                switch (signs.get(i))
                {
                    case "+":
                        result = numbers.get(i)+numbers.get(i+1);
                        Shift(i, result);
                        i--;
                        break;
                    case "-":
                        result = numbers.get(i)-numbers.get(i+1);
                        Shift(i, result);
                        i--;
                        break;
                }
            }

            result = numbers.get(0);

            Clear_results(view);

            numbers.add(result);

            if(result == (int)result)
            {
                int result_int = (int)result;

                view_result.setText(String.valueOf(result_int));

                end_num_pos = String.valueOf(result_int).length();
                Change_end_num(end_num_pos);
            }
            else
            {
                view_result.setText(String.valueOf(result));

                end_num_pos = String.valueOf(result).length();
                Change_end_num(end_num_pos);
            }
        }
        catch (Exception E)
        {
            buffer = "Calculating error";
            view_result.setText(buffer);
        }
    }
}