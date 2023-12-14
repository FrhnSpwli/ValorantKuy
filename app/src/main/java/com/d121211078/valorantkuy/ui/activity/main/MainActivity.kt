package com.d121211078.valorantkuy.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211078.valorantkuy.data.models.Agent
import com.d121211078.valorantkuy.ui.activity.detail.DetailActivity
import com.d121211078.valorantkuy.ui.theme.ValorantKuyTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantKuyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.background(Color.Blue),  // Change background color here
                            title = {
                                Text(
                                    text = "Valorant Agent",
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                        )
                        val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                        ListAgentsScreen(mainViewModel.mainUiState)
                    }

                }
            }
        }
    }

    @Composable
    private fun ListAgentsScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        when (mainUiState) {
            is MainUiState.Loading -> CenterText(text = "Loading...")
            is MainUiState.Error -> CenterText(text = "Something Error")
            is MainUiState.Success -> AgentList(mainUiState.agents)
        }
    }

    @Composable
    fun CenterText(text: String) {
        // Wrap the content with a Box to apply the centering modifiers
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Optional padding
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
            )
        }
    }

    @Composable
    fun AgentList(agents: List<Agent>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(agents) { agent ->
                AgentItem(agent = agent)
            }
        }
    }

    @Composable
    fun AgentItem(agent: Agent) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp),)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("AGENT", agent)
                    startActivity(intent)
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Agent Poster
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(agent.displayIcon)
                        .crossfade(true)
                        .build(), contentDescription = agent.displayName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                // Agent Details
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = agent.displayName.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = agent.role?.displayName.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = agent.description.toString(), style = MaterialTheme.typography.bodyMedium)

                // Other details like rating, genre, etc. can be added here
            }
        }
    }
}

