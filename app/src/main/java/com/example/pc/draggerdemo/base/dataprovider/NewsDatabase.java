package com.example.pc.draggerdemo.base.dataprovider;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContent;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContentData;

/**
 * Created by PC on 5/5/2018.
 */
@android.arch.persistence.room.Database(entities = {NewsResponseContent.class, NewsResponseContentData.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();

    private static NewsDatabase INSTANCE;

    public static NewsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsDatabase.class, "NewsDatabase")
                            .build();
                    // Create database here
                }
            }
        }
        return INSTANCE;
    }


}
