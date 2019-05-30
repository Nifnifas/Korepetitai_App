package com.example.aplikacija;

        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;

public class CategoriesFragment extends Fragment implements LinearLayout.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        LinearLayout lay1 = rootView.findViewById(R.id.cat1);
        lay1.setOnClickListener(this);
        LinearLayout lay2 = rootView.findViewById(R.id.cat2);
        lay2.setOnClickListener(this);
        LinearLayout lay3 = rootView.findViewById(R.id.cat3);
        lay3.setOnClickListener(this);
        LinearLayout lay4 = rootView.findViewById(R.id.cat4);
        lay4.setOnClickListener(this);
        LinearLayout lay5 = rootView.findViewById(R.id.cat5);
        lay5.setOnClickListener(this);
        LinearLayout lay6 = rootView.findViewById(R.id.cat6);
        lay6.setOnClickListener(this);
        LinearLayout lay7 = rootView.findViewById(R.id.cat7);
        lay7.setOnClickListener(this);
        LinearLayout lay8 = rootView.findViewById(R.id.cat8);
        lay8.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment;

        switch (v.getId()) {
            case R.id.cat1:
                fragment = new ViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.cat2:
                fragment = new ViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.cat3:
                fragment = new ViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.cat4:
                fragment = new ViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.cat5:
                fragment = new ViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.cat6:
                fragment = new ViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.cat7:
                fragment = new ViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case R.id.cat8:
                fragment = new ViewFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
        }
    }
}
