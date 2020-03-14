package sr.unasat.bproduct.fragments;


import android.os.Bundle;


import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import sr.unasat.bproduct.R;
import sr.unasat.bproduct.afterlogin.MainActivity;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    private Button captureImage, detectImage;
    private ImageView imageView;
    private TextView textView;




    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_ragment_one, container, false);
        captureImage = fragmentView.findViewById(R.id.capture_image);
        detectImage = fragmentView.findViewById(R.id.detect_text);
        imageView = fragmentView.findViewById(R.id.imageViewDisplay);
        textView = fragmentView.findViewById(R.id.text_display);

        captureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Opening Camera, this could take a second",Toast.LENGTH_LONG).show();
            }
        });

        detectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getActivity(),"deciphering  Text, hold on",Toast.LENGTH_LONG).show();
            }
        });


        return fragmentView;
    }

}




