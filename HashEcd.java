import java.util.Random;

class tabelaHashEcd {
	
	No[] tabela;
    int[] ultimosValores = new int[3];
    int quantidadeUltimos = 0;
	
	class No {

		int chave;
        No proximo;
		
		public No(int chave) {
			this.chave = chave;
		}

        void mudarChave(int chave) {
            this.chave = chave;
        }

        void mudarProximo(No proximo) {
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

		if (tabela[i].chave != -1) {
            if (quantidadeUltimos < 3) {
                ultimosValores[quantidadeUltimos] = chave;
                quantidadeUltimos++;
            } else {
                ultimosValores[0] = ultimosValores[1];
                ultimosValores[1] = ultimosValores[2];
                ultimosValores[2] = chave;
            }

            No insercao = new No(chave);

            if (tabela[i].proximo != null && tabela[i].proximo.chave < chave) {
                No atual = tabela[i].proximo;

                while (atual.proximo != null && atual.proximo.chave < chave) {
                    atual = atual.proximo;
                }

                insercao.mudarProximo(atual.proximo);
                atual.mudarProximo(insercao);
            } else {
                insercao.mudarProximo(tabela[i].proximo);
                tabela[i].mudarProximo(insercao);
            }

		} 
        
        else {
            tabela[i] = new No(chave);
        }

	}
	
	int buscaEcd(int chave) {
		int i = funcaoHash(chave);
		if (tabela[i].chave == chave) {
			return i;
		}
		No atual = tabela[i].proximo;
		int posicao = 1;
		while (atual != null) {
			if (atual.chave == chave) {
                System.out.print("Posição na lista: " + posicao + " | ");
                return i;
			}
			atual = atual.proximo;
			posicao++;
		}
		return -1;
	}
	
	void exibeTabela() {
		for (int i = 0; i < this.tabela.length; i++) {
			System.out.println("Entrada: " + i + " | " + tabela[i].chave + " | ");
            if (tabela[i].proximo != null) {
                System.out.print("Lista atrelada: ");
                No atual = tabela[i].proximo;
                while (atual != null) {
                    System.out.print(" | " + atual.chave);
                    atual = atual.proximo;
                }
            }
            System.out.println();
		}
		System.out.println();
	}

    void exibeInfo() {
        No maiorLista = tabela[0].proximo;
        int tamanhoMaior = 0;

        for (No n = maiorLista; n != null; n = n.proximo) {
            tamanhoMaior++;
        }

        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i].proximo != null) {
                int tamanhoAtual = 0;
                for (No n = tabela[i].proximo; n != null; n = n.proximo) {
                    tamanhoAtual++;
                }
                if (tamanhoMaior <= tamanhoAtual) {
                    maiorLista = tabela[i].proximo;
                    tamanhoMaior = tamanhoAtual;
                }
            }
        }

        System.out.println(" - MAIOR LISTA ENCONTRADA - ");

        for (No n = maiorLista; n != null; n = n.proximo) {
            System.out.print(" | " + n.chave);
        }

        System.out.println();

        int listasGeradas = 0;

        for (int k = 0; k < tabela.length; k++) {
            if (tabela[k].chave != -1 && tabela[k].proximo != null) {
                listasGeradas++;
            }
        }

        System.out.println(" - NÚMERO DE LISTAS GERADAS: " + listasGeradas);

        int posicoesVazias = 0;

        for (int l = 0; l < tabela.length; l++) {
            if (tabela[l].chave == -1) {
                posicoesVazias++;
            }
        }

        System.out.println(" - NÚMERO DE POSIÇÕES VAZIAS: " + posicoesVazias);

        System.out.print(" - TRÊS ÚLTIMOS VALORES INSERIDOS: ");
        for (int p = 0; p < ultimosValores.length; p++) {
            System.out.print(" | " + ultimosValores[p]);
        }

        System.out.println();

    }

}

class HashEcd {
    public static void main(String[] args) {

        Random random = new Random(1090);

        tabelaHashEcd tabela = new tabelaHashEcd();
        tabela.iniciar(100);

        tabela.exibeTabela();

        for (int i = 0; i < 1000; i++) {
			tabela.insereEcd(random.nextInt(0, 100001));
		}

        tabela.exibeTabela();    
        System.out.println(tabela.buscaEcd(9099));
        System.out.println(tabela.buscaEcd(87599));
        tabela.exibeInfo();

    }

}