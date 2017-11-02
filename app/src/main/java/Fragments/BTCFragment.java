package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.progtobi.andelabitcoinapp.R;

import java.util.List;

import Adapters.ViewCoinsAdapter;
import Contract.BtcContract;
import Model.ViewModel;
import Presenter.BtcPresenter;
import Util.EventManagerApp;

/**
 * Created by PROG. TOBI on 06-Oct-17.
 */

public class BTCFragment extends Fragment implements BtcContract.View {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    BtcPresenter presenter;
    private RecyclerView exchangeRecycler;
    SwipeRefreshLayout refreshLayout;
    private ViewCoinsAdapter viewCoinAdapter;
    int mNoOfColumns;

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
        mNoOfColumns = calculateNoOfColumns(getActivity());
        refreshLayout = rootView.findViewById(R.id.btcswiperefresh);
        presenter = new BtcPresenter(this);
        exchangeRecycler = rootView.findViewById(R.id.exchangerecycler);
        exchangeRecycler.setLayoutManager(new GridLayoutManager(getActivity(), mNoOfColumns));

        bindRecycler(rootView);
        return rootView;

    }

    public void bindRecycler(View rootView) {
        exchangeRecycler = rootView.findViewById(R.id.exchangerecycler);
        exchangeRecycler.setLayoutManager(new GridLayoutManager(getActivity(), mNoOfColumns));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.fetchExchange("btc");
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
    public void setPresenter(BtcPresenter btcPresenter) {
        presenter = btcPresenter;
    }

    @Override
    public void checkInternet() {
        if (EventManagerApp.getInstance().isOnline(getActivity())) {
            presenter.fetchExchange("btc");
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
        refreshLayout.setRefreshing(b);
    }

    @Override
    public void setDevelopersAdapter(List<ViewModel> countrie) {
        viewCoinAdapter = new ViewCoinsAdapter(countrie, getActivity(), "btc");
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setMoveDuration(1000);
        defaultItemAnimator.setChangeDuration(1000);
        exchangeRecycler.setItemAnimator(defaultItemAnimator);
        exchangeRecycler.setAdapter(viewCoinAdapter);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 110);
        return noOfColumns;
    }
}
