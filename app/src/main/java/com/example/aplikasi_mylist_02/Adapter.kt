package com.example.financeworks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasi_mylist_02.R
import com.example.aplikasi_mylist_02.Transaction

class Adapter(private val transactionList : ArrayList<Transaction>) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {
    //transaction list : id recycleviewnya

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = transactionList[position]

        holder.nameItem.text = currentitem.nameItem
        holder.descItem.text = currentitem.descItem
        holder.priceItem.text = currentitem.priceItem

//        sesuai dengan yang di transaction.kt
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val nameItem: TextView = itemView.findViewById(R.id.name_item)
        val descItem: TextView = itemView.findViewById(R.id.description_item)
        val priceItem: TextView = itemView.findViewById(R.id.price_item)

//        ini untuk menampilkan dalam bentuk recycleview
    }
}