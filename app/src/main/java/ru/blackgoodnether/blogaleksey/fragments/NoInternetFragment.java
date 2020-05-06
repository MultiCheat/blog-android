package ru.blackgoodnether.blogaleksey.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;

import ru.blackgoodnether.blogaleksey.MainActivity;
import ru.blackgoodnether.blogaleksey.R;

public class NoInternetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_no_internet, null);
        final WebView webView = WebFragment.webView;
        view.findViewById(R.id.povt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.checkInternetConnection(view.getContext())) {
                   MainActivity.newInstance().loadFragment(new WebFragment());
                }
            }
        });
        view.findViewById(R.id.quit_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.newInstance().finish();
            }
        });
        return view;
    }
}
