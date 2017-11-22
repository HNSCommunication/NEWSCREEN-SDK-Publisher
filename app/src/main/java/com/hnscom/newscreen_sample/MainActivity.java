package com.hnscom.newscreen_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;


import com.hnscom.hns_endad.NewscreenAD;

public class MainActivity extends AppCompatActivity {

    private NewscreenAD newscreenAD = new NewscreenAD(MainActivity.this); //현재액티비티

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch hnsadSwitch = (Switch) findViewById(R.id.newscreen_switch);

        hnsadSwitch.setChecked(newscreenAD.isRunningNewscreen());
        hnsadSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    newscreenAD.init("d67d8ab4f4c10bf22aa353e27879133c");
                }else{
                    newscreenAD.stopAd();
                }
            }
        });

    }
}
