package com.nexkraft.brac.workshop.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexkraft.brac.workshop.R
import com.nexkraft.brac.workshop.databinding.ActivityNotificationBinding
import com.nexkraft.brac.workshop.ui.base.BaseActivity
import com.nexkraft.brac.workshop.viewmodel.MainViewModel
import com.nexkraft.brac.workshop.viewmodel.MiscViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Notification : BaseActivity() {

    private val binding by lazy { ActivityNotificationBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModel()
    private val miscViewModel: MiscViewModel by viewModel()

    var staffPin: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupActionbar()
        setRecyclerView()

        mainViewModel.userModel.observe(this) {
            it?.let { userModel ->
                staffPin = userModel.staffPIN
                binding.appBar.userPin.text = userModel.staffPIN
//                getSupportList()

            }
        }



    }

    private fun setupActionbar() {
        binding.appBar.textViewScreen.text = "Notification"
        binding.appBar.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }


    private fun setRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
      //  binding.recyclerView.adapter = adminSupportAdapter
    }


    override fun isNetworkAvailable(isConnected: Boolean) {
        binding.tvConnectionStatus.visibility = if (isConnected) View.GONE else View.VISIBLE

    }

}