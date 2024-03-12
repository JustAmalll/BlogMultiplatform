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
    val popular: Boolean = false,
    val main: Boolean = false,
    val sponsored: Boolean = false,
    val editorVisibility: Boolean = true,
    val messagePopup: Boolean = false,
    val linkPopup: Boolean = false,
    val imagePopup: Boolean = false
)