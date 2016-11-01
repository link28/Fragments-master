package edu.tecii.android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * Created by ppmtz_000 on 26/10/2016.
 */
public class FragmentRed extends Fragment implements FragmentCallbacks {

    MainActivity main;
    TextView txtRed;
    Button btnRedClock;

    public static FragmentRed newInstance(String strArg1){
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle();
        bundle.putString("arg1",strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof MainCallbacks)){
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivity)getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        LinearLayout view_Layout_red = (LinearLayout)inflater.inflate(R.layout.layout_red,null);
        txtRed = (TextView)view_Layout_red.findViewById(R.id.textView1Red);
        try{
            Bundle arguments = getArguments();
            String redMessage = arguments.getString("args1","");
            txtRed.setText(redMessage);
    }catch (Exception e){
            Log.e("RED BUNDLE ERROR - ",""+e.getMessage());
        }
        btnRedClock = (Button) view_Layout_red.findViewById(R.id.button1Red);
        btnRedClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String redMessage = "Red clock:\n "+ new Date().toString();
                txtRed.setText(redMessage);
                main.onMsgFromFragToMain("RED-FRAG", redMessage);
            }
        });
        return view_Layout_red;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue){
        txtRed.setText("THIS MESSAGE COMES FROM MAIN: "+ strValue);
    }
}
