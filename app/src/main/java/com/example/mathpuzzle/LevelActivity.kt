package com.example.mathpuzzle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathpuzzle.ui.theme.MathPuzzleTheme

class LevelActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val continuePuzzleLevel = MainActivity.Companion.sp.getInt("level", 0)

        Log.d("Answer Check", "Current level passed: $continuePuzzleLevel")
        setContent {
            MathPuzzleTheme {
                Design(continuePuzzleLevel)
            }
        }
    }

    @Composable
    fun Design(currentLevel: Int) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Image(
                    painter = painterResource(R.drawable.leval_background_image),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "Background image",
                    contentScale = ContentScale.FillBounds
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp)
                ) {
                    items(75) { index ->

                        val levelStatus = MainActivity.sp.getString("Level$index", "Lock")

                        OutlinedCard(modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp),
                            border = BorderStroke(4.dp, color = Color.White),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = Color(0xFF42A5F5)
                            ),
                            onClick = {
                                Log.d("Answer Check", "Level ${index + 1} clicked. Status: $levelStatus")
                                if (levelStatus == "Lock" && currentLevel != index) {
                                    Log.d("Answer Check", "Level ${index + 1} is locked. Cannot play.")
                                    Toast.makeText(this@LevelActivity, "Level ${index + 1} is locked!", Toast.LENGTH_SHORT).show()
                                } else {
                                    val playIntent =
                                        Intent(this@LevelActivity, PlayActivity::class.java)
                                    playIntent.putExtra("Puzzle", index)
                                    startActivity(playIntent)
                                    finish()
                                }

                            }) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {

                                if (levelStatus == "Completed") {
                                    Text(
                                        text = (index + 1).toString(),
                                        fontSize = 25.sp,
                                        color = Color.White
                                    )
                                    Image(
                                        painter = painterResource(R.drawable.right),
                                        contentDescription = "",
                                        modifier = Modifier.size(50.dp)

                                    )
                                } else if (levelStatus == "Skip" || currentLevel == index ) {
                                    Text(
                                        text = (index + 1).toString(),
                                        fontSize = 25.sp,
                                        color = Color.White
                                    )
                                } else {

                                        Image(
                                            painter = painterResource(R.drawable.lock),
                                            contentDescription = "",
                                            modifier = Modifier.size(50.dp)
                                        )

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
