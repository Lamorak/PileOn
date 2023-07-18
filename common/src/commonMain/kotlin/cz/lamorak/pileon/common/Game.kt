package cz.lamorak.pileon.common

import androidx.compose.runtime.*

class Game {
    var state by mutableStateOf(deck.shuffled().chunked(PILE_SIZE).plus(listOf(emptyList(), emptyList())))

    companion object {
        const val PILE_SIZE = 4
    }

    val isCompleted: Boolean
        get() = state.all { pile -> pile.isEmpty() || (pile.size == PILE_SIZE && pile.groupBy { it.rank }.size == 1) }

    fun getMovedCards(from: Int, cardsCount: Int): List<Card> {
        println("get $from $cardsCount")
        if (from !in 0..14) return emptyList()

        val fromPile = state[from]
        if (cardsCount !in 1..fromPile.size) return emptyList()

        val movedCards = fromPile.takeLast(cardsCount)

        return if (movedCards.groupBy { it.rank }.size == 1) movedCards else emptyList()
    }

    fun canDropToPile(to: Int, movedCards: List<Card>): Boolean {
        if (to !in 0..14) return false
        if (movedCards.isEmpty()) return false

        val toPile = state[to]
        if (toPile.size + movedCards.size > PILE_SIZE) return false

        return toPile.isEmpty() || toPile.last().rank == movedCards.first().rank
    }

    fun move(from: Int, to: Int, cardsCount: Int) {
        println("move $from $to $cardsCount")
        if (from == to) return

        val movedCards = getMovedCards(from, cardsCount)

        if (!canDropToPile(to, movedCards)) return

        state = state.mapIndexed { index, pile ->
            when (index) {
                from -> pile.take(pile.size - cardsCount)
                to -> pile.plus(movedCards)
                else -> pile
            }
        }
    }
}

val deck = Suit.values().flatMap { suit ->
    Rank.values().map { rank ->
        Card(rank, suit)
    }
}

data class Card(
    val rank: Rank,
    val suit: Suit,
) {

    override fun toString(): String {
        return rank.symbol + suit.symbol
    }
}

enum class Rank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ;

    val symbol: String
        get() = when (this) {
            ACE -> "A"
            JACK -> "J"
            QUEEN -> "Q"
            KING -> "K"
            else -> "${ordinal + 1}"
        }
}

enum class Suit(val symbol: String) {
    HEARTS("♥️"),
    DIAMONDS("♦️"),
    CLUBS("♣️"),
    SPADES("♠️"),
}