package developer.abdusamid.movieappdatabase

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdusamid.movieappdatabase.Adapters.HomeAdapter
import developer.abdusamid.movieappdatabase.Adapters.MyOnClickListener
import developer.abdusamid.movieappdatabase.DB.MyDBHelper
import developer.abdusamid.movieappdatabase.DB.User
import developer.abdusamid.movieappdatabase.Object.MyData
import developer.abdusamid.movieappdatabase.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var myDBHelper: MyDBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {
            myDBHelper = MyDBHelper(root.context)
            val list = myDBHelper.readUser()

            imageAdd.setOnClickListener {
                Toast.makeText(root.context, "Add Menu", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.addFragment)
            }

            homeAdapter = HomeAdapter(list, object : MyOnClickListener {
                override fun editMovie(user: User, position: Int) {
                    MyData.myPos = position
                    findNavController().navigate(R.id.editFragment, bundleOf("userInfo" to user))
                }

                override fun deleteMovie(user: User, position: Int) {
                    Toast.makeText(root.context, "Item Deleted", Toast.LENGTH_SHORT).show()
                    myDBHelper.deleteUser(user)
                    list.removeAt(position)
                    homeAdapter.notifyItemRemoved(position)
                    homeAdapter.notifyItemRangeChanged(position, list.size)
                }

                override fun onClick(position: Int) {
                    MyData.myPos = position
                    findNavController().navigate(R.id.aboutFragment)
                }
            })
            myRv.adapter = homeAdapter

            return binding.root
        }
    }
}