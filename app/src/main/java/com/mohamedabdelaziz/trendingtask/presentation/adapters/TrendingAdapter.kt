package com.mohamedabdelaziz.trendingtask.presentation.adapters

import android.content.Context
import android.graphics.Color

import androidx.recyclerview.widget.RecyclerView
import com.mohamedabdelaziz.trendingtask.presentation.adapters.TrendingAdapter.TrendingHolder
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.mohamedabdelaziz.trendingtask.R
import com.bumptech.glide.Glide
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import com.mohamedabdelaziz.trendingtask.databinding.TrendingItemBinding
import java.util.ArrayList

class TrendingAdapter(private val context: Context) : RecyclerView.Adapter<TrendingHolder>() {
    private var trendingList: MutableList<TrendingResponse> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingHolder {
        val trendingItemBinding: TrendingItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.trending_item, parent, false)
        return TrendingHolder(trendingItemBinding)
    }

    override fun onBindViewHolder(holder: TrendingHolder, position: Int) {
        val trendingResponse = trendingList[position]
        //parent item
        holder.trendingItemBinding.nameTv.text = trendingResponse.name
        holder.trendingItemBinding.authorTv.text = trendingResponse.author
        Glide.with(context).load(trendingResponse.avatar)
                .circleCrop()
                .into(holder.trendingItemBinding.imageView)
        //expended item
        holder.trendingItemBinding.descriptionTv.text = trendingResponse.description
        holder.trendingItemBinding.languageTv.text = trendingResponse.language
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(Color.parseColor(trendingResponse.languageColor))
        gradientDrawable.setSize(25, 25)
        holder.trendingItemBinding.languageColorImageView.setImageDrawable(gradientDrawable)
        holder.trendingItemBinding.starTv.text = trendingResponse.stars.toString()
        holder.trendingItemBinding.forkTv.text = trendingResponse.forks.toString()
        holder.itemView.setOnClickListener {
            if (holder.trendingItemBinding.subDetailsConstraintLayout.visibility == View.GONE) {
                holder.trendingItemBinding.subDetailsConstraintLayout.visibility = View.VISIBLE
                holder.trendingItemBinding.trendItemCardView.setCardBackgroundColor(ContextCompat.getColor(context,
                        R.color.selected_color))
            } else if (holder.trendingItemBinding.subDetailsConstraintLayout.visibility == View.VISIBLE) {
                holder.trendingItemBinding.subDetailsConstraintLayout.visibility = View.GONE
                holder.trendingItemBinding.trendItemCardView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.white))
            }
        }
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    fun setList(trendingList: List<TrendingResponse>) {
        this.trendingList = trendingList as MutableList<TrendingResponse>
        notifyDataSetChanged()
    }

    class TrendingHolder(var trendingItemBinding: TrendingItemBinding) : RecyclerView.ViewHolder(trendingItemBinding.root)


}