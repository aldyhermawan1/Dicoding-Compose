package com.hermawan.composestarter.simpletesting.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.hermawan.composestarter.R
import com.hermawan.composestarter.temperature.ui.theme.ComposeStarterTheme
import org.junit.*

class CalculatorAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ComposeStarterTheme {
                CalculatorApp()
            }
        }
    }

    @Test
    fun calculate_area_of_rectangle_correct() {
        with(composeTestRule) {
            onNodeWithText(activity.getString(R.string.enter_length)).performTextInput("3")
            onNodeWithText(activity.getString(R.string.enter_width)).performTextInput("4")
            onNodeWithText(activity.getString(R.string.count)).performClick()
            onNodeWithText(activity.getString(R.string.result, "12.0")).assertExists()
        }
    }

    @Test
    fun wrong_input_not_calculated() {
        with(composeTestRule) {
            onNodeWithText(activity.getString(R.string.enter_length)).performTextInput("..3")
            onNodeWithText(activity.getString(R.string.enter_width)).performTextInput("4")
            onNodeWithText(activity.getString(R.string.count)).performClick()
            onNodeWithText(activity.getString(R.string.result, "0.0")).assertExists()
        }
    }
}