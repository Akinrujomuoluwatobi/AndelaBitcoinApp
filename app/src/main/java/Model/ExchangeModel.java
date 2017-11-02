package Model;


import com.google.gson.annotations.SerializedName;

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

    private float AUD, INR, COP, EGP, ZAR, GBP, PHP, PLN, JPY, CAD,
            BRL, GHC, HKD, PKR, THB, UGX, UAH;

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

    public float getAUD() {
        return AUD;
    }

    public float getINR() {
        return INR;
    }

    public float getCOP() {
        return COP;
    }

    public float getEGP() {
        return EGP;
    }

    public float getZAR() {
        return ZAR;
    }

    public float getGBP() {
        return GBP;
    }

    public float getPHP() {
        return PHP;
    }

    public float getPLN() {
        return PLN;
    }

    public float getJPY() {
        return JPY;
    }

    public float getCAD() {
        return CAD;
    }

    public float getBRL() {
        return BRL;
    }

    public float getGHC() {
        return GHC;
    }

    public float getHKD() {
        return HKD;
    }

    public float getPKR() {
        return PKR;
    }

    public float getTHB() {
        return THB;
    }

    public float getUGX() {
        return UGX;
    }

    public float getUAH() {
        return UAH;
    }

}
