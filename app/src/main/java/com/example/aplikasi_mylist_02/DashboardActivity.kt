package com.example.aplikasi_mylist_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financeworks.Adapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DashboardActivity : AppCompatActivity() {

        private lateinit var dbref : DatabaseReference
        private lateinit var userRecyclerView: RecyclerView
        private lateinit var userArrayList: ArrayList<Transaction>
        private lateinit var auth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_dashboard)

//            //update
//            val updateBtn: ImageButton = findViewById(R.id.update_btn)
//            updateBtn.setOnClickListener {
//                val intent = Intent(this, UpdateActivity::class.java)
//                startActivity(intent)
////       Ini untuk intent ke activity  lain ketika mau di ekseuki update
//            }
//
//            //delete
//            val deleteBtn: ImageButton = findViewById(R.id.delete_btn)
//            deleteBtn.setOnClickListener {
//                val intent = Intent(this, DeleteActivity::class.java)
//                startActivity(intent)
//            }

            //add
            val addBtn: ImageView = findViewById(R.id.vitur_addlist)
            addBtn.setOnClickListener {
                val intent = Intent(this, CreateActivity::class.java)
                startActivity(intent)
            }

//



            //RecylerView Read from database (Firebase)
            userRecyclerView = findViewById(R.id.transaction_list)
            userRecyclerView.layoutManager = LinearLayoutManager(this)
            userRecyclerView.setHasFixedSize(true)

            userArrayList = arrayListOf<Transaction>()
            getTransactionData()




//        //add float button
//        val addflt_btn = findViewById<FloatingActionButton>(R.id.create_btn)
//        addflt_btn.setOnClickListener {
//            val intent = Intent(this, CreateActivity::class.java)
//            startActivity(intent)
//        }
        }

        private fun getTransactionData() {
            dbref = FirebaseDatabase.getInstance().getReference("Transaction")

//  ini untuk mengambil data

            dbref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()) {
                        for (transactionSnapshot in snapshot.children) {

                            val transaction = transactionSnapshot.getValue(Transaction::class.java)
                            userArrayList.add(transaction!!)
                        }

                        userRecyclerView.adapter = Adapter(userArrayList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

