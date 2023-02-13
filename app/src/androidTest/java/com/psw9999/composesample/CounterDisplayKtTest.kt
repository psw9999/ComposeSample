package com.psw9999.composesample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.psw9999.composesample.ui.theme.ComposeSampleTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterDisplayKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ComposeSampleTheme {
                CounterDisplay(modifier = Modifier.fillMaxSize().background(Color.Black))
            }
        }
    }

    @Test
    fun 모든_뷰가_정상적으로_존재하는가() {
        composeTestRule.onNodeWithTag("Counter Display").assertExists()
        composeTestRule.onNodeWithTag("Input").assertExists()
        composeTestRule.onNodeWithText("Copy").assertExists()
    }

    @Test
    fun 빈_값_입력시_허용하지_않는_값으로_출력되는지_확인() {
        composeTestRule.onNodeWithText("Copy").performClick()
        composeTestRule.onNodeWithTag("Counter Display").assertTextEquals("Invalid entry")
    }

    @Test
    fun 입력값_1_입력하고_버튼_클릭시_정상적으로_출력되는가() {
        composeTestRule.onNodeWithTag("Input").performTextInput("1")
        composeTestRule.onNodeWithText("Copy").performClick()
        composeTestRule.onNodeWithTag("Counter Display").assertTextContains("Counter = 1")
    }

    @Test
    fun 입력값_abc_입력하고_버튼_클릭시_정상적으로_출력되는가() {
        composeTestRule.onNodeWithTag("Input").performTextInput("abc")
        composeTestRule.onNodeWithText("Copy").performClick()
        composeTestRule.onNodeWithTag("Counter Display").assertTextEquals("Invalid entry")
    }

}