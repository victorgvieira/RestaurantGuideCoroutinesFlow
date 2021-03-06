package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.inflate

class RestaurantsAdapter(
    private val listRestaurants: List<Restaurant>,
    private val onClick : (view: View, restaurant: Restaurant) -> Unit,
    private val onClickLastPage : () -> Unit,
    private val onClickNextPage : () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        if (position == lastPosition()) {
            ROW_PAGINATION
        } else {
            ROW_RESTAURANT
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == ROW_PAGINATION) {
            RestaurantsPaginationViewHolder(
                parent.inflate(R.layout.row_footer_recycle_view_restaurants)
            )
        } else {
            RestaurantViewHolder(
                parent.inflate(R.layout.row_restaurant)
            )
        }

    override fun getItemCount(): Int = listRestaurants.size + INCREMENT_ROW_PAGINATION

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == ROW_PAGINATION) {
            (holder as? RestaurantsPaginationViewHolder)?.bind(
                listRestaurants.size,
                onClickLastPage,
                onClickNextPage
            )
        } else {
            (holder as? RestaurantViewHolder)?.bind(
                listRestaurants[position],
                onClick
            )
        }
    }

    private fun lastPosition(): Int = itemCount - 1

    companion object {
        private const val ROW_RESTAURANT = 0
        private const val ROW_PAGINATION = 1
        private const val INCREMENT_ROW_PAGINATION = 1
    }
}