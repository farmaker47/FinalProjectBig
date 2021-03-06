package com.udacity.gradle.builditbigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.george.androidlibraryforjokes.AndroidLibraryMainActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static final String NUMBER_OF_RECEIVER = "close_adv_tell_joke";
    private BroadcastReceiver mBroadcastReceiver;
    private IntentFilter mFilter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBroadcastReceiver = new broadcastReceived();
        mFilter = new IntentFilter();
        mFilter.addAction(NUMBER_OF_RECEIVER);

        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver, mFilter);
    }

    //receive from paid flavor
    private class broadcastReceived extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String actionGet = intent.getAction();
            if (actionGet.equals(NUMBER_OF_RECEIVER)) {
                tellJoke();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke() {
        new EndpointsAsyncTask(this).execute();
    }

}

class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;
    private Context mContext;
    public static final String JOKE_FROM_JAVA = "joke_from_java";

    public EndpointsAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(String... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(mContext.getString(R.string.apiUrl))
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            myApiService = builder.build();
        }
        try {
            return myApiService.getGoodJoke().execute().getDataJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(mContext, AndroidLibraryMainActivity.class);
        intent.putExtra(JOKE_FROM_JAVA, result);
        mContext.startActivity(intent);
    }
}