package com.example.android.vk_info.utils;


import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

//https://api.vk.com/method/users.get?user_ids=210700286&fields=bdate&access_token=93ae3f2e93ae3f2e93ae3f2ee893d85c66993ae93ae3f2ef38b5ad54d956b48aeac6cc7&v=5.80

//вспомогательный класс
public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.vk.com/";
    private static final String VK_USERS_GET = "method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";
    private static final String version_value = "5.89";
    private static final String PARAM_SK = "access_token";
    private static final String access_token = "93ae3f2e93ae3f2e93ae3f2ee893d85c66993ae93ae3f2ef38b5ad54d956b48aeac6cc7";


    public static URL generalURL(String userID) {
        //собираем запрос
        Uri buildUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID, userID)
                .appendQueryParameter(PARAM_SK, access_token)
                .appendQueryParameter(PARAM_VERSION, version_value)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext())
                return scanner.next();
            else
                return null;
        } catch (UnknownHostException e) { // нет интернета
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }


}
