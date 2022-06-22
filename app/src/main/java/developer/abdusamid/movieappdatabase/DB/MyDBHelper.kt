package developer.abdusamid.movieappdatabase.DB

import android.annotation.SuppressLint
import android.content.*
import android.database.sqlite.*
import developer.abdusamid.movieappdatabase.DB.MyDBObject.DB_NAME
import developer.abdusamid.movieappdatabase.DB.MyDBObject.TABLE_NAME
import developer.abdusamid.movieappdatabase.DB.MyDBObject.VERSION
import developer.abdusamid.movieappdatabase.DB.MyDBObject.ID
import developer.abdusamid.movieappdatabase.DB.MyDBObject.NAME
import developer.abdusamid.movieappdatabase.DB.MyDBObject.AUTHOR
import developer.abdusamid.movieappdatabase.DB.MyDBObject.ABOUT
import developer.abdusamid.movieappdatabase.DB.MyDBObject.DATA

class MyDBHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, VERSION), MyDBInterface {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $NAME TEXT NOT NULL, $AUTHOR TEXT NOT NULL, $ABOUT TEXT NOT NULL, $DATA TEXT NOT NULL)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    override fun createUser(user: User) {
        val dataBase = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME, user.name)
        contentValue.put(AUTHOR, user.author)
        contentValue.put(ABOUT, user.about)
        contentValue.put(DATA, user.data)
        dataBase.insert(TABLE_NAME, null, contentValue)
        dataBase.close()
    }

    @SuppressLint("Recycle")
    override fun readUser(): ArrayList<User> {
        val list = ArrayList<User>()
        val query = "SELECT * FROM $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val user = User()
                user.id = cursor.getInt(0)
                user.name = cursor.getString(1)
                user.author = cursor.getString(2)
                user.about = cursor.getString(3)
                user.data = cursor.getString(4)
                list.add(user)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateUser(user: User): Int {
        val contentValue = ContentValues()
        contentValue.put(ID, user.id)
        contentValue.put(NAME, user.name)
        contentValue.put(AUTHOR, user.author)
        contentValue.put(ABOUT, user.about)
        contentValue.put(DATA, user.data)
        return writableDatabase.update(TABLE_NAME, contentValue, "$ID = ?", arrayOf("${user.id}"))
    }

    override fun deleteUser(user: User) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$ID = ?", arrayOf("${user.id}"))
        database.close()
    }
}