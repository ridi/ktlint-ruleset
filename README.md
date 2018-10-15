# ktlint-ruleset

[![Build Status](https://travis-ci.org/ridi/ktlint-ruleset.svg?branch=master)](https://travis-ci.org/ridi/ktlint-ruleset)
[![](https://jitpack.io/v/ridi/ktlint-ruleset.svg)](https://jitpack.io/#ridi/ktlint-ruleset)

Custom ktlint ruleset for RIDI style guide.

This project was written with reference to [this information](https://github.com/shyiko/ktlint#creating-a-ruleset).

## Rules

### no-exclamation-not-operator

Force use `.not()` method instead of an exclamation mark prefix operator(`!`) for `logical not`.

### multiline-arguments

There should be line breaks both after the left parenthesis and before the right parenthesis in multiline arguments(except multiline by anonymous object literals or lambda expressions).
```kotlin
fun foo() {
    // Yes
    bar(1, 2, "", false)

    // No
    bar(
        1, 2, "", false)

    // Yes
    bar(
        1, 2, "", false
    )

    // No
    bar(1, 2,
        "", false)

    // Yes
    bar(
        1, 2,
        "", false
    )

    // No
    baz(bar(
        1, 2, "", false
    ))

    // Yes
    baz(
        bar(
            1, 2, "", false
        )
    )

    // Yes
    qux(object : A {
    }, 1, 2)

    // Yes
    qux(
        object : A {
        },
        1, 2
    )

    // No
    qux(object : A {
    },
    1, 2)

    // Yes
    quxx(1, 2, { _ ->
    })

    // Yes
    quxx(
        1, 2,
        { _ ->
        }
    )

    // No
    quxx(1, 2,
        { _ ->
        })

    // Yes
    quxx(1, 2, Thread { _ ->
    })
}
```
