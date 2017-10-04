package Contract;

import Presenter.ExchangePresenter;

/**
 * Created by PROG. TOBI on 04-Oct-17.
 */

public class ExchangeContract {

    ExchangeContract.View mview;
    public interface View{

        void setPresenter(ExchangePresenter exchangePresenter);
    }
}
