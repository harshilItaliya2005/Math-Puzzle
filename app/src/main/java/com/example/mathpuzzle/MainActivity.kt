package com.example.mathpuzzle

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mathpuzzle.ui.theme.MathPuzzleTheme

class MainActivity : ComponentActivity() {
companion object{
     lateinit var sp: SharedPreferences
     lateinit var edit : SharedPreferences.Editor
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = getSharedPreferences("Math", MODE_PRIVATE)
        edit = sp.edit()
        edit.putBoolean("level_completed", true).apply()

        for (i in 0 until 75) {
            if(sp.getString("Level$i","").equals("")){
                edit.putString("Level$i", "Lock").apply()
            }
        }

        enableEdgeToEdge()

        setContent {
            MathPuzzleTheme {

                val continuePuzzleLevel = sp.getInt("level", 0)
                Design(continuePuzzleLevel)
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
                    painter = painterResource(R.drawable.background_image),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "Background image",
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CardButton(text = "PLAY", onClick = {
                        val play = Intent(applicationContext, PlayActivity::class.java)
                        play.putExtra("Puzzle", puzzleLevel)
                        startActivity(play)
                    }, buttonImg = R.drawable.play_botton)
                    CardButton(text = "LEVEL", onClick = {
                        val play = Intent(applicationContext, LevelActivity::class.java)
                        startActivity(play)
                    }, buttonImg = R.drawable.level_botton)
                    CardButton(
                        text = "BUY PRO", onClick = {}, buttonImg = R.drawable.buy_pro_button
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        BottomView(this)
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

    @Composable
    fun BottomView(rowScope: RowScope) {
        rowScope.apply {
            Image(
                painter = painterResource(R.drawable.share),
                contentDescription = "Share",
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)
                    .clickable { },
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = painterResource(R.drawable.privacy_policy_botton),
                contentDescription = "Privacy policy",
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp, 50.dp)
                    .clickable { },
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = painterResource(R.drawable.mail),
                contentDescription = "Info",
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)
                    .clickable { },
                contentScale = ContentScale.FillBounds
            )
        }
    }

}
