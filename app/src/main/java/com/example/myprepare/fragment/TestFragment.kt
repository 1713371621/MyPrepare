package com.example.myprepare.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myprepare.R

class TestFragment : Fragment() {
  
  private var param1: String? = null
  private var param2: String? = null
  
  override fun onAttach(context: Context) {
    super.onAttach(context)
    Log.d(TAG, "onAttach: ")
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate: ")
    arguments?.let {
      param1 = it.getString(ARG_PARAM1)
      param2 = it.getString(ARG_PARAM2)
    }
  }
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    Log.d(TAG, "onCreateView: ")
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_test, container, false)
  }
  
  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    Log.d(TAG, "onActivityCreated: ")
  }
  
  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart: ")
  }
  
  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume: ")
  }
  
  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause: ")
  }
  
  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop: ")
  }
  
  override fun onDestroyView() {
    Log.d(TAG, "onDestroyView: ")
    super.onDestroyView()
  }
  
  override fun onDestroy() {
    Log.d(TAG, "onDestroy: ")
    super.onDestroy()
  }
  
  override fun onDetach() {
    Log.d(TAG, "onDetach: ")
    super.onDetach()
  }
  
  companion object {
  
    private const val TAG = "TestFragment"
    private const val ARG_PARAM1 = "param1"
    private const val ARG_PARAM2 = "param2"
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
      TestFragment().apply {
        arguments = Bundle().apply {
          putString(ARG_PARAM1, param1)
          putString(ARG_PARAM2, param2)
        }
      }
  }
}