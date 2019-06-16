package com.example.fapp_navi_drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fapp_navi_drawer.bll.Food;

import java.util.ArrayList;
import java.util.List;

public class CaloriesAndFood extends AppCompatActivity {
    private ListView lv_Furehstueck;
    private List<Food> ArrayList_Fruehstueck = new ArrayList<Food>();
    private ListView lv_Mittagessen;
    private List<Food> ArrayList_Mittagessen = new ArrayList<Food>();
    private ListView lv_Abendessen;
    private List<Food> ArrayList_Abendessen = new ArrayList<Food>();
    private final static int REQUEST_CODE = 1;
    private ArrayAdapter<Food> ArrayAdapter_Abend;
    private ArrayAdapter<Food> ArrayAdapter_Mittag;
    private ArrayAdapter<Food> ArrayAdapter_Frueh;
    private TextView TVEaten;
    private TextView TVLeft;
    private TextView TVGoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calories_and_food);
        lv_Furehstueck = this.findViewById(R.id.ListFruehstueck);
        Button btnaddFruehstueck = this.findViewById(R.id.AddBreakfast);
        lv_Mittagessen = this.findViewById(R.id.ListMittag);
        Button btnaddMittag = this.findViewById(R.id.addmittagessen);
        lv_Abendessen = this.findViewById(R.id.ListAbend);
        Button btnaddAbend = this.findViewById(R.id.AddAbendessen);
         TVEaten = this.findViewById(R.id.CaloriesEaten);
         TVLeft = this.findViewById(R.id.CalorisLeft);
         TVGoal = this.findViewById(R.id.CaloriesZiel);

        ArrayAdapter_Frueh = new ArrayAdapter<Food>(
                this,
                android.R.layout.simple_list_item_1,
                ArrayList_Fruehstueck );
        lv_Furehstueck.setAdapter((ArrayAdapter_Frueh));

        ArrayAdapter_Mittag = new ArrayAdapter<Food>(
                this,
                android.R.layout.simple_list_item_1,
                ArrayList_Mittagessen );
        lv_Mittagessen.setAdapter((ArrayAdapter_Mittag));

        ArrayAdapter_Abend = new ArrayAdapter<Food>(
                this,
                android.R.layout.simple_list_item_1,
                ArrayList_Abendessen );
        lv_Abendessen.setAdapter((ArrayAdapter_Abend));

        btnaddFruehstueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( CaloriesAndFood.this, MainActivity.class);
                intent.putExtra("Value","Frueh");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        btnaddMittag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( CaloriesAndFood.this, MainActivity.class);
                intent.putExtra("Value","Mittag");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        btnaddAbend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( CaloriesAndFood.this, MainActivity.class);
                intent.putExtra("Value","Abend");
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
                    Food food = (Food) dataIntent.getSerializableExtra("Foodback");
                    String msg = dataIntent.getStringExtra("Value");
                    int Goal = Integer.parseInt(TVGoal.getText().toString());
                    int Eaten = Integer.parseInt(TVEaten.getText().toString());
                    int LEFT = Integer.parseInt(TVLeft.getText().toString());

                    if(msg.equals("Frueh"))
                    {
                        ArrayList_Fruehstueck.add(food);
                        ArrayAdapter_Frueh.notifyDataSetChanged();

                    }else if (msg.equals("Mittag"))
                    {
                        ArrayList_Mittagessen.add(food);
                        ArrayAdapter_Mittag.notifyDataSetChanged();

                    }else if (msg.equals("Abend"))
                    {
                        ArrayList_Abendessen.add(food);
                        ArrayAdapter_Abend.notifyDataSetChanged();
                    }

                     Eaten = Eaten + food.getCalories();
                     LEFT = Goal - Eaten;

                    TVEaten.setText(String.valueOf(Eaten));

                    TVLeft.setText(String.valueOf(LEFT));

                }
                break;
            default:
                Toast.makeText(CaloriesAndFood.this, "Not food added!", Toast.LENGTH_LONG).show();
                break;

        }

    }
}
