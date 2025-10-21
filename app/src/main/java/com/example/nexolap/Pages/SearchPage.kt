package com.example.nexolap.Pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.myComponents.ListHorizontal


@Composable
fun SearchPage(modifier: Modifier = Modifier) {
    ListHorizontal()
}

@Preview
@Composable
fun PreviewSearchPage() {
    SearchPage()
}