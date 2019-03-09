package com.madukubah.alan.footballclubfinal.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseFavorite(ctx: Context): ManagedSQLiteOpenHelper(ctx,
        "finalfootball.db", null, 1) {

    companion object {
        private var instance: DatabaseFavorite? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseFavorite {
            if (instance == null) {
                instance = DatabaseFavorite(ctx.applicationContext)
            }
            return instance as DatabaseFavorite
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(TableMatch.TABLE_MATCHES, true,
                TableMatch.MATCHES_ID to INTEGER + PRIMARY_KEY
        )

        db?.createTable(TableTeam.TABLE_TEAMS, true,
                TableTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TableTeam.TEAMS_ID to TEXT + UNIQUE,
                TableTeam.TEAMS_NAME to TEXT,
                TableTeam.BADGE_TEAM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(TableMatch.TABLE_MATCHES, true)
        db?.dropTable(TableTeam.TEAMS_NAME, true)
    }
}
val Context.db: DatabaseFavorite
    get() = DatabaseFavorite.getInstance(applicationContext)