package Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Contract.ExchangeContract;
import Model.ExchangeModel;
import Model.ViewModel;
import Util.APIService;
import Util.ApiClient;
import Util.Mysingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROG. TOBI on 04-Oct-17.
 */

public class ExchangePresenter {
    ExchangeContract.View mView;
    ArrayList<ExchangeModel> mExchanges;
    APIService eventManApi;
    String[] countries = {"USD","EUR","NGN"};
    //List<ViewModel> country;
    List<String> country = new ArrayList<>();
    List<Double> values = new ArrayList<>();
    String Base_URL = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,EUR,NGN&extraParams=AndelaBitcoinApp";

    public ExchangePresenter(ExchangeContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mView.checkInternet();
        eventManApi = ApiClient.getClient().create(APIService.class);
    }

    public void fetchExchange() {
        mView.showLoading(true);
        /*JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, Base_URL, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Log.d("RESPONSE2", String.valueOf(response.getJSONObject("")));
                    Log.d("RESPONSE", response.getString("USD"));
                    double code = response.getDouble("USD");
                    mView.showMessage(String.valueOf(code));


                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    mView.showMessage("Connection Could Not be Establish At The Moment, Try Later...");
                    error.printStackTrace();
                } else if (error instanceof AuthFailureError) {
                    mView.showMessage("Failure Authenticating the Request...");
                    error.printStackTrace();

                } else if (error instanceof ServerError) {
                    mView.showMessage("Error Response from the Server...");
                    error.printStackTrace();

                } else if (error instanceof ParseError) {
                    mView.showMessage("Server Error...");
                    error.printStackTrace();

                } else if (error instanceof NetworkError) {
                    mView.showMessage("Network Error, Check Your Network Connection...");
                    error.printStackTrace();

                }

                //pb.setVisibility(View.GONE);

            }
        });
        mView.addResquest(stringRequest);*/

        eventManApi = ApiClient.getClient().create(APIService.class);
        eventManApi.getData().enqueue(new Callback<ExchangeModel>() {
            @Override
            public void onResponse(Call<ExchangeModel> call, Response<ExchangeModel> response) {
                if (response.body() != null) {
                    ViewModel viewModel = new ViewModel();
                    //viewModel.setcName();
                    values.add((double) response.body().getUSD());
                    values.add((double) response.body().getEUR());
                    values.add((double) response.body().getNGN());

                    populateEventsList();

                } else {
                    Log.d("RESPONSE", "Response Body is empty");
                }

                mView.showLoading(false);
            }

            @Override
            public void onFailure(Call<ExchangeModel> call, Throwable t) {

                Log.d("onFailure", t.getMessage());
                mView.showMessage(t.toString());
                mView.showLoading(false);
            }
        });
    }


    private void populateEventsList() {
        if (values.size() < 1) {
            mView.showMessage("Events list is empty");
        } else {
            mView.setDevelopersAdapter(values, country);
            //meventAdapter.notifyDataSetChanged();
        }
    }
}
