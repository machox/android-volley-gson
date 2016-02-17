package com.example.volleygson.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.volleygson.R;
import com.example.volleygson.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obay on 17/02/16.
 */
public class PostAdapter extends ArrayAdapter<Post> {
    private List<Post> posts;
    private Activity activity;
    private int listId;

    public PostAdapter(Activity a, int textViewResourceId, List<Post> posts) {
        super(a, textViewResourceId, posts);
        this.posts = posts;
        this.listId = textViewResourceId;
        activity = a;
    }

    @Override
    public int getCount() {
        if (posts == null) {
            return 0;
        } else {
            return posts.size();
        }
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }

    public static class ViewHolder{
        public TextView title;
        public TextView body;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final ViewHolder holder;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(listId, null);
            holder = new ViewHolder();
            holder.body = (TextView) v.findViewById(R.id.body);
            holder.title = (TextView) v.findViewById(R.id.title);
            v.setTag(holder);
        }
        else {
            holder=(ViewHolder)v.getTag();
        }

        final Post post = posts.get(position);
        if (post != null) {
            holder.title.setText(post.getTitle());
            holder.body.setText(post.getBody());
        }
        return v;
    }

}
