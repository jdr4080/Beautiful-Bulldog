package com.example.jdr006.beautifulbulldog;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BulldogListFragment extends Fragment {

    private RecyclerView bulldogList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter bulldogAdapter;

    public BulldogListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bulldog_list, container, false);

        final ArrayList<Bulldog> bulldogs = new ArrayList<Bulldog>();
        bulldogList = (RecyclerView)v.findViewById(R.id.bulldog_list);

        Bulldog dog1 = new Bulldog();
        dog1.setName("Spike");
        dog1.setAge("5");

        Bulldog dog2 = new Bulldog();
        dog2.setName("Duke");
        dog2.setAge("2");

        bulldogs.add(dog1);
        bulldogs.add(dog2);

        layoutManager = new LinearLayoutManager(getContext());
        bulldogList.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bulldog bulldog = (Bulldog) bulldogs.get(position);
                Intent intent = new Intent(view.getContext(), BulldogActivity.class);
                intent.putExtra("bulldog", (Serializable)bulldog);
                startActivity(intent);
            }
        };

        bulldogAdapter = new BulldogAdapter(getContext(), bulldogs, listener);
        bulldogList.setAdapter(bulldogAdapter);

        return v;
    }

}
