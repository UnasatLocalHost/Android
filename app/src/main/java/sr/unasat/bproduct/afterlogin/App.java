package sr.unasat.bproduct.afterlogin;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1_ID="channel2";
    public static final String CHANNEL_2_ID="channel2";

    @Override
    public void onCreate(){
        super.onCreate();
        createNotificationChannels();
    }
    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel  channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "BProduct Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Your message has been sent");
            NotificationChannel  channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "BProduct Notification 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("Your message has been sent again");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);

        }
    }
}
