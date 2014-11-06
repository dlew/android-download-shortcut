package net.danlew.download;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.widget.Toast;

public class DownloadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Uri uri = null;
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SEND)) {
            CharSequence text = intent.getCharSequenceExtra(Intent.EXTRA_TEXT);
            CharSequence subject = intent.getCharSequenceExtra(Intent.EXTRA_SUBJECT);

            if (!TextUtils.isEmpty(text) && URLUtil.isNetworkUrl(text.toString())) {
                uri = Uri.parse(text.toString());
            }
            else if (!TextUtils.isEmpty(subject) && URLUtil.isNetworkUrl(subject.toString())) {
                uri = Uri.parse(subject.toString());
            }
        }
        else if (action.equals(Intent.ACTION_VIEW)) {
            uri = intent.getData();
        }

        if (uri != null && URLUtil.isNetworkUrl(uri.toString())) {
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            dm.enqueue(request);
        }
        else {
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_LONG).show();
        }

        finish();
    }

}
