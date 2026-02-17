package com.example.nexolap.Data.repository

import com.example.nexolap.modelo.UsuarioDTO

interface IUsuarioRepo {
    fun readAll(onSucess: (List<UsuarioDTO>) -> Unit, onError: () ->Unit)
    fun read(id: Int, onSucess: (usuarioCreado : UsuarioDTO?) -> Unit, onError: () ->Unit)
}


