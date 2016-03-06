package com.chubbymobile.wwh.chubbymobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridViewMenuActivity extends Activity {

    private GridView mGridView;//MyGridView
    //定义图标数组
    private int[] imageRes = { R.drawable.surprise, R.drawable.angry,
            R.drawable.smiley, R.drawable.sad, R.drawable.surprise, R.drawable.sad,
            R.drawable.smiley, R.drawable.angry, R.drawable.surprise,
            R.drawable.angry, R.drawable.smiley, R.drawable.sad };
    //定义标题数组
    private String[] itemName = { "审前调查", "需求评估", "在册人员", "请销假", "集中教育",
            "个别教育", "心理测评", "生活量表", "矫正方案", "矫正建议", "出勤统计", "综合查询" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Activity标题不显示
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏显示
        setContentView(R.layout.activity_grid_view_menu);

        mGridView = (GridView) findViewById(R.id.MyGridView);
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int length = itemName.length;
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImageView", imageRes[i]);
            map.put("ItemTextView", itemName[i]);
            data.add(map);
        }
//为itme.xml添加适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(GridViewMenuActivity.this,
                data, R.layout.item, new String[] { "ItemImageView","ItemTextView" }, new int[] { R.id.ItemImageView,R.id.ItemTextView });
        mGridView.setAdapter(simpleAdapter);
//为mGridView添加点击事件监听器
        mGridView.setOnItemClickListener(new GridViewItemOnClick());
    }
    //定义点击事件监听器
    public class GridViewItemOnClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
            Toast.makeText(getApplicationContext(), position + "",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode== KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(GridViewMenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
