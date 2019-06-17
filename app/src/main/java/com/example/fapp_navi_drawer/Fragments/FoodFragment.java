package com.example.fapp_navi_drawer.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fapp_navi_drawer.Activities.Food.MainFoodActivity;
import com.example.fapp_navi_drawer.R;
import com.example.fapp_navi_drawer.bll.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {
    public FoodFragment() {
        // Required empty public constructor
    }
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
    private FragmentActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ConstraintLayout view = (ConstraintLayout) inflater.inflate(R.layout.food_main_activity, container, false);
        super.onCreate(savedInstanceState);
        activity = getActivity();
        lv_Furehstueck = view.findViewById(R.id.ListFruehstueck);
        Button btnaddFruehstueck = view.findViewById(R.id.AddBreakfast);
        lv_Mittagessen = view.findViewById(R.id.ListMittag);
        Button btnaddMittag = view.findViewById(R.id.addmittagessen);
        lv_Abendessen = view.findViewById(R.id.ListAbend);
        Button btnaddAbend = view.findViewById(R.id.AddAbendessen);
        TVEaten = view.findViewById(R.id.CaloriesEaten);
        TVLeft = view.findViewById(R.id.CalorisLeft);
        TVGoal = view.findViewById(R.id.CaloriesZiel);

        ArrayAdapter_Frueh = new ArrayAdapter<Food>(
                activity,
                android.R.layout.simple_list_item_1,
                ArrayList_Fruehstueck );
        lv_Furehstueck.setAdapter((ArrayAdapter_Frueh));

        ArrayAdapter_Mittag = new ArrayAdapter<Food>(
                activity,
                android.R.layout.simple_list_item_1,
                ArrayList_Mittagessen );
        lv_Mittagessen.setAdapter((ArrayAdapter_Mittag));

        ArrayAdapter_Abend = new ArrayAdapter<Food>(
                activity,
                android.R.layout.simple_list_item_1,
                ArrayList_Abendessen );
        lv_Abendessen.setAdapter((ArrayAdapter_Abend));

        btnaddFruehstueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( activity, MainFoodActivity.class);
                intent.putExtra("Value","Frueh");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        btnaddMittag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( activity, MainFoodActivity.class);
                intent.putExtra("Value","Mittag");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        btnaddAbend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( activity, MainFoodActivity.class);
                intent.putExtra("Value","Abend");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult( int requestCode, int resultCode, Intent dataIntent){
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
                Toast.makeText(activity, "Not food added!", Toast.LENGTH_LONG).show();
                break;

        }

    }
}
