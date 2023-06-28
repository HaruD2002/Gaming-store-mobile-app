package com.example.prm_project.utils;

import com.lambdapioneer.argon2kt.Argon2Kt;
import com.lambdapioneer.argon2kt.Argon2KtResult;
import com.lambdapioneer.argon2kt.Argon2Mode;

import java.security.SecureRandom;

public class PasswordHashing {
    private byte[] salt;
    private String password;
    private final Argon2Kt argon2Kt = new Argon2Kt();

    private byte[] getSalt(){
        SecureRandom random = new SecureRandom();
        salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public String encoding(String password){
        final Argon2KtResult result = argon2Kt.hash(Argon2Mode.ARGON2_I,password.getBytes(), getSalt());
        this.password = result.encodedOutputAsString();
        return this.password;
    }

    public boolean verifyPassword(String password, String encode_password){
        return argon2Kt.verify(Argon2Mode.ARGON2_I, encode_password, password.getBytes());
    }


}
