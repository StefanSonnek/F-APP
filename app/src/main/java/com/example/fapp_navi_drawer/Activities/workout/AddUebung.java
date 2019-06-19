package com.example.fapp_navi_drawer.Activities.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fapp_navi_drawer.Activities.MainActivity;
import com.example.fapp_navi_drawer.DAL.DatabaseManagerUebung;
import com.example.fapp_navi_drawer.Fragments.WorkoutFragment;
import com.example.fapp_navi_drawer.R;
import com.example.fapp_navi_drawer.bll.MUSKELGRUPPE;
import com.example.fapp_navi_drawer.bll.Uebung;


public class AddUebung extends AppCompatActivity {

    private Button btnAddRecord;
    private Button btnCancel;
    private EditText etBezeichnung;
    private EditText etBeschreibung;
    private Spinner spinnerGruppe;
    private EditText etSaetze;
    private EditText etWdh;
    private EditText etGwt;

    private DatabaseManagerUebung dbManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_add);
        setTitle("Add Record");

        etBezeichnung = (EditText) findViewById(R.id.etBezeichnung);
        etBeschreibung = (EditText) findViewById(R.id.etBeschreibung);
        etSaetze=(EditText)findViewById(R.id.etSaetze);
        etWdh=(EditText)findViewById(R.id.etWdh);
        etGwt=(EditText)findViewById(R.id.etGwt);
        spinnerGruppe = (Spinner) findViewById(R.id.spinnerGruppe);


        spinnerGruppe.setAdapter(new ArrayAdapter<MUSKELGRUPPE>(this, android.R.layout.simple_spinner_dropdown_item, MUSKELGRUPPE.values()));

        btnAddRecord = (Button) findViewById(R.id.add_record);
        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String beschreibung = etBeschreibung.getText().toString();
                final String bezeichnung = etBezeichnung.getText().toString();
                final int sets= Integer.parseInt(etSaetze.getText().toString());
                final int wdh= Integer.parseInt(etWdh.getText().toString());
                final int gwt= Integer.parseInt(etGwt.getText().toString());
                final MUSKELGRUPPE muskelgruppe = (MUSKELGRUPPE) spinnerGruppe.getSelectedItem();

                dbManager.insert(new Uebung(0, bezeichnung, beschreibung,sets,wdh,gwt, muskelgruppe));


                Intent intent = new Intent();
                intent.putExtra("fragment", "Workout");
                intent.putExtra("uebung", new Uebung(1, bezeichnung, beschreibung, sets, wdh, gwt, muskelgruppe));
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("fragment", "Workout");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        dbManager = new DatabaseManagerUebung(this);
        dbManager.open();
    }
}