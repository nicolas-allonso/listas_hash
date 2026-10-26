import java.util.Random;

class tabelaHashEcd {
	
	No[] tabela;
	
	class No {

		int chave;
        No[] proximo;
		
		public No(int chave) {
			this.chave = chave;
		}

        void mudarChave(int chave) {
            this.chave = chave;
        }

        void mudarProximo(No[] proximo) {
            this.proximo = proximo;
        }

	}
	
	void iniciar(int p) {
		this.tabela = new No[p];
		for (int i = 0; i < p; i++) {
        	tabela[i] = new No(-1);
    	}
	}
	
	int funcaoHash(int chave) {
		int i = chave % tabela.length;
		if (i < 0) {
			i = i + tabela.length;
		}
		return i;
	}
	
	void insereEcd(int chave) {
		
        int i = funcaoHash(chave);
        No[] novoProximo;

		if (tabela[i].chave != -1) {
            if (tabela[i].proximo != null) {
                No insercao = new No(chave);
                novoProximo = new No[tabela[i].proximo.length + 1];
                int indiceInsercao = 0;

                for (int j = 0; j < tabela[i].proximo.length && tabela[i].proximo[j].chave < chave; j++) {
                    novoProximo[j] = tabela[i].proximo[j];
                    indiceInsercao++;
                }

                novoProximo[indiceInsercao] = insercao;
                indiceInsercao++;

                for (int k = indiceInsercao; k < novoProximo.length; k++) {
                    novoProximo[k] = tabela[i].proximo[k - 1];
                }
                
            } else {
                novoProximo = new No[1];
                novoProximo[0] = new No(chave);
            }
            
            tabela[i].mudarProximo(novoProximo);

		} 
        
        else {
            tabela[i] = new No(chave);
        }

	}
	
	int buscaEcd(int chave) {
		int i = funcaoHash(chave);
		if (tabela[i].chave == -1) {
			return -1;
		} else {
			return i;
		}
	}
	
	void exibeTabela() {
		for (int i = 0; i < this.tabela.length; i++) {
			System.out.println("Entrada: " + i + " | " + tabela[i].chave + " | ");
            if (tabela[i].proximo != null) {
                System.out.print("Lista atrelada: ");
                for (int j = 0; j < tabela[i].proximo.length; j++) {
                    System.out.print(" | " + tabela[i].proximo[j].chave);
                }
            }
            System.out.println();
		}
		System.out.println();
	}

    void exibeInfo() {
        No[] maiorLista = tabela[0].proximo;

        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i].proximo != null) {
                if (maiorLista.length <= tabela[i].proximo.length) {
                    maiorLista = tabela[i].proximo;
                }
            }
        }

        System.out.println(" - MAIOR LISTA ENCONTRADA - ");

        for (int j = 0; j < maiorLista.length; j++) {
            System.out.print(" | " + maiorLista[j].chave);
        }

        // QUANTIDADE DE LISTA GERADAS

        // QUANTAS POSIÇÕES FICARAM VAZIAS

        // TRÊS ÚLTIMOS VALORES

    }

}

class HashEcd {
    public static void main(String[] args) {

        Random random = new Random(1090);

        tabelaHashEcd tabela = new tabelaHashEcd();
        tabela.iniciar(100);

        tabela.exibeTabela();

        for (int i = 0; i < 1000; i++) {
			tabela.insereEcd(random.nextInt(0, 100000));
		}

        tabela.exibeTabela();    
        tabela.exibeInfo();

    }

}
