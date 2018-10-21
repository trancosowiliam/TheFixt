package br.com.jwk.thefixt.ext

import br.com.jwk.thefixt.data.model.Movie
import com.parse.ParseObject
import com.parse.ParseUser

internal fun Movie.toParseObject() =
        ParseObject("Movie").apply {
            put("imdbID", imdbID)
            put("title", title)
            put("img", img)
            put("year", year)
            put("duration", duration)
            put("belongTo", ParseUser.getCurrentUser())
        }

internal fun ParseObject.toMovie() =
        Movie(
                getString("imdbID"),
                getString("title"),
                getString("img"),
                getString("year") ?: "",
                getString("duration")
        )