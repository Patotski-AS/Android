package com.example.android.vk_info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.android.vk_info.utils.NetworkUtils.generalURL;
import static com.example.android.vk_info.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    private EditText etNumberID;
    private TextView tvFoundText;
    private TextView tvErrorMassage;
    private ProgressBar pbLoadIndicator;

    private void showTvFoundText() {
        tvErrorMassage.setVisibility(View.INVISIBLE);
        tvFoundText.setVisibility(View.VISIBLE);
    }

    private void showTvErrorMassage() {
        tvFoundText.setVisibility(View.INVISIBLE);
        tvErrorMassage.setVisibility(View.VISIBLE);

    }

    // работа в другом (не в главном) потоке
    class VKQueryTask extends AsyncTask<URL, Void, String> {
        //до
        @Override
        protected void onPreExecute() {
            pbLoadIndicator.setVisibility(View.VISIBLE);
        }

        //работа в другом потоке
        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        //после
        @Override
        protected void onPostExecute(String response) {
            String firstName = null;
            String lastName = null;
            if (response != null && !response.equals("")) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("response");
                    JSONObject userInfo = jsonArray.getJSONObject(0);

                    firstName = userInfo.getString("first_name");
                    lastName = userInfo.getString("last_name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String result = "Имя: " + firstName + "\n" + "Фамилия: " + lastName;
                tvFoundText.setText(result);

                showTvFoundText();
            } else
                showTvErrorMassage();

            pbLoadIndicator.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumberID = findViewById(R.id.et_numberID);
        tvFoundText = findViewById(R.id.tv_foundText);
        tvErrorMassage = findViewById(R.id.tv_error_massage);
        pbLoadIndicator = findViewById(R.id.pb_load_indicator);

    }

    //нажатие кнопки
    public void click_button_find(View view) {
        URL url = generalURL(etNumberID.getText().toString());
        new VKQueryTask().execute(url);
    }
}