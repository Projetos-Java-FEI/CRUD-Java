package service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordService {

    // Método para verificar a senha inserida com a senha criptografada armazenada
    public static boolean verificarSenha(String senhaInserida, String senhaCriptografada) {
        return BCrypt.checkpw(senhaInserida, senhaCriptografada);
    }
}
