package sr.unasat.bproduct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import sr.unasat.bproduct.Database.SQliteHelper;
import sr.unasat.bproduct.Entity.User;



public class Account_Activity extends AppCompatActivity {
  User user;

     Button change,delete,view;

    SQliteHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_);
        change = findViewById(R.id.button_username_change);
        delete = findViewById(R.id.button_delete_user);
        view = findViewById(R.id.button_credentials);

        db = new SQliteHelper(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res  =db.getAllData();
                if (res.getCount() == 0) {
                    //show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("id :" +res.getString(0) +"\n");
                    buffer.append("username :" +res.getString(1) +"\n");
                    buffer.append("email :" +res.getString(2) +"\n");
                    buffer.append("password :" +res.getString(3) +"\n");
                    //buffer.append("last_login :" +res.getString(4) +"\n");

                }

                //show all data
                showMessage("Your Credentials",buffer.toString());

            }
        });


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   db.updateUser(user);
//                Intent intent = new Intent(Account_Activity.this,ChangeActivity.class);
//                startActivity(intent);
//
            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              db.deletedRecords();
                Toast.makeText(Account_Activity.this,"account deleted,  bye bye ",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Account_Activity.this,LoginActivity.class));

            }
        });




    }

    public void showMessage(String title,String Message ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }
}
