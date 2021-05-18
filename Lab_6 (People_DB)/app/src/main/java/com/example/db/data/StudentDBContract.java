package com.example.db.data;

import android.provider.BaseColumns;

public final class StudentDBContract
{
    public static class Students implements BaseColumns
    {
        public static final String tableName = "Students";
        public static final String _ID = BaseColumns._ID;
        public static final String columnSurname = "Surname";
        public static final String columnName = "Name";
    }


}
