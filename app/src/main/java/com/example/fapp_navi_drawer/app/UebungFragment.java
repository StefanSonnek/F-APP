package com.example.fapp_navi_drawer.app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fapp_navi_drawer.R;
import com.example.fapp_navi_drawer.Uebung;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UebungFragment extends Fragment {

    private String selectionArgs;
    private UebungAdapter uebungAdapter;
    private ListView listView;

    public static UebungFragment newInstance(String selectionArgs) {
        UebungFragment uebungFragment = new UebungFragment();
        Bundle args = new Bundle();
        args.putString("selectionArgs", selectionArgs);
        uebungFragment.setArguments(args);
        return uebungFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectionArgs = getArguments().getString("selectionArgs");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);



        final ArrayList<Uebung> todos = null; //sonnek webservices einbinden

        listView =  view.findViewById(R.id.list);
        listView.setEmptyView(view.findViewById(R.id.empty));
        uebungAdapter = new UebungAdapter(getActivity(), todos);
        listView.setAdapter(uebungAdapter);
        uebungAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                Intent modify_intent = new Intent(getActivity(), UpdateUebung.class);
                modify_intent.putExtra("toDo", todos.get(position));

                startActivity(modify_intent);
            }
        });

        return view;
    }
}
