package seoul.emergency.bbibbo.questionnaire;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by is on 2016-08-22.
 */
public class InnerDBManager extends SQLiteOpenHelper {

    public InnerDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE QUESTIONNAIRE_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, nationality TEXT, age TEXT, gender TEXT, height TEXT, weight TEXT, m1 TEXT, m2 TEXT, m3 TEXT, m4 TEXT, m5 TEXT, m6 TEXT, m7 TEXT, m8 TEXT, m9 TEXT, m10 TEXT, f1 TEXT,  f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT, f6 TEXT, f7 TEXT, f8 TEXT, f9 TEXT, eating TEXT, smoking TEXT, drinking TEXT, note TEXT, otherDisease_m TEXT, otherDisease_f TEXT, bloodType TEXT, f10 TEXT);");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String[] PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String[] strarr = new String[33];

        for(int i =0; i<33 ; i++)
        {
            if(i != 0 && i != 1 && i != 2 && i != 4 && i != 5 && i != 28)
                strarr[i] = new String("-1");
        }

        Cursor cursor = db.rawQuery("select * from QUESTIONNAIRE_LIST", null);
        while(cursor.moveToNext()) {
            cursor.getString(0);

            int i=0;
            while(true)
            {
                strarr[i] = new String(cursor.getString(i+1));
                i++;
                if(i==33)
                    break;
            }
        }
        return strarr;
    }
}
