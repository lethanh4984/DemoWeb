//package ASM1.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//
//public class testSecurity {
//
//
//    @Configuration
//    public class SecurityConfig {
//
//        public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
//            return new JdbcUserDetailsManager(dataSource);
//        }
//
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//            httpSecurity
//                    .formLogin(form -> form
//                            .loginPage("/login") // Đường dẫn tới trang login tùy chỉnh
//                            .loginProcessingUrl("/perform_login") // URL xử lý khi người dùng submit form login
//                            .defaultSuccessUrl("/posts", true) // URL chuyển đến khi đăng nhập thành công
//                            .failureUrl("/login?error=true") // URL chuyển đến khi đăng nhập thất bại
//                            .permitAll() // Cho phép tất cả người dùng truy cập trang login
//                    )
//                    .logout(logout -> logout
//                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Đường dẫn để logout
//                            .logoutSuccessUrl("/login?logout=true") // URL chuyển đến khi logout thành công
//                            .invalidateHttpSession(true) // Hủy session hiện tại
//                            .deleteCookies("JSESSIONID") // Xóa cookie JSESSIONID
//                            .permitAll()
//                    )
//                    .authorizeHttpRequests(authorize -> authorize
//                            .requestMatchers("/error_404", "/css/**", "/js/**", "/images/**", "/reg").permitAll() // Cho phép truy cập không cần đăng nhập
//                            .requestMatchers("/posts/**","/i/**").hasAnyRole("USER", "ADMIN") // ROLE_USER và ROLE_ADMIN có quyền truy cập vào /posts/
//                            .requestMatchers("/**").hasRole("ADMIN") // Chỉ ROLE_ADMIN mới có thể truy cập vào /types/
//                            .anyRequest().authenticated() // Các request khác yêu cầu đăng nhập
//                    );
//            return httpSecurity.build();
//        }
//
//
//    }
//
//}
