/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author artur
 */

public class GerarHashDebuggers {
    public static void main(String[] args) {
        String senhaAdmin = "adm123";
        String senhaDev = "dev123";

        String hashAdmin = BCrypt.hashpw(senhaAdmin, BCrypt.gensalt());
        String hashDev = BCrypt.hashpw(senhaDev, BCrypt.gensalt());

        System.out.println("Hash senha Admin: " + hashAdmin);
        System.out.println("Hash senha Dev: " + hashDev);
    }
}

