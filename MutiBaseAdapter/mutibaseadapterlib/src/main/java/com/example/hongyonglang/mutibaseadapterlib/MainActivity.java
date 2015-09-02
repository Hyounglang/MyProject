package com.example.hongyonglang.mutibaseadapterlib;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongyonglang on 15/9/1.
 */
public class MainActivity extends Activity{

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        setAdapter();
    }

    private void setAdapter(){
        List<TestClass> datas = new ArrayList<>();
        for (int i = 0;i<30;i++){
            TestClass testClass = new TestClass();
            if (i % 2 == 0){
                testClass.name = "Type_A";
                testClass.type = 1;
            }else{
                testClass.name = "Type_B";
                testClass.type = 2;
            }
                datas.add(testClass);
        }
        MutiTypeAdapter<TestClass> adapter = new MutiTypeAdapter<>(this,datas);
        adapter.addTypeDelegation(new ViewTypeDelegation<TestClass>(R.layout.item_a,1) {
            @Override
            protected void doYours(ViewHolderHelper helper, TestClass item) {
                helper.getTextView(R.id.tv).setText(item.name);
            }

            @Override
            protected void onItemClick(View v, int position) {
                Toast.makeText(MainActivity.this,"position ="+position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.addTypeDelegation(new ViewTypeDelegation<TestClass>(R.layout.item_b, 2) {
            @Override
            protected void doYours(ViewHolderHelper helper, TestClass item) {
                helper.getButton(R.id.btn).setText(item.name);
                helper.getButton(R.id.btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Button is clicked!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            protected void onItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, "position =" + position, Toast.LENGTH_SHORT).show()   ;
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }
}
