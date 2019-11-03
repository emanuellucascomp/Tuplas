package br.com.wb;

import br.com.wb.controllers.UserController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int option;
        UserController controller = new UserController();
        System.out.println("================================");
        System.out.println(" |Projeto 4 - Espaço de Tuplas| ");
        System.out.println("================================");
        System.out.println("| Options:                     |");
        System.out.println("|        1. Write user         |");
        System.out.println("|        2. Retrieve user      |");
        System.out.println("|        3. Exit               |");
        System.out.println("================================");
        System.out.println("Select option: ");
        while(true){
            Scanner input = new Scanner( System.in );
            option = input.nextInt();
            switch (option){
                case 1:
                    System.out.println("Insert user name: ");
                    Scanner nameInsertInput = new Scanner( System.in );
                    String userInsertName = nameInsertInput.nextLine();
                    controller.writeUser(userInsertName);
                    break;
                case 2:
                    System.out.println("Insert user name: ");
                    Scanner nameRetrieveInput = new Scanner( System.in );
                    String userRetrieveName = nameRetrieveInput.nextLine();
                    controller.retrieveUser(userRetrieveName);
                    break;
                case 3:
                    System.out.println("Exit selected");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection");
            }
        }



    }
}
