package com.kakaohealthcare.compose_ui_practice.screen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kakaohealthcare.compose_ui_practice.R
import com.kakaohealthcare.compose_ui_practice.data.BottomBarItem
import com.kakaohealthcare.compose_ui_practice.navigation.NavConst

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier,
        bottomBar = { BottomNavigation(navController) }
    ){
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.screenRoute,
            modifier = Modifier.padding(it)
        ) {
            composable(BottomNavItem.Home.screenRoute) {
                HomeScreen(navController = navController)
            }
            composable(NavConst.DIARY_ADD) {
                DiaryEditorScreen(navController = navController)
            }
            composable(BottomNavItem.Diary.screenRoute) {
                DiaryScreen(navController = navController)
            }
            composable(BottomNavItem.Gallery.screenRoute) {
                GalleryScreen(navController = navController)
            }
        }
    }
}

sealed class BottomNavItem(
    val title: String, val icon: ImageVector, val screenRoute: String
) {
    object Home : BottomNavItem(NavConst.HOME, Icons.Filled.Home, NavConst.HOME)
    object Diary : BottomNavItem(NavConst.DIARY, Icons.Filled.Star, NavConst.DIARY)
    object Gallery : BottomNavItem(NavConst.GALLERY, Icons.Filled.Favorite, NavConst.GALLERY)
}


@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.Diary,
        BottomNavItem.Gallery,
    )

    NavigationBar(
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                label = { Text(item.title, fontSize = 9.sp) },
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}