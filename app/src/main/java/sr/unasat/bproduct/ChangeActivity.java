package sr.unasat.bproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sr.unasat.bproduct.Database.SQliteHelper;
import sr.unasat.bproduct.Entity.User;

public class ChangeActivity extends AppCompatActivity {

    Button change;
    User user;

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
        change = findViewById(R.id.submit_change);
       // updateData();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateUser(user);
            }
        });

    }

//    public void updateData(){
//       boolean isUpdated = db.updateData(username.getText().toString(),email.getText().toString()
//               ,password.getText().toString());
//
//       if (isUpdated == true) {
//           Toast.makeText(ChangeActivity.this,"INFO HAS BEEN CHANGED",Toast.LENGTH_LONG).show();
//
//       }else
//           Toast.makeText(ChangeActivity.this,"INFO HAS NOT BEEN CHANGED",Toast.LENGTH_LONG).show();
//    }
//
//
//    public boolean insertData() {
//      boolean isInserted =  db.insertData(username.getText().toString(),email.getText().toString()
//                ,password.getText().toString());
//        if (isInserted == true) {
//            Toast.makeText(ChangeActivity.this,"INFO HAS BEEN INSERTED",Toast.LENGTH_LONG).show();
//        }
//        else
//            Toast.makeText(ChangeActivity.this,"INFO HAS NOT BEEN INSERTED",Toast.LENGTH_LONG).show();
//
//        return isInserted;
//    }



}
