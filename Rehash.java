import java.util.Random;

class tabelaRehashing {
	
	No[] tabela;
	int insercoes;
	int colisoes;
	
	class No {
		int chave;
		int colisoes;
		
		public No(int chave, int colisoes) {
			this.chave = chave;
			this.colisoes = colisoes;
		}
	}
	
	boolean vazio(int p) {
		if (tabela[p].chave == -1) {
			return true;
		} else {
			return false;
		}
	}
	
	void iniciar(int p) {
		this.tabela = new No[p];
		for (int i = 0; i < p; i++) {
        	tabela[i] = new No(-1, 0);
    	}
	}
	
	int funcaoHash(int chave) {
		int i = chave % tabela.length;
		if (i < 0) {
			i = i + tabela.length;
		}
		return i;
	}
	
	int rehashing (int i) {
		return (i + 1) % tabela.length;
	}
	
	void insereRehashing(int chave) {
		if (this.insercoes >= (this.tabela.length * 0.9)) {
			System.out.println("Limite de inserções atingido");
			return;
		}
		int i = funcaoHash(chave);
		int colisoes_atuais = 0;
		while(tabela[i].chave != -1) {
			this.colisoes++;
			colisoes_atuais++;
			i = rehashing(i);
		}
		tabela[i] = new No(chave, colisoes_atuais);
		this.insercoes++;
	}
	
	int buscaHashing(int chave) {
		int i = funcaoHash(chave);
		while(!vazio(i) && tabela[i].chave != chave) {
			i = rehashing(i);
		}
		if (tabela[i].chave == -1) {
			return -1;
		} else {
			return i;
		}
	}
	
	void exibeTabela() {
		for (int i = 0; i < this.tabela.length; i++) {
			System.out.println("Entrada: " + i + " | " + tabela[i].chave);
		}
		System.out.println();
	}

	void tresMaiores() {
		No[] maiores = new No[3];

		for (int i = 0; i < maiores.length; i++) {
			maiores[i] = new No(-1, -1);
		}

		for (int i = 0; i < tabela.length; i++) {
			for (int j = 0; j < maiores.length; j++) {
				if (tabela[i].colisoes > maiores[j].colisoes) {
					for (int k = maiores.length - 1; k > j; k--) {
						maiores[k] = maiores[k - 1];
					}
					maiores[j] = tabela[i];
					break;
				}
			}
		}

		for (int i = 0; i < maiores.length; i++) {
			System.out.println(maiores[i].chave + " | Quantidade de Colisões: " + maiores[i].colisoes);
		}
	}

	void posicoesLivres() {

	for (int i = 0; i < tabela.length; i++) {
		if (tabela[i].chave == -1) {
			System.out.println("Posição livre: " + i);
		}
	}

	}

}

public class Rehash {
	public static void main(String[] args) {

		Random random = new Random(1090);

		tabelaRehashing tabela = new tabelaRehashing();
		tabela.iniciar(100);
		tabela.exibeTabela();
		for (int i = 0; i < tabela.tabela.length; i++) {
			tabela.insereRehashing(random.nextInt());
		}
		tabela.exibeTabela();
		System.out.println(tabela.buscaHashing(1560601399));
		System.out.println(tabela.buscaHashing(-731296268));
		System.out.println(tabela.buscaHashing(-1));
		tabela.tresMaiores();
		tabela.posicoesLivres();

		System.out.println("Quantidade de inserções: " + tabela.insercoes);
		System.out.println("Quantidade de colisões: " + tabela.colisoes);

	}
}


