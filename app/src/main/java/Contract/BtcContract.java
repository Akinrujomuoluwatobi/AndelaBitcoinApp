package Contract;

import java.util.List;

import Model.ViewModel;
import Presenter.BtcPresenter;

/**
 * Created by PROG. TOBI on 04-Oct-17.
 */

public class BtcContract {

    public interface View{

        void setPresenter(BtcPresenter btcPresenter);

        void checkInternet();

        void showMessage(String s);

        void showLoading(boolean b);

        void setDevelopersAdapter(List<ViewModel> countrie);

    }
}
