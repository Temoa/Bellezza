package com.temoa.bellezza.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.temoa.bellezza.R;
import com.temoa.bellezza.presenter.MainViewPresenter;
import com.temoa.bellezza.view.IMainView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView, AdapterView.OnItemClickListener {

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private MainViewPresenter mainViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.write));
        listView.setOnItemClickListener(this);
        mainViewPresenter = new MainViewPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        mainViewPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mainViewPresenter.onItemClick(position);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getItem(List<String> item) {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, item));
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toWebActivity(String url) {
        Intent intent = new Intent();
        intent.setClass(this,WebActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}