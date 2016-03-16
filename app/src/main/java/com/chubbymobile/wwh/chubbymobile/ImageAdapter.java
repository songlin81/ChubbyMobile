package com.chubbymobile.wwh.chubbymobile;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.GridView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c)
    {
        mContext=c;
    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageview;
        if(convertView==null)
        {
            imageview=new ImageView(mContext);
            imageview.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageview.setPadding(8,8,8,8);
        }
        else
        {
            imageview=(ImageView) convertView;
        }
        imageview.setImageResource(mThumbIds[position]);
        return imageview;
    }

    private Integer[] mThumbIds={
            R.drawable.angry,R.drawable.sad,
            R.drawable.smiley,R.drawable.surprise,
            R.drawable.angry,R.drawable.sad,
            R.drawable.smiley,R.drawable.surprise,
            R.drawable.angry,R.drawable.sad,
            R.drawable.smiley,R.drawable.surprise,
            R.drawable.angry,R.drawable.sad,
            R.drawable.smiley,R.drawable.surprise,
            R.drawable.angry,R.drawable.sad,
            R.drawable.smiley,R.drawable.surprise,
            R.drawable.angry,R.drawable.sad,
    };
}