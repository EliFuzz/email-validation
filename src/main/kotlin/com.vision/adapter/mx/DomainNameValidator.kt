package com.vision.adapter.mx

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.util.*
import javax.naming.directory.InitialDirContext

class DomainNameValidator {
    suspend fun validate(domainName: String): Boolean {
        val domainChannel = Channel<Boolean>()
        GlobalScope.launch {
            validateDomainName(domainName, domainChannel)
        }
        return domainChannel.receive()
    }

    private suspend fun validateDomainName(domainName: String, channel: Channel<Boolean>) = try {
        val env = Hashtable<String, String>(mapOf(
                "java.naming.factory.initial" to "com.sun.jndi.dns.DnsContextFactory",
                "java.naming.provider.url" to "dns:",
                "com.sun.jndi.dns.timeout.retries" to "1"
        ))
        channel.send(InitialDirContext(env).getAttributes(
                domainName,
                arrayOf("MX")).get("MX") !== null)
    } catch (e: Exception) {
        channel.send(false)
    }
}