package trabso;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Monitor {

    private Integer idCount = 0;
    private PaginaMemoria pivot = null;
    private List<PaginaMemoria> memoriaVirtual = null;
    private Scanner sc = new Scanner(System.in);

    public void funcionar() {
        Integer choice;
        do {
            choice = construirMenu();
            executarOperacaoEscolhida(choice);
        } while (choice != 0);

    }

    private void executarOperacaoEscolhida(Integer choice) {
        try {
            switch (choice) {
                case 1: {
                    executarAdicaoPagina();
                    break;
                }
                case 2: {
                    executarExclusaoPagina();
                    break;
                }
                case 3: {
                    executarVisualizacaoPaginas();
                    break;
                }
                case 4: {
                    executarAcessoPagina();
                    break;
                }
                case 5: {

                    break;
                }
                case 0: {
                    System.out.println("Fim do programa!");
                    Thread.sleep(5000);
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void executarExclusaoPagina() {
        PaginaMemoria entrada = new PaginaMemoria();
        System.out.println("Digite o Id da página a ser removida:");
        Integer id = sc.nextInt();
        entrada.setId(id);
        this.removerPaginaMemoria(entrada);
    }

    private void executarAcessoPagina() {
        PaginaMemoria entrada = new PaginaMemoria();
        System.out.println("Digite o Id da página a ser acessada:");
        Integer id = sc.nextInt();
        entrada.setId(id);
        this.acessarPaginaMemoria(entrada);
    }

    private void executarAdicaoPagina() {
        PaginaMemoria entrada = new PaginaMemoria();
        entrada.setId(++idCount);
        this.addPaginaMemoria(entrada);
    }

    private Integer construirMenu() {
        Integer choice = 0;
        do {
            System.out.println();
            System.out.println("Selecione uma opção abaixo!");
            System.out.println("1) Adicionar Página de memória");
            System.out.println("2) Remover Página de memória");
            System.out.println("3) Visualizar todas as Páginas de memória");
            System.out.println("5) Acessar Página de memória");
            System.out.println("4) Monitorar as Páginas de memória");
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Formato inválido");
            }
            if (0 <= choice && choice < 5) {
                break;
            }
            System.out.flush();
        } while (true);
        return choice;
    }

    private void addPaginaMemoria(PaginaMemoria nova) {
        if (pivot == null) {
            pivot = nova;
            pivot.setProximo(pivot);
        } else {
            addRecursivo(pivot, nova);
        }
    }

    private void addRecursivo(PaginaMemoria atual, PaginaMemoria nova) {
        if (atual.getProximo().equals(pivot)) {
            atual.setProximo(nova);
            nova.setProximo(pivot);
        } else {
            addRecursivo(atual.getProximo(), nova);
        }
    }

    private void removerPaginaMemoria(PaginaMemoria excluida) {
        if (pivot == null) {
            System.out.println("Lista vazia!");
        } else {
            removerRecursivo(pivot, excluida);
        }
    }

    private void removerRecursivo(PaginaMemoria anterior, PaginaMemoria excluida) {
        if (excluida.equals(anterior) && !anterior.getProximo().equals(pivot)) {
            PaginaMemoria temp = pivot;
            do {
                if (anterior.getProximo().equals(pivot)) {
                    anterior.setProximo(pivot.getProximo());
                    pivot = pivot.getProximo();
                    System.out.println("Página removida!");
                    break;
                } else {
                    anterior = anterior.getProximo();
                }
            } while (true);
        } else if (excluida.equals(anterior) && anterior.equals(pivot)) {
            pivot = null;
            System.out.println("Página removida!");
        } else if (excluida.equals(anterior.getProximo()) && !anterior.getProximo().equals(pivot)) {
            anterior.setProximo(anterior.getProximo().getProximo());
            System.out.println("Página removida!");
        } else if (!excluida.equals(anterior.getProximo()) && !anterior.getProximo().equals(pivot)) {
            removerRecursivo(anterior.getProximo(), excluida);
        } else {
            System.out.println("Id não Encontrado!");
        }
    }

    private void executarVisualizacaoPaginas() {
        if (pivot == null) {
            System.out.println("Lista vazia!");
        } else {
            System.out.print(pivot);
            imprimirRecursivo(pivot.getProximo());
        }
    }

    private void imprimirRecursivo(PaginaMemoria proximo) {
        if (!proximo.equals(pivot)) {
            System.out.print(proximo);
            imprimirRecursivo(proximo.getProximo());
        }
    }

    private void acessarPaginaMemoria(PaginaMemoria entrada) {
        PaginaMemoria temp = pivot;
        do {
            if (temp.equals(entrada)) {
                temp.setContAcesso(temp.getContAcesso() + 1);
                System.out.println("Página acessada!");
                break;
            } else {
                temp = temp.getProximo();
            }
        } while (true);
    }

}
