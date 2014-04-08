package com.LRA;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.LRA.GridArrayAdapter.Item;
import com.LRA.R;

public class GridArrayAdapter extends ArrayAdapter<Item> {
	private Context mContext;
	private ArrayList<Item> mItems;
	private int layoutResourceId;
	
    public GridArrayAdapter(Context c,ArrayList<Item> items) {
    	super(c,0);
        mContext = c;
        mItems = items;
        layoutResourceId = R.layout.grid_row;
    }

    public int getCount() {
        return mItems.size();
    }

    public Item getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Item item = mItems.get(position);
        
        if (row == null){
        	LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        	row = inflater.inflate(layoutResourceId, parent,false);
        }
        TextView text = (TextView)row.findViewById(R.id.gridRowTextView);
        ImageView image = (ImageView)row.findViewById(R.id.gridRowImageView);
        	
        if (item.text != null)
        	text.setText(item.text);
        if (item.imageResourceId != 0)
        	image.setImageResource(item.imageResourceId);
 
        if (item.touch != null){
        	row.setOnTouchListener(item.touch);
        }
        
        return row;
    }

    static class Item{
    	String text;
    	int imageResourceId;
    	View.OnTouchListener touch;
    	
    	Item(String text, int imageResourceId, View.OnTouchListener touch){
    		this.text=text;
    		this.imageResourceId=imageResourceId;
    		this.touch=touch;
    	}
    }
}


