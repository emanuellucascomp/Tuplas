package br.com.wb;

import br.com.wb.controllers.UserController;

public class Main {
    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.writeUser("Emanuel");
        controller.retrieveUser("Emanuel");
    }
}
