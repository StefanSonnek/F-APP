package com.example.fapp_navi_drawer.Activities.Food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fapp_navi_drawer.Activities.MainActivity;
import com.example.fapp_navi_drawer.DAL.DatabaseManagerFood;
import com.example.fapp_navi_drawer.R;
import com.example.fapp_navi_drawer.bll.Food;

public class NewFood_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_new_food);
        Button btnaddFood = this.findViewById(R.id.btnaddFood);

        final EditText foodName = this.findViewById(R.id.food_Name);
        final EditText idCalories = this.findViewById(R.id.idKalories);
        final EditText idProtein = this.findViewById(R.id.idProtein);
        final EditText idCarbs = this.findViewById(R.id.idCarbs);
        final EditText idFat = this.findViewById(R.id.idFat);

        btnaddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid =CheckFood(foodName,idCalories,idProtein,idCarbs,idFat);

                if(valid) {
                    Food foodtoadd = new Food(foodName.getText().toString(), Integer.parseInt(idCalories.getText().toString()), Integer.parseInt(idProtein.getText().toString()), Integer.parseInt(idCarbs.getText().toString()), Integer.parseInt(idFat.getText().toString()));
                    DatabaseManagerFood dbM = new DatabaseManagerFood(NewFood_Activity.this);
                    dbM.open();
                    dbM.insert(foodtoadd);
                    dbM.close();
                    Intent intent = new Intent(NewFood_Activity.this, MainActivity.class);
                    intent.putExtra("foodtoAdd",foodtoadd);
                    setResult(2, intent);
                    finish();
                }
            }
        });
    }

    private boolean CheckFood(EditText foodName, EditText idCalories, EditText idProtein, EditText idCarbs, EditText idFat)
    {
        boolean valid = true;

        if(foodName.getText().toString().trim().length()==0)
        {
            valid = false;
            foodName.setError("Please fill in the name");
        }else if(idCalories.getText().toString().trim().length()==0)
        {
            valid = false;
            idCalories.setError("Please fill in the amount of calories");
        }else if(idProtein.getText().toString().trim().length()==0)
        {
            valid = false;
            idProtein.setError("Please fill in the amount of protein");
        }else if(idCarbs.getText().toString().trim().length()==0)
        {
            valid = false;
            idCarbs.setError("Please fill in the amount of carbs");
        }else if(idFat.getText().toString().trim().length()==0)
        {
            valid = false;
            idFat.setError("Please fill in the amount of fat");
        }

        return valid;
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}

