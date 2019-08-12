package com.ridi.ktlint

import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.test.lint
import org.assertj.core.api.Assertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class NoExclamationNotOperatorRuleTest : Spek({
    describe("no-exclamation-not-operator rule") {
        // whenever KTLINT_DEBUG env variable is set to "ast" or -DktlintDebug=ast is used
        // com.pinterest.ktlint.test.(lint|format) will print AST (along with other debug info) to the stderr.
        // this can be extremely helpful while writing and testing rules.
        // uncomment the line below to take a quick look at it
        // System.setProperty("ktlintDebug", "ast")

        val rule = NoExclamationNotOperatorRule()

        it("should prohibit usage of ! operator") {
            Assertions.assertThat(
                rule.lint(
                    """
                    fun fn() {
                       val a = true
                       val b = !a
                       val c = a != b
                       val d: String? = ""
                       val e = d!!
                       val f = "!"
                    }
                    """.trimIndent()
                )
            ).isEqualTo(listOf(LintError(3, 12, rule.id, "Unexpected !, use .not() instead")))
        }
    }
})
