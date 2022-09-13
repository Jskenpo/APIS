package com.plataformas.labs_apis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.plataformas.labs_apis.adapter.Adapter
import com.plataformas.labs_apis.database.Character


class FragmentList : Fragment(R.layout.fragment_list) {

lateinit var recycler: RecyclerView
lateinit var list: MutableList<Character>
lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.listofCharacters)


    }

}