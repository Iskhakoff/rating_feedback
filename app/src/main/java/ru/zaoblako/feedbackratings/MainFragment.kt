package ru.zaoblako.feedbackratings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import ru.zaoblako.feedbackratings.adapter.Adapter
import ru.zaoblako.feedbackratings.adapter.viewholder.HeaderViewHolder
import ru.zaoblako.feedbackratings.adapter.viewholder.RatingFoodViewHolder
import ru.zaoblako.feedbackratings.adapter.viewholder.RatingViewHolder

class MainFragment : Fragment(), HeaderViewHolder.OnChangeRatingHeaderListener, RatingViewHolder.OnRatingChangeListener, RatingFoodViewHolder.OnChangeFoodRatingListener {

    private lateinit var mRecycler : RecyclerView
    private lateinit var mProgress : ProgressBar
    private lateinit var mMainContainer : NestedScrollView
    private lateinit var mFeedBackText : TextInputEditText
    private lateinit var mSubmit : Button

    private lateinit var adapter : Adapter
    private lateinit var mViewModel : MainViewModel

    companion object {
        @JvmStatic
        fun newInstance() : MainFragment {
            val fragment = MainFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupUI(view)
        setupRecycler()
    }

    private fun setupRecycler() {

    }

    override fun onChangeRatingHeader(position: Int, rating: Float) {
        TODO("Not yet implemented")
    }

    override fun onRatingChange(position: Int, rating: Float) {
        TODO("Not yet implemented")
    }

    override fun onChangeFoodRating(position: Int, rating: Float) {
        TODO("Not yet implemented")
    }

    override fun onSelectedNoFoodOption(position: Int, isNoFood: Boolean) {
        TODO("Not yet implemented")
    }

    private fun setupUI(view : View) {
        mRecycler      = view.findViewById(R.id.rating_recycler)
        mProgress      = view.findViewById(R.id.emulation_progress)
        mMainContainer = view.findViewById(R.id.parent_container)
        mFeedBackText  = view.findViewById(R.id.feedback_text_et)
        mSubmit        = view.findViewById(R.id.submit_results_btn)
    }
}