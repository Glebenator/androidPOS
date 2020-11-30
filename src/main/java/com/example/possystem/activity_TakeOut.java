package com.example.possystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

public class activity_TakeOut extends AppCompatActivity {
    public CharSequence callerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__take_out);
    }

    public void openMenu(View view){
        Table tableobj = new Table();

        tableobj.setNumber(view.getTooltipText());
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("table", tableobj);
        startActivity(intent);
    }
    public CharSequence getCallerID(){
        return callerID;
    }
}

