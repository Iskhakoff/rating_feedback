package ru.zaoblako.feedbackratings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.zaoblako.feedbackratings.adapter.Adapter
import ru.zaoblako.feedbackratings.adapter.decoration.RatingItemSpacingDecoration
import ru.zaoblako.feedbackratings.adapter.viewholder.HeaderViewHolder
import ru.zaoblako.feedbackratings.adapter.viewholder.RatingFoodViewHolder
import ru.zaoblako.feedbackratings.adapter.viewholder.RatingViewHolder
import ru.zaoblako.feedbackratings.data.ViewState

class MainFragment : Fragment(),
        HeaderViewHolder.OnChangeRatingHeaderListener,
        RatingViewHolder.OnRatingChangeListener,
        RatingFoodViewHolder.OnChangeFoodRatingListener {

    private lateinit var mRecycler : RecyclerView
    private lateinit var mProgress : ProgressBar
    private lateinit var mMainContainer : NestedScrollView
    private lateinit var mFeedBackText : EditText
    private lateinit var mSubmit : Button

    private lateinit var adapter : Adapter
    private lateinit var mViewModel : MainViewModel

    companion object {
        @JvmStatic
        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
        setupRecycler()

        mViewModel.getCells().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        mViewModel.getState().observe(viewLifecycleOwner, {
            render(it)
        })

        mSubmit.setOnClickListener {
            mViewModel.submitTotalFeedback(text = mFeedBackText.text.toString())
        }
    }

    private fun setupRecycler() {
        adapter = Adapter(layoutInflater, this, this, this)
        mRecycler.layoutManager = LinearLayoutManager(context)
        mRecycler.isNestedScrollingEnabled = false
        mRecycler.addItemDecoration(RatingItemSpacingDecoration(
            resources.getDimension(R.dimen.horizontal_spacing_rating_list_item).toInt(),
            resources.getDimension(R.dimen.vertical_spacing_rating_list_item).toInt()
        ))
        mRecycler.itemAnimator = null
        mRecycler.adapter = adapter
    }

    override fun onRatingChange(position: Int, rating: Float) {
        mViewModel.updateRating(position, rating)
    }

    override fun onChangeFoodRating(position: Int, rating: Float) {
        mViewModel.updateFoodRating(position, rating)
    }

    override fun onSelectedNoFoodOption(position: Int, isNoFood: Boolean) {
        mViewModel.selectedNoFood(position, isNoFood)
    }

    override fun onChangeRatingHeader(rating: Float) {
        mViewModel.updateHeaderRating(rating)
    }

    private fun render(state: ViewState) {
        when (state){
            is ViewState.Loading -> {
                mMainContainer.visibility = View.GONE
                mProgress.visibility = View.VISIBLE
            }
            is ViewState.SuccessSubmit -> {
                mProgress.visibility = View.GONE
                mMainContainer.visibility = View.VISIBLE
                Toast.makeText(context, state.totalFeedback.toString(), Toast.LENGTH_LONG).show()
            }
            is ViewState.Default -> {
                mProgress.visibility = View.GONE
                mMainContainer.visibility = View.VISIBLE
            }
        }
    }

    private fun setupUI(view : View) {
        mRecycler      = view.findViewById(R.id.rating_recycler)
        mProgress      = view.findViewById(R.id.emulation_progress)
        mMainContainer = view.findViewById(R.id.parent_container)
        mFeedBackText  = view.findViewById(R.id.feedback_text_et)
        mSubmit        = view.findViewById(R.id.submit_results_btn)
    }
}