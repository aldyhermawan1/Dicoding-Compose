package com.hermawan.composestarter.jetcoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hermawan.composestarter.R
import com.hermawan.composestarter.jetcoffee.model.BottomBarItem
import com.hermawan.composestarter.jetcoffee.model.Menu
import com.hermawan.composestarter.jetcoffee.model.dummyBestSellerMenu
import com.hermawan.composestarter.jetcoffee.model.dummyCategory
import com.hermawan.composestarter.jetcoffee.model.dummyMenu
import com.hermawan.composestarter.jetcoffee.ui.components.CategoryItem
import com.hermawan.composestarter.jetcoffee.ui.components.HomeSection
import com.hermawan.composestarter.jetcoffee.ui.components.MenuItem
import com.hermawan.composestarter.jetcoffee.ui.components.SearchBar
import com.hermawan.composestarter.jetcoffee.ui.theme.ComposeStarterTheme
import com.hermawan.composestarter.jetcoffee.ui.theme.LightGray

class JetCoffeeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStarterTheme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = { BottomBar() }
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            Banner()
            HomeSection(
                title = stringResource(id = R.string.section_category),
                content = { CategoryRow() }
            )
            HomeSection(title = stringResource(id = R.string.section_favorite_menu), Modifier, {
                MenuRow(listMenu = dummyMenu)
            })
            HomeSection(title = stringResource(id = R.string.section_best_seller_menu)) {
                MenuRow(listMenu = dummyBestSellerMenu)
            }
        }
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(dummyCategory, key = { it.textCategory }) {
            CategoryItem(category = it)
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) {
            MenuItem(menu = it)
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            ),
        )
        navigationItems.map {
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
                label = {
                    Text(text = it.title)
                },
                selected = it.title == navigationItems[0].title,
                unselectedContentColor = LightGray,
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetCoffeePreview() {
    ComposeStarterTheme {
        JetCoffeeApp()
    }
}