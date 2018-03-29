package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Button buttonForJoke;
    private ProgressBar progressBar;
    private static final String NUMBER_OF_RECEIVER = "close_adv_tell_joke";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = root.findViewById(R.id.progressBar);
        buttonForJoke = root.findViewById(R.id.buttonForJoke);
        buttonForJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSendSignal();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

    private void toSendSignal() {
        Intent intent = new Intent();
        intent.setAction(NUMBER_OF_RECEIVER);
        getActivity().sendBroadcast(intent);

    }

}
