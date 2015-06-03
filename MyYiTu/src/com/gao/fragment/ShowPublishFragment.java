package com.gao.fragment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.gao.adapter.ShowPublishAdapter;
import com.gao.data.GlobleData;
import com.gao.http.HttpUtils;
import com.gao.myyitu.R;
import com.gao.utils.ParseData;
import com.gao.view.CustomeProgressDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ShowPublishFragment extends SherlockFragment
{
	int pageNow = 1;
	private static final String TAG="ShowPublishFragment";
	private View rootView;
	private PullToRefreshListView pullToRefreshListView;
	private CustomeProgressDialog customeProgressDialog;
	private List<Map<String,String>> showpublish_list;
	private ShowPublishAdapter showPublishAdapter;
//	private LinkedList<Map<String,String>> showpublish_linklist;
	public ShowPublishFragment()
	{
//		this.showpublish_linklist = new LinkedList<Map<String,String>>();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if(null == rootView)
		{
			rootView = inflater.inflate(R.layout.showpublish_fragment, null);
		}
		pullToRefreshListView = (PullToRefreshListView)rootView.findViewById(R.id.pull_refresh_list);
		pullToRefreshListView.setMode(Mode.BOTH);
		pullToRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>()
		{

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView)
			{
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(getSherlockActivity(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				new GetRefreshData().execute(String.valueOf(pageNow));
			}
			
		});
		new GetPublishWorkTask().execute(String.valueOf(pageNow));
		return rootView;
	}
	
	class GetPublishWorkTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			String publish_content="";
			HashMap<String, String> map = new HashMap<String, String>();
			SharedPreferences sharedPreferences = getSherlockActivity().getSharedPreferences("UserInfo", Context.MODE_APPEND);
			
			if(sharedPreferences.getString("userid", "").equals(""))
			{
				map.put("pageNow", params[0]);
				publish_content = HttpUtils.getServletContentByGet(GlobleData.GET_PUBLISH_4LOGOUTURL, map, "UTF-8");
			}
			else
			{
				map.put("userid", sharedPreferences.getString("userid", ""));
				map.put("pageNow", params[0]);
				publish_content = HttpUtils.getServletContentByGet(GlobleData.GET_PUBLISH_URL, map, "UTF-8");
			}
			
			Log.i(TAG, "publish_content-------->"+publish_content);
			return publish_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			showpublish_list = ParseData.getShowWorksList(result);
			Log.i(TAG, "showpublish_list---------->"+showpublish_list);
			//。。。。
//			for(int i=0;i<showpublish_list.size();i++)
//			{
//				showpublish_linklist.addFirst(showpublish_list.get(i));
//			}
			showPublishAdapter = new ShowPublishAdapter(getSherlockActivity(), showpublish_list,false);
			pullToRefreshListView.setAdapter(showPublishAdapter);
			super.onPostExecute(result);
		}
	}
	
	class GetRefreshData extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			String refresh_content="";
			HashMap<String, String> map = new HashMap<String, String>();
			SharedPreferences sharedPreferences = getSherlockActivity().getSharedPreferences("UserInfo", Context.MODE_APPEND);
			pageNow = Integer.valueOf(params[0])+1;
			params[0] = String.valueOf(Integer.valueOf(params[0])+1);
			Log.i(TAG, "params[0]---->"+params[0]);
			if(sharedPreferences.getString("userid", "").equals(""))
			{
				map.put("pageNow", params[0]);
				refresh_content = HttpUtils.getServletContentByGet(GlobleData.GET_PUBLISH_4LOGOUTURL,map , "UTF-8");
			}
			else
			{
				map.put("userid", sharedPreferences.getString("userid", ""));
				map.put("pageNow", params[0]);
				refresh_content = HttpUtils.getServletContentByGet(GlobleData.GET_PUBLISH_URL, map, "UTF-8");
			}
			Log.i(TAG, "refresh_content-------->"+refresh_content);
			return refresh_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			if(result.equals("[]")||result == null)
			{
				Toast.makeText(getSherlockActivity(), "暂无更多内容", Toast.LENGTH_SHORT).show();
			}
			else
			{
				List<Map<String, String>> refresh_list = ParseData.getShowWorksList(result);
				for(int i =0 ;i<refresh_list.size();i++)
				{
					showpublish_list.add(refresh_list.get(i));
				}
				showPublishAdapter.setRefreshData(showpublish_list);
				showPublishAdapter.notifyDataSetChanged();
			}

			pullToRefreshListView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
}
