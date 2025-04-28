package empire.digiprem.ui.Screen.dashboard_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.images
import org.jetbrains.compose.resources.painterResource


@Composable
fun RealEstateItem() {

    Box(
        modifier = Modifier.shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp)).background(Color.White)
            .padding(8.dp).clickable {  },
    ) {
        Column(
            modifier = Modifier.wrapContentSize().width(300.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(0.6f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        modifier = Modifier.height(20.dp).padding(2.dp),
                        contentDescription = null,
                    )
                    Text(
                        "Douala-Ndogbong ",
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(
                    modifier = Modifier.weight(0.4f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.CalendarToday,
                        modifier = Modifier.height(20.dp).padding(2.dp),
                        contentDescription = null,
                    )
                    Text("10/20/25", overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodySmall)
                }


            }

            Column(
                modifier = Modifier.fillMaxWidth().height(250.dp).padding(vertical = 5.dp),
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                    Text(
                        text = "Terrain Titrer",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text("Acheter:", style = MaterialTheme.typography.bodyMedium)
                    Text("50.000cfa/m2", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                }
                Box(
                    modifier = Modifier.fillMaxSize().padding(vertical = 5.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.images),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }

            val tabTemp= listOf(
                Icons.Default.Workspaces,
                Icons.Default.CarCrash,
                Icons.Default.Phone,
                Icons.Default.Notifications,
                Icons.Default.WifiProtectedSetup,
                Icons.Default.Send,
            )
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth().height(70.dp).padding(2.dp),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(5.dp) ,
                verticalArrangement =  Arrangement.spacedBy(5.dp)
            ){
                items(tabTemp.size) {
                    Row(
                        modifier = Modifier//.shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            tabTemp[it],
                            modifier = Modifier.height(25.dp).padding(2.dp),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text("{$it}00m2", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().height(50.dp).padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f).shadow(elevation = 10.dp, shape = RoundedCornerShape(5.dp))
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.Handshake,
                        modifier = Modifier.height(40.dp).padding(2.dp),
                        contentDescription = null,
                    )
                    Text("0", style = MaterialTheme.typography.bodyMedium)
                }

                Row(
                    modifier = Modifier.weight(1f).shadow(elevation = 10.dp, shape = RoundedCornerShape(5.dp))
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                ) {
                    Icon(
                        Icons.Default.Favorite,
                        modifier = Modifier.height(40.dp).padding(2.dp),
                        contentDescription = null,
                    )
                }

                Row(
                    modifier =Modifier.height(40.dp).weight(1f).shadow(elevation = 10.dp, shape = RoundedCornerShape(5.dp))
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("more...", style = MaterialTheme.typography.bodyMedium)
                }

            }
        }
    }

}