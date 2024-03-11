package dev.amal.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Input
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import dev.amal.blogmultiplatform.components.AdminPageLayout
import dev.amal.blogmultiplatform.components.LabeledSwitch
import dev.amal.blogmultiplatform.models.JsTheme
import dev.amal.blogmultiplatform.util.Constants.FONT_FAMILY
import dev.amal.blogmultiplatform.util.Constants.SIDE_PANEL_WIDTH
import dev.amal.blogmultiplatform.util.isUserLoggedIn
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun CreatePage() {
    isUserLoggedIn {
        CreateScreen()
    }
}

@Composable
fun CreateScreen() {
    val breakpoint = rememberBreakpoint()

    AdminPageLayout {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .margin(topBottom = 50.px)
                .padding(left = if (breakpoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .maxWidth(700.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SimpleGrid(numColumns = numColumns(base = 1, sm = 3)) {
                    LabeledSwitch(
                        modifier = Modifier.margin(
                            right = 24.px,
                            bottom = if (breakpoint < Breakpoint.SM) 12.px else 0.px
                        ),
                        checked = false,
                        onCheckedChange = {},
                        text = "Popular"
                    )
                    LabeledSwitch(
                        modifier = Modifier.margin(
                            right = 24.px,
                            bottom = if (breakpoint < Breakpoint.SM) 12.px else 0.px
                        ),
                        checked = false,
                        onCheckedChange = {},
                        text = "Main"
                    )
                    LabeledSwitch(
                        modifier = Modifier.margin(
                            bottom = if (breakpoint < Breakpoint.SM) 12.px else 0.px
                        ),
                        checked = false,
                        onCheckedChange = {},
                        text = "Sponsored"
                    )
                }
                Input(
                    type = InputType.Text,
                    value = "",
                    onValueChanged = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.px)
                        .padding(leftRight = 20.px)
                        .backgroundColor(JsTheme.LightGray.rgb)
                        .borderRadius(r = 4.px)
                        .fontFamily(FONT_FAMILY)
                        .fontSize(16.px),
                    placeholder = "Subtitle"
                )
            }
        }
    }
}