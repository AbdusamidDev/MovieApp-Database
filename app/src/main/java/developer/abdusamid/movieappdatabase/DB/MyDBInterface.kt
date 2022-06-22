package developer.abdusamid.movieappdatabase.DB

interface MyDBInterface {
    fun createUser(user: User)
    fun readUser():ArrayList<User>
    fun updateUser(user: User):Int
    fun deleteUser(user: User)
}