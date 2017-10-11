package Util;

import java.util.ArrayList;
import java.util.List;

import Model.ExchangeModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PROG. TOBI on 10-Jul-17.
 */

public interface APIService {
    /*@FormUrlEncoded
    @POST("")
    Call<> userLogin(@Field("uname") String uname,
                     @Field("pk") String upass);*/

    @GET("price?fsym=BTC&tsyms=USD,EUR,NGN&extraParams=AndelaBitcoinApp")
    Call<ExchangeModel> getData();

}
