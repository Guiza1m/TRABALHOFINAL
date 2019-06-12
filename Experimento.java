public class Experimento {
	private int dia;
	private int mes;
	private int ano;
	private Resultado [] resultado = new Resultado [10];

	public Experimento(int dia, int mes, int ano) 
	{		
		//Chamarei um método para verificar se a data é válida que me retorna um boolean
		if (dataEhValida(dia, mes, ano) == false) 
		{
			System.out.println("Data invalida");
			System.exit(0);
		}
		else 
		{
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
		}
	}

    //=======================================================================================================================
    //=======================================================================================================================

    	//Esse metodo irá verificar parâmetros de todas as classes, validando-os
    	public static void verificadorDeParametrosString(String parametro)
    	{
    		//Se o parametro for nulo ou vazio, encerro o programa
    		if (parametro == null || parametro.equals("")) 
    		{
    			System.out.println("Nao foi possivel identificar o parametro.");
    			System.exit(0);
    		}
    	}    

    //=======================================================================================================================
    //=======================================================================================================================

	public boolean addResultado(Resultado resultado) 
	{
		if (resultado == null) 
		{
			//Se o parametro for nulo ou vazio, encerro o programa
			System.out.println("Nao foi possivel identificar o parametro.");
    		System.exit(0);
		}

		//Variável booleana que será retornada
		boolean retorno = true;	
				
		//=================================================Verificação==========================================================

		boolean verificador = false;		
		for (int contador = 0; contador < this.resultado.length && retorno == true; contador++)
		{	
			//Verifico se algum resultado no vetor this.resultado já é igual ao resultado que eu desejo adicionar
			if(this.resultado[contador] != null && this.resultado[contador].equals(resultado))
			{							
				System.out.println("Nao foi possivel adicionar o resultado no experimento, ja existe um resultado equivalente a esse adicionado.");
				retorno = false;				
			}		
			//Verifico se o resultado não excede 10 resultados, verificando se há algum espaço nulo no vetor resultado
			if (this.resultado[contador] == null && verificador == false && retorno == true) 
			{
				verificador = true;
			}			
		}

		//Se o verificador for igual a 0 indica que não encontrou nenhum espaço nulo no vetor para ser adicionado(CHEIO)
		if(verificador == false && retorno == true)
		{
			System.out.println("Nao foi possivel adicionar o resultado no experimento, o limite de resultados foi excedido.");
			retorno = false;
		}		
			
		//=======================================================================================================================
		
		boolean iteracao = true;
		//Adiciono o resultado
		if (retorno == true) 
		{
			//Percorro o vetor resultado até achar um espaço nulo
			for (int i = 0; i < this.resultado.length && iteracao == true; i++) 
			{
				//Adiciono o resultado passado como parâmetro no vetor resultado
				if (this.resultado[i] == null) 
				{
					this.resultado[i] = resultado;	
					//Paro a iteração
					iteracao = false;
				}	
			}
		}
				
		//Retorno o boolean
		return retorno;			
	}	

	//=======================================================================================================================
    //=======================================================================================================================

	public float getMediaResultados(String medida) 
	{
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);

		//vetor que recebera o vetor do metodo getValoresResultados
		float [] auxiliar = getValoresResultados(medida);
		
		//auxiliar que contara as posiçoes validas do vetor
		int aux = 0;

		//variavel que guardara a soma das posiçoes validas do vetor
		float soma = 0;

		//iterando sobre o vetor auxiliar
		for (int i = 0; i < auxiliar.length; i++) 
		{
			//se a posição for valida:
			if (auxiliar[i] != Float.MAX_VALUE) 
			{
				//essa posição é somada
				soma += auxiliar[i];
				//e a variavel de posiçoes validas é incrementada
				aux++;
			}
		}
		//retorna a divisao da soma pela qtd de posiçoes validas
		if (aux == 0) 
		{
			return Float.MAX_VALUE;
		}
		else
		{
			return soma / aux;
		}
	}

	//=======================================================================================================================
    //=======================================================================================================================

	public float[] getValoresResultados(String medida) 
	{
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);

		//Para cada resultado, há 1 valor que pode ser adicionado com o nome de medida, logo há 10 valores possíveis
		float[] vetorRetorno = new float[10];

		//Preencho o vetorRetorno com Float.MAX_VALUE
		for (int i = 0; i < vetorRetorno.length; i++) 
		{
			vetorRetorno[i] = Float.MAX_VALUE;
		}
		
		//Variável que armazena a posição vazia do vetorRetorno
		int posicao = 0;
		//Percorro o vetor resultados		
		for (int i = 0; i < resultado.length; i++) 
		{
			//Verifico se há algo nulo
			if (resultado[i] != null && resultado[i].getAvaliacaoPorMedida(medida) != null) 
			{
				//Se getAvaliacaoPorMedida for diferente de null, isso indica que existe uma avaliação com a medida passada pertencente a esse resultado
				//Logo eu armazeno essa avaliação
				vetorRetorno[posicao] = resultado[i].getAvaliacaoPorMedida(medida).getValor();
				posicao++;									
			}	
		}

		return vetorRetorno;
	}

	//=======================================================================================================================
    //=======================================================================================================================

	public float getMediaResultadosPorAlgoritmo(String algoritmo, String medida)
	{
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(algoritmo);
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);

		//Variavel que armazena quantos resultados possuem o mesmo dataset e medida
		int quantidadeDeResultados = 0;
		//Variavel que armazena a soma das medidas
		float somaDasMedidas = 0;
		//Variavel que armazena a media
		float media = 0;

		//Variavel que verifica se foi ou não encontrado o resultado
		boolean encontrado = false;

		//Pesquiso no vetor resultado se existe um resultado correspondente ao algoritmo
		//Variável que armazenará quantos valores existem		
		for (int contador = 0; contador < resultado.length; contador++) 
		{
			//Verifico se resultado[contador] não é nulo e se a avaliacao retornada não é nula
			if (resultado[contador] != null && resultado[contador].getAvaliacaoPorMedida(medida) != null) 
			{		
				//Verifico se o algoritmo no vetor resultado é igual ao algoritmo parâmetro
				//A medida não necessita ser verificada pois ao usar o getAvaliacaoPorMedida, se ele retorna diferente de nulo indica
				//que existe naquele resultado uma avaliação com o nome de medida igual a medida parâmetro 
				if (resultado[contador].getNomeDoAlgoritmo().equals(algoritmo)) 
				{										
					somaDasMedidas += resultado[contador].getAvaliacaoPorMedida(medida).getValor();	
					//Conto quantos resultados possuem com esse algoritmo e essa medida		
					quantidadeDeResultados++;													
					encontrado = true;					
				}
			}
		}

		//Faço a media
		media = somaDasMedidas / quantidadeDeResultados;

		//Se encontrado for falso eu retorno Float.MAX_VALUE pois não foi encontrado um resultado com mesmo algoritmo e mesma media
		if (encontrado == false)
		media = Float.MAX_VALUE; 

		//Retorno a media
		return media;
	}

	//=======================================================================================================================
    //=======================================================================================================================

	public float getMediaResultadosPorDataset(String dataset, String medida)
	{
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(dataset);
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);

		//Variavel que armazena quantos resultados possuem o mesmo dataset e medida
		int quantidadeDeResultados = 0;
		//Variavel que armazena a soma das medidas
		float somaDasMedidas = 0;
		//Variavel que armazena a media
		float media = 0;

		//Variavel que verifica se foi ou não encontrado o resultado
		boolean encontrado = false;

		//Pesquiso no vetor resultado se existe um resultado correspondente ao dataset
		for (int contador = 0; contador < resultado.length; contador++) 
		{
			//Verifico se resultado[contador] não é nulo e se a avaliacao retornada não é nula
			if (resultado[contador] != null && resultado[contador].getAvaliacaoPorMedida(medida) != null) 
			{		
				//Verifico se o dataset no vetor resultado é igual ao dataset parâmetro e se a medida é a mesma
				//A medida não necessita ser verificada pois ao usar o getAvaliacaoPorMedida, se ele retorna diferente de nulo indica
				//que existe naquele resultado uma avaliação com o nome de medida igual a medida parâmetro 
				if (resultado[contador].getNomeDoDataset().equals(dataset)) 
				{									
					somaDasMedidas += resultado[contador].getAvaliacaoPorMedida(medida).getValor();	
					//Conto quantos resultados possuem com esse algoritmo e essa medida		
					quantidadeDeResultados++;													
					encontrado = true;						
				}
			}
		}

		//Faço a media
		media = somaDasMedidas / quantidadeDeResultados;

		//Se encontrado for falso eu retorno Float.MAX_VALUE pois não foi encontrado um resultado com mesmo dataset e mesma media
		if (encontrado == false)
		media = Float.MAX_VALUE; 

		//Retorno a media
		return media;
	}

	//=======================================================================================================================
    //=======================================================================================================================

	public Resultado getMelhorResultado(String medida) 
	{
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);

		//Variavel que armazena o maior valor de resultado
        float maior = -Float.MAX_VALUE;
        //Variavel que armazena a posição do maior valor de resultado
        int posicaoMaior = -1;

        //Percorre o vetor resultado
        for (int contador = 0; contador < resultado.length; contador++) 
        {
        	//Verifica se o resultado ou a avaliação retornada não são nulos
            if (resultado[contador] != null && resultado[contador].getAvaliacaoPorMedida(medida) != null) 
            {
            	//Verifica se o valor da avaliação retornada é maior que o valor referência de maior
                if (resultado[contador].getAvaliacaoPorMedida(medida).getValor() > maior) 
                {
                	//Se for, armazeno o maior valor na variável maior
                    maior = resultado[contador].getAvaliacaoPorMedida(medida).getValor();
                    //Armazeno a posição do maior valor
                    posicaoMaior = contador;
                }
            }
        }

        //Se a posição for igual a -1 significa que a medida não foi utilizada
        if (posicaoMaior == -1) 
            return null;
        //Caso contrario retorno o resultado com maior valor        
        else
            return resultado[posicaoMaior];
    }

    //=======================================================================================================================
    //=======================================================================================================================

    public Resultado getMelhorResultadoPorAlgoritmo(String algoritmo, String medida)
    //sigo basicamente o mesmo algoritmo do metodo getMelhoResultado, porem
    //faço uma comparação a mais, comparando o nome do algoritmo parametro
    //com o nome do algoritmo do iésimo resultado
    {
    	//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(algoritmo);

        float maior = -Float.MAX_VALUE;
        int posicaoMaior = -1;
        for (int i = 0; i < resultado.length; i++) 
        {
            if (resultado[i] != null && resultado[i].getAvaliacaoPorMedida(medida) != null) 
            {
                //aqui é feita a comparação do nome do algoritmo
                //A medida não precisa ser comparada pois como getAvaliacaoPorMedida retorna diferente de nulo, a medida existe no resultado
                if (resultado[i].getNomeDoAlgoritmo().equals(algoritmo)) 
                {
                    if (resultado[i].getAvaliacaoPorMedida(medida).getValor() > maior) 
                    {
                        maior = resultado[i].getAvaliacaoPorMedida(medida).getValor();
                        posicaoMaior = i;
                    }                   
                }
            }
        }

        //Se a posição for -1 indica que não foi achado um resultado com essa medida e algoritmo
        if (posicaoMaior == -1) 
        {
            return null;
        }
        else
        return resultado[posicaoMaior];
    }
    //=======================================================================================================================
    //=======================================================================================================================

    public Resultado getMelhorResultadoPorDataset(String dataset, String medida) 
    //novamente, sigo o mesmo algoritmo getMelhorResultado, porém
    //aqui, compara-se o nome do dataset ao invés do nome do algoritmo
    {
    	//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(dataset);
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);

        float maior = -Float.MAX_VALUE;
        int posicaoMaior = -1;
        for (int i = 0; i < resultado.length; i++) 
        {
            if (resultado[i] != null && resultado[i].getAvaliacaoPorMedida(medida) != null) 
            {
                //aqui é feita a comparação do nome do dataset
                //A medida não precisa ser comparada pois como getAvaliacaoPorMedida retorna diferente de nulo, a medida existe no resultado
                if (resultado[i].getNomeDoDataset().equals(dataset)) 
                {
                    if (resultado[i].getAvaliacaoPorMedida(medida).getValor() > maior) 
                    {
                        maior = resultado[i].getAvaliacaoPorMedida(medida).getValor();
                        posicaoMaior = i;
                    }                   
                }
            }
        }
        if (posicaoMaior == -1) 
        {
            return null;
        }
        else
        return resultado[posicaoMaior];
    }

    //=======================================================================================================================
    //=======================================================================================================================

    public Resultado getPiorResultado(String medida) 
    {
    	//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);

        float menor = Float.MAX_VALUE;//variavel que armazena o valor maximo float (para comparação inicial)
        int posicaoMenor = -1; //armazena a posição do resultado de menor valor
	
        //percorro o vetor
        for (int contador = 0; contador < resultado.length; contador++) {
	
            //verifico se o resultado nao é nulo e se a avaliaçao retornada pelo metodo getAvaliacaoPorMedida tambem nao é nulo
            if (resultado[contador] != null && resultado[contador].getAvaliacaoPorMedida(medida) != null) 
            {
	    		//A medida não precisa ser comparada pois como getAvaliacaoPorMedida retorna diferente de nulo, a medida existe no resultado
                //verifico se o valor dessa avaliação é menor que a menor avaliação armazenada ate o momento
                if (resultado[contador].getAvaliacaoPorMedida(medida).getValor() < menor)
                {
		
                    //se for, esse valor torna-se o menor valor
                    menor = resultado[contador].getAvaliacaoPorMedida(medida).getValor();
                    //e sua posição no vetor de resultados é armazenada
                    posicaoMenor = contador;
                }
            }
        }
        //se a posição for igual a -1 significa que a medida nao foi utilizada
        if (posicaoMenor == -1) {
            return null;//retorno null
        }
        //caso contrario, retorno o respectivo resultado
        else {
            return resultado[posicaoMenor];
        }
    }

    //=======================================================================================================================
    //=======================================================================================================================
    
    public Resultado getPiorResultadoPorAlgoritmo(String algoritmo, String medida) 
    {
    	//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(algoritmo);

        //sigo basicamente o mesmo algoritmo getPiorResultado, porem
        //aqui, comparo tambem o nome do algoritmo parametro com o nome do algoritmo
        //do iésimo resultado
        float menor = Float.MAX_VALUE;
        int posicaoMenor = -1;

        for (int i = 0; i < resultado.length; i++) {
            if (resultado[i] != null && resultado[i].getAvaliacaoPorMedida(medida) != null) {
	    
                //aqui é feita a comparação dos nomes de algoritmo
                //A medida não precisa ser comparada pois como getAvaliacaoPorMedida retorna diferente de nulo, a medida existe no resultado
                if (resultado[i].getNomeDoAlgoritmo().equals(algoritmo)) 
                {
                    if (resultado[i].getAvaliacaoPorMedida(medida).getValor() < menor) 
                    {
                        menor = resultado[i].getAvaliacaoPorMedida(medida).getValor();
                        posicaoMenor = i;
                    }
                }
            }
        }

        if (posicaoMenor == -1) {
            return null;
        }
        else {
            return resultado[posicaoMenor];
        }
    }
    
    //=======================================================================================================================
    //=======================================================================================================================

    public Resultado getPiorResultadoPorDataset(String dataset, String medida) 
    {
    	//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(dataset);

        //sigo novamente o mesmo algoritmo getPiorResultado, porem
        //aqui comparo tambem o nome do dataset parametro com o nome
        //do data set do iésimo resultado
        float menor = Float.MAX_VALUE;
        int posicaoMenor = -1;

        for (int i = 0; i < resultado.length; i++) {
            if (resultado[i] != null && resultado[i].getAvaliacaoPorMedida(medida) != null) {
	    
                //aqui é feita a comparação dos nomes de dataset
                 //A medida não precisa ser comparada pois como getAvaliacaoPorMedida retorna diferente de nulo, a medida existe no resultado
                if (resultado[i].getNomeDoDataset().equals(dataset)) 
                {
                    if (resultado[i].getAvaliacaoPorMedida(medida).getValor() < menor) 
                    {
                        menor = resultado[i].getAvaliacaoPorMedida(medida).getValor();
                        posicaoMenor = i;
                    }
                }
            }
        }

        if (posicaoMenor == -1) {
            return null;
        }
        else {
            return resultado[posicaoMenor];
        }
    }

    //=======================================================================================================================
    //=======================================================================================================================
    
    public Dataset getDatasetMelhorResultado(String medida) 
    {
     	//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);

        //chamo o metodo que retorna o melhor resultado
        Resultado auxiliar = getMelhorResultado(medida);
        if (auxiliar != null) {//se o resultado retornado nao for nulo
            //retorno o dataset utilizado nesse resultado
            return auxiliar.getDataset();
        }
        //se for nulo, retorno nulo
        else
            return null;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    public Dataset getDatasetMelhorResultadoAlgoritmo(String algoritmo, String medida) 
    {
    	//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(algoritmo);

        //chamo o metodo que retorna o melhor resultado por algoritmo
        Resultado auxiliar = getMelhorResultadoPorAlgoritmo(medida, algoritmo);
        //se o resultado retornado for nulo
        if (auxiliar == null)
            return null;//retorno nulo

        //se nao for nulo
        else
            //retorno o dataset utilizado nesse resultado
            return auxiliar.getDataset();
    }

    //=======================================================================================================================
    //=======================================================================================================================

    public Algoritmo getAlgoritmoMelhorResultado(String medida) 
    {
    	//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);

        //chamo o metodo que retorna o melhor resultado
        Resultado auxiliar = getMelhorResultado(medida);
        //se o resultado for nulo
        if (auxiliar == null)
            return null;//retorno nulo

        //se nao for nulo
        else
            //retorno o algoritmo utilizado nesse resultado
            return auxiliar.getAlgoritmo();
    }

    //=======================================================================================================================
    //=======================================================================================================================

    public Algoritmo getAlgoritmoMelhorResuladoDataset(String dataset, String medida) 
    {
    	//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(medida);
		//Chamo o metodo para verificar se o parâmetro recebido é valido
		verificadorDeParametrosString(dataset);

        //chamo o metodo que retorna o melhor resultado por dataset
        Resultado auxiliar = getMelhorResultadoPorDataset(dataset, medida);
        //se o resultado for nulo
        if (auxiliar == null)
            return null;//retorno nulo

        //se nao for nulo
        else
            //retorno o algoritmo utilizado nesse resultado
            return auxiliar.getAlgoritmo();
    }

    //=======================================================================================================================
    //=======================================================================================================================
    
    //metodo auxiliar que auxilia o metodo getNomesAlgoritmosUtilizados
    public boolean existeNoVetor(String nomeDeAlgoritmo, String [] vetor) 
    {
    	if (vetor == null) 
    	{
    		System.out.println("Nao foi possivel identificar o parametro.");
    		System.exit(0);
    	}

        boolean controle = false;//variavel de retorno
        //percorro o vetor enquanto controle == false
        for (int i = 0; controle == false && i < vetor.length; i++) {
            if (vetor[i] != null) {//se a iésima posicao nao for vazia
                if (nomeDeAlgoritmo.equals(vetor[i])) {//e se o nome do algoritmo ja existir no vetor
                    controle = true;//controle recebe true
                }
            }
        }
        //retorno a variavel de controle (se nao houver repetição retorna false, se houver, retorna true)
        return controle;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    public String [] getNomesAlgoritmosUtilizados() 
    {
        //vetor de retorno
        String [] retorno = new String [10];
        //variavel que controla as posições do vetor de retorno
        int auxiliar = 0;
        //percorro o vetor
        for (int i = 0; i < resultado.length; i++) {
            //se o iésimo resultado não for nulo
            if (resultado[i] != null) {
                //uso o metodo auxiliar e armazeno o valor numa variavel auxiliar
                boolean aux = existeNoVetor(resultado[i].getNomeDoAlgoritmo(), retorno);
                //se o retorno do metodo for false
                if (aux == false) {
                    //adiciona o nome do algoritmo no vetor de retorno
                    retorno[auxiliar] = resultado[i].getNomeDoAlgoritmo();
                }
                //se o retorno for true
                else {
                    //adiciona null no vetor de retorno
                    auxiliar--;
                }
            }
            //caso a iésima posição seja nula
            else
                retorno[auxiliar] = null;//tambem adiciona null ao vetor de retorno
            //ao fim da iteração, incrementa em uma unidade a variavel de controle
            //de posições do vetor de retorno
            auxiliar++;
        }
		return retorno;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //metodo que valida a data (utilizado na inicialização do objeto experimento)
    private boolean dataEhValida(int dia, int mes, int ano) 
    {
    	boolean retorno = true;
    	//algoritmo copiado do AVA (validador de datas)
    	if(dia <= 0 || dia >= 32 || mes <= 0 || mes >= 13 || ano < 1)
    	{
    		retorno = false;
    	}
    	else
    	{
    		switch (mes) 
    		{
    			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
    			retorno = true;
    			
    			case 4: case 6: case 9: case 11:
    			if(dia <= 30)     			
    				retorno = true;
    			else 
    				retorno = true;
    			
    			case 2:
    			if(dia <= 28)     			
    				retorno = true;    			
    			else if (dia == 29 && ano % 4 == 0)    			
    				retorno = true;
				else
				 	// dia 30 ou 31 ou dia 29 em anos nao bissextos
					retorno = false;				
			}
		}
		return retorno;
	}	

	//=======================================================================================================================
    //=======================================================================================================================
	public void imprimeResumoExperimento()
	{
		//Mostrar a data da realização dos experimentos
		//Converterei as datas para string para que eu possa formata-las
		String dia, mes, ano;
		dia = Integer.toString(this.dia);
		mes = Integer.toString(this.mes);
		ano = Integer.toString(this.ano);
		if(this.dia < 10)
			dia = "0" + dia;
		if(this.mes < 10)
			mes = "0" + mes;
		if(this.ano < 1000)
		{
			//Se o ano for menor que 1000, conto quantos algarismos ele tem e acrescento 0 na frente da string até completar 4 algarismos
			String anoAux = "";
			for (int cont = 0; cont < 4 - ano.length(); cont++) 
			{
				anoAux += "0";	
			}
			ano = anoAux + ano;
		}
		System.out.println("\n===========================================================================");		
		System.out.println("=================================Resumo====================================");
		//Imprimo a data
		System.out.println("\nData do experimento: " + dia + "/" + mes + "/" + ano + ".");

		//Agora imprimirei os resultados que pelo metodo toString em cada classe já retorna as caracteristicas
		for (int contador = 0; contador < resultado.length; contador++) 
		{
			System.out.println("\nResultado[" + (contador+1) + "]: " + resultado[contador]);	
		}
		System.out.println("\n===========================================================================");		
		System.out.println("===========================================================================");		
	}	

	//=======================================================================================================================
    //=======================================================================================================================
}
