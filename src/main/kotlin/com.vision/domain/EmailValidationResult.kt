package com.vision.domain

data class EmailValidationResult(
        val email: String,
        val validInput: Boolean,
        val validDomain: Boolean? = null
)