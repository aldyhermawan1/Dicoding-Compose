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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hermawan.composestarter.R
import com.hermawan.composestarter.jetcoffee.model.Menu
import com.hermawan.composestarter.jetcoffee.model.dummyBestSellerMenu
import com.hermawan.composestarter.jetcoffee.model.dummyCategory
import com.hermawan.composestarter.jetcoffee.model.dummyMenu
import com.hermawan.composestarter.jetcoffee.ui.components.CategoryItem
import com.hermawan.composestarter.jetcoffee.ui.components.MenuItem
import com.hermawan.composestarter.jetcoffee.ui.components.SearchBar
import com.hermawan.composestarter.jetcoffee.ui.components.SectionText
import com.hermawan.composestarter.jetcoffee.ui.theme.ComposeStarterTheme

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
fun JetCoffeeApp() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Banner()
        SectionText(title = stringResource(id = R.string.section_category))
        CategoryRow()
        SectionText(title = stringResource(id = R.string.section_favorite_menu))
        MenuRow(listMenu = dummyMenu)
        SectionText(title = stringResource(id = R.string.section_best_seller_menu))
        MenuRow(listMenu = dummyBestSellerMenu)
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

@Preview(showBackground = true)
@Composable
fun JetCoffeePreview() {
    ComposeStarterTheme {
        JetCoffeeApp()
    }
}