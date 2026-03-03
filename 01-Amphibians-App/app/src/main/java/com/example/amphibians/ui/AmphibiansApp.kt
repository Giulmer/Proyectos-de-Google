

@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.amphibians.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.screens.AmphibiansViewModel
import com.example.amphibians.ui.screens.HomeScreen

@Composable
fun AmphibiansApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AmphibiansAppTopBar(scrollBehavior = scrollBehavior) }
    ) {
        val uiState by viewModel.uiState.collectAsState()
        HomeScreen(
            amphibiansUiState = uiState,
            onRetry = viewModel::getAmphibians,
            modifier = Modifier.padding(it))
    }
}

@Composable
fun AmphibiansAppTopBar(modifier: Modifier = Modifier, scrollBehavior: TopAppBarScrollBehavior) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "Amphibians")
            }
        },
        modifier = modifier

    )
}