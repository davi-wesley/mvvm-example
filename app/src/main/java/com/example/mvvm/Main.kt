package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

class Main : AppCompatActivity(), View.OnClickListener{


    private lateinit var binding : ActivityMainBinding;
    private lateinit var viewModel : MainViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmButton.setOnClickListener(this)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setObserver()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.confirm_button){
            v.isEnabled = false
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            viewModel.doLogin(email, password)

        }
    }

    private fun setObserver(){
        viewModel.welcome().observe(this) {
            binding.textWelcome.text = it;
        }

        viewModel.login().observe(this){
            if(it) Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_SHORT).show()
            else Toast.makeText(applicationContext, "Falha!", Toast.LENGTH_SHORT).show()
            binding.confirmButton.isEnabled = true
        }
    }
}