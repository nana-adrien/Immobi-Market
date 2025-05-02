package empire.digiprem.ui.Screen.dashboard_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import composeApp.src.commonMain.ComposeResources.drawable.images
import org.jetbrains.compose.resources.painterResource


@Composable
fun RealEstateItem() {

    Box(
        modifier = Modifier.shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp)).background(Color.White)
            .clickable { }
            .padding(8.dp),
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

            val tabTemp = listOf(
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
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
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
                    modifier = Modifier.height(40.dp).weight(1f)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(5.dp))
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

data class Tag(
    val imageVector: ImageVector,
    val value: String
)


@Composable
fun RealEstateItem2() {


    val tabTemp = listOf(
        Tag(
            Icons.Default.Workspaces,
            "1000km2"
        ),
        Tag(
            Icons.Default.CarCrash,
            "100"
        ),
        Tag(
            Icons.Default.Phone,
            "10km"
        ),
        Tag(
            Icons.Default.Notifications,
            "2"
        ),
        Tag(
            Icons.Default.WifiProtectedSetup,
            "1"
        )
    )

    Box(
        modifier = Modifier.width(200.dp) .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp)).background(Color.White)
            .clickable { }
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.wrapContentSize()
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
                        modifier = Modifier.height(18.dp).padding(2.dp),
                        contentDescription = null,
                        tint = Color.Gray
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
                        modifier = Modifier.height(18.dp).padding(2.dp),
                        contentDescription = null,
                        tint = Color.Gray
                    )
                    Text(
                        "il y'a 2 minutes",
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp)
                    )
                }

            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.weight(0.4f).fillMaxWidth().padding(5.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "Terrain Titrer",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2
                    )
                }
                Row(
                    modifier = Modifier.weight(0.6f).fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    //Text("Acheter:", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "50.000cfa/m2",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth().height(200.dp).padding(bottom = 5.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp)).padding(vertical = 5.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.images),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    FlowRow(
                        modifier = Modifier.fillMaxWidth().background(Color.Black.copy(alpha = 0.5f))
                            .align(Alignment.BottomCenter).padding(5.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        tabTemp.forEach {
                            Row(
                                modifier = Modifier//.shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                                    .padding(2.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    it.imageVector,
                                    modifier = Modifier.height(20.dp).padding(2.dp),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                                Text(it.value, style = MaterialTheme.typography.bodySmall, color = Color.White)
                            }
                        }
                    }
                }
            }
            var expandedText by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth().clickable { expandedText = !expandedText },
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.background_immeuble),
                            contentDescription = "Photo de profil",
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .border(1.dp, Color.Gray, CircleShape)
                        )
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                text = "userName",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "~20yrs",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    color = Color.Gray
                                )
                            )
                        }
                    }
                    Icon(if (expandedText) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown, null)
                }
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Voici la description de l’article publié par l’utilisateur. Elle peut être un peu longue.Voici la description de l’article publié par l’utilisateur. Elle peut être un peu longue.Voici la description de l’article publié par l’utilisateur. Elle peut être un peu longue.",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = if (expandedText) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth().height(30.dp).padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f).shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                        .background(Color.White).padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.Handshake,
                        modifier = Modifier.padding(2.dp),
                        contentDescription = null,
                    )
                    Text("0", style = MaterialTheme.typography.bodyMedium)
                }
                Row(
                    modifier = Modifier.weight(1f).shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                        .background(Color.White).padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        modifier = Modifier.padding(2.dp),
                        contentDescription = null,
                    )
                    Text("0", style = MaterialTheme.typography.bodyMedium)
                }
                Row(
                    modifier = Modifier.weight(1f).shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                        .background(Color.White).padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("more...", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }

}