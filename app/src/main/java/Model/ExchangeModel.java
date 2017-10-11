package Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PROG. TOBI on 04-Oct-17.
 */

public class ExchangeModel {


    @SerializedName("USD")
    private float USD;

    @SerializedName("EUR")
    private float EUR;

    @SerializedName("NGN")
    private float NGN;

    public float getUSD() {
        return USD;
    }

    public void setUSD(float USD) {
        this.USD = USD;
    }

    public float getEUR() {
        return EUR;
    }

    public void setEUR(float EUR) {
        this.EUR = EUR;
    }

    public float getNGN() {
        return NGN;
    }

    public void setNGN(float NGN) {
        this.NGN = NGN;
    }

    /*@SerializedName("BTC")
    private List<Values> BTC;


    public class Values {

        @SerializedName("USD")
        private float USD;

        @SerializedName("EUR")
        private float EUR;

        @SerializedName("NGN")
        private float NGN;

        public float getUSD() {
            return USD;
        }

        public float getEUR() {
            return EUR;
        }

        public float getNGN() {
            return NGN;
        }
    }*/

}
