package dev.amal.blogmultiplatform.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.rememberPageContext
import dev.amal.blogmultiplatform.models.ControlStyle
import dev.amal.blogmultiplatform.models.EditorControl
import dev.amal.blogmultiplatform.navigation.Screen
import kotlinx.browser.document
import kotlinx.browser.localStorage
import org.w3c.dom.Element
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.get

@Composable
fun isUserLoggedIn(content: @Composable () -> Unit) {
    val context = rememberPageContext()
    var isUserExists by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        val userId = localStorage["userId"]
        isUserExists = !userId.isNullOrEmpty() && checkUserId(id = userId)

        if (!isUserExists) {
            context.router.navigateTo(Screen.AdminLogin.route)
        }
    }

    if (isUserExists) {
        content()
    }
}

fun logout() {
    localStorage.removeItem("userId")
    localStorage.removeItem("username")
}

fun getTextAreaValueById(id: String): String =
    (document.getElementById(elementId = id) as HTMLTextAreaElement).value

fun getSelectedIntRange(editor: HTMLTextAreaElement): IntRange? {
    val start = editor.selectionStart ?: return null
    val end = editor.selectionEnd ?: return null
    return IntRange(start = start, endInclusive = (end - 1))
}

fun getSelectedText(editor: HTMLTextAreaElement): String? {
    val range = getSelectedIntRange(editor = editor) ?: return null
    return editor.value.substring(range)
}

fun applyStyle(
    editor: HTMLTextAreaElement,
    editorPreview: Element,
    controlStyle: ControlStyle
) {
    editor.value = editor.value.replaceRange(
        range = getSelectedIntRange(editor = editor) ?: return,
        replacement = controlStyle.style
    )
    editorPreview.innerHTML = editor.value
}

fun applyControlStyle(
    editorControl: EditorControl,
    editor: HTMLTextAreaElement,
    editorPreview: Element,
    onLinkClick: () -> Unit,
    onImageClick: () -> Unit
) {
    val selectedText = getSelectedText(editor = editor)

    val applyStyle: (ControlStyle) -> Unit = {
        applyStyle(editor = editor, editorPreview = editorPreview, controlStyle = it)
    }

    when (editorControl) {
        EditorControl.Bold -> applyStyle(ControlStyle.Bold(selectedText = selectedText))
        EditorControl.Italic -> applyStyle(ControlStyle.Italic(selectedText = selectedText))
        EditorControl.Link -> onLinkClick()
        EditorControl.Title -> applyStyle(ControlStyle.Title(selectedText = selectedText))
        EditorControl.Subtitle -> applyStyle(ControlStyle.Subtitle(selectedText = selectedText))
        EditorControl.Quote -> applyStyle(ControlStyle.Quote(selectedText = selectedText))
        EditorControl.Code -> applyStyle(ControlStyle.Code(selectedText = selectedText))
        EditorControl.Image -> onImageClick()
    }
}