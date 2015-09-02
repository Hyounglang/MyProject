package com.example.hongyonglang.mutibaseadapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tv;

    @SuppressWarnings("unchecked")
    public final <E extends View> E getView(int id)
    {
        try {
            return (E)findViewById(id);
        }catch (ClassCastException ex){
            Log.e("tag","Could not cast View to concrete class.",ex);
            throw ex;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = getView(R.id.tv);
        tv.setText("nihaoma");
    }

}
