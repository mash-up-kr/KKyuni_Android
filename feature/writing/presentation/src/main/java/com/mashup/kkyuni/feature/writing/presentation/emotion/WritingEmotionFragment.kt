package com.mashup.kkyuni.feature.writing.presentation.emotion

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.core.constant.Constant
import com.mashup.kkyuni.feature.writing.presentation.R
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingEmotionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WritingEmotionFragment :
    BindingFragment<FragmentWritingEmotionBinding>(R.layout.fragment_writing_emotion) {
    private val writingViewModel by navGraphViewModels<WritingViewModel>(R.id.writing_graph)
    private val emotionViewModel by viewModels<WritingEmotionViewModel>()

    private val backPressCallBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        addBackPressedCallBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addBackPressedCallBack()
        initView()
        collectFlows()
    }

    private fun addBackPressedCallBack() {
        activity?.onBackPressedDispatcher?.addCallback(this, backPressCallBack)
    }

    private fun initView() {
        binding.run {
            emotionViewModel = this@WritingEmotionFragment.emotionViewModel

            setEmotion(writingViewModel.getCurrentWriting().emotion ?: Constant.Emotion.UNKNOWN)
        }
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                emotionViewModel.run {
                    launch {
                        nextEvent.collect {
                            updateEmotion(it)

                            navigateToWritingMusic()
                        }
                    }

                    launch {
                        backEvent.collect {
                            onBackPressed()
                        }
                    }
                }
            }
        }
    }

    // 감정 선택 완료 후 업로드할 감정 update
    private fun updateEmotion(emotion: Constant.Emotion) {
        writingViewModel.updateEmotion(emotion)
    }

    // 선택했었던 감정이 있다면 세팅
    private fun setEmotion(emotion: Constant.Emotion) {
        emotionViewModel.setEmotion(emotion)
    }

    private fun navigateToWritingMusic() {
        findNavController().navigate(R.id.writingMusicFragment)
    }

    private fun onBackPressed() {
        findNavController().popBackStack(R.id.kobaCalendarFragment, false)
    }

    override fun onDetach() {
        super.onDetach()
        backPressCallBack.remove()
    }
}