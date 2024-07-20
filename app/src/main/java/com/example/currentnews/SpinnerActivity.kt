package com.example.currentnews

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter

import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.currentnews.databinding.ActivityMainBinding
import com.example.currentnews.databinding.ActivitySpinnerBinding

class SpinnerActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpinnerBinding
    lateinit var autoCompleteTextView: AutoCompleteTextView
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var arrayAdapter2: ArrayAdapter<String>
    lateinit var listitem : List<String>
    lateinit var listitem2 : List<String>
    var selectedCountry : String? = null
    var selectedCategory : String? = null

//    val countryMap = mapOf(
//        "India" to "in",
//        "USA" to "us",
//        "Japan" to "jp",
//        "Canada" to "ca"
//    )
//
//    val categoryMap = mapOf(
//        "Politics" to "politics",
//        "Business" to "business"
//    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

         listitem = listOf("India","USA","UK","Canada","Australia")
         listitem2 = listOf("Politics","Business","General","Science","Entertainment","Sport")



        sendDAta()

        backpress()

        }

    private fun backpress() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                AlertDialog.Builder(this@SpinnerActivity)
                    .setTitle("Exit Alert")
                    .setMessage("Do you want to exit")
                    .setPositiveButton("Yes"){_,_->
                        finishAffinity()
                    }
                    .setNegativeButton("No"){_,_->}
                    .setCancelable(false)
                    .create()
                    .show()

            }

        })
    }

    private fun sendDAta() {

//        val countryList = countryMap.keys.toList()
//        val categoryList = categoryMap.keys.toList()
        arrayAdapter = ArrayAdapter(this,R.layout.list_item,listitem)
        binding.autoCompleteTxt.setAdapter(arrayAdapter)
        arrayAdapter2 = ArrayAdapter(this,R.layout.list_item,listitem2)
        binding.autoCompleteTxt2.setAdapter(arrayAdapter2)

        binding.autoCompleteTxt.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            selectedCountry = parent.getItemAtPosition(position).toString()
//            Toast.makeText(this, "Selected: $selectedCountry", Toast.LENGTH_SHORT).show()

            passData()
        }
        binding.autoCompleteTxt2.onItemClickListener = AdapterView.OnItemClickListener { parent2, view, position, id ->
            selectedCategory = parent2.getItemAtPosition(position).toString()


            passData()
        }
    }

    private fun passData() {
        if (selectedCountry != null && selectedCategory != null){
            val intent = Intent(this,MainActivity::class.java)
//        intent.putExtra("category",selectedCategory)
//        intent.putExtra("country",selectedCountry)
//        startActivity(intent)


             when(selectedCountry){
                "India" -> intent.putExtra("country","in")
                "USA" -> intent.putExtra("country","us")
                "Canada" -> intent.putExtra("country","ca")
                "UK" -> intent.putExtra("country","gb")
                "Australia" -> intent.putExtra("country","au")
                else -> intent.putExtra("country","in")


            }
            when(selectedCategory){
                "Business" -> intent.putExtra("category","business")
                "Politics" -> intent.putExtra("category","politics")
                "Science" -> intent.putExtra("category","science")
                "General" -> intent.putExtra("category","general")
                "Entertainment" -> intent.putExtra("category","entertainment")
                "Sport" -> intent.putExtra("category","sport")
                else -> intent.putExtra("category","business")
            }
            startActivity(intent)

        }

    }

}
