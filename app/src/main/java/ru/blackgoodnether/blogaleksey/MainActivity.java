package ru.blackgoodnether.blogaleksey;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import ru.blackgoodnether.blogaleksey.fragments.NoInternetFragment;
import ru.blackgoodnether.blogaleksey.fragments.WebFragment;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    @Override
    public void onBackPressed() {
        WebView webView = WebFragment.webView;
        if(webView != null) {
            if(webView.canGoBack() & checkInternetConnection(this)) {
               webView.goBack();
            } else finish();
        } else finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        if(!checkInternetConnection(this)) {
            loadFragment(new NoInternetFragment());
        } else {
            loadFragment(new WebFragment());
        }
    }

    public static MainActivity newInstance() {
        return MainActivity.instance;
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_f, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager con_manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected());
    }
}
