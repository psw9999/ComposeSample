package com.psw9999.composesample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
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
}