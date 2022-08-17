package com.example.presenter.base

import com.example.domain.model.Model

interface ItemMapper<M : Model , MI : ModelItem> {

    fun mapToPresenter(model : M): MI

    fun mapToDoMain(modelItem : MI): M
}
