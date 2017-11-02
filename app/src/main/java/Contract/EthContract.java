package Contract;

import java.util.List;

import Model.ViewModel;
import Presenter.EthPresenter;

/**
 * Created by PROG. TOBI on 04-Oct-17.
 */

public class EthContract {

    public interface View {

        void setPresenter(EthPresenter btcPresenter);

        void checkInternet();

        void showMessage(String s);

        void showLoading(boolean b);

        void setDevelopersAdapter(List<ViewModel> countrie);

    }
}
