package com.example.fabre_thomas_rattrapage.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.fabre_thomas_rattrapage.R
import com.example.fabre_thomas_rattrapage.data.model.GlobalCases

class MainAdapter(private val countries: ArrayList<GlobalCases>, private val clickListener: (GlobalCases) -> Unit) : RecyclerView.Adapter<HolderCountries>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderCountries {
        val inflate = LayoutInflater.from(parent.context)
        return HolderCountries(inflate, parent)

    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: HolderCountries, position: Int) {
        val item: GlobalCases = countries[position]
        holder.bind(item, clickListener)
    }

    fun addCountries(countries: List<GlobalCases>) {
        this.countries.apply {
            clear()
            addAll(countries)
        }

    }
}

class HolderCountries (inflater: LayoutInflater, parent : ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_layout , parent , false)) {

    private var country : TextView?= null
    private var cases : TextView?= null
    private var todayCases :TextView?=null
    private var active :TextView?=null
    private var deaths :TextView?=null
    private var todayDeaths :TextView?=null
    private var recovered :TextView?=null
    private var critical :TextView?=null
    private var container : ConstraintLayout?= null

    //initialisation

    init{
        country = itemView.findViewById(R.id.country_name)
        cases = itemView.findViewById(R.id.cases)
        todayCases = itemView.findViewById(R.id.todays_cases)
        active = itemView.findViewById(R.id.active_cases)
        deaths = itemView.findViewById(R.id.deaths)
        todayDeaths = itemView.findViewById(R.id.todays_deaths)
        recovered = itemView.findViewById(R.id.recovered)
        critical = itemView.findViewById(R.id.critical)

        container = itemView.findViewById(R.id.container)
    }

    fun bind(item : GlobalCases, clickListener: (GlobalCases) -> Unit){
        country?.text = item.country
        cases?.text = item.cases.toString()
        todayCases?.text = item.cases.toString()
        active?.text = item.active.toString()
        deaths?.text = item.deaths.toString()
        todayDeaths?.text = item.todayDeaths.toString()
        recovered?.text = item.recovered.toString()
        critical?.text = item.critical.toString()

        container?.setOnClickListener{clickListener(item)}

    }


}