package com.wzh.gank.mygank.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wzh.gank.mygank.model.Favorite;

/**
 * Created by WZH on 2017/9/11.
 */
class MyDataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDataBaseHelper";


    private Context mContext;

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }


    //创建
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String sql = "create table if not exists " + FavoriteDAO.TABLE_NAME
                + " ("
                + FavoriteDAO.KEY_ID + " integer primary key autoincrement, "
                + FavoriteDAO._ID + " text, "
                + FavoriteDAO._TITLE + " text, "
                + FavoriteDAO._URL + " text, "
                + FavoriteDAO._STATE + " text);";
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG, "Create Success");
    }

    //升级
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String sqlStr = "DROP TABLE IF EXISTS " + FavoriteDAO.TABLE_NAME;
        sqLiteDatabase.execSQL(sqlStr);
        onCreate(sqLiteDatabase);
    }
}
