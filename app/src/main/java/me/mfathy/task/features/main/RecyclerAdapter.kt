package me.mfathy.task.features.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.mfathy.task.R
import me.mfathy.task.data.store.remote.service.ServiceFactory.makePicassoLoader
import javax.inject.Inject


/**
 * RecyclerView adapter to redraw list item.
 */
class RecyclerAdapter @Inject constructor(
    private val mContext: Context,
    private var items: MutableList<Any>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent, mContext)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = items[position]
        holder.bindView(holder, doctor)
    }


    fun appendItems(newItems: List<Any>) {
        this.items.addAll(newItems)
        notifyItemRangeInserted(itemCount, newItems.size)
    }

    fun resetItems() {
        this.items.clear()
        notifyDataSetChanged()
    }


    class ViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        private var imageViewDoctor: ImageView? = view.findViewById(R.id.imageView_doctor)
        private var textViewDoctorName: TextView? =
            view.findViewById(R.id.textView_doctor_name)
        private var textViewDoctorAddress: TextView? =
            view.findViewById(R.id.textView_doctor_address)

        private val mPicasso: Picasso = makePicassoLoader(context)

        companion object {
            fun create(parent: ViewGroup, context: Context): ViewHolder {
                val itemView = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.view_list_item_doctor, parent, false)
                return ViewHolder(itemView, context)
            }
        }

        fun bindView(holder: ViewHolder, doctor: Any) {
            
        }
    }

}