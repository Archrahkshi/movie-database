package com.archrahkshi.moviedatabase.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.archrahkshi.moviedatabase.databinding.SearchToolbarBinding
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

private const val MIN_LENGTH = 3

class SearchBar(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private lateinit var binding: SearchToolbarBinding
    private val searchSubject = PublishSubject.create<String>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = SearchToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        binding.deleteTextButton.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        binding.searchEditText.afterTextChanged {
            searchSubject.onNext(it.toString())
            if (!it.isNullOrEmpty() && !binding.deleteTextButton.isVisible) {
                binding.deleteTextButton.isVisible = true
            }
            if (it.isNullOrEmpty() && binding.deleteTextButton.isVisible) {
                binding.deleteTextButton.isVisible = false
            }
        }
    }

    fun observeSearchContent() = searchSubject
        .debounce(500, TimeUnit.MILLISECONDS)
        .map { it.trim() }
        .filter { it.length > MIN_LENGTH || it.isEmpty() }
        .distinctUntilChanged()

    fun clear() {
        binding.searchEditText.setText("")
    }
}
