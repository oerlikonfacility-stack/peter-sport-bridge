package de.peter.sportbridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF27834A)
private val PaleGreen = Color(0xFFEAF4ED)
private val Ink = Color(0xFF1F2A23)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Green,
                    onPrimary = Color.White,
                    secondary = Green,
                    background = Color(0xFFF7F9F7),
                    surface = Color.White,
                    onSurface = Ink
                )
            ) {
                SportBridgeScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SportBridgeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Sport Bridge", fontWeight = FontWeight.Bold)
                        Text(
                            "DEIN SPORT. DEINE DATEN.",
                            fontSize = 10.sp,
                            color = Color.Gray,
                            letterSpacing = 1.2.sp
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F9F7))
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = PaleGreen),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(Icons.Default.Watch, contentDescription = null, tint = Green)
                        Column {
                            Text("Huawei Watch Fit 3", fontWeight = FontWeight.SemiBold)
                            Text("Datenverbindung noch nicht eingerichtet", color = Green, fontSize = 13.sp)
                        }
                    }
                }
            }
            item {
                Text("HEUTE", fontWeight = FontWeight.Bold, color = Color.Gray, fontSize = 12.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    MetricCard("Schritte", "—", "Noch keine Daten", Modifier.weight(1f), Icons.Default.DirectionsWalk)
                    MetricCard("Herzfrequenz", "—", "Noch keine Daten", Modifier.weight(1f), Icons.Default.Favorite)
                }
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    MetricCard("Kalorien", "—", "Noch keine Daten", Modifier.weight(1f), Icons.Default.FitnessCenter)
                    MetricCard("Ruhepuls", "—", "Noch keine Daten", Modifier.weight(1f), Icons.Default.Favorite)
                }
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(Modifier.padding(18.dp)) {
                        Text("Datenquellen", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(Modifier.height(10.dp))
                        StatusRow("Huawei Health", "Noch nicht verbunden")
                        StatusRow("Health Kit", "Entwicklerfreigabe ausstehend")
                        StatusRow("Direkter Bluetooth-Zugriff", "Wird später geprüft")
                    }
                }
            }
            item {
                Text(
                    "Dies ist der erste technische Prototyp. Es werden noch keine Gesundheitsdaten ausgelesen oder übertragen.",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}

@Composable
private fun MetricCard(
    title: String,
    value: String,
    subtitle: String,
    modifier: Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Icon(icon, contentDescription = null, tint = Green)
            Spacer(Modifier.height(12.dp))
            Text(value, fontSize = 27.sp, fontWeight = FontWeight.Bold, color = Ink)
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(subtitle, fontSize = 11.sp, color = Color.Gray)
        }
    }
}

@Composable
private fun StatusRow(label: String, status: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontWeight = FontWeight.Medium, fontSize = 13.sp)
        Text(status, color = Color.Gray, fontSize = 11.sp)
    }
}
