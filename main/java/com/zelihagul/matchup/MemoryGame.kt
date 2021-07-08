package com.zelihagul.matchup

import com.zelihagul.matchup.utils.*

class MemoryGame {

    val cards: List<MemoryCard>
    var numPairsFound = 0
    val chosennote = x.shuffled().take(4)
    var indexOfSingleSelectedCard: Int? = null

    init {
        val chosenlist = (chosennote + chosennote).shuffled()
        cards = chosenlist.map { MemoryCard(it) }
    }

    fun flip(position: Int): Boolean {
        val card: MemoryCard = cards[position]
        var foundMatch = false

        if (indexOfSingleSelectedCard == null) {
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            foundMatch = checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier != cards[position2].identifier) {
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == 4
    }


    private fun restoreCards() {
        for(card: MemoryCard in cards){
            if(!card.isMatched){
            card.isFaceUp = false
        }
    }

}}
