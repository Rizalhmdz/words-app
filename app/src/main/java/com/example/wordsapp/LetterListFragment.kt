package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {

    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    private var isLinearLayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        chooseLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun chooseLayout(){
        if (isLinearLayout){
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
        recyclerView.adapter = LetterAdapter()
        recyclerView.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        val button = menu.findItem(R.id.switch_layout)
        setIcon(button)
    }

    private fun setIcon(menuItem: MenuItem?) {
        if(isLinearLayout){
            menuItem?.icon = getDrawable(requireContext(), R.drawable.ic_linear_layout)
        } else {
            menuItem?.icon = getDrawable(requireContext(), R.drawable.ic_grid_layout)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.switch_layout -> {
                isLinearLayout = !isLinearLayout
                chooseLayout()
                setIcon(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}