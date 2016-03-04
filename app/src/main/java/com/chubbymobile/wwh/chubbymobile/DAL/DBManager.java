package com.chubbymobile.wwh.chubbymobile.DAL;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    public void add(List<Person> persons) {
        db.beginTransaction();
        try {
            for (Person person : persons) {
                db.execSQL("INSERT INTO person VALUES(null, ?, ?, ?)", new Object[]{person.name, person.age, person.info});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public int updateAge(Person person) {
        ContentValues cv = new ContentValues();
        cv.put("age", person.age);
        return db.update("person", cv, "name = ?", new String[]{person.name});
    }

    public int deleteOldPerson(Person person) {
        return db.delete("person", "age >= ?", new String[]{String.valueOf(person.age)});
    }

    public int deleteAllPersons() {
        return db.delete("person", null, null);
    }

    public List<Person> query() {
        ArrayList<Person> persons = new ArrayList<Person>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            Person person = new Person();
            person._id = c.getInt(c.getColumnIndex("_id"));
            person.name = c.getString(c.getColumnIndex("name"));
            person.age = c.getInt(c.getColumnIndex("age"));
            person.info = c.getString(c.getColumnIndex("info"));
            persons.add(person);
        }
        c.close();
        return persons;
    }

    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM person", null);
        return c;
    }

    public void closeDB() {
        db.close();
    }
}