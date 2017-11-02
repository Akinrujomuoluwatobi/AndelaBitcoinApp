package Util;

import Model.ExchangeModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PROG. TOBI on 10-Jul-17.
 */

public interface APIService {

    @GET("price?fsym=BTC&tsyms=USD,EUR,NGN,AUD,INR,COP,EGP,ZAR,GBP,PHP,PLN,JPY,CAD,BRL,GHC,HKD,PKR,THB,UGX,UAH" +
            "&extraParams=AndelaBitcoinApp")
    Call<ExchangeModel> getBtc();

    @GET("price?fsym=ETH&tsyms=USD,EUR,NGN,AUD,INR,COP,EGP,ZAR,GBP,PHP,PLN,JPY,CAD,BRL,GHC,HKD,PKR,THB,UGX,UAH" +
            "&extraParams=AndelaBitcoinApp")
    Call<ExchangeModel> getEth();

}
