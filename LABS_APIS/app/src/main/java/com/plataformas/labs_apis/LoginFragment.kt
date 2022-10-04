package com.plataformas.labs_apis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var Email : TextInputLayout
    private lateinit var Password : TextInputLayout
    private lateinit var buttonLog : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Email = view.findViewById(R.id.textInputLayoutEmail)
        Password = view.findViewById(R.id.textInputLayoutPassword)
        buttonLog = view.findViewById(R.id.buttonLogin)
    }

    


}