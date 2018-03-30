package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    //from https://developers.google.com/admob/android/interstitial
    private InterstitialAd mInterstitialAd;
    private Button buttonForJoke;
    private ProgressBar progressBar;
    private static final String TAG = MainActivityFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = root.findViewById(R.id.progressBar);
        buttonForJoke = root.findViewById(R.id.buttonForJoke);

        MobileAds.initialize(getActivity(),
                getString(R.string.initializeADS));

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.adUnit));

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        buttonForJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    Log.e(TAG, getString(R.string.notLoadedYet));

                    MainActivity mainActivity = (MainActivity)getActivity();
                    mainActivity.tellJoke();
                    //load new ad
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            }
        });

        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {

                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.tellJoke();
                progressBar.setVisibility(View.VISIBLE);
                //load new ad
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        return root;
    }

}
