package com.example.aplikacija;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        String[] results = {"1 CV", "2 CV", "3 CV", "4 CV", "5 CV"};
        ListView listview = rootView.findViewById(R.id.listResults);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), R.layout.view_results, results);
        listview.setAdapter(listViewAdapter);

        return rootView;
    }
}
