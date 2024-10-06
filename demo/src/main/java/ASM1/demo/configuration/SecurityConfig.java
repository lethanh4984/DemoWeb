package ASM1.demo.configuration;


import ASM1.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private final IUserService iUserService;



//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }

    public SecurityConfig(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(iUserService);
//        dao.setPasswordEncoder(passwordEncoder());
        dao.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        System.out.println("dang xay dung dao");
        return dao;
    }


    @Bean
    public SecurityFilterChain myfilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin(form -> form
                        .loginPage("/login") // Đường dẫn tới trang login tùy chỉnh
                        .loginProcessingUrl("/login") // URL xử lý khi người dùng submit form login
                        .defaultSuccessUrl("/api/admins/home", true) // URL chuyển đến khi đăng nhập thành công
                        .failureUrl("/deny") // URL chuyển đến khi đăng nhập thất bại
                        .permitAll() // Cho phép tất cả người dùng truy cập trang login
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Đường dẫn để logout
//                        .logoutSuccessUrl("/login?logout=true") // URL chuyển đến khi logout thành công
                        .invalidateHttpSession(true) // Hủy session hiện tại
                        .deleteCookies("JSESSIONID") // Xóa cookie JSESSIONID
                        .permitAll()
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/**", "/admin1/assets/**", "/user/assets/**","/login").permitAll() // Cho phép truy cập không cần đăng nhập
                        .requestMatchers( "/i/**").hasAnyRole("USER", "ADMIN") // Chỉ ROLE_USER và ROLE_ADMIN mới có thể truy cập vào các trang này
                        .requestMatchers("/api/admins/**").hasRole("ADMIN") // Chỉ ROLE_ADMIN mới có thể truy cập vào trang /admin
                );
        System.out.println("dang xay dung config secu");
        return httpSecurity.build();
    }


}
