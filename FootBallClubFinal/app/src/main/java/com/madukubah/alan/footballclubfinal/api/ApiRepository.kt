package com.madukubah.alan.footballclubfinal.api

import java.net.URL

class ApiRepository {
    fun doRequest(url: String) : String {
        return URL(url).readText()
    }
}