package com.riprojects.hiddennorthnorfolk.api.security


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf {it.disable()}
            .authorizeHttpRequests { auth ->
                auth.anyRequest().permitAll()
            }.build()
    }
}