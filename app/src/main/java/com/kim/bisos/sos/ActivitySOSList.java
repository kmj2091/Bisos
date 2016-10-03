package com.kim.bisos.sos;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.kim.bisos.utils.HttpComm;
import com.kim.bisos.utils.HttpURL;
import com.kim.bisos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivitySOSList extends Activity {
	Context mContext;
	
	private ArrayList<InfoClass> mCareList = null;
	private ListView mItemLV = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

		mContext = this;
        initUI();
    }
	public void initUI(){
		getListSOS();

		mItemLV = (ListView) findViewById(R.id.item_lv);
	}

	AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {

			Toast.makeText(
					getApplicationContext(),
					"ITEM CLICK = " + position,
					Toast.LENGTH_SHORT
			).show();
		}
	};

	public class CustomBaseAdapter extends BaseAdapter {

		private LayoutInflater inflater = null;
		private JSONArray infoList = null;
		private ViewHolder viewHolder = null;
		private Context mContext = null;

		public CustomBaseAdapter(Context c , JSONArray Array){
			this.mContext = c;
			this.inflater = LayoutInflater.from(c);
			this.infoList = Array;
		}

		// Adapter가 관리할 Data의 개수를 설정 합니다.
		@Override
		public int getCount() {
			return infoList.length();
		}

		// Adapter가 관리하는 Data의 Item 의 Position을 <객체> 형태로 얻어 옵니다.
		@Override
		public JSONObject getItem(int position) {
			try {
				return infoList.getJSONObject(position);
			} catch (JSONException e) {
				e.printStackTrace();
			}
            return null;
		}

		// Adapter가 관리하는 Data의 Item 의 position 값의 ID 를 얻어 옵니다.
		@Override
		public long getItemId(int position) {
			return position;
		}

		// ListView의 뿌려질 한줄의 Row를 설정 합니다.
		@Override
		public View getView(int position, View convertview, ViewGroup parent) {

			View v = convertview;

			if(v == null){
				viewHolder = new ViewHolder();
				v = inflater.inflate(R.layout.list_item_sos, null);
                viewHolder.state        = (TextView)v.findViewById(R.id.list_item_sos_state_tv);
                viewHolder.registerDate = (TextView)v.findViewById(R.id.list_item_sos_registerDate_tv);
                viewHolder.title        = (TextView)v.findViewById(R.id.list_item_sos_title_tv);
                viewHolder.user         = (TextView)v.findViewById(R.id.list_item_sos_user_tv);
                viewHolder.cout         = (TextView)v.findViewById(R.id.list_item_sos_count_tv);

				v.setTag(viewHolder);

			}else {
				viewHolder = (ViewHolder)v.getTag();
			}

            try {
				JSONObject data = getItem(position);
                viewHolder.state.setText(getItem(position).getString("status"));
                viewHolder.registerDate.setText(getItem(position).getString("registerDate"));
                viewHolder.title.setText(getItem(position).getString("title"));
                viewHolder.user.setText(getItem(position).getJSONObject("user").getString("userId"));
                //            viewHolder.cout.setText(getItem(position).getString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

			return v;
		}
		/*
         * ViewHolder
         * getView의 속도 향상을 위해 쓴다.
         * 한번의 findViewByID 로 재사용 하기 위해 viewHolder를 사용 한다.
         */
		class ViewHolder{
            public TextView state = null;
            public TextView registerDate = null;
            public TextView title = null;
            public TextView user = null;
            public TextView cout = null;
		}

		@Override
		protected void finalize() throws Throwable {
			free();
			super.finalize();
		}

		private void free(){
			inflater = null;
			infoList = null;
			viewHolder = null;
			mContext = null;
		}
	}
	public class InfoClass {
		public String title;
		public Drawable image;
		public String button;

		public InfoClass() { }

		public InfoClass(String title, Drawable image, String button){
			this.title = title;
			this.image = image;
			this.button = button;
		}
	}

	public void getListSOS(){
		JSONObject mDataJO = new JSONObject();

		String lat     = "37.493443";
		String lon     = "126.836594";

//		Log.e("gm","conversionState(Status):"+Status);
//		Log.e("gm","conversionState(Status):"+conversionState(Status));
//		try {
//			mDataJO.put("phoneNumber"   ,Title);
//			mDataJO.put("userId"        ,Body);
//			mDataJO.put("status"        ,conversionState(Status));
//			mDataJO.put("lat"           ,lat);
//			mDataJO.put("lon"           ,lon);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}


		final HttpComm mHttpComm = new HttpComm(mContext);
		//서버 통신에 필요한 url 셋팅
		mHttpComm.setUrl(HttpURL.SOSList);

		//서버 통신에 필요한 데이터
		mHttpComm.setQeryJO(mDataJO);

		//서버 통신 후 할일을 등록
		mHttpComm.setRunnable(new Runnable() {
			@Override
			public void run() {
				// 성공 여부 확인
				if(!mHttpComm.isSuccess()) {
					//실패시
					Toast.makeText(mContext, mHttpComm.mBodyS, Toast.LENGTH_SHORT).show();
				}
				//성공시
				Toast.makeText(mContext, R.string.desc_success, Toast.LENGTH_SHORT).show();

                JSONArray SOSList = null;
                try {
                    SOSList = mHttpComm.mRecvJO.getJSONArray("Data");
                    mItemLV.setAdapter(new CustomBaseAdapter(ActivitySOSList.this, SOSList));
                    mItemLV.setOnItemClickListener(onItemClickListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
			}
		});
		mHttpComm.start();
	}
}




