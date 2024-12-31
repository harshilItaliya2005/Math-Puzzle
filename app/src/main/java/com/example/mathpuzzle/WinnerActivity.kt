package com.example.mathpuzzle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathpuzzle.ui.theme.MathPuzzleTheme

@Suppress("UNCHECKED_CAST")
class WinnerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MathPuzzleTheme {
                val currentPuzzleLevel = intent.getIntExtra("Puzzle", 1)
                Design(currentPuzzleLevel)
            }
        }
    }

    @Composable
    fun Design(puzzleLevel: Int) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Image(
                    painter = painterResource(R.drawable.win_bg),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "Background image",
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "$puzzleLevel",
                            fontWeight = FontWeight.Bold,
                            fontSize = 60.sp
                        )
                        Image(
                            painter = painterResource(R.drawable.level_complete),
                            contentScale = ContentScale.FillBounds, contentDescription = "",
                            modifier = Modifier
                                .size(250.dp, 60.dp)
                                .padding(5.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(.7f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = "", contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(.8f)
                            .padding(top = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CardButton(text = "", onClick = {
                            Log.d("Answer Check", "$puzzleLevel")
                                if (puzzleLevel == 75) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Your All Level Is Completed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    val intent = Intent(applicationContext, PlayActivity::class.java)
                                    intent.putExtra("Puzzle", puzzleLevel)
                                    startActivity(intent)
                                }
                            finish()
                        }, buttonImg = R.drawable.continuebtn)

                        CardButton(text = "LEVEL", onClick = {
                            val mainMenu = Intent(applicationContext, MainActivity::class.java)
                            startActivity(mainMenu)
                        }, buttonImg = R.drawable.main_menu)

                        CardButton(
                            text = "BUY PRO", onClick = {}, buttonImg = R.drawable.buy_pro_button
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(.2f)
                            .padding(bottom = 10.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.share_icon),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = "",
                            modifier = Modifier.size(60.dp, 100.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun CardButton(text: String, onClick: () -> Unit, buttonImg: Int) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onClick)
                .size(200.dp, 60.dp)
        ) {
            Image(
                painter = painterResource(buttonImg),
                contentDescription = text,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }

}
