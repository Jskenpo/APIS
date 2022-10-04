package com.plataformas.labs_apis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var Email : TextInputLayout
    private lateinit var Password : TextInputLayout
    private lateinit var buttonLog : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Email = view.findViewById(R.id.textInputLayoutEmail)
        Password = view.findViewById(R.id.textInputLayoutPassword)
        buttonLog = view.findViewById(R.id.buttonLogin)

        verificar()
        setListeners()
    }

    private fun verificar(){
        CoroutineScope(Dispatchers.IO).launch{
            val actual = requireContext().dataStore.getPreferencesValue(KEY_EMAIL)
            if (actual != null){
                navegar()
            }

        }
    }

    private fun setListeners(){
        buttonLog.setOnClickListener {
            login(
                email = Email.editText!!.text.toString(),
                password = Password.editText!!.text.toString()
            )
        }
    }

    private fun login(email:String, password:String){
        if ((email == getString(R.string.correo)) && (email == password )){
            guardar(email)
        }else{
            Toast.makeText(requireContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
        }
    }

    private fun navegar(){
        CoroutineScope(Dispatchers.Main).launch{
            requireView().findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToFragmentList())
        }
    }

    private fun guardar(email:String){
        CoroutineScope(Dispatchers.IO).launch {
            requireContext().dataStore.savePreferencesValue(KEY_EMAIL, email)
            navegar()
        }
    }



}