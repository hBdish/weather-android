package com.example.weather.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.MainViewModal
import com.example.weather.adapters.WeatherAdapter
import com.example.weather.adapters.WeatherModel
import com.example.weather.databinding.FragmentHoursBinding
import com.example.weather.databinding.FragmentMainBinding
import org.json.JSONArray
import org.json.JSONObject

class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModal by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            adapter.submitList(getHoursList(it))
        }
    }

    private fun initRcView() = with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
        val list = listOf(
            WeatherModel(
                "",
                "12:00",
                "sunny",
                "",
                "25 C",
                "",
                "",
                "",
            ),
            WeatherModel(
                "",
                "13:00",
                "sunny",
                "",
                "27 C",
                "",
                "",
                "",
            ),
            WeatherModel(
                "",
                "14:00",
                "sunny",
                "",
                "35 C",
                "",
                "",
                "",
            )
        )
        adapter.submitList(list)
    }

    private fun getHoursList(item: WeatherModel): List<WeatherModel> {
        val hoursArray = JSONArray(item.hoursForecast)
        val list = ArrayList<WeatherModel>()
        for (i in 0 until hoursArray.length()) {
            val item = WeatherModel(
                item.city,
                (hoursArray[i] as JSONObject).getString("time"),
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("text"),
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("icon"),
                (hoursArray[i] as JSONObject).getString("temp_c"),
                "",
                "",
                ""
            )
            list.add(item)
        }
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}
