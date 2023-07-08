package com.example.intentexample2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.intentexample2.MainActivity.Companion.EXTRA_NAME
import com.example.intentexample2.MainActivity.Companion.TAG_APP
import com.example.intentexample2.R.string.msgAccept
import com.example.intentexample2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    companion object {
        const val CONDITION = "CONDITION"
        const val EXTRA_RESULT = "EXTRA_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Se recuperan los datos y se asignan al TextView
        val nameReceived = intent.getStringExtra(EXTRA_NAME)

        binding.tvMessageReceived.text = getString(msgAccept, nameReceived)

        val intent = Intent()

        binding.btnAccept.setOnClickListener {
            val intentResult: Intent = Intent().apply {
                putExtra(EXTRA_RESULT, binding.rtStars.rating)
                putExtra(CONDITION, "Condiciones aceptadas")
            }
            setResult(Activity.RESULT_OK, intentResult)
            Log.d(TAG_APP, "Valor devuelto OK")
            finish()
        }

        binding.btnCancel.setOnClickListener {
            intent.putExtra(CONDITION, "Se canceló el contrato")
            setResult(Activity.RESULT_CANCELED, intent)
            Log.d(TAG_APP, "Valor devuelto CANCELED")
            finish()
        }
    }
}