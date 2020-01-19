package com.vision

import com.vision.adapter.ktor.ValueObjectSerializer
import com.vision.adapter.mx.DomainNameValidator
import com.vision.domain.Email
import com.vision.domain.EmailValidationResult
import com.vision.domain.InvalidEmailException
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
fun Application.main() {
    val domainNameValidator = DomainNameValidator()

    install(StatusPages) {
        exception<Throwable> { e ->
            call.respondText(e.toString(),
                    ContentType.Text.Plain, HttpStatusCode.InternalServerError)
        }
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            registerTypeAdapter(Email::class.java, ValueObjectSerializer)
        }
    }

    install(CallLogging)

    routing {
        get("/") {
            call.respondText("vision", ContentType.Application.Json)
        }

        route("/api/validate") {
            get("/{email}/") {
                val value = call.parameters["email"] ?: ""
                try {
                    val email = Email(value)
                    call.respond(
                            EmailValidationResult(
                                    email.value,
                                    true,
                                    domainNameValidator.validate(email.domain)
                            )
                    )
                } catch (e: InvalidEmailException) {
                    call.respond(EmailValidationResult(value, false))
                }
            }
        }
    }
}
