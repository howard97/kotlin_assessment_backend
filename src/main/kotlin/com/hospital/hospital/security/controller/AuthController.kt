package com.hospital.hospital.security.controller

import com.example.auth.dtos.LoginDto
import com.example.auth.dtos.MessageDto
import com.hospital.hospital.security.dtos.RegisterDto
import com.hospital.hospital.security.model.User
import com.hospital.hospital.security.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1")
class AuthController(private val userService: UserService) {
    /**+
     * an End point to submit patient data
     * @param body
     * @return responseEntity<Any>
     */
    @PostMapping("/register")
    fun register(@RequestBody body: RegisterDto): ResponseEntity<Any> {
        if(this.userService.checkIfUseExists(body.email)){
            return ResponseEntity.badRequest().body(MessageDto("YOU ARE ALREADY A USER ON THE SYSTEM"))
        }
        return ResponseEntity.ok(this.userService.save(body))
    }


    /**+
     * an End point to login a user
     * @param body
     * @return responseEntity<Any>
     */
    @PostMapping("login")
    fun login(@RequestBody body: LoginDto, response: HttpServletResponse):ResponseEntity<Any>{
       val user = this.userService.findByEmail(body.email)
           ?: return ResponseEntity.badRequest().body(MessageDto("User Not Found"))

        if(!user.comparePassword(body.password)){
            return ResponseEntity.badRequest().body(MessageDto("Invalid Password"))
        }

        val issuer = user.id.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))//1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt",jwt)
        cookie.isHttpOnly=true
        response.addCookie(cookie)

        return ResponseEntity.ok(MessageDto("success"))
    }

    /**+
     * an End point to get user data who are authenticated
     * @param jwt
     * @return responseEntity<Any>
     */
    @GetMapping("/user")
    fun user(@CookieValue("jwt") jwt:String?):ResponseEntity<Any>{
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(MessageDto("UnAuthenticated, Kindly Login"))
            }

            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
            return  ResponseEntity.ok(this.userService.getById(body.issuer.toLong()))
        }catch(e:Exception){
            return  ResponseEntity.status(401).body(MessageDto("UnAuthenticated, Kindly Login"))
        }
    }

    /**+
     * an End point to log out users and immediately expire access token
     * @param response
     * @return responseEntity<Any>
     */
    @PostMapping("/logout")
    fun logout( response: HttpServletResponse):ResponseEntity<Any>{
        val cookie = Cookie("jwt","")
        cookie.maxAge=0
        response.addCookie(cookie)
        return ResponseEntity.ok(MessageDto("success"))
    }

}