package com.decagonhq.clads.ui.client

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.decagonhq.clads.data.domain.client.Client
import com.decagonhq.clads.data.domain.client.Measurement
import com.decagonhq.clads.databinding.ClientDetailsFragmentBinding
import com.decagonhq.clads.ui.BaseFragment
import com.decagonhq.clads.ui.client.adapter.AddMeasurementAdapter
import com.decagonhq.clads.ui.client.adapter.ClientListRvAdapter
import com.decagonhq.clads.ui.client.adapter.RecyclerClickListener
import com.decagonhq.clads.ui.media.MediaFragmentPhotoNameArgs
import com.decagonhq.clads.util.hideView
import com.decagonhq.clads.util.showView
import com.decagonhq.clads.viewmodels.ClientViewModel
import com.decagonhq.clads.viewmodels.ClientsRegisterViewModel
import kotlinx.coroutines.flow.forEach
import java.sql.ClientInfoStatus
import java.util.*

class ClientDetailsFragment : BaseFragment(), RecyclerClickListener {
    private var _binding: ClientDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: ClientDetailsFragmentArgs by navArgs()
    private lateinit var clientListRvAdapter: ClientListRvAdapter
    private val clientViewModel: ClientViewModel by viewModels()
    private val clientsRegisterViewModel: ClientsRegisterViewModel by activityViewModels()
    private lateinit var clientMeasurementAdapter: AddMeasurementAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ClientDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            clientDetailsFragmentClientNameTextView.text = args.clientModel?.fullName
            clientDetailsFragmentNumberTextView.text = args.clientModel?.phoneNumber
            clientDetailsFragmentEmailTextView.text = args.clientModel?.email
            clientDetailsFragmentGenderItemTextView.text = args.clientModel?.gender
            val address = args.clientModel?.deliveryAddresses?.firstOrNull()
            address?.let {
                clientDetailsFragmentDeliveryAddressItemTextView.text =
                    "${it.street?.capitalize()} ~ ${it.city?.capitalize()} ~ ${it.state}"
            }
            val clientInitials = args.clientModel?.fullName?.split(" ")?.get(0)?.substring(0, 1)
                ?.capitalize(Locale.ROOT) +
                    args.clientModel?.fullName?.split(" ")?.get(1)?.substring(0, 1)
                        ?.capitalize(Locale.ROOT)

            clientsDetailsInitialsTextView.text = clientInitials
            val recyclerView = args.clientModel?.measurements?.toMutableList()

            measurementsFragmentRecyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            /*Toggle Empty Message View*/
            if (recyclerView.isNullOrEmpty()) {
                measurementsFragmentTestingTextView.showView()
            } else {
                measurementsFragmentTestingTextView.hideView()
                clientMeasurementAdapter =
                    AddMeasurementAdapter(
                        recyclerView,
                        this@ClientDetailsFragment,
                        this@ClientDetailsFragment
                    )
                measurementsFragmentRecyclerView.adapter = clientMeasurementAdapter


            }
        }


    }

    private fun displayRecyclerviewOrNoClientText(clients: List<Client>) {
        if (clients.isEmpty()) {
            binding.measurementsFragmentTestingTextView.isVisible = true
            binding.measurementsFragmentRecyclerView.isVisible = false
        } else {
            clientListRvAdapter.submitList(clients)
            binding.measurementsFragmentRecyclerView.adapter?.notifyDataSetChanged()
            binding.measurementsFragmentTestingTextView.isVisible = false
            binding.measurementsFragmentRecyclerView.isVisible = true
        }
    }

    override fun onItemClickToEdit(position: Int, currentList: MutableList<Measurement>) {
//        showToast("edit")
    }

    override fun onItemClickToDelete(position: Int, currentList: MutableList<Measurement>) {
//        showToast("delete")
    }

}



