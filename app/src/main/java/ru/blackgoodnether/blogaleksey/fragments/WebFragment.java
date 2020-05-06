package ru.blackgoodnether.blogaleksey.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import ru.blackgoodnether.blogaleksey.CustomWebViewClient;
import ru.blackgoodnether.blogaleksey.MainActivity;
import ru.blackgoodnether.blogaleksey.R;

public class WebFragment extends Fragment {

    public static WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_web, null);
        webView = view.findViewById(R.id.web);
        final MainActivity content = MainActivity.newInstance();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.navbo);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.back: {
                        webView.goBack();
                        break;
                    }
                    case R.id.quit: {
                        content.finish();
                        break;
                    }
                    case R.id.verh: {
                        webView.scrollTo(0, 0);
                        break;
                    }
                }
                return false;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new CustomWebViewClient((ProgressBar) view.findViewById(R.id.progressBar), view));
        webView.loadUrl("http://blog.alexeylesin.ru");
        return view;
    }
}
