package developer.abdusamid.movieappdatabase.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdusamid.movieappdatabase.DB.User
import developer.abdusamid.movieappdatabase.databinding.ItemRvBinding

class HomeAdapter(
    private var list: ArrayList<User>,
    private val myOnClickListener: MyOnClickListener
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: User, position: Int) {
            binding.apply {
                itemTextName.text = user.name
                itemTextAuthor.text = user.author
                itemDate.text = user.data
                item.setOnClickListener {
                    myOnClickListener.onClick(position)
                }
                itemEdit.setOnClickListener {
                    myOnClickListener.editMovie(user, position)
                }
                itemDelete.setOnClickListener {
                    myOnClickListener.deleteMovie(user, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bin = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bin)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(list[position], position)

    override fun getItemCount(): Int = list.size
}

interface MyOnClickListener {
    fun editMovie(user: User, position: Int)
    fun deleteMovie(user: User, position: Int)
    fun onClick(position: Int)
}