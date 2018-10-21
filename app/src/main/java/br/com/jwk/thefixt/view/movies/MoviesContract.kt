package br.com.jwk.thefixt.view.movies

import br.com.jwk.thefixt.base.BasePresenter
import br.com.jwk.thefixt.base.BaseView

interface MoviesContract {
    interface Presenter : BasePresenter<View> {
    }

    interface View : BaseView<Presenter> {

    }
}