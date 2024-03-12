import com.varabyte.kobweb.gradle.application.extensions.AppBlock.LegacyRouteRedirectStrategy
import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.script

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.serialization.plugin)
}

group = "dev.amal.blogmultiplatform"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")

            head.add {
                script {
                    src = "/highlight.min.js"
                }
                link {
                    rel = "stylesheet"
                    href = "/github-dark.css"
                }
                script {
                    src = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                }
                link {
                    rel = "stylesheet"
                    href = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                }
                link(rel = "preconnect", href = "https://fonts.googleapis.com")

                link(rel = "preconnect", href = "https://fonts.gstatic.com") {
                    attributes["crossorigin"] = ""
                }
                link(
                    href = "https://fonts.googleapis.com/css2?family=Roboto&display=swap",
                    rel = "stylesheet"
                )
            }
        }
        legacyRouteRedirectStrategy.set(LegacyRouteRedirectStrategy.DISALLOW)
    }
}

kotlin {
    configAsKobwebApplication("blogmultiplatform", includeServer = true)

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(libs.kotlinx.serialization)
        }
        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            implementation(libs.kotlinx.serialization)
        }
        jvmMain.dependencies {
            compileOnly(libs.kobweb.api)
            implementation(libs.mongodb.kotlin.driver)
            implementation(libs.kotlinx.serialization)
        }
    }
}
