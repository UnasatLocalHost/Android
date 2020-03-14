package sr.unasat.bproduct.fragments;



import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;





import sr.unasat.bproduct.API.Service;
import sr.unasat.bproduct.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {


    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_fragment_three, container, false);


        return fragmentView;

    }

}