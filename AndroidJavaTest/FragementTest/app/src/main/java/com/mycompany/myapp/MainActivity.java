package com.mycompany.myapp;

import android.app.*;
import android.os.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ActionBar actionbar = getActionBar();
        if(actionbar != null){
            actionbar.hide();
        }
        
    }
}
