package com.example.fapp_navi_drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fapp_navi_drawer.bll.Food;

public class FoodAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_add);

        final TextView IdFatAmount = this.findViewById(R.id.IdFatAmount);
        final TextView IdCarbsAmount = this.findViewById(R.id.IdCarbsAmount);
        final TextView IdCaloriesAmount = this.findViewById(R.id.IdCaloriesAmount);
        final TextView IdProteinAmount = this.findViewById(R.id.IdProteinAmount);
        final EditText Servings = this.findViewById(R.id.IdServings);
        final Button buttonAddFood = this.findViewById(R.id.Add_Food);

        Intent intent = getIntent();
        final Food food = (Food) intent.getSerializableExtra("foodInfo");
        final String msgback = intent.getStringExtra("Value");
        fillTextViews(food,IdCaloriesAmount,IdCarbsAmount,IdFatAmount,IdProteinAmount);

        Servings.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if(Servings.getText().toString().trim().length()==0)
                {
                    Servings.setError("Please fill in the amount of servings");
                }

                else{
                    IdCaloriesAmount.setText(String.valueOf(food.getCalories()* Integer.parseInt(Servings.getText().toString())));
                    IdCarbsAmount.setText(String.valueOf(food.getCarbs()* Integer.parseInt(Servings.getText().toString())));
                    IdFatAmount.setText(String.valueOf(food.getFat()* Integer.parseInt(Servings.getText().toString())));
                    IdProteinAmount.setText(String.valueOf(food.getProtein()* Integer.parseInt(Servings.getText().toString())));
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food foodback = new Food(food.getName(),food.getCalories()* Integer.parseInt(Servings.getText().toString()),food.getProtein()* Integer.parseInt(Servings.getText().toString()),food.getFat()* Integer.parseInt(Servings.getText().toString()),food.getCarbs()* Integer.parseInt(Servings.getText().toString()));
                Intent intent = new Intent( FoodAddActivity.this, MainActivity.class);
                intent.putExtra("Foodback",foodback);
                intent.putExtra("Value",msgback);
                setResult(1, intent);
                finish();
            }
        });
    }

    private void fillTextViews(Food food, TextView idCaloriesAmount, TextView idCarbsAmount, TextView idFatAmount, TextView idProteinAmount) {
        idCaloriesAmount.setText(String.valueOf(food.getCalories()));
        idCarbsAmount.setText(String.valueOf(food.getCarbs()));
        idFatAmount.setText(String.valueOf(food.getFat()));
        idProteinAmount.setText(String.valueOf(food.getProtein()));
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
