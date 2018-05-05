package com.example.pc.draggerdemo.modules.sports;


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
import com.example.pc.draggerdemo.modules.SharedAdapter;
import com.example.pc.draggerdemo.modules.main.FragmentChangeListener;
import com.example.pc.draggerdemo.modules.main.MainActivity;
import com.example.pc.draggerdemo.modules.sports.di.component.DaggerSportComponent;
import com.example.pc.draggerdemo.modules.sports.di.module.SportModule;
import com.example.pc.draggerdemo.modules.sports.mvp.model.SportsResponse;
import com.example.pc.draggerdemo.modules.sports.mvp.presenter.SportsPresenter;
import com.example.pc.draggerdemo.modules.sports.mvp.view.ISportsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportsFragment extends Fragment implements ISportsView {
    FragmentChangeListener fragmentChangeListener;
    SharedAdapter sharedAdapter;
    @BindView(R.id.activity_main_rv)
    RecyclerView mRecyclerView;

    @Inject
    SportsPresenter sportsPresenter;

    public SportsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentChangeListener = ((MainActivity) getActivity()).getInterface();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        * initialize the component
        * view and inject it instance
        * */
        DaggerSportComponent.builder()
                .applicationComponent(fragmentChangeListener.getMainComponent())
                .sportModule(new SportModule((ISportsView) this))
                .build()
                .inject(this);

        /*initialize the adapter*/
        initlizeAdapter();

        /*initialize the presenter*/
        initilizePresenter();

    }


    private void initlizeAdapter() {
        sharedAdapter = new SharedAdapter(getActivity(), getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(sharedAdapter);

    }

    private void initilizePresenter() {
        sportsPresenter.getSports();
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
    public void onLoadView(SportsResponse sportsResponse) {
        if (sportsResponse.getContent() != null) {
//            sharedAdapter.addAll(sportsResponse.getContent());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sportsPresenter.unsubscibeSubscription();
    }

    @Override
    public void onStop() {
        super.onStop();
        sportsPresenter.unsubscibeSubscription();
    }
}
