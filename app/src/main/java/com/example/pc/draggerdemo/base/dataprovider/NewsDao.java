package com.example.pc.draggerdemo.base.dataprovider;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContent;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContentData;

import java.util.List;


/**
 * Created by PC on 5/5/2018.
 */

@Dao
public interface NewsDao {
    @Query(" Select * from News_Header_Details ")
    LiveData<List<NewsResponseContent>> getAllWords();
    @Insert
    void insertHeaderData(NewsResponseContent content);
    @Insert
    void insertChildData(NewsResponseContentData data);

    @Query("DELETE  from News_Header_Details")
    void deleteAllHeaderData();


    @Query("DELETE  from NEWS_CONTENT")
    void deleteAllChildData();

    @Query(" Select * from NEWS_CONTENT where HEADER_ID = :headerId ")
    LiveData<NewsResponseContentData> getChidNewsData(int headerId);
}
