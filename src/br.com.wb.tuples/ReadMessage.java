package br.com.wb.tuples;

import net.jini.space.JavaSpace;

//http://www.inf.ufsc.br/~frank/INE5418/JavaSpaces/
//http://www.oracle.com/technetwork/articles/java/javaspaces-140665.html
//http://www.artima.com/javaseminars/modules/JavaSpaces/

public class ReadMessage {

    public static void main(String[] args) {
        try {
            System.out.println("Procurando pelo servico JavaSpace...");
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();

            if (space == null) {
                System.out
                        .println("O servico JavaSpace nao foi encontrado. Encerrando...");
                System.exit(-1);
            }
            System.out.println("O servico JavaSpace foi encontrado.");

            while (true) {
                Message template = new Message();
                Message msg = (Message) space.take(template, null, 60 * 1000);
                if (msg == null) {
                    System.out
                            .println("Tempo de espera esgotado. Encerrando...");
                    System.exit(0);
                }

                System.out.println("Mensagem recebida: " + msg.content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
