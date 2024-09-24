package com.estagio.gubee.usingThreads.services

import com.estagio.gubee.usingThreads.dtos.UserRequest
import com.estagio.gubee.usingThreads.dtos.UserResponse
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.function.ServerResponse.async
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors

@Service
class SimulationCreateUser (
    private val emailService: SimulationEmailService
) {



    suspend fun createUserUsingCoroutine(usuario: UserRequest): UserResponse {
        val userCreated = UserResponse(UUID.randomUUID(), usuario.name, usuario.email)

        emailService.sendEmailWithCoroutine(userCreated, "boas vindas", "olá")

        return userCreated;
    }

    fun createUserUsingThread(usuario: UserRequest): UserResponse {
        val userCreated = UserResponse(UUID.randomUUID(), usuario.name, usuario.email)

        emailService.sendEmailWithThreads(userCreated, "boas vindas", "olá")

        return userCreated;
    }

    fun createUserUsingThread2(usuario: UserRequest): UserResponse {


        val executor = Executors.newCachedThreadPool()

        val call = Callable<UserResponse> {
            val userCreated = UserResponse(UUID.randomUUID(), usuario.name, usuario.email)

            Thread.sleep(1000)

            emailService.sendEmailWithThreads(userCreated, "boas vindas", "olá")

            return@Callable userCreated
        }

        val res = executor.submit(call)

        return res.get();
    }

    fun createUserVirtualThread(usuario: UserRequest): UserResponse {
        val userCreated = UserResponse(UUID.randomUUID(), usuario.name, usuario.email)

        emailService.sendEmailWithVirtualThreads(userCreated, "boas vindas", "olá")

        return userCreated;
    }

    fun createUserUsingAnnotation(usuario: UserRequest): UserResponse {
        val userCreated = UserResponse(UUID.randomUUID(), usuario.name, usuario.email)

        emailService.sendEmailWithAnnotation(userCreated, "boas vindas", "olá")

        return userCreated;
    }

}