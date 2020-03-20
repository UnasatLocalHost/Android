package sr.unasat.bproduct.fragments;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.nio.file.Files;

import sr.unasat.bproduct.LoginActivity;
import sr.unasat.bproduct.R;
import sr.unasat.bproduct.RegisterActivity;
import sr.unasat.bproduct.afterlogin.App;

import static android.os.Build.VERSION_CODES.N;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {
    private NotificationManagerCompat notificationManager;
    private Button share_btn;
    Button logOut;


    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_fragment_two, container, false);
        share_btn = fragmentView.findViewById(R.id.share_btn);
        logOut = fragmentView.findViewById(R.id.action_settings);
        notificationManager = NotificationManagerCompat.from(getActivity());


        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                String Text = "Take a look at my insane photo";
                Uri uriToImage = Uri.parse("file://my_pictures");
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, Text);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                shareIntent.setType("images/*");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "Share images..."));
                sendOnChannel1(v);
                sendOnChannel2(v);


            }
        });
        return fragmentView;
    }

    public void sendOnChannel1(View v) {
        Notification notification = new NotificationCompat.Builder(getActivity(), App.CHANNEL_1_ID)

                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("Notification")
                .setContentText("Your message has been sent")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

    }

    public void sendOnChannel2(View v) {
        Notification notification = new NotificationCompat.Builder(getActivity(), App.CHANNEL_2_ID)

                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("Notification")
                .setContentText("Your message has been sent")
                .setPriority(NotificationCompat.PRIORITY_LOW)


                .build();

        notificationManager.notify(2, notification);

    }
}

