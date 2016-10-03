package com.kim.bisos.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kim.bisos.ActivityEquipment;
import com.kim.bisos.ActivityPuncture;
import com.kim.bisos.ActivitySafe;
import com.kim.bisos.R;

public class FragmentTabAfter extends Fragment {


    Button safe_btn;
    Button equipment_btn;
    Button puncture_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_after, container, false);
        TextView local = (TextView) v.findViewById(R.id.local);



        safe_btn = (Button) v.findViewById(R.id.safe_btn);


        safe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(FragmentTabAfter.this.getActivity(),ActivitySafe.class);


                startActivity(Intent);
                getActivity().finish();

            }

        });


        equipment_btn = (Button) v.findViewById(R.id.equipment_btn);


        equipment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(FragmentTabAfter.this.getActivity(), ActivityEquipment.class);


                startActivity(Intent);
                getActivity().finish();

            }

        });




        puncture_btn = (Button) v.findViewById(R.id.puncture_btn);


        puncture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(FragmentTabAfter.this.getActivity(), ActivityPuncture.class);


                startActivity(Intent);
                getActivity().finish();

            }

        });






        return v;
    }

}


