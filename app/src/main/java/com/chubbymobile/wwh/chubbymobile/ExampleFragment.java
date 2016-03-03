package com.chubbymobile.wwh.chubbymobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExampleFragment extends Fragment
{
    //三个一般必须重载的方法
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        System.out.println("ExampleFragment--onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        System.out.println("ExampleFragment--onCreateView");
        return inflater.inflate(R.layout.example_fragment_layout, container, false);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        System.out.println("ExampleFragment--onPause");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        System.out.println("ExampleFragment--onResume");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        System.out.println("ExampleFragment--onStop");
    }
}