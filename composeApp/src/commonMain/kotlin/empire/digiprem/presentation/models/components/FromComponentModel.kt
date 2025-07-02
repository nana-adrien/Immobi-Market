package empire.digiprem.presentation.models.components

import empire.digiprem.presentation.components.forms.Option


data class CheckTextFieldData(
    val label: String,
    val value: Boolean,
    val enabled: Boolean = true,
    val enabledModification: Boolean = true
)
data class RadioTextFieldData(
    val label: String,
    val selectedOption: String,
    val option: List<String>,
    val enabled: Boolean = true,
    val enabledModification: Boolean = true
)
data class FormSelectImagesData(
    val selectedImage: Any? = null,
    val images: List<Any> = emptyList()
)
data class FormTextFieldWrapperData(
    val label: String = "",
    val enabledModification: Boolean = true,
    val enabledClick: Boolean = true
)
data class IncrementDecrementValueData(
    val label: String,
    val value: Int = 0,
    val minValue: Int = 0,
    val maxValue: Int = 100,
    val enabledModification: Boolean = true
)
data class EdithTextFieldData(
    val label: String,
    val value: String,
    val enabledModification: Boolean = true,
    val enabled: Boolean = true
)
data class SwitchTextFieldData(
    val label: String,
    val value: Boolean,
    val enabledModification: Boolean = true,
    val enabled: Boolean = true
)
data class FormContentWrapperData(
    val title: String = ""
)
data class SelectMultipleOptionsData(
    val enabledModification: Boolean = true,
    val options: List<Option> = emptyList(),
    val selectedOptions: List<String> = emptyList()
)
