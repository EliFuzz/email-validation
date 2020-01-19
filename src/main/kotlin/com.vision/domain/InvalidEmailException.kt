package com.vision.domain

class InvalidEmailException(value: String): IllegalArgumentException("$value invalid")