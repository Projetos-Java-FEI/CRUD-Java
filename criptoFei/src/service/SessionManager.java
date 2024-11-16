package service;

import model.User;


public class SessionManager {
    private static User user;
    
    // Metodo usado pós login para definir o usuário logado como user para o programa
    public static void setUser(User u) {
        user = u;
    }
    
    public static User getUser() { // usar esse metodo para poder acessar os getters e setters de User
        return user;
    }
}
