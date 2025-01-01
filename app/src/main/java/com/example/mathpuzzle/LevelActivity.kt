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
        val currentPuzzleLevel = intent.getIntExtra("Puzzle", 1)
        Log.d("LevelActivity", "Current level passed: $currentPuzzleLevel")
        setContent {
            MathPuzzleTheme {
                Design(currentPuzzleLevel)
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

                val arr = (1..75).toList()
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp)
                ) {
                    items(arr.size) { index ->
                        val levelNumber = arr[index]
                        val levelKey = "Level$levelNumber"

                        OutlinedCard(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(10.dp),
                            border = BorderStroke(4.dp, color = Color.White),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = Color(0xFF42A5F5)
                            ),
                            onClick = {
                                val levelStatus = MainActivity.sp.getString(levelKey, "Lock")
                                Log.d("Answer Check" , "Level $levelNumber clicked. Status: $levelStatus")
                                if (levelStatus == "Completed" || levelNumber == currentLevel) {
                                    Log.d(
                                        "Answer Check",
                                        "Navigating to PlayActivity for level $levelNumber"
                                    )
                                    val playIntent =
                                        Intent(this@LevelActivity, PlayActivity::class.java)
                                    playIntent.putExtra("Puzzle", levelNumber)
                                    startActivity(playIntent)
                                    finish()
                                } else {
                                    Log.d(
                                        "Answer Check",
                                        "Level $levelNumber is locked. Cannot play."
                                    )
                                    Toast.makeText(
                                        this@LevelActivity,
                                        "Level $levelNumber is locked!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = (index + 1).toString(),
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}