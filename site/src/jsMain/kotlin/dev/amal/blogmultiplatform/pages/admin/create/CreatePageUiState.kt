package dev.amal.blogmultiplatform.pages.admin.create

import dev.amal.blogmultiplatform.models.Category

data class CreatePageUiState(
    val id: String = "",
    val title: String = "",
    val subtitle: String = "",
    val thumbnailInput: String = "",
    val thumbnail: String = "",
    val thumbnailInputDisabled: Boolean = true,
    val content: String = "",
    val category: Category = Category.Programming,
    val buttonText: String = "Create",
    val popular: Boolean = false,
    val main: Boolean = false,
    val sponsored: Boolean = false,
    val editorVisibility: Boolean = true,
    val messagePopup: Boolean = false,
    val linkPopup: Boolean = false,
    val imagePopup: Boolean = false
) {
    fun reset() = copy(
        id = "",
        title = "",
        subtitle = "",
        thumbnail = "",
        content = "",
        category = Category.Programming,
        buttonText = "Create",
        main = false,
        popular = false,
        sponsored = false,
        editorVisibility = true,
        messagePopup = false,
        linkPopup = false,
        imagePopup = false
    )
}