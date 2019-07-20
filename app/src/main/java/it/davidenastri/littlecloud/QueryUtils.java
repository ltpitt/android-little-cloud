package it.davidenastri.littlecloud;

/**
 * Created by DNastri on 5/11/2017.
 */

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.LOG_TAG;

public final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }


    public static void changeColor(String rgbString, final String colorSet, final View onClickView,
                                   final View tabLightView, final ProgressBar spinner) {
        AsyncHttpClient client = new AsyncHttpClient();
        final String PARTICLE_DEVICE_ID = "";
        final String PARTICLE_TOKEN_ID = "";
        final String PARTICLE_API_URL = "";
        final RequestParams params = new RequestParams();
        params.put("access_token", PARTICLE_TOKEN_ID);
        params.put("args", rgbString);
        client.post(PARTICLE_API_URL + PARTICLE_DEVICE_ID + "/setColor", params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                // called when response HTTP status is "200 OK"
                // Print the chosen color to log
                Log.i(LOG_TAG, colorSet);
                //Toast.makeText(tabLightView.getContext(), colorSet, Toast.LENGTH_SHORT).show();
                //Snackbar.make(onClickView, "Lamp color changed successfully, yey! :D", Snackbar.LENGTH_LONG).show();
                spinner.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e(LOG_TAG, PARTICLE_API_URL + PARTICLE_DEVICE_ID + "/setColor" + params);
                //Toast.makeText(tabLightView.getContext(), PARTICLE_API_URL + PARTICLE_DEVICE_ID + "/setColor" + params, Toast.LENGTH_SHORT).show();
                Snackbar.make(onClickView, "Little Cloud is offline... :(", Snackbar.LENGTH_LONG).show();
                spinner.setVisibility(View.INVISIBLE);
            }
        });
    }


    public static void changeAudio(final String commandString, final View onClickView) {
        AsyncHttpClient client = new AsyncHttpClient();
        final String PARTICLE_DEVICE_ID = "";
        final String PARTICLE_TOKEN_ID = "";
        final String PARTICLE_API_URL = "";
        final RequestParams params = new RequestParams();
        params.put("access_token", PARTICLE_TOKEN_ID);
        params.put("args", commandString);
        Log.i("changeAudio:", commandString);
        client.post(PARTICLE_API_URL + PARTICLE_DEVICE_ID + "/dfMini", params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                // called when response HTTP status is "200 OK"
                // Print the chosen audio command to log
                Log.i(LOG_TAG, commandString);
                //Toast.makeText(tabSoundView.getContext(), colorSet, Toast.LENGTH_SHORT).show();
                //Snackbar.make(onClickView, "Lamp audio changed successfully, yey! :D", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                //Log.e(LOG_TAG, PARTICLE_API_URL + PARTICLE_DEVICE_ID + "/setColor" + params);
                //Toast.makeText(tabSoundView.getContext(), PARTICLE_API_URL + PARTICLE_DEVICE_ID + "/setColor" + params, Toast.LENGTH_SHORT).show();
                Snackbar.make(onClickView, "Little Cloud is offline... :(", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}