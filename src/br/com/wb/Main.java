package br.com.wb;

import br.com.wb.controllers.DispositiveController;
import br.com.wb.controllers.EnvironmentController;
import br.com.wb.controllers.UserController;

import javax.naming.ServiceUnavailableException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ServiceUnavailableException {

        int option;
        UserController controller = new UserController();
        DispositiveController dispositiveController = new DispositiveController();
        EnvironmentController environmentController = new EnvironmentController();

        System.out.println("================================");
        System.out.println(" |Projeto 4 - Espaço de Tuplas| ");
        System.out.println("================================");
        System.out.println("| Options:                     |");
        System.out.println("|  1. Inserir usuário          |");
        System.out.println("|  2. Buscar usuário           |");
        System.out.println("|  3. Inserir dispositivo      |");
        System.out.println("|  4. Buscar dispositivo       |");
        System.out.println("|  5. Inserir ambiente         |");
        System.out.println("|  6. Buscar ambiente          |");
        System.out.println("|  7. Listar ambientes          |");
        System.out.println("|  7. Sair                     |");
        System.out.println("================================");
        System.out.println("Selecionar opção: ");
        while(true){
            Scanner input = new Scanner( System.in );
            option = input.nextInt();
            switch (option){
                case 1:
                    System.out.println("Inserir nome do usário: ");
                    Scanner nameInsertInput = new Scanner( System.in );
                    String userInsertName = nameInsertInput.nextLine();
                    System.out.println("Inserir nome do ambiente: ");
                    Scanner environmentInsertInput = new Scanner( System.in );
                    String environmentInsertName = environmentInsertInput.nextLine();
                    controller.writeUser(userInsertName, environmentInsertName);
                    System.out.println("Selecionar opção: ");
                    break;
                case 2:
                    System.out.println("Inserir nome do usário: ");
                    Scanner nameRetrieveInput = new Scanner( System.in );
                    String userRetrieveName = nameRetrieveInput.nextLine();
                    controller.retrieveUser(userRetrieveName);
                    System.out.println("Selecionar opção: ");
                    break;
                case 3:
                    System.out.println("Inserir nome do dispositivo: ");
                    Scanner nameInput = new Scanner( System.in );
                    String dispositiveName = nameInput.nextLine();
                    System.out.println("Inserir nome do ambiente: ");
                    Scanner environmentInput = new Scanner( System.in );
                    String environmentName = environmentInput.nextLine();
                    dispositiveController.writeDispositive(dispositiveName, environmentName);
                    System.out.println("Selecionar opção: ");
                    break;
                case 4:
                    System.out.println("Inserir nome do dispositivo: ");
                    Scanner retrieveDispoInput = new Scanner( System.in );
                    String retrieveName = retrieveDispoInput.nextLine();
                    dispositiveController.retrieveDispositive(retrieveName);
                    System.out.println("Selecionar opção: ");
                    break;
                case 5:
                    System.out.println("Inserir nome do ambiente: ");
                    Scanner environment = new Scanner( System.in );
                    String insertEnvironmentName = environment.nextLine();
                    environmentController.writeEnvironment(insertEnvironmentName);
                    System.out.println("Selecionar opção: ");
                    break;
                case 6:
                    System.out.println("Inserir nome do ambiente: ");
                    Scanner e = new Scanner( System.in );
                    String r = e.nextLine();
                    environmentController.retrieveEnvironment(r);
                    System.out.println("Selecionar opção: ");
                    break;
                case 7:
                    environmentController.listAllEnvironments();
                    System.out.println("Selecionar opção: ");
                    break;
                case 8:
                    System.out.println("Exit selected");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection");
            }
        }



    }
}
