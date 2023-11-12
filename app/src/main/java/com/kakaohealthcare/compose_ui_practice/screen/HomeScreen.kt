package com.kakaohealthcare.compose_ui_practice.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kakaohealthcare.compose_ui_practice.data.ContentRepository
import com.kakaohealthcare.compose_ui_practice.data.Contents
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import com.kakaohealthcare.compose_ui_practice.data.BottomBarItem
import com.kakaohealthcare.compose_ui_practice.navigation.NavConst

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = Modifier
            .padding(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // 작성화면 이동
                navController.navigate(NavConst.DIARY_ADD)
            }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "write",
                    tint = Color.White,
                )
            }
        }
    ) {
        ShowContent(navController = navController)
    }

}

@Composable
fun ShowContent(navController: NavController) {
    val contentRepo = ContentRepository()
    val getAllData = contentRepo.getAllData()

    LazyColumn(
        contentPadding = PaddingValues(16.dp, 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(items = getAllData) { contents ->
            CustomItem(contents = contents, navController)
        }
    }
}

@Composable
fun CustomItem(contents: Contents, navController: NavController) {

    val painter =
        rememberImagePainter(data = contents.imgUrl)

    Box(
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .clickable(
                onClick = {
                    // DiaryEditorScreen(navController = navController)
                    //Toast.makeText(context, contents.title, Toast.LENGTH_SHORT).show()
                }
            )
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Profile image
//            Image(
//                modifier = Modifier
//                    .clip(shape = CircleShape)
//                    .size(56.dp),
//                painter = painterResource(id = contents.imgUrl),
//                contentDescription = contents.content
//            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp),
            ) {

                Image(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(55.dp),
                    painter = painter,
                    contentDescription = "Forest Image",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = contents.ldapId,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            ) {

                // Text that shows the name
                Text(
                    text = contents.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.padding(top = 10.dp))
                // Text that shows the time
                Text(
                    text = contents.content,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun HomePreview() {
//    val navController = rememberNavController()
//    HomeScreen(navController = navController)
//}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun HomeDarkPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}