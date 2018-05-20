package com.wzh.gank.mygank.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.wzh.gank.mygank.model.Favorite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 开发者：WZH
 * @date 创建时间：2017/9/11
 * @Description： 数据库封装操作类
 */

public class FavoriteDAO {
    public static final String DB_NAME = "GANK.db";//数据库名称
    public static final String TABLE_NAME = "favorite_gank"; // 数据表名称
    public static final int DB_VERSION = 1; //数据库版本

    //表的字段名称

    public static String KEY_ID = "key_id";
    public static String _ID = "id";
    public static String _URL = "url";
    public static String _TITLE = "title";
    public static String _STATE = "state";

    private SQLiteDatabase mSQLiteDatabase;
    private Context mContext;
    private MyDataBaseHelper mMyDataBaseHelper;  //数据库打开帮助类


    public FavoriteDAO(Context context) {
        mContext = context;
    }

    //打开数据库
    public void openDB() {
        mMyDataBaseHelper = new MyDataBaseHelper(mContext, DB_NAME, null, DB_VERSION);

        try {
            mSQLiteDatabase = mMyDataBaseHelper.getWritableDatabase();//获取可写数据库
        } catch (SQLException e) {
            mSQLiteDatabase = mMyDataBaseHelper.getReadableDatabase();//获取只读数据库
        }
    }

    //关闭数据库
    public void closeDB() {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.close();
        }
    }

    //插入一条数据
    public long insertOneData(Favorite favorite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(_URL, favorite.getUrl());
        contentValues.put(_TITLE, favorite.getTitle());
        contentValues.put(_STATE, favorite.getState());
        return mSQLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    //删除一条数据
    public long deleteOneData(String id) {
        return mSQLiteDatabase.delete(TABLE_NAME, _ID + "=" + id, null);
    }

    //删除所有数据
    public long deleteAllData() {
        return mSQLiteDatabase.delete(TABLE_NAME, null, null);
    }

    //更新一条数据
    public long updateOneData(String id, Favorite favorite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(_URL, favorite.getUrl());
        contentValues.put(_TITLE, favorite.getTitle());
        contentValues.put(_STATE, favorite.getState());
        return mSQLiteDatabase.update(TABLE_NAME, contentValues, _ID + "=" + id, null);
    }

    //查询一条数据
    public List<Favorite> queryOneData(String id) {
        Cursor results = mSQLiteDatabase.query(
                TABLE_NAME, new String[]{_ID, _URL, _TITLE, _STATE},
                _ID + "=" + "'" + id + "'", null, null, null, null);
        return converToTree(results);
    }


    //查询所有数据
    public List<Favorite> queryAllData() {
        Cursor results = mSQLiteDatabase.query(
                TABLE_NAME, new String[]{_ID, _URL, _TITLE, _STATE},
                null, null, null, null, null);
        return converToTree(results);
    }


    private List<Favorite> converToTree(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        List<Favorite> favoriteList = new ArrayList<>();
        for (int i = 0; i < resultCounts; i++) {
            Favorite favorite = new Favorite();
            favorite.setId(cursor.getInt(0));
            favorite.setUrl(cursor.getString(cursor.getColumnIndex(_URL)));
            favorite.setTitle(cursor.getString(cursor.getColumnIndex(_TITLE)));
            favorite.setState(cursor.getString(cursor.getColumnIndex(_STATE)));
            favoriteList.add(favorite);
            cursor.moveToNext();
        }
        return favoriteList;
    }

}
