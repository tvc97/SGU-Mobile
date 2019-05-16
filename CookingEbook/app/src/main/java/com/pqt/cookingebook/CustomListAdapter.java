package com.pqt.cookingebook;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<Item> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public final static String MESSAGE_HIGH_RES_URL = "com.pqt.cookingebook.MESSAGE_HIGH_RES_URL";


    public CustomListAdapter(Context aContext, List<Item> listData) {
        this.context = aContext;
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.btn = (Button) convertView.findViewById(R.id.btn);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Item item = this.listData.get(position);

        new DownloadImageTask(holder.image).execute(item.getImage());
        holder.text.setText(item.getText());

        holder.btn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                HighResImageActivity highResImageActivity = new HighResImageActivity(item.getHighResUrl());
                Intent viewImageIntent = new Intent(context, HighResImageActivity.class);

                viewImageIntent.putExtra(MESSAGE_HIGH_RES_URL, item.getHighResUrl());

                context.startActivity(viewImageIntent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView text;
        TextView btn;
    }
}
