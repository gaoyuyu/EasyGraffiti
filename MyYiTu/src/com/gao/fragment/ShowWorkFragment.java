package com.gao.fragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;
import com.gao.adapter.ShowWorkAdapter;
import com.gao.data.GlobleData;
import com.gao.fragment.ShowPublishFragment.GetRefreshData;
import com.gao.http.HttpUtils;
import com.gao.myyitu.R;
import com.gao.utils.ParseData;
import com.gao.utils.ProgressDialogUtils;
import com.gao.view.CustomeProgressDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class ShowWorkFragment extends SherlockFragment
{
	private static final String TAG="ShowWorkFragment";
	private FragmentManager fm;
	private FragmentTransaction ft;
	private View rootView;
	private CustomeProgressDialog customeProgressDialog;
	private List<Map<String,String>> showworks_list;
	private ShowWorkAdapter showWorkAdapter;
	private PullToRefreshListView pullToRefreshListView;
	int pageNow = 1; 
	public ShowWorkFragment()
	{
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		// TODO Auto-generated method stub
		
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if(null == rootView)
		{
			rootView = inflater.inflate(R.layout.showwork_fragment, null);
		}
		pullToRefreshListView = (PullToRefreshListView)rootView.findViewById(R.id.pull_refresh_showworklist);
		pullToRefreshListView.setMode(Mode.PULL_FROM_START);
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
		Log.i(TAG, "showworks_list-onCreateView-->"+showworks_list);
		new ShowWorkTask().execute(String.valueOf(pageNow));
		return rootView;
	}
	
	
	class ShowWorkTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			customeProgressDialog = CustomeProgressDialog.ctreateDialog(getActivity());
			ProgressDialogUtils.startProgressDialog("加载数据中...", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("pageNow", params[0]);
			String show_content = HttpUtils.getServletContentByGet(GlobleData.SHOW_WORKS_URL, map, "UTF-8");
			return show_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			showworks_list = ParseData.getShowWorksList(result);
			showWorkAdapter = new ShowWorkAdapter(getActivity(), showworks_list);
			pullToRefreshListView.setAdapter(showWorkAdapter);
			super.onPostExecute(result);
		}
	}
	/**
	 * 刷新获得最新的top10条
	 * @author Gao
	 *
	 */
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
			// TODO Auto-generated method stub
			HashMap<String, String> map = new HashMap<String, String>();
			pageNow = Integer.valueOf(params[0])+1;
			params[0] = String.valueOf(Integer.valueOf(params[0])+1);
			Log.i(TAG, "params[0]---->"+params[0]);
//			map.put("pageNow", params[0]);
			map.put("pageNow", "1");
			String showrefresh_content = HttpUtils.getServletContentByGet(GlobleData.SHOW_WORKS_URL, map, "UTF-8");
			return showrefresh_content;
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
//				List<Map<String, String>> refresh_list = ParseData.getShowWorksList(result);
//				for(int i =0 ;i<refresh_list.size();i++)
//				{
//					showworks_list.add(refresh_list.get(i));
//				}
				showworks_list = ParseData.getShowWorksList(result);
				showWorkAdapter.setRefreshData(showworks_list);
				showWorkAdapter.notifyDataSetChanged();
			}

			pullToRefreshListView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
	
}
