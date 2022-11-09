package com.tuxoo.too_memo.util

import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateTimeUtil {

    private const val formatPattern = "d MMM yyyy"
    val dateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(formatPattern).withZone(ZoneId.systemDefault())
}