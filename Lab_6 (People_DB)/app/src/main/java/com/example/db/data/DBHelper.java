package com.example.db.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.db.data.StudentDBContract.*;


public class DBHelper extends SQLiteOpenHelper
{
    public static final String databaseName = "studentDb.db";
    public static final int databaseVersion = 1;

    public DBHelper(Context context)
    {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "%s TEXT, "
                        + "%s TEXT);",
                Students.tableName,
                Students._ID,
                Students.columnSurname,
                Students.columnName);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("DROP TABLE IF EXISTS ").append(Students.tableName);
        db.execSQL(sql.toString());
        onCreate(db);
    }
}
