package com.ridi.ktlint

import com.github.shyiko.ktlint.core.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.lexer.KtSingleValueToken

class NoExclamationNotOperatorRule : Rule("no-exclamation-not-operator") {
    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node is LeafPsiElement && node.textMatches("!") && node.elementType is KtSingleValueToken) {
            emit(node.startOffset, "Unexpected !, use .not() instead", false)
        }
    }
}
