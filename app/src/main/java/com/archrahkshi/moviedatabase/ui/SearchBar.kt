package com.archrahkshi.moviedatabase.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.databinding.SearchToolbarBinding
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

private const val MIN_LENGTH = 3

class SearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private lateinit var binding: SearchToolbarBinding
    private var hint: String = ""
    private var isCancelVisible: Boolean = true
    private val searchSubject = PublishSubject.create<String>()

    init {
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
                hint = getString(R.styleable.SearchBar_hint).orEmpty()
                isCancelVisible = getBoolean(R.styleable.SearchBar_cancel_visible, true)
                recycle()
            }
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

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = SearchToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        binding.searchEditText.hint = hint
        binding.deleteTextButton.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    fun observeSearchContent() = searchSubject
        .debounce(500, TimeUnit.MILLISECONDS)
        .map { it.trim() }
        .filter { it.length > MIN_LENGTH }
        .distinctUntilChanged()

    fun setText(text: String?) {
        binding.searchEditText.setText(text)
    }

    fun clear() {
        binding.searchEditText.setText("")
    }
}
