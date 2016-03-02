package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.chubbymobile.wwh.chubbymobile.DAL.DBManager;
import com.chubbymobile.wwh.chubbymobile.DAL.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseActivity extends Activity implements View.OnClickListener {

    private DBManager mgr;
    private ListView listView;
    Button btnToDB, btnToQuery, updateBtn, deleteBtn, removeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        listView = (ListView) findViewById(R.id.listView);
        mgr = new DBManager(this);

        btnToDB = (Button) findViewById(R.id.addBtn);
        btnToDB.setOnClickListener(this);
        btnToQuery = (Button) findViewById(R.id.queryBtn);
        btnToQuery.setOnClickListener(this);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(this);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(this);
        removeBtn = (Button) findViewById(R.id.removeBtn);
    }

    public void onClick(View view) {
        if (view == btnToDB) {
            add(view);
        }
        if (view == btnToQuery) {
            query(view);
        }
        if (view == updateBtn) {
            update(view);
        }
        if (view == deleteBtn) {
            delete(view);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mgr.closeDB();
    }

    public void add(View view) {
        ArrayList<Person> persons = new ArrayList<Person>();
        Person person1 = new Person("600800", 10, "C Hold");
        Person person2 = new Person("600801", 17, "D+ Sell");
        Person person3 = new Person("600802", 5, "C Hold");
        Person person4 = new Person("600803", 22, "A+ Buy");
        Person person5 = new Person("600804", 37, "D- Sell");
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);
        mgr.add(persons);
    }

    public void query(View view) {
        List<Person> persons = mgr.query();
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Person person : persons) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", person.name);
            map.put("info", person.age + " --> " + person.info);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
                new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }

    public void update(View view) {
        Person person = new Person();
        person.name = "600800";
        person.age = 110;
        int count = 0;
        count = mgr.updateAge(person);
        Toast.makeText(DatabaseActivity.this, "the number of rows affected: " + count, Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        Person person = new Person();
        person.age = 110;
        int count = 0;
        count = mgr.deleteOldPerson(person);
        Toast.makeText(DatabaseActivity.this, "the number of rows affected: " + count, Toast.LENGTH_SHORT).show();
    }

    public void PurgeAll(View view) {
        int count = 0;
        count = mgr.deleteAllPersons();
        Toast.makeText(DatabaseActivity.this, "the number of rows affected: " + count, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(DatabaseActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
