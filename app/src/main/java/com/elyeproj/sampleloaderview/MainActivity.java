package com.elyeproj.sampleloaderview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elyeproj.loaderviewlibrary.LoaderImageView;
import com.elyeproj.loaderviewlibrary.LoaderTextView;

public class MainActivity extends AppCompatActivity {

    private int WAIT_DURATION = 5000;
    private DummyWait dummyWait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    private void loadData() {
        if (dummyWait != null) {
            dummyWait.cancel(true);
        }
        dummyWait = new DummyWait();
        dummyWait.execute();
    }

    private void postLoadData() {
        ((TextView)findViewById(R.id.txt_name)).setText("Mr. Donald Trump");
        ((TextView)findViewById(R.id.txt_title)).setText("President of United State (2017 - current)");
        ((TextView)findViewById(R.id.txt_phone)).setText("+001 2345 6789");
        ((TextView)findViewById(R.id.txt_email)).setText("donald.trump@donaldtrump.com");
        ((ImageView)findViewById(R.id.image_icon)).setImageResource(R.drawable.trump);
    }

    public void resetLoader(View view) {
        ((LoaderTextView)findViewById(R.id.txt_name)).resetLoader();
        ((LoaderTextView)findViewById(R.id.txt_title)).resetLoader();
        ((LoaderTextView)findViewById(R.id.txt_phone)).resetLoader();
        ((LoaderTextView)findViewById(R.id.txt_email)).resetLoader();
        ((LoaderImageView)findViewById(R.id.image_icon)).resetLoader();
        loadData();
    }

    class DummyWait extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(WAIT_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            postLoadData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dummyWait != null) {
            dummyWait.cancel(true);
        }
    }
}
