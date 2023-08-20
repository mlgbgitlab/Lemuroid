package com.swordfish.lemuroid.app.mobile.feature.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.swordfish.lemuroid.app.mobile.shared.compose.ui.LemuroidEmptyView
import com.swordfish.lemuroid.app.mobile.shared.compose.ui.LemuroidGameCard
import com.swordfish.lemuroid.lib.library.db.entity.Game

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onGameClick: (Game) -> Unit,
    onGameLongClick: (Game) -> Unit
) {
    val games = viewModel.favorites.collectAsLazyPagingItems()

    if (games.itemCount == 0) {
        LemuroidEmptyView()
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(144.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(games.itemCount, key = { games[it]?.id ?: -1 }) { index ->
            val game = games[index] ?: return@items
            LemuroidGameCard(
                game = game,
                onClick = { onGameClick(game) },
                onLongClick = { onGameLongClick(game) }
            )
        }
    }
}
