package com.kim.bisos.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kim.bisos.R;

import java.util.ArrayList;

public class ActivityItemList extends Activity {
	
	private ArrayList<InfoClass> mCareList = null;
	private ListView mItemLV = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        initUI();
    }
	public void initUI(){
		mItemLV = (ListView) findViewById(R.id.item_lv);

		mCareList = new ArrayList<InfoClass>();

		for(int i = 0 ; i < 10 ; i++){
			mCareList.add(new InfoClass(i + "번째" + " ListView 입니다.",getResources().getDrawable(R.mipmap.ic_launcher),"" + i));
		}

		mItemLV.setAdapter(new CustomBaseAdapter(this, mCareList));
		mItemLV.setOnItemClickListener(onItemClickListener);
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
}


