package ru.blackgoodnether.blogaleksey;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ru.blackgoodnether.blogaleksey.fragments.NoInternetFragment;

public class CustomWebViewClient extends WebViewClient {

    private final ProgressBar bar;
    private final View viewMain;

    public CustomWebViewClient(ProgressBar bar, View view) {
        this.bar = bar;
        this.viewMain = view;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(Uri.parse(url).getHost().endsWith("blog.alexeylesin.ru")) {
            return false;
        }
        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        return true;
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        MainActivity.newInstance().loadFragment(new NoInternetFragment());
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        view.setVisibility(viewMain.INVISIBLE);
        bar.setVisibility(viewMain.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.setVisibility(viewMain.VISIBLE);
        bar.setVisibility(viewMain.INVISIBLE);
        super.onPageFinished(view, url);

    }

}
