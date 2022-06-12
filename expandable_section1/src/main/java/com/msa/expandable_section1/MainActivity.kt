package com.msa.expandable_section1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.msa.expandable_section1.ui.theme.Base_JetPack_Compose_WidgetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Base_JetPack_Compose_WidgetsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    //Text(text = "Hello $name!")
    expandable()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Base_JetPack_Compose_WidgetsTheme {
        Greeting("Android")
    }
}

@Composable
fun expandable(){
    Column(modifier = Modifier.fillMaxWidth()) {
        var visible by remember { mutableStateOf(false) }
        //header
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { visible = !visible }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Click me",
                    style = MaterialTheme.typography.displayMedium
                )
                Icon(
                    modifier = Modifier.rotate(animateFloatAsState(if
                            (visible) 180f else 0f).value),
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }

            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black.copy(ContentAlpha.disabled)
            )
        }
        //the 4 items
        Column {
            (1..4).forEach {
                AnimatedVisibility(
                    visible = visible,
                    enter = expandVertically(
                        spring(
                            stiffness = Spring.StiffnessLow,
                            visibilityThreshold = IntSize.VisibilityThreshold
                        ),
                    ),
                    exit = shrinkVertically(),
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Hello",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Divider(
    modifier: Modifier? = Modifier,
    color: Color? = MaterialTheme.colorScheme.background,
    thickness: Dp? = 1.dp,
    startIndent: Dp? = 0.dp
): Unit{

}