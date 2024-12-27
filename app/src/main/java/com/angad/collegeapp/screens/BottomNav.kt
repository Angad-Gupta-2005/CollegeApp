package com.angad.collegeapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.angad.collegeapp.R
import com.angad.collegeapp.models.BottomNavItem
import com.angad.collegeapp.models.NavItem
import com.angad.collegeapp.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController: NavController) {

    val navController1 = rememberNavController()

//    For toast we create the context instance
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
//    For storing the drawer state
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

//   List of item for navigation drawer
    val list = listOf(
        NavItem("Website", R.drawable.world),
        NavItem("Notice", R.drawable.notice),
        NavItem("Notes", R.drawable.wirte),
        NavItem("Contact Us", R.drawable.contact_mail),
    )

//   This function is use to show the navigation drawer
    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier.height(220.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "")

                list.forEachIndexed { index, items ->
                    NavigationDrawerItem(
                        label = { Text(text = items.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            Toast.makeText(context, "${items.title} clicked", Toast.LENGTH_SHORT)
                                .show()
                            //    Closed the navigation drawer after click on any item
                            scope.launch { drawerState.close() }
                        },
                        //    For icons
                        icon = {
                            Image(
                                painter = painterResource(id = items.icon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(title = { Text(text = "College App") },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() }}) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                            }
                        }
                    )
                },
            //    For bottom navigation bar
                bottomBar = {
                    MyBottomNav(navController = navController1)
                }
            ) { padding ->
                NavHost(
                    navController = navController1,
                    startDestination = Routes.Home.route,
                    modifier = Modifier.padding(padding)
                ){
                    composable(route = Routes.Home.route) {
                        Home()
                    }

                    composable(Routes.Gallery.route) {
                        Gallery()
                    }

                    composable(Routes.AboutUs.route) {
                        AboutUs()
                    }

                    composable(Routes.Faculty.route) {
                        Faculty()
                    }
                }
            }
        }
    )
}

@Composable
fun MyBottomNav(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()

//    List of item for bottom navigation
    val list = listOf(
        BottomNavItem("Home", R.drawable.home, Routes.Home.route),
        BottomNavItem("Faculty", R.drawable.graduated, Routes.Faculty.route),
        BottomNavItem("Gallery", R.drawable.image_gallery, Routes.Gallery.route),
        BottomNavItem("About Us", R.drawable.info, Routes.AboutUs.route),
    )

    BottomAppBar {
        list.forEach {
        //    Getting the current route
            val curRoute = it.route
            val otherRoute =
                try {
                    backStackEntry.value!!.destination.route
                } catch (e: Exception){
                    Routes.Home.route
                }

            val selected = curRoute == otherRoute
            
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(it.route){
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                } },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}