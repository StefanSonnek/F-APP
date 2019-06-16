package com.example.fapp_navi_drawer.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fapp_navi_drawer.R;
import com.example.fapp_navi_drawer.MUSKELGRUPPE;
import com.example.fapp_navi_drawer.Uebung;

public class UpdateUebung extends AppCompatActivity {

    private EditText etBeschreibung, etBezeichnung,etSets,etWdh,etGwt;
    private Button btnUpdate, btnDelete;
    private Spinner spinnerMuskel;

    private ArrayAdapter<MUSKELGRUPPE> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Record");
        setContentView(R.layout.activity_update_todo);

        adapter = new ArrayAdapter<MUSKELGRUPPE>(this, android.R.layout.simple_spinner_dropdown_item, MUSKELGRUPPE.values());


        etBezeichnung = (EditText) findViewById(R.id.etBezeichnung);
        etBeschreibung = (EditText) findViewById(R.id.etBeschreibung);
        spinnerMuskel = (Spinner) findViewById(R.id.spinnerGruppe);
        etSets=(EditText)findViewById(R.id.etSaetze);
        etWdh=(EditText)findViewById(R.id.etWdh);
        etGwt=(EditText)findViewById((R.id.etGwt));
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        spinnerMuskel.setAdapter(adapter);

        Intent intent = getIntent();
        final Uebung u = (Uebung) intent.getExtras().getSerializable("Uebung");

        etBezeichnung.setText(u.getBezeichnung());
        etBeschreibung.setText(u.getBeschreibung());
        spinnerMuskel.setSelection(adapter.getPosition(u.getMuseklgruppe()));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.setBeschreibung(etBeschreibung.getText().toString());
                u.setBezeichnung(etBezeichnung.getText().toString());
                u.setSets(Integer.parseInt(etSets.getText().toString()));
                u.setWdh(Integer.parseInt(etWdh.getText().toString()));
                u.setGwt(Integer.parseInt(etGwt.getText().toString()));
                u.setMuseklgruppe((MUSKELGRUPPE) spinnerMuskel.getSelectedItem());

               //abspeichern mittels webservice
                returnToActivity();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //delete mittels ws
                returnToActivity();
            }

        });
    }

    private void returnToActivity() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
