package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.progtobi.andelabitcoinapp.R;

import java.util.List;

import Adapters.ViewCoinsAdapter;
import Contract.ExchangeContract;
import Model.ExchangeModel;
import Presenter.ExchangePresenter;
import Util.EventManagerApp;

/**
 * Created by PROG. TOBI on 06-Oct-17.
 */

public class BTCFragment extends Fragment implements ExchangeContract.View {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ExchangePresenter presenter;
    private RecyclerView exchangeRecycler;
    List<ExchangeModel> mEventList;
    SwipeRefreshLayout refreshLayout;
    private ViewCoinsAdapter viewCoinAdapter;

    public BTCFragment.OnFragmentInteractionListener mListener;

    public BTCFragment() {

    }

    public static BTCFragment newInstance(String param1, String param2) {
        BTCFragment fragment = new BTCFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.btcfragmentlayout, container, false);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        refreshLayout = rootView.findViewById(R.id.btcswiperefresh);
        presenter = new ExchangePresenter(this);
        exchangeRecycler = rootView.findViewById(R.id.exchangerecycler);
        exchangeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        bindRecycler(rootView);
        return rootView;

    }

    public void bindRecycler(View rootView) {
        exchangeRecycler = rootView.findViewById(R.id.exchangerecycler);
        exchangeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.fetchExchange();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BTCFragment.OnFragmentInteractionListener) {
            mListener = (BTCFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(ExchangePresenter exchangePresenter) {
        presenter = exchangePresenter;
    }

    @Override
    public void checkInternet() {
        if (EventManagerApp.getInstance().isOnline(getActivity())) {
            presenter.fetchExchange();
        } else {
            showMessage("Pls Check Internet Connection");
        }
    }

    @Override
    public void showMessage(String s) {
        displayM(s);
    }

    public void displayM(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(boolean b) {
        //ongoingeventpb.setVisibility(b ? View.VISIBLE : View.GONE);
        refreshLayout.setRefreshing(b);
    }

    @Override
    public void setDevelopersAdapter(List<Double> mExchanges, List<String> countries) {

        viewCoinAdapter = new ViewCoinsAdapter(mExchanges, countries, getActivity());
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setMoveDuration(1000);
        defaultItemAnimator.setChangeDuration(1000);
        exchangeRecycler.setItemAnimator(defaultItemAnimator);
        exchangeRecycler.setAdapter(viewCoinAdapter);
    }

    @Override
    public void addResquest(JsonObjectRequest stringRequest) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
