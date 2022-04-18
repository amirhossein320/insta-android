package ir.amir.isnta.presenter.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import ir.amir.isnta.databinding.FragmentLoginBinding
import ir.amir.isnta.presenter.base.BaseFragment
import ir.amir.isnta.util.setLocalApp

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val TAG = javaClass.canonicalName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLanguagesSpinner()
    }

    private fun setupLanguagesSpinner() {
        binding.spLanguages.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
//                if (position == 0)
//                else
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun setupForgetButton() {
        binding.btnForgetPass.apply {
            text = ""
        }
    }
}