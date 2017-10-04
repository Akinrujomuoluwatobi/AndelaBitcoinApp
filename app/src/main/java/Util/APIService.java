package Util;

import com.royalteck.progtobi.aauaeventmanager.Model.EventModel;

import java.util.ArrayList;

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

    @GET("api")
    Call<ArrayList<EventModel>> getData();

}
