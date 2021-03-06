package sr.unasat.bproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import sr.unasat.bproduct.API.FetchData;
import sr.unasat.bproduct.Database.SQliteHelper;
import sr.unasat.bproduct.Entity.User;
import sr.unasat.bproduct.afterlogin.MainActivity;


public class LoginActivity extends AppCompatActivity{


    EditText editTextEmail;
    EditText editTextPassword;
    TextView about;

    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    Button buttonLogin;


    SQliteHelper sQliteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        sQliteHelper = new SQliteHelper(this);
        initCreateAccountTextView();

        initViews();
        about = (TextView)findViewById(R.id.tvAbout);

        //onclick on the button

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login runner= new Login();
                runner.execute();



                //check if user input is correct
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//
//                fragmentManager.beginTransaction().replace(R.id.frameLayout,about).commit();
//                Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,Popup.class));

            }
        });


    }

    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(Html.fromHtml("\"<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>\"));"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;

    }
    public boolean validate() {
       boolean valid = false;

        //Get values from EditText fields
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {

            valid = true;

            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }

        return valid;
    }
    private void LoadMainActivity() {
       Intent intent= new Intent(this, MainActivity.class);
       startActivity(intent);
    }



    public class Login extends  AsyncTask <String,String,String> {
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        @Override
        protected void onPreExecute() {
            if (validate()) {

                // Authenticate user
                User currentUser = sQliteHelper.Authenticate(new User(null, null, Email, Password));
                // check if Authentication is successful or not
                if (currentUser != null) {
                    Snackbar.make(buttonLogin, "Successful logged In", Snackbar.LENGTH_LONG).show();
                    LoadMainActivity();

                } else {
                    Snackbar.make(buttonLogin, "Failed to login, please try again", Snackbar.LENGTH_LONG).show();
                }
            }
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                sQliteHelper.updateTime(date,Email);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }

}


