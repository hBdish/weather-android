package com.example.weather.fragment

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weather.adapters.VpAdapter
import com.example.weather.adapters.WeatherModel
import com.example.weather.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject
import java.lang.Error

const val API_KEY = "c5dd137094604bcbb2b93330231311"

class MainFragment : Fragment() {

  private val fList = listOf(
    HoursFragment.newInstance(),
    DaysFragment.newInstance(),
  )
  private val tList = listOf(
    "Hours",
    "Days",
  )

  private lateinit var binding: FragmentMainBinding
  private lateinit var pLauncher: ActivityResultLauncher<String>

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentMainBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    checkPermission()
    init()
    requestWeatherData("Voronezh")
  }

  private fun init() = with(binding) {
    val adapter = VpAdapter(activity as FragmentActivity, fList)
    vp.adapter = adapter
    TabLayoutMediator(tabLayout, vp) { tab, position ->
      tab.text = tList[position]
    }.attach()
  }

  private fun permissionListener() {
    pLauncher = registerForActivityResult(
      ActivityResultContracts
        .RequestPermission()
    ) {
      Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
    }
  }

  private fun checkPermission() {
    if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
      permissionListener()
      pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
  }

  private fun requestWeatherData(city: String) {
    val url = "https://api.weatherapi.com/v1/forecast.json" +
      "?key=$API_KEY" +
      "&q=$city" +
      "&days=2" +
      "&aqi=no" +
      "&alerts=no"

    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
      Request.Method.GET,
      url,
      { response ->
        parseWeatherData(response)
      },
      { Log.d("myLog", "volley Error: $it") }
    )

    queue.add(stringRequest)
  }

  private fun parseWeatherData(result: String) {
    val mainObject = JSONObject(result)

    val item = WeatherModel(
      mainObject.getJSONObject("location").getString("name"),
      mainObject.getJSONObject("current").getString("last_updated"),
      mainObject.getJSONObject("current").getJSONObject("condition").getString("text"),
      mainObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
      mainObject.getJSONObject("current").getString("temp_c"),
      "",
      "",
      ""
    )

  }

  companion object {
    @JvmStatic
    fun newInstance() = MainFragment()
  }
}
