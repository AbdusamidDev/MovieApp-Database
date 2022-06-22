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
import developer.abdusamid.movieappdatabase.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var myDBHelper: MyDBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        binding.apply {
            addSave.setOnClickListener {
                if (addMovie.text.isNotEmpty() && authorMovie.text.isNotEmpty() && aboutMovie.text.isNotEmpty() && dataMovie.text.isNotEmpty()) {
                    val user = User(
                        addMovie.text.toString(),
                        authorMovie.text.toString(),
                        aboutMovie.text.toString(),
                        dataMovie.text.toString()
                    )
                    myDBHelper = MyDBHelper(root.context)
                    myDBHelper.createUser(user)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(root.context, "Write Something", Toast.LENGTH_SHORT).show()
                }
            }

            return binding.root
        }
    }
}