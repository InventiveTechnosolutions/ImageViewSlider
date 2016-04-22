package com.inventivetechnologies.imageviewslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shiv on 17-Apr-16.
 */
public class ImageAdapter extends BaseAdapter {
    // Keep all Images in array


    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;


    // Constructor
    public ImageAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mItems.add(new Item("Food & Restuarant", R.drawable.food));
        mItems.add(new Item("Movies", R.drawable.cinema));
        mItems.add(new Item("Travels", R.drawable.travel));
        mItems.add(new Item("Doctors", R.drawable.doctor));
        mItems.add(new Item("Movies", R.drawable.cinema));
        mItems.add(new Item("Travels", R.drawable.travel));
        mItems.add(new Item("Doctors", R.drawable.doctor));


    }

    /* public ImageAdapter(Context context, int[] Imageid ) {


         mContext = context;
         this.Imageid = Imageid;



     }
 */
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).drawableId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
   /*     LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        convertView.setLayoutParams(new GridView.LayoutParams(params));*/
        View v = convertView;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_single, parent, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }
        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);
        //Fetch images name and id form Item class
        Item item = (Item) getItem(position);
        picture.setImageResource(item.drawableId);
        name.setText(item.name);


        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }


}
