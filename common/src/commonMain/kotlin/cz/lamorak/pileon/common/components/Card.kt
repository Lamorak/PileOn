package cz.lamorak.pileon.common.components

import Card
import Rank
import Suit
//import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Card as MaterialCard

@Composable
fun Card(card: Card, index: Int) {
    MaterialCard(
        shape = RoundedCornerShape(5),
        backgroundColor = Color(0xffcccccc),
        border = BorderStroke(1.dp, Color.Black),
        elevation = (index+1).dp,
        modifier = Modifier
            .aspectRatio(2 / 3f)
            .padding(4.dp),
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = card.toString(),
        )
    }
}

//@Preview
//@Composable
//fun CardPreview() {
//    Box {
//        Card(Card(Rank.ACE, Suit.SPADES), 0)
//    }
//}