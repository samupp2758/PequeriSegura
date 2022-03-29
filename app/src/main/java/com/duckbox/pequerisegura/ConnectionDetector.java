package com.duckbox.pequerisegura;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {



    public ConnectionDetector(){

        }

        public boolean isConected(Context cnt) {

            ConnectivityManager conecm = (ConnectivityManager) cnt.getSystemService(Service.CONNECTIVITY_SERVICE);
            if (conecm != null) {
                NetworkInfo info = conecm.getActiveNetworkInfo();
                if (info != null) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }


                }
            }
return false;
        }

}
