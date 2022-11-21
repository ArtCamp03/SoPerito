package br.arc_camp_tcc.soperito.service.repository.remote.api

import br.arc_camp_tcc.soperito.service.model.Person.PersonLoginModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PersonService {
    @POST("pagina/login.php")
    @FormUrlEncoded
    fun login( @Field("email") email: String,
               @Field("senha") senha: String
    ) : Call<PersonLoginModel>

}