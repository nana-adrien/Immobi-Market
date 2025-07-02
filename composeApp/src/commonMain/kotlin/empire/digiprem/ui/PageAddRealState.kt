package empire.digiprem.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import composeApp.src.commonMain.ComposeResources.drawable.IM_Plan_de_travail_1
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.config.isCompactApplication
import empire.digiprem.model.AppFile
import empire.digiprem.presentation.components.AppIconActionButton
import empire.digiprem.presentation.components.AppTextField
import empire.digiprem.presentation.components.forms.*
import empire.digiprem.presentation.views.AppCardWrapperEx
import empire.digiprem.presentation.views.SelectContentTextField
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun PageAddRealStatePreview() {
    AddRealStateForm()
}

@Composable
fun AddRealStateForm() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {

        item {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                AddRealStateFormItem1()
                AddRealStateFormItem2()
            }

        }
        item {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Box(modifier = Modifier.weight(1f)){
                    AddRealStateFormItem3()
                }
                Box(modifier = Modifier.weight(1f)){
                    AddRealStateFormItem5()
                }

            }
        }
        item {
            AddRealStateFormItem4()
        }
        item {
            Spacer(Modifier.height(20.dp))
        }


    }

}


@Composable
fun AddRealStateFormItem1() {
    var selectOption by remember { mutableStateOf("bonjour") }
    var selectOption2 by remember { mutableStateOf("bonjour") }
    var value by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("0.0") }
    var value3 by remember { mutableStateOf("0.0") }
    var enableMenuOption by remember { mutableStateOf(false) }
    var enableMenuOption2 by remember { mutableStateOf(false) }


    FormContentWrapper("Texte 1") {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AppTextField(
                label = { Text("Titre de l'annonce") },
                value = value,
                onValueChange = { value = it }
            )
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    SelectContentTextField(
                        enabledMenu = enableMenuOption2,
                        onDismissRequest = { enableMenuOption2 = !enableMenuOption2 },
                        selected = selectOption2,
                        options = listOf("bonjour", "le", "monde"),
                        onValueChange = { selectOption2 = it }
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    SelectContentTextField(
                        enabledMenu = enableMenuOption,
                        onDismissRequest = { enableMenuOption = !enableMenuOption },
                        selected = selectOption,
                        options = listOf("bonjour", "le", "monde"),
                        onValueChange = { selectOption = it }
                    )
                }


            }
            AppTextField(
                label = { Text("Montant en FCFA") },
                trailingIcon = { Text("FCFA") },
                value = value3,
                onValueChange = { value3 = it }
            )
            EdithTextField(
                label = "Superficie en m2",
                value = value2,
                onValueChange = { value2 = it },
                trailingIcon = { Text("m2") },
            )
            EdithTextField(
                label = "Superficie en m2",
                enabledModification = false,
                value = value2,
                onValueChange = { value2 = it },
                trailingIcon = { Text("m2") },
            )
        }
    }
}

@Composable
fun AddRealStateFormItem2() {
    var value1 by remember { mutableStateOf(0) }
    var value2 by remember { mutableStateOf(0) }
    var value3 by remember { mutableStateOf(0) }
    FormContentWrapper(
        title = "caracteristiquue"
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            IncrementeDecrementValue(
                label = "nombre de pieces", value1, enabledModification = false,
            ) {
                value1 = it

            }
            IncrementeDecrementValue(

                label = "nombre de pieces", value1
            ) {
                value1 = it

            }
            IncrementeDecrementValue(label = "nombre de chambre", value2) {
                value2 = it

            }
            IncrementeDecrementValue(label = "nombre de pieces", value2, enabledModification = false) {
                value2 = it

            }
            IncrementeDecrementValue(label = " nombree de salon ", value3) {
                value3 = it
            }
        }
    }
}

@Composable
fun AddRealStateFormItem3() {
    var value1 by remember { mutableStateOf(false) }
    var value2 by remember { mutableStateOf(false) }
    var value3 by remember { mutableStateOf(false) }
    var selectOption by remember { mutableStateOf("bonjour") }
    FormContentWrapper(
        title = "Equipement"
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SwitchTextField(
                enabled = true,
                label = "Add Real State",
                value = value1,
                onValueChange = { value1 = it }
            )
            CheckTextField(
                enabled = true,
                label = "Add Real State",
                value = value3,
                onValueChange = { value3 = it }
            )
            CheckTextField(
                enabled = true,
                label = "Add Real State",
                value = value3,
                onValueChange = { value3 = it }
            )
            SwitchTextField(
                enabled = true,
                label = "Add Real State",
                value = value2,
                onValueChange = { value2 = it }
            )
            SwitchTextField(
                enabled = true,
                label = "Add Real State",
                value = value2,
                enabledModification = false,
                onValueChange = { value2 = it }
            )
            RadioTextField(
                enabled = true,
                label = "select",
                selectedOption = selectOption,
                option = listOf("bonjour", "le", "monde"),
                onSelectOption = { selectOption = it },
            )
            RadioTextField(
                enabled = true,
                label = "select",
                enabledModification = false,
                selectedOption = selectOption,
                option = listOf("bonjour", "le", "monde"),
                onSelectOption = { selectOption = it },
            )
        }
    }
}

@Composable
fun AddRealStateFormItem4() {
    var selectedImage by remember { mutableStateOf<Any?>(null) }
    var images by remember { mutableStateOf<MutableList<Any>>(mutableListOf(Res.drawable.background_immeuble)) }

    FormContentWrapper(
        title = "Images"
    ) {
        FormSelectImages(
            selectedImage = selectedImage,
            images = images,
            onSelectImages = {
                images = (images + it.toMutableList()).toMutableList()
            },
            onRemoveImage = {
                it?.let {
                    images = (images - it).toMutableList()
                }
            },
            onSelectedImageChange = {
                selectedImage = it
            }
        )
    }
}

@Composable
fun AddRealStateFormItem5() {

    var selectedOption by remember { mutableStateOf(listOf("MAISON")) }
    FormContentWrapper(
        title = "Equipement"
    ) {
        SelectMultipOptions(
            options = listOf(
                Option("MAI1SON7", "Maison"),
                Option("1CHAMBRE", "Chambre"),
                Option("APPAR1TEMENT", "Appartement"),
                Option("MAI1SONr", "Maison"),
                Option("CHA1MBRrE", "Chambre"),
                Option("APPA1EMaENT", "Appartement"),
                Option("MAISONa", "Maison"),
                Option("CHAMBc1RE", "Chambre"),
                Option("APPARTHE1MENT", "Appartement"),
                Option("M1A4ISONH", "Maison"),
                Option("CHA8MB4RE", "Chambre"),
                Option("APPAR1TEMENT", "Appartement"),
                Option("M1AIS7ON", "Maison"),
                Option("CHAM1BRE", "Chambre"),
                Option("APPA7RTE1MENT", "Appartement"),
                Option("MAI4SON1", "Maison"),
                Option("CHAM15BRE", "Chambre"),
                Option("AP1PAR8TEMENT", "Appartement"),
                Option("MAI8SON", "Maison"),
                Option("CH8AMBR1E", "Chambre"),
                Option("APPA1RTEM8ENT", "Appartement"),
            ),
            selectedOptions = selectedOption,
            onSelectOption = {
                if (selectedOption.contains(it)) {
                    selectedOption -= it
                } else {
                    selectedOption += it
                }
            }
        )
    }
}
