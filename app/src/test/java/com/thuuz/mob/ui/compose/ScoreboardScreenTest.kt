package com.thuuz.mob.ui.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel5)
class ScoreboardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun captureScoreboardScreen() {
        composeTestRule.setContent {
            ScoreboardScreenPreview()
        }
        
        composeTestRule.onRoot().captureRoboImage("app/build/outputs/roborazzi/ScoreboardScreen.png")
    }
}
