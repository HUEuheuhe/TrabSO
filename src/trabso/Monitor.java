package trabso;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Monitor {

    private Integer idCount = 0, tempoExecucao = 0;
    private PaginaMemoria pivot = null;
    private List<PaginaMemoria> memoriaVirtual = null;
    private final Scanner sc = new Scanner(System.in);

    public void funcionar() {
        memoriaVirtual = new ArrayList<PaginaMemoria>();
        Integer choice;
        do {
            tempoExecucao++;
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
                    monitorarPaginasMemoria();
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
        PaginaMemoria paginaRemovida;
        System.out.println("Digite o Id da página a ser removida:");
        Integer id = sc.nextInt();
        entrada.setId(id);
        paginaRemovida = this.removerPaginaMemoria(entrada);
        if (paginaRemovida != null) {
            memoriaVirtual.add(paginaRemovida);
            System.out.println(paginaRemovida);
        }
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
            System.out.println("Tempo de Execução: " + tempoExecucao);
            System.out.println();
            System.out.println("Selecione uma opção abaixo!");
            System.out.println("1) Adicionar Página de memória");
            System.out.println("2) Remover Página de memória");
            System.out.println("3) Visualizar todas as Páginas de memória");
            System.out.println("4) Acessar Página de memória");
            System.out.println("5) Monitorar as Páginas de memória");
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Formato inválido");
            }
            if (0 <= choice && choice <= 5) {
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

    private PaginaMemoria removerPaginaMemoria(PaginaMemoria excluida) {
        if (pivot == null) {
            System.out.println("Memória vazia!");
            return null;
        } else {
            return removerRecursivo(pivot, excluida);
        }
    }

    private PaginaMemoria removerRecursivo(PaginaMemoria anterior, PaginaMemoria excluida) {
        PaginaMemoria paginaremovida = null;
        if (excluida.equals(anterior) && !anterior.getProximo().equals(pivot)) {
            PaginaMemoria temp = pivot;
            do {
                if (anterior.getProximo().equals(pivot)) {
                    anterior.setProximo(pivot.getProximo());
                    paginaremovida = pivot;
                    pivot = pivot.getProximo();
                    System.out.println("Página removida!");
                    break;
                } else {
                    anterior = anterior.getProximo();
                }
            } while (true);
        } else if (excluida.equals(anterior) && anterior.equals(pivot)) {
            paginaremovida = pivot;
            pivot = null;
            System.out.println("Página removida!");
        } else if (excluida.equals(anterior.getProximo()) && !anterior.getProximo().equals(pivot)) {
            paginaremovida = anterior.getProximo();
            anterior.setProximo(anterior.getProximo().getProximo());
            System.out.println("Página removida!");
        } else if (!excluida.equals(anterior.getProximo()) && !anterior.getProximo().equals(pivot)) {
            paginaremovida = removerRecursivo(anterior.getProximo(), excluida);
        } else {
            System.out.println("Id não Encontrado!");
        }
        return paginaremovida;
    }

    private void executarVisualizacaoPaginas() {
        if (pivot == null) {
            System.out.println("Memória vazia!");
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
        PaginaMemoria temp = pivot.getProximo();
        do {
            if (temp.equals(entrada)) {
                temp.setContAcesso(temp.getContAcesso() + 1);
                System.out.println("Página acessada!");
                break;
            } else if (temp.equals(pivot)) {
                System.out.println("Página não encontrada!");
                break;
            } else {
                temp = temp.getProximo();
            }
        } while (true);
    }

    private void monitorarPaginasMemoria() {
        PaginaMemoria monitorado = null;
        if (pivot == null) {
            System.out.println("Memória vazia!");
        } else {
            monitorado = pivot;
            monitorado = monitorarRecursivo(pivot.getProximo(), monitorado);
            System.out.println("Página eleita a partir do índice de remoção: " + monitorado);
        }
    }

    private PaginaMemoria monitorarRecursivo(PaginaMemoria proximo, PaginaMemoria monitorado) {
        if (!proximo.equals(pivot)) {
            if (monitorado.getIndiceRemocao(tempoExecucao) < proximo.getIndiceRemocao(tempoExecucao)) {
                monitorado = proximo;
            }
            return monitorarRecursivo(proximo.getProximo(), monitorado);
        }
        return monitorado;
    }

}
