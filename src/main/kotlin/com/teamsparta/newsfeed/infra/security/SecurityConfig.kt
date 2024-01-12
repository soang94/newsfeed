package com.teamsparta.newsfeed.infra.security

import com.teamsparta.newsfeed.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
        private val jwtAuthenticationFilter: JwtAuthenticationFilter,
        private val authenticationEntrypoint: CustomAuthenticationEntrypoint,
        private val accessDeniedHandler: CustomAccessDeniedHandler
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
                .httpBasic { it.disable() }
                .formLogin { it.disable() }
                .csrf { it.disable() }
                .authorizeHttpRequests {
                    it.requestMatchers(AntPathRequestMatcher("/login")).permitAll()
                    it.requestMatchers(AntPathRequestMatcher("/signup")).permitAll()
                    it.requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                    it.requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                            .anyRequest().authenticated()
                }
                // 기존 UsernamePasswordAuthenticationFilter 가 존재하던 자리에 JwtAuthenticationFilter 적용
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
                .exceptionHandling {
                    it.authenticationEntryPoint(authenticationEntrypoint)
                    it.accessDeniedHandler(accessDeniedHandler)
                }
                .build()
    }

}