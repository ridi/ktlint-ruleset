package com.ridi.ktlint

import com.pinterest.ktlint.core.RuleSet
import com.pinterest.ktlint.core.RuleSetProvider

class RidiRuleSetProvider : RuleSetProvider {
    override fun get() = RuleSet("ridi", NoExclamationNotOperatorRule(), MultilineArgumentsRule())
}
