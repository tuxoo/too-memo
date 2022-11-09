package com.tuxoo.too_memo.model.notes.entity

import java.time.Instant

data class Note(
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: Instant,
    val lastModified: Instant
)
