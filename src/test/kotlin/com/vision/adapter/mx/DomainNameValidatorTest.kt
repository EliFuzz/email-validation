package com.vision.adapter.mx

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DomainNameValidatorTest {
    private val domainNameValidator = DomainNameValidator()

    @Test
    fun validatesValidDomain() = runBlocking {
        val res = domainNameValidator.validate("gmail.com")
        assertTrue { res }
    }

    @Test
    fun validatesInvalidDomain() = runBlocking {
        val res = domainNameValidator.validate("gmail.comc")
        assertFalse { res }
    }
}