package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather.fragment.MainFragment

const val API_KEY = "c5dd137094604bcbb2b93330231311"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, MainFragment.newInstance())
            .commit()
    }

//    private fun getResult(name: String) {
//        val url = "https://api.weatherapi.com/v1/current.json" +
//          "?key=$API_KEY&q=$name&aqi=no"
//
//        val queue = Volley.newRequestQueue(this)
//
//        val stringRequest = StringRequest(
//            Request.Method.GET,
//            url,
//            { response ->
//                val obj = JSONObject(response)
//                val temp = obj.getJSONObject("current")
//                Log.d("myLog", "Response: ${temp.getString("temp_c")}") },
//            { Log.d("myLog", "volley Error: $it") }
//        )
//
//        queue.add(stringRequest)
//    }
}
