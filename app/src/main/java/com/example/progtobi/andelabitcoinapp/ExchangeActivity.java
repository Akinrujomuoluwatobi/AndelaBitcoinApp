package com.example.progtobi.andelabitcoinapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import Adapters.ViewCoinsAdapter;
import Contract.BtcContract;
import Model.ExchangeModel;
import Model.ViewModel;
import Presenter.BtcPresenter;
import Util.EventManagerApp;

public class ExchangeActivity extends AppCompatActivity implements BtcContract.View {

    BtcPresenter presenter;
    private RecyclerView exchangeRecycler;
    List<ExchangeModel> mEventList;
    private ViewCoinsAdapter viewCoinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new BtcPresenter(this);
        exchangeRecycler = (RecyclerView) findViewById(R.id.exchangerecycler);
        exchangeRecycler.setLayoutManager(new LinearLayoutManager(ExchangeActivity.this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exchange, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(BtcPresenter btcPresenter) {
        presenter = btcPresenter;
    }

    @Override
    public void checkInternet() {
        if (EventManagerApp.getInstance().isOnline(this)) {
            presenter.fetchExchange("btc");
        } else {
            showMessage("Pls Check Internet Connection");
        }
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(ExchangeActivity.this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(boolean b) {

    }

    @Override
    public void setDevelopersAdapter(List<ViewModel> countrie) {
        viewCoinAdapter = new ViewCoinsAdapter(countrie, ExchangeActivity.this, "eth");
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setMoveDuration(1000);
        defaultItemAnimator.setChangeDuration(1000);
        exchangeRecycler.setItemAnimator(defaultItemAnimator);
        exchangeRecycler.setAdapter(viewCoinAdapter);
    }

}
