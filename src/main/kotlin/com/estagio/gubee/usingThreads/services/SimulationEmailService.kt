package com.estagio.gubee.usingThreads.services

import com.estagio.gubee.usingThreads.dtos.UserResponse
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class SimulationEmailService {

    fun sendEmail(user: UserResponse, subject: String, body: String) {
        Thread.sleep(Duration.ofSeconds(2))
        println("email enviado para '${user.email}' com id ${user.id}")
    }

    suspend fun sendEmailWithCoroutine(user: UserResponse, subject: String, body: String) = coroutineScope {
            delay(Duration.ofSeconds(2).toSeconds())
            println("email enviado para '${user.email}' com id ${user.id}")
    }

    fun sendEmailWithThreads(user: UserResponse, subject: String, body: String) {
        Thread {
            Thread.sleep(Duration.ofSeconds(2))
            println("email enviado para '${user.email}' com id ${user.id}")
        }.start()
    }


    fun sendEmailWithVirtualThreads(user: UserResponse, subject: String, body: String) {
        Thread.startVirtualThread {
            Thread.sleep(Duration.ofSeconds(2))
            println("email enviado para '${user.email}' com id ${user.id}")
        }
    }

    @Async
    fun sendEmailWithAnnotation(user: UserResponse, subject: String, body: String) {
        Thread.startVirtualThread {
            Thread.sleep(Duration.ofSeconds(2))
            println("email enviado para '${user.email}' com id ${user.id}")
        }
    }

}