package com.datamanagement.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login getLoginByUsernameAndPassword(String username, String password);
}
