package Presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Contract.EthContract;
import Model.ExchangeModel;
import Model.ViewModel;
import Util.APIService;
import Util.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROG. TOBI on 04-Oct-17.
 */

public class EthPresenter {
    EthContract.View mView;
    APIService eventManApi;
    List<ViewModel> countrie;

    public EthPresenter(EthContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mView.checkInternet();
        eventManApi = ApiClient.getClient().create(APIService.class);
    }

    public void fetchExchange(String fragtype) {
        mView.showLoading(true);
        eventManApi = ApiClient.getClient().create(APIService.class);
        if (fragtype.equals("eth")) {
            eventManApi.getEth().enqueue(new Callback<ExchangeModel>() {
                @Override
                public void onResponse(Call<ExchangeModel> call, Response<ExchangeModel> response) {
                    if (response.body() != null) {
                        countrie = new ArrayList<ViewModel>();
                        modelSet("USD", response.body().getUSD());
                        modelSet("EUR", response.body().getEUR());
                        modelSet("NGN", response.body().getNGN());
                        modelSet("AUD", response.body().getAUD());
                        modelSet("INR", response.body().getINR());
                        modelSet("COP", response.body().getCOP());
                        modelSet("EGP", response.body().getEGP());
                        modelSet("ZAR", response.body().getZAR());
                        modelSet("GBP", response.body().getGBP());
                        modelSet("PHP", response.body().getPHP());
                        modelSet("PLN", response.body().getPLN());
                        modelSet("JPY", response.body().getJPY());
                        modelSet("CAD", response.body().getCAD());
                        modelSet("BRL", response.body().getBRL());
                        modelSet("GHC", response.body().getGHC());
                        modelSet("HKD", response.body().getHKD());
                        modelSet("PKR", response.body().getPKR());
                        modelSet("THB", response.body().getTHB());
                        modelSet("UGX", response.body().getUGX());
                        modelSet("UAH", response.body().getUAH());

                        populateEventsList();

                    } else {
                        Log.d("RESPONSE", "Response Body is empty");
                    }

                    mView.showLoading(false);
                }

                @Override
                public void onFailure(Call<ExchangeModel> call, Throwable t) {
                    Log.d("onFailure", t.getMessage());
                    mView.showMessage("Connection timed out... try again");
                    mView.showLoading(false);
                }
            });
        }

    }

    public void modelSet(String ctry, float v) {
        ViewModel viewModel = new ViewModel();
        viewModel.setcName(ctry);
        viewModel.setcValue(v);
        countrie.add(viewModel);

    }

    private void populateEventsList() {
        if (countrie.size() < 1) {
            mView.showMessage("Events list is empty");
        } else {
            mView.setDevelopersAdapter(countrie);
        }
    }
}
