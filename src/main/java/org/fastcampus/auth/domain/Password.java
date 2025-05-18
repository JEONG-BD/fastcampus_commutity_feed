package org.fastcampus.auth.domain;

public class Password {


    private final String encryptPassword;

    private Password(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public static Password createEncryptPassword(String password){
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("패스워드는 빈값이 될 수 없습니다.");
        }

        return new Password(SHA256.encrypt(password));
    }

    public boolean matchPassword(String password){
        return  encryptPassword.matches(SHA256.encrypt(password));
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }
}
