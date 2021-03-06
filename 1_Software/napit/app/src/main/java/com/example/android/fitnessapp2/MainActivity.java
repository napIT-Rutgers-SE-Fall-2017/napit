package com.example.android.fitnessapp2;
// written by Aishwarya Srikanth, John grun
// tested by Aishwarya Srikanth, john grun

// The main screen the user is presented with prior to logging in.
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    Button LogInButton, RegisterButton ;
    EditText Email, Password ;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    private Session session;
    String TempPassword = "NOT_FOUND" ;
    public static final String UserEmail = "";
    //Context context;

    //private SessionHandler sessionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating a new SessionHandler object
        //sessionHandler = new SessionHandler(context);

        LogInButton = (Button)findViewById(R.id.buttonLogin);
        RegisterButton = (Button)findViewById(R.id.buttonRegister);
        Email = (EditText)findViewById(R.id.editEmail);
        Password = (EditText)findViewById(R.id.editPassword);

        sqLiteHelper = new SQLiteHelper(this);
        session= new Session(this);

        //SQLiteDataBaseBuild();

        //SQLiteTableBuild();

        //Adding click listener to log in button.
        //LogInButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {

                // Calling EditText is empty or no method.
                //CheckEditTextStatus();

                // Calling login method.
                //LoginFunction();

        //SQLiteTableBuild();

        //Adding click listener to log in button.
        LogInButton.setOnClickListener(this);

        // Adding click listener to register button.
        RegisterButton.setOnClickListener(this);
        if(session.loggedin())
        {
            startActivity(new Intent(MainActivity.this,DashboardActivity.class ));
            finish();
        }

    }

    public void SQLiteDataBaseBuild(){

        //not the place to put this. Databse should be created once the app starts for the first time
        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    // SQLite table build method.
    public void SQLiteTableBuild() {

        //not the place to put this. Databse should be created once the app starts for the first time
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_Email + " VARCHAR, " + SQLiteHelper.Table_Column_3_Password + " VARCHAR);");
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME_Sensor+" ("+SQLiteHelper.Table_Column_Sensor_ID+" INTEGER PRIMARY KEY, "+ SQLiteHelper.Table_Column_Sensor_Reading+" REAL);"); // + SQLiteHelper.Tables_Column_Sensor_Timestamp+ "DATETIME DEFAULT CURRENT_TIMESTAMP);");
    }

    // Login function starts from here.
    public void LoginFunction(){
/*
        if(EditTextEmptyHolder) {

            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));

                    // Closing cursor.
                    cursor.close();
                }
            }

            // Calling method to check final result ..
            CheckFinalResult();

        }
        else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(MainActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }
*/
        String email= Email.getText().toString();
        String pass= Password.getText().toString();

        if (sqLiteHelper.getUser(email,pass))
        {
            session.setLoggedin(true);
            startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Wrong Email/Password!", Toast.LENGTH_LONG).show();
        }

        session.setEmail(email);

    }

    // Checking EditText is empty or not.
    /*
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserEmail, EmailHolder);
            startActivity(intent);

            //starting a session
            //sessionHandler.setName(EmailHolder);

        }
        else {

            Toast.makeText(MainActivity.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }
    */

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.buttonLogin:
                LoginFunction();
                break;
            case R.id.buttonRegister:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            default:
        }


    }
}

