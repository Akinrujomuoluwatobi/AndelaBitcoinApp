package Presenter;

import Contract.ExchangeContract;

/**
 * Created by PROG. TOBI on 04-Oct-17.
 */

public class ExchangePresenter {
    ExchangeContract.View mView;

    public ExchangePresenter(ExchangeContract.View view) {
        mView = view;

        mView.setPresenter(this);
    }
}
