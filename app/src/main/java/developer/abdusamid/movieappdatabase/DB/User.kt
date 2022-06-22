package developer.abdusamid.movieappdatabase.DB

import java.io.Serializable

class User : Serializable {
    var id: Int? = null
    var name: String? = null
    var author: String? = null
    var about: String? = null
    var data: String? = null

    constructor(name: String?, author: String?, about: String?, data: String?) {
        this.name = name
        this.author = author
        this.about = about
        this.data = data
    }

    constructor()
    constructor(id: Int?, name: String?, author: String?, about: String?, data: String?) {
        this.id = id
        this.name = name
        this.author = author
        this.about = about
        this.data = data
    }
}