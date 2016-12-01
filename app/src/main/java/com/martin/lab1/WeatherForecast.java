package com.martin.lab1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherForecast extends AppCompatActivity {
    private ProgressBar bar;
    private TextView currentTemp;
    private TextView tempMin;
    private TextView tempMax;
    private ImageView weatherImage;

    protected static final String WeatherForecast = "WeatherForcastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);


        Log.i(WeatherForecast, "In onCreate()");

        bar = (ProgressBar) findViewById(R.id.weatherBar);
        bar.setMax(10);
        bar.setVisibility(View.VISIBLE);

        currentTemp = (TextView) findViewById(R.id.currentTemp);
        tempMax = (TextView) findViewById(R.id.maxTemp);
        tempMin = (TextView) findViewById(R.id.minTemp);
        weatherImage = (ImageView) findViewById(R.id.weatherImage);


        new ForecastQuery().execute();
    }

    public class ForecastQuery extends AsyncTask<String, Integer, String> {

        public String min;
        public String max;
        public String current;
        public Bitmap currentWeather;
        public String iconName;
        public int state;

        public boolean fileExistence(String fName) {
            File file = getBaseContext().getFileStreamPath(fName);
            return file.exists();
        }

        protected String doInBackground(String... args) {

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();

                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(conn.getInputStream(), null);

                int type = XmlPullParser.START_DOCUMENT;

                while (type != XmlPullParser.END_DOCUMENT) {

                    switch (type) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            String name = parser.getName();
                            if (name.equals("temperature")) {
                                current = parser.getAttributeValue(null, "value");
                                publishProgress(25);
                                min = parser.getAttributeValue(null, "min");
                                publishProgress(50);
                                max = parser.getAttributeValue(null, "max");
                                publishProgress(75);

                                Log.i(WeatherForecast, "searching for: " + max + min);
                            }

                            if (name.equals("weather")) {
                                iconName = parser.getAttributeValue(null, "icon");

                                URL iconURL = new URL("http://openweathermap.org/img/w/"
                                        + iconName + ".png");

                                String fileName = iconName + ".png";

                                if (!fileExistence(fileName)) {
                                    HttpURLConnection connection = (HttpURLConnection) iconURL.openConnection();
                                    connection.connect();
                                    currentWeather = BitmapFactory.decodeStream(connection.getInputStream());

                                    FileOutputStream outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                                    currentWeather.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                    outputStream.flush();
                                    outputStream.close();
                                    publishProgress(100);
                                    Log.i(WeatherForecast, "Downloading: " + fileName);
                                    connection.disconnect();
                                } else {
                                    Log.i(WeatherForecast, "searching for: " + fileName); //TODO fix
                                    File file = getBaseContext().getFileStreamPath(fileName);
                                    FileInputStream fis = new FileInputStream(file);

                                    currentWeather = BitmapFactory.decodeStream(fis);

                                }

                            }

                            break;
                        case XmlPullParser.END_TAG:
                            break;
                        case XmlPullParser.TEXT:
                            break;
                    }
                    type = parser.next(); //advances to next xml event
                }

            } catch (Exception e) {

            }

            return null;
        }

        public void onProgressUpdate(Integer... value) {
            bar.setVisibility(View.VISIBLE);
            bar.setProgress(value[0]);
        }

        public void onPostExecute(String s) {
            currentTemp.setText("Current Temp: " + current + "C");
            tempMin.setText("Minimum Temp: " + min + "C");
            tempMax.setText("Maximum Temp: " + max + "C");
            weatherImage.setImageBitmap(currentWeather);

            bar.setVisibility(View.INVISIBLE);
        }


    }
}
