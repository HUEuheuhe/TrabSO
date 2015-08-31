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
					PaginaMemoria entrada = new PaginaMemoria();
					entrada.setId(++idCount);
					this.addPaginaMemoria(entrada);
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
				case 0: {
					System.out.println("Fim do programa!");
					Thread.sleep(5000);
					break;
				}
				default:
					System.out.println("Opção inválida!");
					break;
			}
		} catch (InterruptedException ex) {
			Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
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
			if (0 <= choice && choice < 5) {
				break;
			}
			System.out.print("\b");
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
		if (atual.getProximo().equals(atual)) {
			atual.setProximo(nova);
			nova.setProximo(atual);
		} else {
			addRecursivo(atual, nova);
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
