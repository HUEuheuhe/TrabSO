package trabso;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Monitor {

    private PaginaMemoria pivot = null;
    private List<PaginaMemoria> memoriaVirtual = null;
    private Scanner sc = new Scanner(System.in);

    public void funcionar() {
        Integer choice = construirMenu();
        executarOperacaoEscolhida(choice);

    }

    private void executarOperacaoEscolhida(Integer choice) {
        switch (choice) {
            case 1: {
                PaginaMemoria entrada = new PaginaMemoria();
                
                this.addPaginaMemoria(pivot, entrada);
                break;
            }
            case 2: {

                break;
            }
            case 3: {

                break;
            }
            case 4: {

                break;
            }
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private Integer construirMenu() {
        Integer choice = 0;
        do {
            System.out.println("Selecione uma opção abaixo!");
            System.out.println("1) Adicionar Página de memória");
            System.out.println("2) Remover Página de memória");
            System.out.println("3) Visualizar todas as Páginas de memória");
            System.out.println("4) Monitorar as Páginas de memória");
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Formato inválido");
            }
            if (0 < choice && choice < 5) {
                break;
            }
            System.out.print("\b");
            System.out.flush();
        } while (true);
        return choice;
    }

    private void addPaginaMemoria(PaginaMemoria atual, PaginaMemoria nova) {
        if (atual == null) {
            atual = nova;
            atual.setProximo(atual);
        } else {
            if (atual.getProximo().equals(pivot)) {
                atual.setProximo(nova);
                nova.setProximo(pivot);
            } else {
                addPaginaMemoria(nova, atual.getProximo());
            }
        }
    }

    private void removePaginaMemoria(PaginaMemoria anterior, PaginaMemoria excluida) {
        if (anterior == null) {
            System.out.println("Lista vazia!");
        } else {
            if (anterior.getId().equals(excluida.getId())) {
                anterior = anterior.getProximo();
            } else if (anterior.getProximo().getId().equals(excluida.getId())) {
                anterior.setProximo(anterior.getProximo().getProximo());
                excluida.setProximo(null);
            } else {
                removePaginaMemoria(anterior.getProximo(), excluida);
            }
        }
    }

}
