package com.ridi.ktlint

import com.github.shyiko.ktlint.core.Rule
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.psiUtil.children

class MultilineArgumentsRule : Rule("multiline-arguments") {
    override fun visit(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit
    ) {
        if (node.elementType == KtNodeTypes.VALUE_ARGUMENT_LIST) {
            var checkNeeded = false
            var child = node.firstChildNode.treeNext
            while (child.elementType != KtTokens.RPAR) {
                if (child.textContains('\n') &&
                    child.firstChildNode?.elementType != KtNodeTypes.OBJECT_LITERAL &&
                    child.firstChildNode?.elementType != KtNodeTypes.LAMBDA_EXPRESSION &&
                    (child.firstChildNode?.elementType == KtNodeTypes.CALL_EXPRESSION &&
                        child.firstChildNode.children().any { it.elementType == KtNodeTypes.LAMBDA_ARGUMENT }).not()) {
                    checkNeeded = true
                    break
                }
                child = child.treeNext
            }

            if (checkNeeded) {
                val afterLeftParenthesis = node.firstChildNode.treeNext
                if ((afterLeftParenthesis is PsiWhiteSpace && afterLeftParenthesis.textContains('\n')).not()) {
                    emit(
                        afterLeftParenthesis.startOffset,
                        "No line break after the left parenthesis in multiline arguments.", false
                    )
                }

                var beforeRightParenthesis = afterLeftParenthesis
                while (beforeRightParenthesis.treeNext.elementType != KtTokens.RPAR) {
                    beforeRightParenthesis = beforeRightParenthesis.treeNext
                }
                if ((beforeRightParenthesis is PsiWhiteSpace && beforeRightParenthesis.textContains('\n')).not()) {
                    emit(
                        beforeRightParenthesis.treeNext.startOffset - 1,
                        "No line break before the right parenthesis in multiline arguments.", false
                    )
                }
            }
        }
    }
}
