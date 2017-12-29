package com.neosoft.kotlinapp.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neosoft.kotlinapp.R
import com.neosoft.kotlinapp.app.MainActivity


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the

 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null
    var a: String? = "abc"
    lateinit var btnNext : AppCompatButton
    val TAG:String="FirstFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
        a = null
        var b = a?.length
        Log.e("TAG", "b:" + b)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view : View= inflater!!.inflate(R.layout.fragment_first, container, false)
        (activity as MainActivity).askPermission()
        btnNext=view.findViewById(R.id.btnNext) as AppCompatButton
        btnNext.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                Log.e(TAG,"button Click")
            }
        })
        return view
    }



    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        fun newInstance(param1: String, param2: String): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
