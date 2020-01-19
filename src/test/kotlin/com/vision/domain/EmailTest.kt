package com.vision.domain

import org.junit.Test
import kotlin.test.assertEquals

class EmailTest {
    private val testEmail = "test@gmail.com"

    @Test
    fun itCanBeCreatedUsingAValidEmailAddress() {
        val email = Email(testEmail)
        assertEquals(email.value, testEmail)
    }

    @Test(expected = InvalidEmailException::class)
    fun itThrowsAnExceptionWhenEmailAddressIsInvalid() {
        Email("test")
    }

    @Test(expected = InvalidEmailException::class)
    fun itThrowsAnExceptionWhenEmailAddressIsEmpty() {
        Email("")
    }

    @Test
    fun itSuppliesDomainNames() {
        val email = Email(testEmail)
        assertEquals(email.domain, "gmail.com")
    }
}