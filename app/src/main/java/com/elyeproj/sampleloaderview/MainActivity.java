package com.elyeproj.sampleloaderview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int WAIT_DURATION = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    private void loadData() {
        new DummyWait().execute();
    }

    private void postLoadData() {
        ((TextView)findViewById(R.id.txt_name)).setText("Mr. Donald Trump");
        ((TextView)findViewById(R.id.txt_title)).setText("Presidency Candidate of United State");
        ((TextView)findViewById(R.id.txt_phone)).setText("+001 1234 5678");
        ((TextView)findViewById(R.id.txt_email)).setText("donald.trump@donaldtrump.com.au");
        ((ImageView)findViewById(R.id.image_icon)).setImageResource(R.drawable.trump);
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

}
