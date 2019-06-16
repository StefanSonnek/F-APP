package com.example.fapp_navi_drawer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fapp_navi_drawer.DAL.DatabaseManagerFood;
import com.example.fapp_navi_drawer.bll.Food;

import java.util.ArrayList;
import java.util.List;

public class MainFoodActivity extends AppCompatActivity {

    private ListView lv;
    private List<Food> your_array_list = new ArrayList<Food>();
    private final static int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_food);
        lv = this.findViewById(R.id.idListView);
        Button btnaddFood = this.findViewById(R.id.btnaddFood);
        fillList(lv);
        Intent intent = getIntent();
        final String msgBack = intent.getStringExtra("Value");

        btnaddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainFoodActivity.this, NewFood_Activity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Food f = your_array_list.get(position);
                Intent intent = new Intent( MainFoodActivity.this, FoodAddActivity.class);
                intent.putExtra("foodInfo",f);
                intent.putExtra("Value",msgBack);
                startActivityForResult(intent,REQUEST_CODE);


        }


    });

    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent dataIntent){
        super.onActivityResult( requestCode, resultCode, dataIntent);
            switch (requestCode) {
                case REQUEST_CODE:
                    if (resultCode == 1) {
                        Food food =(Food)dataIntent.getSerializableExtra("Foodback");
                        Intent intent = new Intent( MainFoodActivity.this, CaloriesAndFood.class);
                        String msg = dataIntent.getStringExtra("Value");
                        intent.putExtra("Foodback",food);
                        intent.putExtra("Value",msg);
                        setResult(1, intent);
                        finish();

                    }else if(resultCode == 2)
                    {
                        this.fillList(lv);
                    }
                    break;
                default:
                    Toast.makeText(MainFoodActivity.this, "No food added!", Toast.LENGTH_LONG).show();
                    break;

            }

    }

    private void fillList(ListView lv)
    {

        DatabaseManagerFood dbM = new DatabaseManagerFood(MainFoodActivity.this);
        dbM.open();
        your_array_list = dbM.fetch();

        ArrayAdapter<Food> arrayAdapter = new ArrayAdapter<Food>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list ){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                textView.setTextColor(Color.WHITE);

                return view;
            }
        };;

        lv.setAdapter(arrayAdapter);

    }
}
