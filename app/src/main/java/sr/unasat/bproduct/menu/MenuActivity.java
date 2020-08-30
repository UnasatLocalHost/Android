package sr.unasat.bproduct.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;


import sr.unasat.bproduct.Account_Activity;
import sr.unasat.bproduct.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflating menu by overriding inflate() method of menuInflater class
        //inflating here means parsing layout xml to views

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Overriding onOptionsItemSelected to perform even on menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.action_log_out:
                System.out.println("help");
                return true;

            case R.id.action_account_settings:
                //new intent to go change account settings like, update username, delete account etc
                //openAccountActivity();
                startActivity(new Intent(MenuActivity.this,Account_Activity.class));

                return true;

                default: super.onOptionsItemSelected(menuItem);


        }

        return true;
    }



}