package com.example.ProjekatIsa.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.ProjekatIsa.security.CustomUserDetailsService;
import com.example.ProjekatIsa.security.TokenUtils;
import com.example.ProjekatIsa.security.auth.RestAuthenticationEntryPoint;
import com.example.ProjekatIsa.security.auth.TokenAuthenticationFilter;
import com.example.ProjekatIsa.service.UserService;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer   {
	
	
	// Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
				// BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
				
				@Bean
				public PasswordEncoder passwordEncoder() {
					return new  BCryptPasswordEncoder();
				}

				@Autowired
				private UserService userDetailsService;

				// Neautorizovani pristup zastcenim resursima
				@Autowired
				private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

				@Bean
				@Override
				public AuthenticationManager authenticationManagerBean() throws Exception {
					return super.authenticationManagerBean();
				}

				// Definisemo nacin autentifikacije
				@Autowired
				public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
					auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
				}

				@Autowired
				TokenUtils tokenUtils;
			
				
				@Bean
			    public CorsFilter corsFilter() {
			        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			        CorsConfiguration config = new CorsConfiguration();
			        config.setAllowCredentials(true);
			        config.addAllowedOrigin("*");
			        config.addAllowedHeader("*");
			        config.addAllowedMethod("OPTIONS");
			        config.addAllowedMethod("GET");
			        config.addAllowedMethod("POST");
			        config.addAllowedMethod("PUT");
			        config.addAllowedMethod("DELETE");
			        source.registerCorsConfiguration("/**", config);
			        return new CorsFilter(source);
			    }

				// Definisemo prava pristupa odredjenim URL-ovima
				@Override
				protected void configure(HttpSecurity http) throws Exception {
					http
						// komunikacija izmedju klijenta i servera je stateless
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
						
						// za neautorizovane zahteve posalji 401 gresku
						.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
						
						// svim korisnicima dopusti da pristupe putanjama /auth/** i /h2-console/**
						.authorizeRequests()
						.antMatchers("/api/**").permitAll()
						.antMatchers("/auth/**").permitAll()
						.antMatchers("/api/mainSecurity/**").permitAll()
						.antMatchers("/avioCompany").permitAll()
						.antMatchers("/avioCompany/**").permitAll()

						.antMatchers("/flight").permitAll()
						.antMatchers("/flight/**").permitAll()
						.antMatchers("/rentalcars").permitAll()
						.antMatchers("/rentalcars/**").permitAll()
						.antMatchers("/friends/**").permitAll()
						.antMatchers("/car/**").permitAll()
						.antMatchers("/addServices/**").permitAll()
						.antMatchers("/reservationRoom/**").permitAll()
						//.antMatchers("/reservationRoom/chekIfFlightIsBooked/**").permitAll()
						.antMatchers("/reservationRoom/getAllMyFlights/**").permitAll()
						//.antMatchers("/chekIfFlightIsBooked/**").permitAll()
						.antMatchers("/getAllMyFlights/**").permitAll()
						.antMatchers("/filijale/**").permitAll()
						.antMatchers("/seatArrangement/**").permitAll()
						.antMatchers("/seatArrangement").permitAll()
						.antMatchers("/getDiscountRoomsid/**").permitAll()
						.antMatchers("/getDiscountRoomsid").permitAll()
						.antMatchers("/rooms/**").permitAll()
						.antMatchers("/rooms").permitAll()
						.antMatchers("/pricing/**").permitAll()
						.antMatchers("/pricing").permitAll()
						.antMatchers("/discounts/**").permitAll()
						.antMatchers("/discounts").permitAll()
						.antMatchers("/rooms/getDiscountRoomsid/**").permitAll()
						.antMatchers("/rooms/getDiscountRoomsid").permitAll()
						.antMatchers("/hotels/**").permitAll()
						.antMatchers("/hotels").permitAll()
						.antMatchers("/searchHotels").permitAll()
						.antMatchers("/registerAdmin/**").permitAll()
						.antMatchers("/registerAdmin").permitAll()
						.antMatchers("/category/**").permitAll()
						.antMatchers("/category").permitAll()
						.antMatchers("/searchRooms").permitAll()
						.antMatchers("/searchRooms/**").permitAll()
						.antMatchers("/carReservation").permitAll()
						.antMatchers("/rating").permitAll()
						.antMatchers("/h2-console/**").permitAll()
						.antMatchers("/ws/**").permitAll()
						
						// svaki zahtev mora biti autorizovan
						.anyRequest().authenticated().and().cors().and()
						
						// presretni svaki zahtev filterom
						.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userDetailsService), BasicAuthenticationFilter.class);

					http.csrf().disable();
				}
				
				// Generalna bezbednost aplikacije
				@Override
				public void configure(WebSecurity web) throws Exception {
					// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
					web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
					web.ignoring().antMatchers(HttpMethod.POST, "/auth/logout");
					web.ignoring().antMatchers(HttpMethod.POST, "/accommodation");
					web.ignoring().antMatchers(HttpMethod.POST, "/api/mainSecurity/setAuthentication");
					web.ignoring().antMatchers(HttpMethod.POST, "/api/registerUser");
					web.ignoring().antMatchers(HttpMethod.POST, "/api/registerAdmin");
					web.ignoring().antMatchers(HttpMethod.POST, "/api/registerAdmin/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/api/mainSecurity/logout");
					web.ignoring().antMatchers(HttpMethod.GET, "/avioCompany/getAll");
					web.ignoring().antMatchers(HttpMethod.GET, "/avioCompany/**");

					web.ignoring().antMatchers(HttpMethod.GET, "/rentalcars/getCars");
					web.ignoring().antMatchers(HttpMethod.POST, "/avioCompany");
					web.ignoring().antMatchers(HttpMethod.POST, "/destination");
					web.ignoring().antMatchers(HttpMethod.GET, "/destination");
					web.ignoring().antMatchers(HttpMethod.DELETE, "/destination/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/flight");
					web.ignoring().antMatchers(HttpMethod.POST, "/flight/**");

					web.ignoring().antMatchers(HttpMethod.POST, "/flight/reservations");

					web.ignoring().antMatchers(HttpMethod.DELETE, "/flight/**");
					web.ignoring().antMatchers(HttpMethod.PUT, "/flight/update");
					web.ignoring().antMatchers(HttpMethod.PUT, "/flight/seats");
					web.ignoring().antMatchers(HttpMethod.PUT, "/api");
					web.ignoring().antMatchers(HttpMethod.GET, "/flight/**");

					web.ignoring().antMatchers(HttpMethod.PUT, "/avioCompany/update");
					web.ignoring().antMatchers(HttpMethod.DELETE, "/avioCompany/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/hotels/getAll");
					web.ignoring().antMatchers(HttpMethod.GET, "/hotels/getAllRooms");
					web.ignoring().antMatchers(HttpMethod.POST, "/hotels/searchHotels");
					web.ignoring().antMatchers(HttpMethod.GET, "/hotels/getAllServices");
					web.ignoring().antMatchers(HttpMethod.GET, "/hotels/getAllRooms/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/hotels/getAllServices/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/hotels");
					web.ignoring().antMatchers(HttpMethod.DELETE, "/hotels/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/car/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/filijale/**");

					web.ignoring().antMatchers(HttpMethod.POST, "/friends");
					web.ignoring().antMatchers(HttpMethod.GET, "/friends/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/friends/accept/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/seatArrangement");
					web.ignoring().antMatchers(HttpMethod.GET, "/seatArrangement/**");

					web.ignoring().antMatchers(HttpMethod.DELETE, "/friends/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/rentalcars/getFilijale");
					web.ignoring().antMatchers(HttpMethod.POST, "/rentalcars");
					web.ignoring().antMatchers(HttpMethod.DELETE, "/rentalcars/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/car");
					web.ignoring().antMatchers(HttpMethod.DELETE, "/car/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/filijale/getCars");
					
					web.ignoring().antMatchers(HttpMethod.POST, "/hotels/searchHotels");
					web.ignoring().antMatchers(HttpMethod.POST, "/hotels/searchRooms");
					web.ignoring().antMatchers(HttpMethod.POST, "/hotels/searchRooms/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/rooms/searchRooms");
					web.ignoring().antMatchers(HttpMethod.POST, "/rooms/searchRooms/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/rooms");
					web.ignoring().antMatchers(HttpMethod.POST, "/rooms/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/discounts/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/discounts");
					web.ignoring().antMatchers(HttpMethod.POST, "/carReservation/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/carReservation");
					web.ignoring().antMatchers(HttpMethod.POST, "/rating/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/rating");
					web.ignoring().antMatchers(HttpMethod.GET, "/rating/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/rating");
					web.ignoring().antMatchers(HttpMethod.POST, "/rating/rateFlight/5");
					web.ignoring().antMatchers(HttpMethod.GET, "/rooms/isReserved");
					web.ignoring().antMatchers(HttpMethod.GET, "/rooms/isReserved/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/rooms/getDiscountRoomsid");
					web.ignoring().antMatchers(HttpMethod.GET, "/rooms/getDiscountRoomsid/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/hotels/deleteRoom");
					web.ignoring().antMatchers(HttpMethod.POST, "/hotels/deleteRoom/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/hotels/deleteService");
					web.ignoring().antMatchers(HttpMethod.POST, "/hotels/deleteService/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/addServices/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/addServices");
					web.ignoring().antMatchers(HttpMethod.GET, "/addServices/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/pricing/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/pricing");
					web.ignoring().antMatchers(HttpMethod.GET, "/pricing/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/pricing/**");
					web.ignoring().antMatchers(HttpMethod.DELETE, "/addServices/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/reservationRoom/**");
					web.ignoring().antMatchers(HttpMethod.POST, "/reservationRoom");
					web.ignoring().antMatchers(HttpMethod.GET, "/reservationRoom/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/reservationRoom/getAllMyFlights/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/getAllMyFlights/**");
					web.ignoring().antMatchers(HttpMethod.GET, "/rentalcars/getAll");
					web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
				}
				
				@Bean
				public DeviceResolverHandlerInterceptor 
				        deviceResolverHandlerInterceptor() {
				    return new DeviceResolverHandlerInterceptor();
				}

				@Bean
				public DeviceHandlerMethodArgumentResolver 
				        deviceHandlerMethodArgumentResolver() {
				    return new DeviceHandlerMethodArgumentResolver();
				}

				@Override
				public void addInterceptors(InterceptorRegistry registry) {
				    registry.addInterceptor(deviceResolverHandlerInterceptor());
				}

				@Override
				public void addArgumentResolvers(
				        List<HandlerMethodArgumentResolver> argumentResolvers) {
				    argumentResolvers.add(deviceHandlerMethodArgumentResolver());
				}
	
}
