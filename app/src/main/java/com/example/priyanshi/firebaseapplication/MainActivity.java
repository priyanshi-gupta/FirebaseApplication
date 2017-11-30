package com.example.priyanshi.firebaseapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;

public class MainActivity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";
    private static final String TAG = "MainActivity";

    private TextView mLevelTextView;
    private Button youtubeButton;
    private Button shareButton;
    private EditText minText, secText, urlText;
    private WebView youtubeView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        minText = (EditText) findViewById(R.id.time_min);
        secText = (EditText) findViewById(R.id.time_sec);
        urlText = (EditText) findViewById(R.id.url_edit);
        youtubeView = (WebView) findViewById(R.id.youtube_webview);
        youtubeButton = ((Button) findViewById(R.id.youtube_button));
        youtubeView.setWebViewClient(new MyBrowser());
//        url = urlText.getText().toString();
        minText.clearFocus();
        secText.clearFocus();
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showYoutubeVid();
            }
        });

        shareButton = ((Button) findViewById(R.id.youtube_share_button));
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareYoutubeVid();
            }
        });

        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }

    private void showYoutubeVid() {
        url = "https://youtu.be/A0RDIT5-bng";
        youtubeView.getSettings().setLoadsImagesAutomatically(true);
        youtubeView.getSettings().setJavaScriptEnabled(true);
        youtubeView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        youtubeView.loadUrl(url);
//        Log.i(TAG, youtubeView.getUrl() );
        //        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/g334Sn9L3rY")));
    }

    private void shareYoutubeVid() {
        url = "https://youtu.be/A0RDIT5-bng";
        String mins = minText.getText().toString();
        String secs = secText.getText().toString();
        String test = url + "?t=" + mins + "m" + secs + "s";

//        urlText = "https://m273s.app.goo.gl/rniX";

        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(url))
                .setDynamicLinkDomain("m273s.app.goo.gl/")
                .buildDynamicLink();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this video");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey start playing this video: " + "m273s.app.goo.gl/rniX");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share via"));
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
