package com.ridi.ktlint

import com.github.shyiko.ktlint.core.RuleSet
import com.github.shyiko.ktlint.core.RuleSetProvider

class RidiRuleSetProvider : RuleSetProvider {
    override fun get() = RuleSet("ridi", NoExclamationNotOperatorRule(), MultilineArgumentsRule())
}
