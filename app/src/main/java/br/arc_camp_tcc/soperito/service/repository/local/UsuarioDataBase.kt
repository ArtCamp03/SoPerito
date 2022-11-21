package br.arc_camp_tcc.soperito.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.arc_camp_tcc.soperito.service.model.EmpregadorModel
import br.arc_camp_tcc.soperito.service.model.PeritoModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel

// DATABASE Conexao com o banco

@Database(
    entities = [UsuarioModel::class, PeritoModel::class, EmpregadorModel::class],
    version = 1
)
abstract class UsuarioDataBase : RoomDatabase() {

    abstract fun usuarioDAO(): UsuarioDAO

    // singleton
    companion object {
        private lateinit var INSTANCE: UsuarioDataBase

        fun getUsuario(context: Context): UsuarioDataBase {
            // constroi o banco de dados
            // se instancia nao for inicializada o laço inicializo
            if (!Companion::INSTANCE.isInitialized) {
                // so entra uma thread por vez
                synchronized(UsuarioDataBase::class) {
                    INSTANCE =
                        Room.databaseBuilder(context, UsuarioDataBase::class.java, "soperitoDB")
                            .addMigrations(MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2 : Migration = object  : Migration(1 , 2){
            override fun migrate(database: SupportSQLiteDatabase) {
               // database.execSQL("DELETE FROM usuario")
            }
        }
    }
}