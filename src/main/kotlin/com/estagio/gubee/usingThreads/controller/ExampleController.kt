package com.estagio.gubee.usingThreads.controller

import com.estagio.gubee.usingThreads.dtos.UserRequest
import com.estagio.gubee.usingThreads.dtos.UserResponse
import com.estagio.gubee.usingThreads.services.SimulationCreateUser
import com.estagio.gubee.usingThreads.services.SimulationEmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/example")
class ExampleController (
    private val userService: SimulationCreateUser
) {

    @PostMapping("saves")
    suspend fun createUserUsingCoroutine(@RequestBody usuario: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.createUserUsingCoroutine(usuario))
    }

    @PostMapping("saves/2")
    fun createUserUsingThread(@RequestBody usuario: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.createUserUsingThread(usuario))
    }

    @PostMapping("saves/3")
    fun createUserUsingThread2(@RequestBody usuario: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.createUserUsingThread2(usuario))
    }

    @PostMapping("saves/4")
    fun createUserVirtualThread(@RequestBody usuario: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.createUserVirtualThread(usuario))
    }

    @PostMapping("saves/5")
    fun createUserUsingAnnotation(@RequestBody usuario: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.createUserUsingAnnotation(usuario))

    }


}