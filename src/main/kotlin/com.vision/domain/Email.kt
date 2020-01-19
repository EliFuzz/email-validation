package com.vision.domain

import com.vision.lib.ValueObject

data class Email(override val value: String) : ValueObject<String> {
    companion object {
        const val USERNAME_REGEX = "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*"
        const val DOMAIN_REGEX = "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
    }

    init {
        if (!Regex("${Email.USERNAME_REGEX}@${Email.DOMAIN_REGEX}").matches(value)) {
            throw InvalidEmailException(value)
        }
    }

    val domain get(): String = value.substringAfter("@")
}