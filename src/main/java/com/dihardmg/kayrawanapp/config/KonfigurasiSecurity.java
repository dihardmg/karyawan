package com.dihardmg.kayrawanapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author : Otorus
 * @since : 1/4/18
 */
@Configuration
@EnableWebSecurity
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String SQL_ROLE
            = "select u.username, p.permission_value as authority "
            + "from c_security_user u "
            + "inner join c_security_role r on u.id_role = r.id "
            + "inner join c_security_role_permission rp on rp.id_role = r.id "
            + "inner join c_security_permission p on rp.id_permission = p.id "
            + "where u.username = ?";

    private static final String SQL_LOGIN
            = "select u.username as username,p.password as password, active "
            + "from c_security_user u "
            + "inner join c_security_user_password p on p.id_user = u.id "
            + "where username = ?";


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl userDetails = new JdbcDaoImpl();
        userDetails.setDataSource(dataSource);
        userDetails.setUsersByUsernameQuery(SQL_LOGIN);
        userDetails.setAuthoritiesByUsernameQuery(SQL_ROLE);
        return userDetails;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/forgot_password/**").permitAll()
                .antMatchers("/reset_password/**").permitAll()

                .antMatchers("/kegiatan/**").hasAnyRole("KEGIATAN_ALL")

                .antMatchers("/institusi/**").hasAnyRole("MASTER_INSTITUSI")
                .antMatchers("/karyawan/**").hasAnyRole("MASTER_KARYAWAN")
                .antMatchers("/jenissurat/**").hasAnyRole("MASTER_JENIS_SURAT")
                .antMatchers("/jabatan/**").hasAnyRole("MASTER_JABATAN")
                .antMatchers("/dosen/**").hasAnyRole("MASTER_DOSEN")
                .antMatchers("/programstudi/**").hasAnyRole("MASTER_PROGRAM_STUDI")
                .antMatchers("/matakuliah/**").hasAnyRole("MASTER_MATA_KULIAH")
                .antMatchers("/surattugas/**").hasAnyRole("MASTER_SURAT_TUGAS")
                .antMatchers("/kategorikegiatan/**").hasAnyRole("MASTER_KATEGORI_KEGIATAN")
                .antMatchers("/jeniskegiatan/**").hasAnyRole("MASTER_JENIS_KEGIATAN")
                .antMatchers("/kategoribuktikegiatan/**").hasAnyRole("MASTER_KATEGORI_BUKTI_KEGIATAN")
                .antMatchers("/jenisbuktikegiatan/**").hasAnyRole("MASTER_JENIS_BUKTI_KEGIATAN")
                .antMatchers("/poinkegiatan/**").hasAnyRole("MASTER_POIN_KEGIATAN")
                .antMatchers("/jenisDokumenPengajuan/**").hasAnyRole("MASTER_JENIS_DOKUMEN_PENGAJUAN")

                .antMatchers("/pengajuan/**").hasAnyRole("PENGAJUAN")


                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
