package controller;

import view.TelaUsuario;
import model.User;


public class ControllerUsuario {
    private TelaUsuario view;
    private User user;

    public ControllerUsuario(TelaUsuario view, User user) {
        this.view = view;
        this.user = user;
    }

    public ControllerUsuario() {
    }

    
    
}
