package com.exemple.movie.view

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.exemple.movie.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        loadLocale()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.changeLang.setOnClickListener { showChangeLang() }
    }


    private fun showChangeLang() {

        val listItems = arrayOf("English", "Uzbek", "Russ")

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Choose Language")
        builder.setSingleChoiceItems(listItems, -1) { dialog, which ->

            if (which == 0) {
                setLocate("def")
                recreate()

            } else if (which == 1) {
                setLocate("uz")
                recreate()

            } else if (which == 2) {
                setLocate("ru")
                recreate()

            }
            dialog.dismiss()

        }
        val dialog = builder.create()
        dialog.show()

    }

    private fun setLocate(s: String) {
        val locale = Locale(s)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", s)
        editor.apply()

    }

    private fun loadLocale() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        setLocate(language.toString())

    }

}
