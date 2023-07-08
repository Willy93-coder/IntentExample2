package com.example.intentexample2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intentexample2.SecondActivity.Companion.CONDITION
import com.example.intentexample2.SecondActivity.Companion.EXTRA_RESULT
import com.example.intentexample2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val responseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val condition = activityResult.data?.getStringExtra(CONDITION).orEmpty()
            val resultRatingBar = activityResult.data?.getFloatExtra(EXTRA_RESULT, 0.0F).toString()
            if (activityResult.resultCode == Activity.RESULT_OK) {
                binding.tvResult.text =
                    "$condition\nGracias por puntuar con $resultRatingBar estrellas."
                binding.tvResult.visibility = View.VISIBLE
            }

            if (activityResult.resultCode == Activity.RESULT_CANCELED) {
                binding.tvResult.text = condition
                binding.tvResult.visibility = View.VISIBLE
            }
        }

    companion object {
        const val TAG_APP = "MY_EXPLICIT_INTENT_2"
        const val EXTRA_NAME = "USER_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Se oculta el TextView que mostrará el resultado
        binding.tvResult.visibility = View.INVISIBLE
        binding.btnSend.setOnClickListener { askConditions() }
    }

    private fun askConditions() {
        Log.d(TAG_APP, "askConditions()")

        // Se vuelve a ocultar el TV que mostrará el resultado
        binding.tvResult.visibility = View.INVISIBLE

        // se crea un objeto de tipo Intent
        val myIntent = Intent(this, SecondActivity::class.java).apply {
            putExtra(EXTRA_NAME, binding.etInformation.text.toString())
        }

        // Se lanza la actividad
        responseLauncher.launch(myIntent)
    }
}