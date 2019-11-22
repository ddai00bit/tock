/*
 * Copyright (C) 2017/2019 e-voyageurs technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.tock.bot.definition

import ai.tock.bot.connector.ConnectorType
import ai.tock.bot.engine.BotDefinitionTest
import ai.tock.bot.engine.dialog.Dialog
import ai.tock.bot.engine.dialog.DialogState
import ai.tock.translator.I18nLabelValue
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.util.Locale
import kotlin.test.assertEquals
import kotlin.test.assertFalse

/**
 *
 */
class BotDefinitionTest {

    private val botDef = BotDefinitionTest()

    @Test
    fun `i18nTranslator() returns an I18nTranslator that use BotDefinition#i18nKeyFromLabel`() {
        val result = botDef.i18nTranslator(Locale.ENGLISH, ConnectorType.none).i18n("test")
        assertEquals(
            I18nLabelValue(
                "namespace_BotTest_test",
                "namespace",
                "bottest",
                "test"
            )
            , result
        )
    }

    @Test
    fun `GIVEN not notified action WHEN the intent is not a enable intent THEN the bot is not activated`() {
        val dialog = mockk<Dialog>()
        val state = DialogState()
        every { dialog.state } returns state
        val result = botDef.enableBot(mockk(), dialog, mockk())
        assertFalse(result)
    }
}