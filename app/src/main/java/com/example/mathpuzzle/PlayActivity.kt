package com.example.mathpuzzle

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathpuzzle.ui.theme.MathPuzzleTheme
import com.example.mathpuzzle.ui.theme.orange




class PlayActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathPuzzleTheme {

                Design()
            }
        }
    }

    @Composable
    fun Design() {
        var inputText = remember { mutableStateOf("") }
        var textArr = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
        val currentAnswer = (1..75).toList()

        val currentLevelIndex = intent.getIntExtra("Puzzle", 0)
        val levelImages = arrayOf(
            R.drawable.level_one,
            R.drawable.level_two,
            R.drawable.level_three,
            R.drawable.level_four,
            R.drawable.level_five,
            R.drawable.level_six,
            R.drawable.level_seven,
            R.drawable.level_eight,
            R.drawable.level_nine,
            R.drawable.level_ten,
            R.drawable.level_eleven,
            R.drawable.level_twelve,
            R.drawable.level_thirteen,
            R.drawable.level_fourteen,
            R.drawable.level_fifteen,
            R.drawable.level_sixteen,
            R.drawable.level_seventeen,
            R.drawable.level_eighteen,
            R.drawable.level_nineteen,
            R.drawable.level_twenty,
            R.drawable.level_twenty_one,
            R.drawable.level_twenty_two,
            R.drawable.level_twenty_three,
            R.drawable.level_twenty_four,
            R.drawable.level_twenty_five,
            R.drawable.level_twenty_six,
            R.drawable.level_twenty_seven,
            R.drawable.level_twenty_eight,
            R.drawable.level_twenty_nine,
            R.drawable.level_thirty,
            R.drawable.level_thirty_one,
            R.drawable.level_thirty_two,
            R.drawable.level_thirty_three,
            R.drawable.level_thirty_four,
            R.drawable.level_thirty_five,
            R.drawable.level_thirty_six,
            R.drawable.level_thirty_seven,
            R.drawable.level_thirty_eight,
            R.drawable.level_thirty_nine,
            R.drawable.level_forty,
            R.drawable.level_forty_one,
            R.drawable.level_forty_two,
            R.drawable.level_forty_three,
            R.drawable.level_forty_four,
            R.drawable.level_forty_five,
            R.drawable.level_forty_six,
            R.drawable.level_forty_seven,
            R.drawable.level_forty_eight,
            R.drawable.level_forty_nine,
            R.drawable.level_fifty,
            R.drawable.level_fifty_one,
            R.drawable.level_fifty_two,
            R.drawable.level_fifty_three,
            R.drawable.level_fifty_four,
            R.drawable.level_fifty_five,
            R.drawable.level_fifty_six,
            R.drawable.level_fifty_seven,
            R.drawable.level_fifty_eight,
            R.drawable.level_fifty_nine,
            R.drawable.level_sixty,
            R.drawable.level_sixty_one,
            R.drawable.level_sixty_two,
            R.drawable.level_sixty_three,
            R.drawable.level_sixty_four,
            R.drawable.level_sixty_five,
            R.drawable.level_sixty_six,
            R.drawable.level_sixty_seven,
            R.drawable.level_sixty_eight,
            R.drawable.level_sixty_nine,
            R.drawable.level_seventy,
            R.drawable.level_seventy_one,
            R.drawable.level_seventy_two,
            R.drawable.level_seventy_three,
            R.drawable.level_seventy_four,
            R.drawable.level_seventy_five
        )

        Scaffold(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.play_bg),
                    contentDescription = "Background",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.12f)
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Image(painter = painterResource(R.drawable.next_btn),
                            contentDescription = "Next Button",
                            modifier = Modifier
                                .size(50.dp)
                                .clickable {

                                    val nextIntent = Intent(applicationContext, PlayActivity::class.java)

                                    nextIntent.putExtra("Puzzle", currentLevelIndex+1)
                                    startActivity(nextIntent)
                                    inputText.value = ""

                                    finish()
                                })
                        Card(
                            modifier = Modifier.size(150.dp, 60.dp),
                            border = BorderStroke(4.dp, Color.Blue),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Black)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Puzzle ${currentLevelIndex+1}",
                                    fontSize = 30.sp,
                                    color = orange,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Image(painter = painterResource(R.drawable.stop_btn),
                            contentDescription = "Stop Button",
                            modifier = Modifier
                                .size(50.dp)
                                .clickable { })
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {


                            if (currentLevelIndex in levelImages.indices) {
                                Image(
                                    painter = painterResource(levelImages[currentLevelIndex]),
                                    contentDescription = "Level Image",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(10.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(.2f)
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .size(90.dp, 50.dp)
                                    .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                                    .padding(10.dp), contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = inputText.value,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp,
                                    color = orange
                                )
                            }
                            Image(painter = painterResource(R.drawable.remove_btn),
                                contentDescription = "Remove btn",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .clickable {
                                        inputText.value = inputText.value.dropLast(1)
                                    }
                                    .size(53.dp))
                        }

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(5),
                            modifier = Modifier
                                .weight(.4f)
                                .padding(10.dp)
                        ) {
                            items(textArr.size) { index ->
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .padding(8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(painter = painterResource(R.drawable.yellow),
                                        contentDescription = "btn $index",
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .clickable {
                                                inputText.value += textArr[index].toString()
                                            }
                                            .size(70.dp))
                                    Text(
                                        text = textArr[index].toString(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp,
                                        color = Color.Black
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(.3f),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 30.dp)
                                    .weight(1f), contentAlignment = Alignment.Center
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        if (inputText.value.isEmpty()) {
                                            Toast.makeText(
                                                applicationContext,
                                                "Please enter an answer!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            if (inputText.value == "${currentAnswer[currentLevelIndex]}") {
                                                Log.d("Answer Check", "Correct answer!")
                                                Toast.makeText(
                                                    applicationContext,
                                                    "You Win!!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                inputText.value = ""
                                                MainActivity.edit.putInt("level",currentLevelIndex+1).apply()
                                                val intent = Intent(applicationContext, WinnerActivity::class.java)
                                                intent.putExtra("Puzzle", currentLevelIndex+1)

                                                MainActivity.sp.getString("Level", "Completed")
                                                startActivity(intent)
                                            } else {
                                                Log.d("Answer Check", "Wrong answer!")
                                                Toast.makeText(
                                                    applicationContext,
                                                    "You Lose! Try Again!!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                inputText.value = ""

                                            }
                                        }
                                    },
                                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Black),
                                    border = BorderStroke(4.dp, Color.Blue),
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Text(
                                        text = "SUBMIT",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 30.sp
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
