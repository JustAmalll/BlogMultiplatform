package dev.amal.blogmultiplatform.pages.admin.create.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.browser.file.loadDataUrlFromDisk
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import dev.amal.blogmultiplatform.components.Button
import dev.amal.blogmultiplatform.components.Input
import dev.amal.blogmultiplatform.models.JsTheme
import kotlinx.browser.document
import org.jetbrains.compose.web.css.px

@Composable
fun ThumbnailUploader(
    thumbnailInput: String,
    thumbnailInputDisabled: Boolean,
    onThumbnailInputChanged: (String) -> Unit,
    onThumbnailSelect: (String, String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .margin(bottom = 20.px)
            .height(54.px)
    ) {
        Input(
            modifier = Modifier
                .fillMaxSize()
                .margin(right = 12.px),
            value = thumbnailInput,
            onValueChanged = onThumbnailInputChanged,
            placeholder = "Thumbnail",
            disabled = thumbnailInputDisabled
        )
        Button(
            text = "Upload",
            bgColor = if (!thumbnailInputDisabled) JsTheme.Gray.rgb else JsTheme.Primary.rgb,
            textColor = if (!thumbnailInputDisabled) JsTheme.DarkGray.rgb else Colors.White,
            onClick = {
                document.loadDataUrlFromDisk(
                    accept = "image/png, image/jpeg",
                    onLoad = { onThumbnailSelect(filename, it) }
                )
            }
        )
    }
}