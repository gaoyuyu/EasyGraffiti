package com.gao.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gao.myyitu.R;

public class CommentListAdapter extends BaseAdapter
{
	private Context context;
	private List<Map<String,String>> comment_list;
	private LayoutInflater inflater;
	static class ViewHolder
	{
		TextView userName_comment,index_comment,content_comment,comment_time;
	}
	
	public CommentListAdapter(Context context,
			List<Map<String, String>> comment_list)
	{
		super();
		this.context = context;
		this.comment_list = comment_list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return comment_list.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		
		if(null == convertView)
		{
			convertView = inflater.inflate(R.layout.comment_item, null);
			viewHolder = new ViewHolder();
			viewHolder.content_comment = (TextView)convertView.findViewById(R.id.content_comment);
			viewHolder.index_comment = (TextView)convertView.findViewById(R.id.index_comment);
			viewHolder.userName_comment = (TextView)convertView.findViewById(R.id.userName_comment);
			viewHolder.comment_time = (TextView)convertView.findViewById(R.id.comment_time);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		/**
		 *         "c_username": "夜之渡鸦",
        "id": 1,
        "s_worksid": "1",
        "work_comment": "太好看了~"
		 */
		viewHolder.userName_comment.setText(comment_list.get(position).get("c_username"));
		viewHolder.content_comment.setText(comment_list.get(position).get("work_comment"));
		viewHolder.comment_time.setText(comment_list.get(position).get("c_time"));
		StringBuffer index = new StringBuffer();
		index.append(position+1);
		index.append("楼");
		viewHolder.index_comment.setText(index);
		return convertView;
	}

}
