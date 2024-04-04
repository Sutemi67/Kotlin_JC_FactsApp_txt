package com.example.tolchokfacts

import android.os.Bundle
import android.widget.TextClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.tolchokfacts.ui.theme.TolchokFactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TolchokFactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Facts()
                }
            }
        }
    }
}

@Composable
fun Facts() {

    val buttonText = when ((Math.random() * 4).toInt()) {
        1 -> "Тыкай давай"
        2 -> "Жми, жми!"
        3 -> "В бой!"
        else -> "Нажми уже"
    }
    val textStrings = when ((Math.random() * 6).toInt()) {
        1 -> "Рандомный мотиватор"
        2 -> "Привет, лучший"
        3 -> "Сколько будет сумма чисел от 1 до 100? Это легче, чем кажется."
        4 -> "Тебе от пацанов привет"
        5 -> "Все у тебя получится. Только делай"
        else -> "Ты лучший. Не только в доте."
    }


    var counter by remember { mutableStateOf(0) }
    val counterRandom = (Math.random() * 9).toInt()
//    var achievementCounter by remember { mutableStateOf(0)}

    val razText =
        if (counter < 10) {
            if (counter in 2..4) {
                "раза"
            } else {
                "раз"
            }
        } else {
            if (counter == 12) {
                "раз"
            } else if (counter == 13) {
                "раз"
            } else if (counter == 14) {
                "раз"
            } else if (counter % 10 in 2..4) {
                "раза"
            } else {
                "раз"
            }
        }

    val textCounter = if (counter == 0) {
        "Ну давай, понажимай и сегодня..."
    } else {
        if (counter % 10 == counterRandom) {
            textStrings
        } else {
            "Ты нажал уже\n $counter $razText"
        }
    }

    //   if (textCounter == textStrings){achievementCounter++}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(Color.White)
    ) {
        Row {
            Text(
                text = "Фраз открыто: "
            )
            /*           Text(
                           text = "$achievementCounter/10"
                       )
             */
        }

        Box(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(0.9f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = textCounter,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(0.9f),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.4f)
                ) {
                    Text(
                        text = "Времечко:",
                        fontWeight = Bold,
                        fontSize = 19.sp,
                        textAlign = TextAlign.Center
                    )
                    AndroidView(
                        // on below line we are initializing our text clock.
                        factory = { context ->
                            TextClock(context).apply {
                                // on below line we are setting 12 hour format.
                                format24Hour?.let { this.format24Hour = "HH:mm:ss" }
                                // on below line we are setting time zone.
                                timeZone?.let { this.timeZone = it }
                                // on below line we are setting text size.
                                textSize.let { this.textSize = 23f }
                            }
                        },
                    )
                }
                Button(
                    onClick = { counter++ },
                    modifier = Modifier
                        .size(200.dp)
                        .shadow(50.dp, shape = CircleShape, spotColor = Color.Red),
                    shape = CircleShape,
                    border = BorderStroke(2.dp, color = Color.Black)
                ) {
                    Text(
                        text = buttonText,
                        fontSize = 33.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TolchokFactsTheme {
        Facts()
    }
}