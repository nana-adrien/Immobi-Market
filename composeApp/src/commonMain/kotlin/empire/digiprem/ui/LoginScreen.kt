package empire.digiprem.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composeApp.src.commonMain.ComposeResources.drawable.*
import empire.digiprem.config.getActualWindowsSize
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {

    var boxHeight = 550.dp
    var boxWidth = 650.dp
    when (getActualWindowsSize().widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            boxHeight = 550.dp
            boxWidth = 350.dp
        }

        WindowWidthSizeClass.Medium -> {
            boxHeight = 500.dp
            boxWidth = 550.dp
        }
    }


    Box(
        Modifier.sizeIn(
            minWidth = 500.dp,
            minHeight = 800.dp
        ).background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.images),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        LazyColumn(
            modifier=Modifier.fillMaxSize()
        ){
            item {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent),
                    navigationIcon = {

                    },
                    title = {},
                    actions = {
                        Button(onClick = {}) {
                            Icon(Icons.Filled.Person, contentDescription = "")
                            Text("Register")
                        }
                    }
                )
            }
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier.wrapContentHeight().width(boxWidth)
                            .clip(RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.wrapContentHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (getActualWindowsSize().widthSizeClass != WindowWidthSizeClass.Compact) {
                                    Box(
                                        modifier = Modifier.weight(0.4f),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(Res.drawable.compose_multiplatform),
                                            contentDescription = ""
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier.weight(0.6f)
                                        .background(Color.White.copy(alpha = 0.8f)).padding(30.dp),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.spacedBy(20.dp),
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(7.dp)
                                    ) {
                                        Text(
                                            text = "Login to your account",
                                            style = TextStyle(
                                                fontSize = 25.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(
                                                5.dp,
                                                alignment = Alignment.CenterHorizontally
                                            )
                                        ) {
                                            Text(
                                                text = "Don't have an account?",
                                                style = TextStyle(fontSize = 13.sp)
                                            )
                                            Text(
                                                text = "Sign Up",
                                                color = MaterialTheme.colorScheme.primary,
                                                style = TextStyle(
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(50.dp).padding(6.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "Cependant, pour une gestion plus avancée et optimisée des layouts adaptatifs, ",
                                            color = Color.Red,
                                            style = TextStyle(fontSize = 13.sp)
                                        )
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        AppTextField()
                                        AppTextField()
                                        Row(
                                            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)
                                                .padding(top = 5.dp, bottom = 10.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Row(
                                                modifier = Modifier.weight(0.5f),
                                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Checkbox(
                                                    modifier = Modifier.size(10.dp),
                                                    checked = true,
                                                    onCheckedChange = {

                                                    }
                                                )
                                                Text("Remember me", style = TextStyle(fontSize = 12.sp))
                                            }
                                            Row(
                                                modifier = Modifier.weight(0.5f),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.End
                                            ) {
                                                Text(
                                                    "Mot de passe oublier ?",
                                                    style = TextStyle(fontSize = 12.sp),
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        }
                                    }
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {

                                        Box(
                                            modifier = Modifier.fillMaxWidth().height(45.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(MaterialTheme.colorScheme.primary).clickable {

                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text("Login with email", color = Color.White)
                                        }

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            HorizontalDivider(modifier = Modifier.weight(0.4f))
                                            Box(
                                                modifier = Modifier.weight(0.2f),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text("Or", color = Color.Gray)
                                            }
                                            HorizontalDivider(modifier = Modifier.weight(0.4f))
                                        }
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceAround
                                        ) {
                                            AppIconButton(
                                                painter = painterResource(Res.drawable.google),
                                                contentDescription = "",
                                                onclick = {

                                                })
                                            AppIconButton(
                                                painter = painterResource(Res.drawable.facebook),
                                                contentDescription = "",
                                                onclick = {

                                                })
                                            AppIconButton(
                                                painter = painterResource(Res.drawable.apple),
                                                contentDescription = "",
                                                onclick = {

                                                })
                                        }
                                    }

                                }
                            }

                        }
                    }
                }
            }
        }
    }

}

@Composable
private fun AppIconButton(
    painter: Painter,
    contentDescription: String,
    onclick: () -> Unit,
) {
    Box(
        Modifier.size(40.dp).clip(CircleShape)
            .background(Color.White)
            .clickable(onClick = onclick)
            .padding(7.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun AppTextField() {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(Color.White)
            .border(width = 0.3.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.width(45.dp),
        ) {
            IconButton(
                onClick = {
                }) {
                Icon(Icons.Default.Person, contentDescription = "")
            }
        }
        BasicTextField(
            modifier = Modifier.weight(1f).wrapContentHeight(),
            value = "bonjour le monde",
            onValueChange = {}
        )
        Box(
            Modifier.width(50.dp),
        ) {
            IconButton(onClick = {

            }) {

            }
        }
    }
}