package com.d121211078.valorantkuy.ui.activity.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211078.valorantkuy.data.models.Agent
import com.d121211078.valorantkuy.ui.theme.ValorantKuyTheme

class DetailActivity : ComponentActivity() {

    private var selectedAgent: Agent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedAgent = intent.getParcelableExtra("AGENT")
        setContent {
            ValorantKuyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailScreen(selectedAgent!!)
                }
            }
        }
    }

    @Composable
    fun DetailScreen(selectedAgent: Agent) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Agent Poster
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(selectedAgent?.fullPortrait)
                        .crossfade(true)
                        .build(),
                    contentDescription = selectedAgent?.displayName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                // Back button
                IconButton(
                    onClick = { onBackPressed() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                // Like button
                IconButton(
                    onClick = { /* Handle like button click */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Like")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Agent Details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = selectedAgent?.displayName.toString(),
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = selectedAgent?.role?.displayName.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = selectedAgent?.description.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = selectedAgent?.role?.description.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ActionButton(Icons.Default.Info, "More Info") { /* Handle More Info click */ }
                    ActionButton(Icons.Default.Send, "Send Message") { /* Handle Send Message click */ }
                }
            }
        }
    }

    @Composable
    fun ActionButton(icon: ImageVector, label: String, onClick: () -> Unit) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.primary)
                .clip(MaterialTheme.shapes.medium)
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = Color.White)
        }
    }
}
