package com.example.listview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val notes:MutableList<User> = mutableListOf()

    private lateinit var toolbarTB:Toolbar
    private lateinit var nameET:EditText
    private lateinit var ageET:EditText
    private lateinit var saveBTN:Button
    private lateinit var peopleLV:ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        nameET= findViewById(R.id.nameET)
        ageET= findViewById(R.id.ageET)
        saveBTN= findViewById(R.id.saveBTN)
        peopleLV= findViewById(R.id.peopleLV)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,notes)
        peopleLV.adapter=adapter

        saveBTN.setOnClickListener(){
            val nameText = nameET.text
            val ageText = ageET.text
            if(nameText.isEmpty() || ageText.isEmpty()) return@setOnClickListener
            notes.add(User(nameText.toString(),ageText.toString().toInt()))
            adapter.notifyDataSetChanged()
            nameText.clear()
            ageText.clear()
        }

        peopleLV.onItemClickListener = AdapterView.OnItemClickListener {
            parent,v,position,id ->
            val note = adapter.getItem(position)
            adapter.remove(note)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }


}