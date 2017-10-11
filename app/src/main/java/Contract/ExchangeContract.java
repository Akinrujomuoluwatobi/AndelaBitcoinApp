package Contract;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

import Model.ExchangeModel;
import Presenter.ExchangePresenter;

/**
 * Created by PROG. TOBI on 04-Oct-17.
 */

public class ExchangeContract {

    public interface View{

        void setPresenter(ExchangePresenter exchangePresenter);

        void checkInternet();

        void showMessage(String s);

        void showLoading(boolean b);

        void setDevelopersAdapter(List<Double> mExchanges, List<String> countries);

        void addResquest(JsonObjectRequest stringRequest);
    }
}
