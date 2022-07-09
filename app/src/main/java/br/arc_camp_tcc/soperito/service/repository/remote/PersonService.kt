package br.arc_camp_tcc.soperito.service.repository.remote

import br.arc_camp_tcc.soperito.service.model.Person.PersonLoginModel
import br.arc_camp_tcc.soperito.service.model.Person.PersonUsuarioModel
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PersonService {

    @POST("/login")
    @FormUrlEncoded
    fun login( @Field("email") email: String,
               @Field("senha") senha: String
    ) : Call<PersonLoginModel>

    @POST("/usuario")
    @FormUrlEncoded
    fun create(@Field("codigUsuario") codigUsuario: Int,
               @Field("codEmp") codEmp: Int,
               @Field("codPerito") codPerito: Int,
               @Field("userPerito") userPerito: Boolean,
               @Field("userEmp") userEmp: Boolean,
               @Field("email") email: String,
               @Field("nome") nome: String,
               @Field("cpf") cpf: String,
               @Field("telefone") telefone: String,
               @Field("senha") senha: String,
               @Field("codSeguranca") codSeguranca: Int
    ) : Call<PersonUsuarioModel>

}