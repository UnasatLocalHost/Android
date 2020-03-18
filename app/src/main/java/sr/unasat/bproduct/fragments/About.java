package sr.unasat.bproduct.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sr.unasat.bproduct.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {

   public static TextView textViewAbout;

    public About() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_about, container, false);
         textViewAbout = fragmentView.findViewById(R.id.Text_View_About);


        return fragmentView;
    }

}
