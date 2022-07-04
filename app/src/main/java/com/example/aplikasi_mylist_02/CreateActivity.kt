package com.example.aplikasi_mylist_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    private lateinit var itemName : EditText
    private lateinit var itemDesc : EditText
    private lateinit var add_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        itemName   = findViewById(R.id.item_name)
        itemDesc   = findViewById(R.id.item_description)
        add_button = findViewById(R.id.add_button)
//      R.id item_name : id edit textnya ada di file layout diliat aja dan disamakan

        val actionBar = supportActionBar
        actionBar!!.title = "Tambah"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        ref = FirebaseDatabase.getInstance().getReference("aplikasi-mylist")
//        nama project di firebasenya

        add_button.setOnClickListener{
//      add_button : id button yang untuk save datanya
            saveData()
//      untuk save datanya
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
    private fun saveData(){
        val nameItem = itemName.text.toString()
        val descItem = itemDesc.text.toString()

//      val disini sesuai dengan yang di transaction.kt(data class)

        val itemID = ref.push().key.toString()
        val transaction = Transaction(itemID,nameItem,descItem)

        ref.child(itemID).setValue(transaction).addOnCompleteListener{
            Toast.makeText(this, "Note berhasil di tambahkan", Toast.LENGTH_SHORT)
            itemName.setText("")
            itemDesc.setText("")

        }
    }

    override fun onSupportNavigateUp():Boolean{
        onBackPressed()
        return true
    }
}



