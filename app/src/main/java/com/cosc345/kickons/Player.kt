package com.cosc345.kickons
/**
 * Class which stores global list of players to be randomly chosen for cards
 *
 */
var playerList = mutableListOf<Player>()
data class Player(
    val name : String?
)
