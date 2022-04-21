package ir.amir.isnta.presenter.login.forgetPassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.amir.isnta.databinding.FragmentForgetPasswordBinding
import ir.amir.isnta.presenter.base.BaseFragment
import ir.amir.isnta.util.restartApp

@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>(FragmentForgetPasswordBinding::inflate) {

    private val TAG = javaClass.canonicalName
    private val viewModel by viewModels<ForgetPasswordViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleState()
    }

    private fun handleState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                when (state) {
                    is ForgetPasswordState.GetLocale ->{}
                    is ForgetPasswordState.ChangeLocal ->
                        requireContext().restartApp(requireActivity())
                    is ForgetPasswordState.ForgePassword -> {
                    }
                }
            }
        }
    }

}