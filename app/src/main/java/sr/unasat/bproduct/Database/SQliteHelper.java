package sr.unasat.bproduct.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.sql.Date;

import sr.unasat.bproduct.Entity.User;


public class SQliteHelper extends SQLiteOpenHelper {
    //database info
    public static final String DATABASE_NAME = "user.db";
    public static final int DATABASE_VERSION = 2;

    //table info
    public static final String TABLE_USERS = "users";
    public static final String USER_ID = "id";
    public static final String USER_USERNAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_LAST_LOGIN = "last_login";

    //sql query create users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + USER_ID + " INTEGER PRIMARY KEY, "
            + USER_USERNAME + " TEXT, "
            + USER_EMAIL + " TEXT, "
            + USER_PASSWORD + " TEXT"
            + USER_LAST_LOGIN +"TEXT"
            + " ) ";

    public SQliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);

    }

    public void addUser(User user) {
        // get writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // create content values to insert
        ContentValues values = new ContentValues();

        // inserting values in User
        values.put(USER_USERNAME,user.username);
        values.put(USER_EMAIL,user.email);
        values.put(USER_PASSWORD,user.password);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{USER_ID, USER_USERNAME, USER_EMAIL, USER_PASSWORD},
                USER_EMAIL + "=?",
                new String[]{user.email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            // match both password check they are not the same
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        return null;
    }

    public boolean ifEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{USER_ID, USER_USERNAME, USER_EMAIL, USER_PASSWORD},
                USER_EMAIL + "=?",
                new String[]{email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean  updateData(String USERNAME, String EMAIL,String PASSWORD){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_USERNAME,USERNAME);
        contentValues.put(USER_EMAIL,EMAIL);
        contentValues.put(USER_PASSWORD,PASSWORD);
        db.update(TABLE_USERS,contentValues,"username = ?", new String[]{USERNAME});
        return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res  = db.rawQuery("select * from " +TABLE_USERS,null);
        return res;

    }

    public  boolean insertData(String USERNAME, String EMAIL,String PASSWORD) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_USERNAME,USERNAME);
        contentValues.put(USER_EMAIL,EMAIL);
        contentValues.put(USER_PASSWORD,PASSWORD);
        long result = db.insert(TABLE_USERS,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public void  deleteData(String username ) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USERS,"username = ?",new String[] {username});



    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_USERNAME,user.username);
        values.put(USER_EMAIL, user.email);
        values.put(USER_PASSWORD,user.password);


        //updating row
        return  db.update(TABLE_USERS,values,"id = ?",new String[]{String.valueOf(user.id)});
    }

    //deleting a single user

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,"id = ?",new String[]{String.valueOf(user.id)});
        db.close();

    }



    public void deletedRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,null,null);
        db.execSQL("delete from "+TABLE_USERS);
        db.close();

    }




}


