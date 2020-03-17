package sr.unasat.bproduct.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.provider.MediaStore;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import sr.unasat.bproduct.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    private Button captureImage, detectImage;
    private ImageView imageView;
    private TextView textView;
    static final int REQUEST_IMAGE_CAPTURE = 1;







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
                dispatchTakePictureIntent();
                Toast.makeText(getActivity(),"Opening Camera, this could take a second",Toast.LENGTH_SHORT).show();
            }
        });

        detectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectTextFromImage();
                Toast.makeText(getActivity(),"deciphering  Text, hold on",Toast.LENGTH_LONG).show();
            }
        });


        return fragmentView;
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity( getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);

        }
    }
    private void detectTextFromImage() {




    }


}




