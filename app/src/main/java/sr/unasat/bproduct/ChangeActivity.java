package sr.unasat.bproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import sr.unasat.bproduct.Database.SQliteHelper;

public class ChangeActivity extends AppCompatActivity {


    SQliteHelper db;

    EditText username,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        db = new SQliteHelper(this);
        username = findViewById(R.id.editText_username);
        email = findViewById(R.id.editText_email);
        password = findViewById(R.id.editText_password);
        updateData();

    }

    public void updateData(){
       boolean isUpdated = db.updateData(username.getText().toString(),email.getText().toString()
               ,password.getText().toString());
    }
}
