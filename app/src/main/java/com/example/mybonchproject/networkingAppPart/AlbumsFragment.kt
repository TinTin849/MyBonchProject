package com.example.mybonchproject.networkingAppPart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybonchproject.R
import com.example.mybonchproject.databinding.FragmentAlbumsBinding
import com.example.mybonchproject.helpersForWork.AlbumsRvAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*

class AlbumsFragment : Fragment() {
    private var fragmentAlbumsBinding: FragmentAlbumsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAlbumsBinding.bind(view)
        fragmentAlbumsBinding = binding

        GlobalScope.launch(Dispatchers.IO) {
            val response = newApi().getAlbum().awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()!!

                withContext(Dispatchers.Main) {
                    //Настройка RecyclerView
                    val adapter = AlbumsRvAdapter()
                    adapter.setData(data)
                    binding.albumsRV.layoutManager = LinearLayoutManager(activity)
                    binding.albumsRV.adapter = adapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAlbumsBinding = null
    }
}