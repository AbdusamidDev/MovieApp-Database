package developer.abdusamid.movieappdatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdusamid.movieappdatabase.DB.MyDBHelper
import developer.abdusamid.movieappdatabase.DB.User
import developer.abdusamid.movieappdatabase.Object.MyData
import developer.abdusamid.movieappdatabase.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var myDBHelper: MyDBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(layoutInflater)
        binding.apply {
            myDBHelper = MyDBHelper(root.context)
            val list = myDBHelper.readUser()

            editMovie.setText(list[MyData.myPos].name)
            editAuthorMovie.setText(list[MyData.myPos].author)
            aboutMovie.setText(list[MyData.myPos].about)
            dataMovie.setText(list[MyData.myPos].data)

            editSave.setOnClickListener {
                Toast.makeText(container!!.context, "Edit Saved", Toast.LENGTH_SHORT).show()
                val name = editMovie.text.toString()
                val author = editAuthorMovie.text.toString()
                val about = aboutMovie.text.toString()
                val date = dataMovie.text.toString()
                if (name.isNotEmpty() && author.isNotEmpty() && about.isNotEmpty() && date.isNotEmpty()) {
                    val user = requireArguments().getSerializable("userInfo") as User
                    user.name = name
                    user.author = author
                    user.about = about
                    user.data = date
                    myDBHelper.updateUser(user)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(root.context, "Write Something", Toast.LENGTH_SHORT).show()
                }
            }

            return root
        }
    }
}