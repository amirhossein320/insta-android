package ir.amir.isnta.presenter.login

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.amir.isnta.databinding.FragmentLoginBinding
import ir.amir.isnta.presenter.base.BaseFragment
import ir.amir.isnta.util.restartApp

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val TAG = javaClass.canonicalName
    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setEvent(LoginEvent.GetLocal)
        handleState()
        setupLanguagesSpinner()
    }

    private fun handleState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                when (state) {
                    is LoginState.GetLocale ->
                        binding.spLanguages.setSelection(if(state.languageId == "en") 0 else 1)
                    is LoginState.ChangeLocal ->
                        requireContext().restartApp(requireActivity())
                }
            }
        }
    }

    private fun setupLanguagesSpinner() {
        binding.spLanguages.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (viewModel.firstTime) {
                    viewModel.firstTime = false
                } else {
                    viewModel.setEvent(
                        LoginEvent.ChangeLocale(if (position == 0) "en" else "fa")
                    )
                }
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