package ir.amir.isnta.presenter.login

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.amir.isnta.R
import ir.amir.isnta.databinding.FragmentLoginBinding
import ir.amir.isnta.presenter.base.BaseFragment
import ir.amir.isnta.util.restartApp
import kotlinx.coroutines.flow.consumeAsFlow

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val TAG = javaClass.canonicalName
    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.firstTime) viewModel.setEvent(LoginEvent.GetLocal)

        handleState()
        setupLanguagesSpinner()
        setupForgotPasswordButton()
        handleEffect()
        onLoginClicked()
    }

    private fun onLoginClicked() {
        binding.btnLogin.setOnClickListener {
            viewModel.setEvent(LoginEvent.Login("",""))
        }
    }

    private fun handleState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                when (state) {
                    is LoginState.IDLE -> {}
                    is LoginState.GetLocale ->
                        binding.spLanguages.setSelection(if (state.languageId == "en") 0 else 1)
                }
            }
        }
    }

    private fun handleEffect() {
        lifecycleScope.launchWhenCreated {
            viewModel.effect.consumeAsFlow().collect { effect ->
                when (effect) {
                    is LoginEffect.ChangeLocal ->
                        requireContext().restartApp(requireActivity())
                    is LoginEffect.ForgePassword -> {
                        viewModel.firstTime = true
                        navigate(R.id.forgetPasswordFragment)
                    }
                    is LoginEffect.Signup -> {
                        viewModel.firstTime = true
                        navigate(R.id.signupFragment)
                    }
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

    private fun setupForgotPasswordButton() {
        val link = getString(R.string.forget_password)
        val linkClick = object : ClickableSpan() {
            override fun onClick(widget: View) {
                viewModel.setEvent(LoginEvent.ForgetPassword)
            }
        }
        val spannable = SpannableString(link).apply {
            setSpan(linkClick, (
                    if (link.contains("?")) link.indexOfFirst { it == '?' }
                    else link.indexOfFirst { it == '؟' }
                    ) + 2,
                link.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.btnForgetPass.apply {
            text = spannable
            movementMethod = LinkMovementMethod.getInstance()
        }
    }
}