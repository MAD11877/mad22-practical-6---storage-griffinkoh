package sg.edu.np.mad.MADPractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLAdapter extends SQLiteOpenHelper {
    public SQLAdapter(Context context){
        super(context, "user.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE User" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT," + "DESCRIPTION TEXT," + "FOLLOWED TEXT)";
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put("ID", user.Id);
        values.put("NAME", user.Name);
        values.put("DESCRIPTION", user.Description);
        values.put("FOLLOWED", user.Followed);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("User", null, values);
    }

    public ArrayList<User> getUsers(){
        String query = "SELECT * FROM User";
        ArrayList<User> newUserList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            newUserList.add(new User(cursor.getString(1), cursor.getString(2),
                    cursor.getInt(0), Boolean.parseBoolean(cursor.getString(3))));
        }
        return newUserList;
    }

    public void updateUser(User user){
        int id = user.Id;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String followStatus = (user.Followed == true) ? "Followed" : "Unfollow";
        values.put("FOLLOWED", followStatus);
        db.update("User", values, "ID=?", new String[]{String.valueOf(id)});
    }

}
