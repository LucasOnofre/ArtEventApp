package com.onoffrice.artevent.utils

import android.text.Html

fun String.htmlToString() = Html.fromHtml(
    this,
    Html.FROM_HTML_MODE_COMPACT
).toString()