package com.example.makinglemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.makinglemonade.ui.theme.MakingLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MakingLemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MakingLemonadeApp()
                }
            }
        }
    }
}

@Composable
fun MakingLemonadeWithImageAndButton(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf( 0) }
//    var squeezeTime by remember { mutableStateOf((4..8).random()) }
//    var haveSqueezed by remember { mutableStateOf(0) }
    var squeezeTime = (4..8).random()
    var haveSqueezed = 0
    val (imageResource, descriptionResource, guideResource) = when (count % 4) {
        0 -> Triple(R.drawable.lemon_tree, R.string.lemon_tree_content_description, R.string.pic1_guide)
        1 -> Triple(R.drawable.lemon_squeeze, R.string.lemon_content_description, R.string.pic2_guide)
        2 -> Triple(R.drawable.lemon_drink, R.string.glass_of_lemonade_content_description, R.string.pic3_guide)
        else -> Triple(R.drawable.lemon_restart, R.string.empty_glass_content_description, R.string.pic4_guide)
    }

//    LaunchedEffect(count) {
//        if (count % 4 == 0) {
//            squeezeTime = (4..8).random()
//        }
//    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .size(200.dp)
            .clickable {
                if (count % 4 == 1) {
                    if (haveSqueezed < squeezeTime - 1)
                        haveSqueezed++
                    else {
                        haveSqueezed = 0
                        count++
                    }
                }
                else count++
            }
            .background(
                Color(red = 195, green = 236, blue = 210),
                shape = RoundedCornerShape(16.dp)
            )  // Màu nền xanh nhạt và bo góc
            .padding(16.dp)
        ) {
            Image(
                painter = painterResource(imageResource),
                //painter = painterResource(imageResource),
                contentDescription = stringResource(descriptionResource),
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(guideResource, squeezeTime), fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
fun MakingLemonadeApp() {
    MakingLemonadeWithImageAndButton(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}