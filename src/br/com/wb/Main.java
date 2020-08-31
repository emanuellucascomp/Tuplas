package br.com.wb;

import br.com.wb.controllers.ChatRoomController;
import br.com.wb.controllers.MessageController;
import br.com.wb.controllers.UserController;

import javax.naming.ServiceUnavailableException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ServiceUnavailableException {

        int option;
        UserController controller = new UserController();
        ChatRoomController chatRoomController = new ChatRoomController();
        MessageController messageController = new MessageController();

        System.out.println("================================");
        System.out.println("  |Projeto - Espaço de Tuplas|  ");
        System.out.println("================================");
        System.out.println("| Options:                     |");
        System.out.println("|  1. Inserir usuário          |");
        System.out.println("|  2. Buscar usuário           |");
        System.out.println("|  3. Mensagem direta          |");
        System.out.println("|  4. Mensagem no canal        |");
        System.out.println("|  5. Inserir sala             |");
        System.out.println("|  6. Buscar sala              |");
        System.out.println("|  9. Listar usuários          |");
        System.out.println("|  10. Mover usuário           |");
        System.out.println("|  12. Sair                    |");
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
                    System.out.println("Inserir nome da sala: ");
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
                    System.out.println("Inserir nome do remetente: ");
                    Scanner remetente = new Scanner( System.in );
                    String from = remetente.nextLine();
                    System.out.println("Inserir nome do destinatário: ");
                    Scanner destinatario = new Scanner( System.in );
                    String to = destinatario.nextLine();
                    System.out.println("Inserir mensagem: ");
                    Scanner mensagem = new Scanner( System.in );
                    String content = mensagem.nextLine();
                    messageController.writeMessage(from, to, false, content);
                    System.out.println("Selecionar opção: ");
                    break;
                case 4:
                    System.out.println("Inserir nome do remetente: ");
                    Scanner re = new Scanner( System.in );
                    String userFrom = re.nextLine();
                    System.out.println("Inserir mensagem: ");
                    Scanner msg = new Scanner( System.in );
                    String message = msg.nextLine();
                    messageController.writeMessage(userFrom, null, true, message);
                    System.out.println("Selecionar opção: ");
                    break;
                case 5:
                    System.out.println("Inserir nome da sala: ");
                    Scanner environment = new Scanner( System.in );
                    String insertEnvironmentName = environment.nextLine();
                    chatRoomController.writeRoom(insertEnvironmentName);
                    System.out.println("Selecionar opção: ");
                    break;
                case 6:
                    System.out.println("Inserir nome da sala: ");
                    Scanner e = new Scanner( System.in );
                    String r = e.nextLine();
                    chatRoomController.retrieveRoom(r);
                    System.out.println("Selecionar opção: ");
                    break;
                case 9:
                    System.out.println("Inserir nome da sala: ");
                    Scanner d = new Scanner( System.in );
                    String dn = d.nextLine();
                    chatRoomController.listUserByRoom(dn);
                    System.out.println("Selecionar opção: ");
                    break;
                case 10:
                    System.out.println("Inserir nome do usuário: ");
                    Scanner s = new Scanner( System.in );
                    String st = s.nextLine();
                    System.out.println("Inserir nome da sala: ");
                    Scanner am = new Scanner( System.in );
                    String a = am.nextLine();
                    chatRoomController.moveUser(st, a);
                    System.out.println("Selecionar opção: ");
                    break;
                case 12:
                    System.out.println("Exit selected");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection");
            }
        }



    }
}
