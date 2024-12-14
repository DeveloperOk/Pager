package com.enterprise.pager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.enterprise.pager.ui.theme.PagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PagerTheme {
                PagerApp()
            }
        }
    }
}





@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerApp() {

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(modifier = Modifier.systemBarsPadding().padding(innerPadding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top){

            AppPagerComponent()

        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppPagerComponent() {
    var imageItems = arrayListOf(R.drawable.image1, R.drawable.image2)

    val imageItemsSize = imageItems.size

    //To make it endless pager
    val pageCount = imageItemsSize * 50

    val pagerState = rememberPagerState(pageCount = {pageCount},
        initialPage = pageCount / 2)

    AppPager(imageItems = imageItems, imageItemsSize = imageItemsSize, pagerState = pagerState)

    AppPageIndicator(imageItemsSize = imageItemsSize, pagerState = pagerState)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppPageIndicator(imageItemsSize: Int, pagerState: PagerState) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(imageItemsSize) { iteration ->

            val color = if (pagerState.currentPage % imageItemsSize == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color = color)
                    .size(16.dp)
            )
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppPager(
    imageItems: ArrayList<Int>,
    imageItemsSize: Int,
    pagerState: PagerState
) {

    HorizontalPager(modifier = Modifier.fillMaxWidth(), state = pagerState) { pageNumber ->

        PageElement(imageItem = imageItems[pageNumber % imageItemsSize])

    }

}

@Composable
fun PageElement(imageItem: Int) {

    Image(modifier = Modifier.fillMaxWidth().height(150.dp),
        painter = painterResource(imageItem),
        contentDescription = "Image",
        contentScale = ContentScale.FillBounds,
    )

}

