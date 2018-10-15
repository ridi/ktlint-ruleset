package com.ridi.ktlint

import com.github.shyiko.ktlint.core.LintError
import com.github.shyiko.ktlint.test.lint
import org.assertj.core.api.Assertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class MultilineArgumentsRuleTest : Spek({
    describe("multiline-arguments rule") {
        // whenever KTLINT_DEBUG env variable is set to "ast" or -DktlintDebug=ast is used
        // com.github.shyiko.ktlint.test.(lint|format) will print AST (along with other debug info) to the stderr.
        // this can be extremely helpful while writing and testing rules.
        // uncomment the line below to take a quick look at it
        // System.setProperty("ktlintDebug", "ast")

        val rule = MultilineArgumentsRule()

        it(
            """
            should check line breaks both after the left parenthesis and
            before the right parenthesis in multiline arguments.
            """.trimIndent()
        ) {
            Assertions.assertThat(
                rule.lint(
                    """
                    fun foo() {
                        bar(1, 2, "", false)
                        bar(
                            1, 2, "", false)
                        bar(
                           1, 2, "", false
                        )
                        bar(1, 2,
                            "", false)
                        bar(
                            1, 2,
                            "", false
                            )
                        baz(bar(
                            1, 2, "", false
                        ))
                        baz(
                            bar(
                                1, 2, "", false
                            )
                        )
                        qux(object : A {
                        }, 1, 2)
                        qux(
                            object : A {
                            },
                            1, 2
                        )
                        qux(object : A {
                        },
                        1, 2)
                        quxx(1, 2, { _ ->
                        })
                        quxx(
                            1, 2,
                            { _ ->
                            }
                        )
                        quxx(1, 2,
                            { _ ->
                            })
                        quxx(1, 2, Thread { _ ->
                        })
                    }
                    """.trimIndent()
                )
            ).isEqualTo(
                listOf(
                    LintError(4, 23, rule.id, "No line break before the right parenthesis in multiline arguments."),
                    LintError(8, 9, rule.id, "No line break after the left parenthesis in multiline arguments."),
                    LintError(9, 17, rule.id, "No line break before the right parenthesis in multiline arguments."),
                    LintError(14, 9, rule.id, "No line break after the left parenthesis in multiline arguments."),
                    LintError(16, 5, rule.id, "No line break before the right parenthesis in multiline arguments."),
                    LintError(29, 9, rule.id, "No line break after the left parenthesis in multiline arguments."),
                    LintError(31, 8, rule.id, "No line break before the right parenthesis in multiline arguments."),
                    LintError(39, 10, rule.id, "No line break after the left parenthesis in multiline arguments."),
                    LintError(41, 9, rule.id, "No line break before the right parenthesis in multiline arguments.")
                )
            )
        }
    }
})
