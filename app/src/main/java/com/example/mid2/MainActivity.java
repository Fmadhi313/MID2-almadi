package com.example.mid2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // we"ll make HTTP request to this URL to retrieve weather conditions
//    String city = "riyadh";
//    String weatherWebserviceURL =
//            "https://api.openweathermap.org/data/2.5/weather?q="+
//                    city
//                    +",&appid="+
//                    "c65705e44ccf09d6ae90acb660fdb071"+
//                    "&units=metric";
    ImageView weatherBackground;
    // Textview to show temperature and description
    TextView temperature, description, humidity, sunriset, sundownt, pressure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //link graphical items to variables
        Calendar c = Calendar.getInstance();
        DateFormat fmtDate = DateFormat.getDateInstance();
        temperature = (TextView) findViewById(R.id.temperature);
        description = (TextView) findViewById(R.id.description);
        humidity = (TextView) findViewById(R.id.humidity);
//        sunriset = (TextView) findViewById(R.id.sunrise);
//        sundownt = (TextView) findViewById(R.id.sundwon);
//        pressure = (TextView) findViewById(R.id.pressure);
//        weatherBackground = (ImageView) findViewById(R.id.weatherbackground);
        Button databttn = (Button)findViewById(R.id.databutton);
        Button secondctv = (Button)findViewById(R.id.secondndactvbutton);


        Spinner cities = (Spinner) findViewById(R.id.spinner);
        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String city = cities.getSelectedItem().toString();
                String weatherWebserviceURL =
                        "https://api.openweathermap.org/data/2.5/weather?q="+
                                city
                                +",&appid="+
                                "c65705e44ccf09d6ae90acb660fdb071"+
                                "&units=metric";
                weather(weatherWebserviceURL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                description.setText("Your picked data is "+
                        fmtDate.format(c.getTime()));
            }
        };

        databttn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, d,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        secondctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));

            }
        });



    }
    public void weather(String url){
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Faisal", "respond ok");
                Log.d("Faisal", response.toString());
                try {
                    JSONObject jsonMain = response.getJSONObject("main");
                    JSONObject jsonMain2 = response.getJSONObject("sys");
                    Double temp = jsonMain.getDouble("temp");
                    temperature.setText(String.valueOf(temp));
                    Log.d("Faisal-temp",String.valueOf(temp));


                    Double hum = jsonMain.getDouble("humidity");
                    humidity.setText("Humidity "+String.valueOf(hum));
                    Log.d("Faisal-humidity",String.valueOf(hum));

//                    String townResponse = response.getString("name");
//                    description.setText(townResponse);
//                    Log.d("Faisal-city",townResponse);

//                    String pressureR = jsonMain.getString("pressure");
////                    pressure.setText("pressure: "+pressureR); I changes this for the value in array test
//                    Log.d("Faisal-cast",String.valueOf(pressureR));

//                    long unixTimestamp = jsonMain2.getLong("sunrise");
//                    long javaTimestamp = unixTimestamp * 1000L;
//                    Date date = new Date(javaTimestamp);
//                    String sunrise = new SimpleDateFormat("hh:mm").format(date);
//                    sunriset.setText("Sunrise "+String.valueOf(sunrise)+" am");
//
//                    long unixTimestamp2 = jsonMain2.getLong("sunset");
//                    long javaTimestamp2 = unixTimestamp2 * 1000L;
//                    Date date2 = new Date(javaTimestamp2);
//                    String sundown = new SimpleDateFormat("hh:mm").format(date2);
//                    sundownt.setText("Sunset "+String.valueOf(sundown)+" pm");

                    /* sub categories as JSON arrays */
//                    JSONArray jsonArray = response.getJSONArray("weather");
//                    for (int i=0; i<jsonArray.length();i++){
//                        Log.d("Faisal-array",jsonArray.getString(i));
//                        JSONObject oneObject = jsonArray.getJSONObject(i);
//                        String wheater =
//                                oneObject.getString("main");
//                        String wheater2 =
//                                oneObject.getString("description");
//                        pressure.setText("pressure: "+wheater2);
//                        Log.d("Faisal-w",wheater);
//                        if (wheater.equals("Clear")){
//                            Glide.with(MainActivity.this).load("https://i.picsum.photos/id/866/536/354.jpg?hmac=tGofDTV7tl2rprappPzKFiZ9vDh5MKj39oa2D--gqhA").into(weatherBackground);
//                        }
//                        else if (wheater.equals("Clouds")){
//                            Glide.with(MainActivity.this).load("https://media.istockphoto.com/photos/sky-background-picture-id598222542?s=612x612").into(weatherBackground);
//                        }
//                        else if (wheater.equals("Rain")){
//                            Glide.with(MainActivity.this).load("https://massago.ca/wp-content/uploads/2018/06/blog-post_rain.jpg").into(weatherBackground);
//                        }
//                        else {
//                            Glide.with(MainActivity.this).load("https://media.istockphoto.com/vectors/simple-weather-vector-icon-in-flat-style-vector-id1140783101?s=612x612").into(weatherBackground);
//                        }
//
//                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
                Log.d("Faisal", "respond error");

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this); queue.add(jsonObj);

    }}