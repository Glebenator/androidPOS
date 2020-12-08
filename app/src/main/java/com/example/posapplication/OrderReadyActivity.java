package com.example.posapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class OrderReadyActivity extends AppCompatActivity {
    List<Table> tableList;
    LinearLayout HL;
    int index = 0;
    View clickedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_orders);
        getItems();
    }

    public void getItems(){
        Thread itemThread = new Thread(new Runnable() {
            @Override
            public void run() {
                tableList = TableDatabase.getTableDatabase(getApplicationContext()).tableDao().getAll();
            }
        });
        itemThread.start();
        try {
            itemThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        displayItems();
    }

    public void displayItems(){
        HL = (LinearLayout) findViewById(R.id.ParentHorizontal); //defined in XML
        for (int i = 0; i < tableList.size(); i++){ //Loop through every available table in Database
            ScrollView sv = new ScrollView(this); //create a scroll view(vertical to fit many menu items)
            LinearLayout ll = new LinearLayout(this); //This layout holds item names
            LinearLayout ParentVertical = new LinearLayout(this); //I made this to seperate a table number from scrolling
            //need to amke Parent Vertical to have some margins between each instance of Parent Vertical
            Space space = new Space(this);
            space.setMinimumWidth(20);
            ParentVertical.setOrientation(LinearLayout.VERTICAL);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.isClickable(); //we can click on the layout which is scrollable
            int finalI = i;

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = finalI;
                    clickedView = v;
                }
            });
            ll.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ParentVertical.setBackgroundColor(Color.LTGRAY);
                            break;
                        case MotionEvent.ACTION_UP:
                            ParentVertical.setBackgroundColor(Color.WHITE);
                            break;
                    }

                    return false;
                }
            });
            ParentVertical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = finalI;
                    clickedView = v;
                }
            });

            for (int j = 0; j < tableList.get(i).getNumItems(); j++){ //loop through each menu item in a given table
                TextView tv = new TextView(this); //create a text for each menu item
                tv.setTextSize(35);
                tv.setText(tableList.get(i).getMenuItem(j).getItemName());
                ll.addView(tv); //add this text to the layout which will be scrollable
            }

            TextView tablenum = new TextView(this); //table number text
            tablenum.setTextSize(40);
            tablenum.setText(tableList.get(i).getNumber());
            ParentVertical.addView(tablenum); //add to the parent layout
            sv.addView(ll); //Scroll view holds LL that holds menu item names
            ParentVertical.addView(sv); //ParenVertical layout holds a table number and a scroll view
            if (tableList.get(i).isReady()) {
                tablenum.setBackgroundColor(Color.GREEN);
                HL.addView(ParentVertical, 0);
                HL.addView(space,1);

            } else {
                tablenum.setBackgroundColor(Color.RED);
                HL.addView(space);
                HL.addView(ParentVertical);
            }

             //Horizontal layout is the main parent.
        }
    }

    // Clear table that has been delivered
    public void clearTable(View v) {
        TableDatabase tableDatabase = TableDatabase.getTableDatabase(getApplicationContext());
        TableDao tableDao = tableDatabase.tableDao();

        if (tableList.get(index).isReady()) {
            Thread deleteThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    tableDao.deleteByTable(tableList.get(index).getNumber());
                }
            });
            deleteThread.start();
            try {
                deleteThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            HL.removeAllViews();
            getItems();
        }
    }
}
