package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    amphibiansUiState: AmphibiansUiState,
    onRetry: () -> Unit
) {

    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Success -> AmphibiansColumnScreen(
            amphibianList = amphibiansUiState.amphibianList,
            modifier = modifier.fillMaxSize()
        )

        is AmphibiansUiState.Error -> ErrorScreen(
            modifier = modifier.fillMaxSize(),
            onRetry = onRetry
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading)
    )
}

@Composable
fun ErrorScreen(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = onRetry) {
            Text(text = stringResource(id = R.string.retry))
        }

    }

}

@Preview(showBackground = true)
@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier
) {

}

@Composable
fun AmphibianCard(
    modifier: Modifier = Modifier,
    amphibian: Amphibian
) {
    Card(modifier = modifier) {

        Column(

        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${amphibian.name}(${amphibian.type})",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()

            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = amphibian.description, modifier = Modifier.padding(14.dp), textAlign = TextAlign.Justify
                )
            }

        }
    }
}

@Composable
fun AmphibiansColumnScreen(
    modifier: Modifier = Modifier,
    amphibianList: List<Amphibian>,
    contentPadding: PaddingValues = PaddingValues(0.dp)

) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),

        modifier = modifier,
        contentPadding = contentPadding

    ) {
        items(items = amphibianList, key = { amphibian -> amphibian.name }) {
            AmphibianCard(
                amphibian = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

    }
}