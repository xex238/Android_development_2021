package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db.data.DBHelper;
import com.example.db.data.StudentDBContract.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private DBHelper dbHelper = null;
    private EditText surnameText = null;
    private EditText nameText = null;

    String temp_name = "";
    String temp_surname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        surnameText = (EditText) findViewById(R.id.surname_TextView);
        nameText = (EditText) findViewById(R.id.name_TextView);

        TextView nameTW = (TextView)findViewById(R.id.name_TextView);
        TextView surnameTW = (TextView)findViewById(R.id.surname_TextView);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                try
                {
                    String[] name_surname = ((TextView)view).getText().toString().split(" ");
                    String name = name_surname[0];
                    String surname = name_surname[1];
                    nameTW.setText(name);
                    surnameTW.setText(surname);
                }
                catch (Exception e)
                {

                }
            }
        });
    }

    public void insertButtononClick(View view)
    {
        String sql = String.format(
                "INSERT INTO %s (%s, %s) VALUES ('%s', '%s');",
                Students.tableName,
                Students.columnSurname,
                Students.columnName,
                surnameText.getText().toString(),
                nameText.getText().toString()
        );
        try
        {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL(sql);
            surnameText.setText("");
            nameText.setText("");

            String text = getString(R.string.success_insert);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();
        }
        catch(SQLException ex)
        {
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String text = getString(R.string.fail_insert);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void deleteButtononClick(View view)
    {
        String sql = String.format(
                "DELETE FROM %s "
                        + "WHERE %s = '%s' "
                        + "AND %s = '%s';",
                Students.tableName,
                Students.columnSurname,
                surnameText.getText().toString(),
                Students.columnName,
                nameText.getText().toString()
        );
        try
        {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL(sql);
            surnameText.setText("");
            nameText.setText("");
            viewButtononClick(view);

            String text = getString(R.string.success_delete);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();
        }
        catch(SQLException ex)
        {
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String text = getString(R.string.fail_delete);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void viewButtononClick(View view)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s;", Students.tableName);
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<String> stringArrayList = new ArrayList<>();
        try
        {
            String surname;
            String name;
            String info;
            int surnameIndex = cursor.getColumnIndex(Students.columnSurname);
            int nameIndex = cursor.getColumnIndex(Students.columnName);
            while (cursor.moveToNext())
            {
                surname = cursor.getString(surnameIndex);
                name = cursor.getString(nameIndex);

                stringArrayList.add(String.format(getResources().getString(R.string.list_view_item2), name, surname));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, stringArrayList);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            String text = getString(R.string.success_view);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();
        }
        catch (Exception e)
        {
            String text = getString(R.string.fail_view);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();
        }
        finally
        {
            cursor.close();
        }
    }

    public void showButtononClick(View view)
    {
        String name;
        String surname;

        TextView nameTW = (TextView)findViewById(R.id.name_TextView);
        temp_name = nameTW.getText().toString();

        TextView surnameTW = (TextView)findViewById(R.id.surname_TextView);
        temp_surname = surnameTW.getText().toString();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = String.format("SELECT * FROM %1$s WHERE (%2$s = '%3$s' and %4$s = '%5$s');", Students.tableName, Students.columnName, temp_name, Students.columnSurname, temp_surname);
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<String> stringArrayList = new ArrayList<>();
        try
        {
            String info;
            int surnameIndex = cursor.getColumnIndex(Students.columnSurname);
            int nameIndex = cursor.getColumnIndex(Students.columnName);
            while (cursor.moveToNext())
            {
                surname = cursor.getString(surnameIndex);
                name = cursor.getString(nameIndex);

                stringArrayList.add(String.format(getResources().getString(R.string.list_view_item2), name, surname));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, stringArrayList);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            String text = getString(R.string.success_show);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();

            Button insert_btn = (Button)findViewById(R.id.insertButton);
            insert_btn.setEnabled(false);

            Button delete_btn = (Button)findViewById(R.id.deleteButton);
            delete_btn.setEnabled(false);

            Button view_btn = (Button)findViewById(R.id.viewButton);
            view_btn.setEnabled(false);

            Button back_btn = (Button)findViewById(R.id.back_button);
            back_btn.setEnabled(true);

            Button update_btn = (Button)findViewById(R.id.update_button);
            update_btn.setEnabled(true);

            TextView mainTW = (TextView)findViewById(R.id.main_textView);
            mainTW.setText(R.string.write_new_values);
        }
        catch (Exception e)
        {
            String text = getString(R.string.fail_show);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();
        }
        finally
        {
            cursor.close();
        }
    }

    public void backButtononClick(View view)
    {
        Button insert_btn = (Button)findViewById(R.id.insertButton);
        insert_btn.setEnabled(true);

        Button delete_btn = (Button)findViewById(R.id.deleteButton);
        delete_btn.setEnabled(true);

        Button view_btn = (Button)findViewById(R.id.viewButton);
        view_btn.setEnabled(true);

        Button back_btn = (Button)findViewById(R.id.back_button);
        back_btn.setEnabled(false);

        Button update_btn = (Button)findViewById(R.id.update_button);
        update_btn.setEnabled(false);

        TextView mainTW = (TextView)findViewById(R.id.main_textView);
        mainTW.setText(R.string.main_text);
    }

    public void updateButtononClick(View view)
    {
        TextView nameTW = (TextView)findViewById(R.id.name_TextView);
        String name = nameTW.getText().toString();

        TextView surnameTW = (TextView)findViewById(R.id.surname_TextView);
        String surname = surnameTW.getText().toString();

        String sql = String.format(
                "UPDATE %1$s SET %2$s = '%3$s', %4$s = '%5$s' "
                        + "WHERE %2$s = '%6$s' "
                        + "AND %4$s = '%7$s';",
                Students.tableName,
                Students.columnSurname,
                surname,
                Students.columnName,
                name,
                temp_surname,
                temp_name
        );
        try
        {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL(sql);
            surnameText.setText("");
            nameText.setText("");
            viewButtononClick(view);

            backButtononClick(view);

            String text = getString(R.string.success_update);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();
        }
        catch(SQLException ex)
        {
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String text = getString(R.string.fail_update);
            Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}