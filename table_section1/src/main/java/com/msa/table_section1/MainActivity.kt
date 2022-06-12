package com.msa.table_section1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msa.table_section1.ui.theme.Base_JetPack_Compose_WidgetsTheme

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
   // Text(text = "Hello $name!")
    Demo_Table()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Base_JetPack_Compose_WidgetsTheme {
        Greeting("Android")
    }
}
@Composable
fun Demo_Table() {
    var visible by remember { mutableStateOf(false) }
    val people = listOf(
        Person("Alex", 21, false, "alex@demo-email.com"),
        Person("Adam", 35, true, "adam@demo-email.com"),
        Person("Iris", 26, false, "iris@demo-email.com"),
        Person("Maria", 32, false, "maria@demo-email.com")
    )

    val cellWidth: (Int) -> Dp = { index ->
        when (index) {
            2 -> 250.dp
            3 -> 350.dp
            else -> 150.dp
        }
    }
    val headerCellTitle: @Composable (Int) -> Unit = { index ->
        val value = when (index) {
            0 -> "Name"
            1 -> "Age"
            2 -> "Has driving license"
            3 -> "Email"
            else -> ""
        }

        Text(
            text = value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Black,
            textDecoration = TextDecoration.Underline
        )
    }
    val cellText: @Composable (Int, Person) -> Unit = { index, item ->
        val value = when (index) {
            0 -> item.name
            1 -> item.age.toString()
            2 -> if (item.hasDrivingLicence) "YES" else "NO"
            3 -> item.email
            else -> ""
        }
        Surface(Modifier.clickable { visible = !visible }) {
            Text(
                text = value,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }

    Table(
        columnCount = 4,
        cellWidth = cellWidth,
        data = people,
        modifier = Modifier.verticalScroll(rememberScrollState()),
        headerCellContent = headerCellTitle,
        cellContent = cellText
    )
}
/**
 * The horizontally scrollable table with header and content.
 * @param columnCount the count of columns in the table
 * @param cellWidth the width of column, can be configured based on index of the column.
 * @param data the data to populate table.
 * @param modifier the modifier to apply to this layout node.
 * @param headerCellContent a block which describes the header cell content.
 * @param cellContent a block which describes the cell content.
 */
@Composable
fun <T> Table(
    columnCount: Int,
    cellWidth: (index: Int) -> Dp,
    data: List<T>,
    modifier: Modifier = Modifier,
    headerCellContent: @Composable (index: Int) -> Unit,
    cellContent: @Composable (index: Int, item: T) -> Unit,
) {
    Surface(
        modifier = modifier
    ) {

        LazyRow(
            modifier = Modifier.padding(16.dp)
        ) {
            items((0 until columnCount).toList()) { columnIndex ->
                Column {
                    (0..data.size).forEach { index ->
                        Surface(
                            border = BorderStroke(1.dp, Color.LightGray),
                            contentColor = Color.Black,
                            modifier = Modifier.width(cellWidth(columnIndex))

                        ) {

                            if (index == 0) {
                                    headerCellContent(columnIndex)
                            } else {
                                cellContent(columnIndex, data[index - 1])

                            }
                        }
                    }
                }
            }
        }
    }
}

