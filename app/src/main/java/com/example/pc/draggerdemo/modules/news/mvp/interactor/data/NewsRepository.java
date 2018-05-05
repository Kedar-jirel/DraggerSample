package com.example.pc.draggerdemo.modules.news.mvp.interactor.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.pc.draggerdemo.base.dataprovider.NewsDao;
import com.example.pc.draggerdemo.base.dataprovider.NewsDatabase;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContent;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponseContentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 5/5/2018.
 */

public class NewsRepository {
    private NewsDao newsDao;
    NewsDatabase newsDatabase;

    public NewsRepository(Application application) {
        newsDatabase = NewsDatabase.getDatabase(application);
        this.newsDao = newsDatabase.newsDao();
    }

    public LiveData<List<NewsResponseContent>> getAllNews() {
        return newsDao.getAllWords();
    }


    public void saveNews(ArrayList<NewsResponseContent> content) {
        new insertAsyncTask(newsDao).execute(content);
    }

    public void deletAllNewsComponent() {
        new deletAllAsyncTask(newsDao).execute();
    }

    public LiveData<NewsResponseContentData> getNewsContentById(int id) {
        return newsDao.getChidNewsData(id);
    }

    private static class insertAsyncTask extends AsyncTask<ArrayList<NewsResponseContent>, Void, Void> {
        private NewsDao mAsyncTaskDao;

        insertAsyncTask(NewsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ArrayList<NewsResponseContent>... params) {
            List<NewsResponseContent> newsResponseContent = (List<NewsResponseContent>) params[0];
            for (NewsResponseContent content : newsResponseContent) {
                mAsyncTaskDao.insertHeaderData(content);
                mAsyncTaskDao.insertChildData(content.getData());
            }
            return null;
        }
    }

    private static class deletAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsDao mAsyncTaskDao;

        public deletAllAsyncTask(NewsDao mWordDao) {
            this.mAsyncTaskDao = mWordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAllHeaderData();
            mAsyncTaskDao.deleteAllChildData();
            return null;
        }
    }
}
