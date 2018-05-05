package com.example.pc.draggerdemo.modules.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pc.draggerdemo.R;
import com.example.pc.draggerdemo.modules.news.di.component.DaggerNewsComponent;
import com.example.pc.draggerdemo.modules.news.mvp.model.NewsResponse;
import com.example.pc.draggerdemo.modules.SharedAdapter;
import com.example.pc.draggerdemo.modules.main.FragmentChangeListener;
import com.example.pc.draggerdemo.modules.main.MainActivity;
import com.example.pc.draggerdemo.modules.news.di.module.NewsModule;
import com.example.pc.draggerdemo.modules.news.mvp.presenter.NewsPresenter;
import com.example.pc.draggerdemo.modules.news.mvp.view.INewsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment implements INewsView {

    @Inject
    protected NewsPresenter mPresenter;
    @BindView(R.id.activity_main_rv)
    RecyclerView mRecyclerView;
    FragmentChangeListener fragmentChangeListener;
    private SharedAdapter sharedAdapter;


    public NewsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentChangeListener = ((MainActivity) getActivity()).getInterface();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resolveDependencies();
        initlizeAdapter();
        initilizePresenter();
    }

    private void initlizeAdapter() {
        sharedAdapter = new SharedAdapter(getActivity(), getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(sharedAdapter);

    }

    private void initilizePresenter() {
        mPresenter.getNews();
    }

    protected void resolveDependencies() {

        DaggerNewsComponent.builder()
                .applicationComponent(fragmentChangeListener.getMainComponent())
                .newsModule(new NewsModule((INewsView) this))
                .build()
                .inject(this);

    }

    @Override
    public void onShowProgressDialog(String s) {
        fragmentChangeListener.onShowProgressDalog(s);
    }

    @Override
    public void onHideProgresDialog() {
        fragmentChangeListener.onHideProgressDialog();

    }

    @Override
    public void onShowToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoadView(NewsResponse newsResponse) {
        if (newsResponse != null) {
            sharedAdapter.addAll(newsResponse.getContent());
        }

    }
}

