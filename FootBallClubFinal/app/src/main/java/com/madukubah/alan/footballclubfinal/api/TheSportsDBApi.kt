package com.madukubah.alan.footballclubfinal.api

import com.madukubah.alan.footballclubfinal.BuildConfig

object TheSportsDBApi {
    private const val idLeague = ".php?id=4328"
    private const val eventPastLeague = "eventspastleague.php?id="
    private const val eventNextLeague = "eventsnextleague.php?id="
    private const val strListLeagueByName = "searchfilename.php?e="
    private const val strSearchEventByName = "searchevents.php?e="
    private const val strSearchTeamByName = "searchteams.php?t="
    private const val strLookUpEvent = "lookupevent.php?id="
    private const val strLookUpTeam = "lookupteam.php?id="
    private const val strLookUpPlayer = "lookup_all_players.php?id="
    private const val strLookUpDetailPlayer = "lookupplayer.php?id="
    private const val strListTeamByNameLeague = "search_all_teams.php?l="

//    fun geteventspastleague( league : String ):String =
//            BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}"+ "/eventspastleague.php?id=" + league
//
//    fun geteventsnextleague( league : String ):String =
//            BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}"+ "/eventsnextleague.php?id=" + league
//
//    fun getTeam( idTeam : String ):String =
//            BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}"+ "/lookupteam.php?id=" + idTeam
//
//    fun getEvent( idEvent: Long ):String =
//            BuildConfig.BASE_URL +"api/v1/json/${BuildConfig.TSDB_API_KEY}"+ "/lookupevent.php?id=" + idEvent

    fun getPastEvent(idLeague: String?) : String {
        return BuildConfig.BASE_URL + eventPastLeague + idLeague
    }

    fun getNextEvent(idLeague: String?) : String {
        return BuildConfig.BASE_URL + eventNextLeague + idLeague
    }
    fun getTeam(idHome: String?) : String {
        return BuildConfig.BASE_URL + strLookUpTeam + idHome
    }

    fun getListTeamByLeague(nameLeague: String?) : String {
        return BuildConfig.BASE_URL + strListTeamByNameLeague + nameLeague
    }
    fun getListDetailTeam(idTeam: String?) : String {
        return BuildConfig.BASE_URL + strLookUpTeam + idTeam
    }
    fun getListPlayerTeam(nameTeam: String?) : String {
        return BuildConfig.BASE_URL + strLookUpPlayer + nameTeam
    }
    fun getDetailPlayerTeam(idPlayer: String?) : String {
        return BuildConfig.BASE_URL + strLookUpDetailPlayer + idPlayer
    }
    fun getSearchEventByName(eventName: String?) : String {
        return BuildConfig.BASE_URL + strSearchEventByName + eventName
    }
    fun getSearchTeamByName(teamName: String?) : String {
        return BuildConfig.BASE_URL + strSearchTeamByName + teamName
    }
    fun getDetailEvent(idDetail: String?) : String {
        return BuildConfig.BASE_URL + strLookUpEvent + idDetail
    }
    fun getHomeBadge(idHome: String?) : String {
        return BuildConfig.BASE_URL + strLookUpTeam + idHome
    }

    fun getAwayBadge(idAway: String?) : String {
        return BuildConfig.BASE_URL + strLookUpTeam + idAway
    }
}