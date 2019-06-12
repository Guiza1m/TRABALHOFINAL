public class Resultado {
    //criando atributos privados
    private Algoritmo algoritmo;//algoritmo utilizado no resultado
    private Dataset dataset;//dataset utilizado no resultado
    private ValorAvaliacao[] avaliacao = new ValorAvaliacao[3];//vetor de avaliaçoes obtidas no resultado
    //metodo construtor
    public Resultado(Algoritmo algoritmo, Dataset dataset) {
        if (algoritmo == null || dataset == null) {
            System.out.println("Nao foi possivel identificar o algoritmo ou o dataset");
            System.exit(0);
        }

        else {
            this.algoritmo = algoritmo;
            this.dataset = dataset;
        }
    }

    //=======================================================================================================================
    //=======================================================================================================================
  
    //esse metodo retorna o vetor de avaliações adicionadas ao resultado
    public ValorAvaliacao[] getAvaliacoes() 
    {        
        return this.avaliacao;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna os dados do algoritmo utilizado 
    public Algoritmo getAlgoritmo() {
        return this.algoritmo;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna o dataset utilizado
    public Dataset getDataset() {
        return this.dataset;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna o nome do algoritmo utilizado
    public String getNomeDoAlgoritmo() {
        return algoritmo.getNome();
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna o nome do dataset utilizado
    public String getNomeDoDataset() {
        return dataset.getNome();
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo adiciona uma avaliação no vetor de avaliaçoes, desde que as condições do métodos sejam obedecidas
    public boolean addAvaliacao(ValorAvaliacao avaliacao) 
    {
        if (avaliacao == null) 
        {
            //Se o parametro for nulo ou vazio, encerro o programa
            System.out.println("Nao foi possivel identificar o parametro.");
            System.exit(0);
        }
        //Retorna true se tudo estiver correto        
        boolean existe = true;
       
        //Variável que armazena a posição vazia no vetor this.avaliacao
        int posicaoVazia = -1;          

        //---------------------------------------------------------------------------------------------------------------      
        
        for (int i = 0; i < this.avaliacao.length && existe == true; i++) 
        {           
            //Armazeno a posição vazia apenas se nenhuma posição vazia foi instanciada ainda
            if(this.avaliacao[i] == null && posicaoVazia == -1)
                posicaoVazia = i;

            if(this.avaliacao[i] != null)
            {
                //Verifico se já existe uma avaliação com esse objeto medida
                if (this.avaliacao[i].getMedida().equals(avaliacao.getMedida())) 
                {
                    //Já existe
                    existe = false;
                    System.out.println("Impossivel adicionar avaliacao, ja existe uma avaliacao para essa medida");
                }
                //Verificar se existe um avaliação com o mesmo nome de medida e mesmo valor
                else if (this.avaliacao[i].getNomeDaMedida().equals(avaliacao.getNomeDaMedida()) && this.avaliacao[i].getValor() == avaliacao.getValor()) 
                {
                    existe = false;
                    System.out.println("Impossivel adicionar avaliacao, ja existe uma avaliacao com mesmo valor e mesma medida");
                }
                else if (this.avaliacao[i].getNomeDaMedida().equals(avaliacao.getNomeDaMedida())) 
                {
                    System.out.println("Impossivel adicionar avaliacao, ja existe uma avaliacao com mesmo nome de medida");
                    existe = false;
                }
            }
        }     
        //Se posicaoVazia for igual a -1, o vetor se encontra cheio
        if(posicaoVazia == -1)
        {            
            existe = false;
            System.out.println("Impossivel adicionar avaliacao, o limite de avaliacoes foi excedido");
        }
        
        //---------------------------------------------------------------------------------------------------------------

        //Adiciona ao vetor this.avaliacao o avaliacao se tudo estiver correto
        if(existe == true)
        {
            this.avaliacao[posicaoVazia] = avaliacao;
        }
        
        //Retorna o boolean
        return existe;
    }    

    //=======================================================================================================================
    //=======================================================================================================================

   //esse metodo retorna os dados da avaliação de acordo com o nome da medida que foi especificado como parametro
    public ValorAvaliacao getAvaliacaoPorMedida(String medida) 
    {
        //Chamo o metodo para verificar se o parâmetro recebido é valido
        Experimento.verificadorDeParametrosString(medida);

        boolean auxiliar = false;

        //Variável que armazena a posição do vetor de Avaliações a qual possui mesmo nome de medida que o parâmetro passado
        int auxiliar2 = -1;

        //Percorrendo o vetor de Avaliacões
        for (int i = 0; i < this.avaliacao.length && auxiliar == false; i++) 
        {            
            //Sendo a avaliação não nula e com nome de medida igual a medida passada como parâmetro
            if (this.avaliacao[i] != null && this.avaliacao[i].getNomeDaMedida().equals(medida)) 
            {
                auxiliar = true;
                auxiliar2 = i;
            }
            else
            {
                //Se chegar aqui, então não foi encontrada nenhuma avaliação com essa medida passada como parâmetro
                auxiliar = false;
            }
        }

        //Se o auxiliar é true, então foi encontrada uma avaliação com tal medida passada como parâmetro
        if (auxiliar == true) 
        {
            return this.avaliacao[auxiliar2];
        }
        //Se não encontrar retorna null
        else
        {           
            return null;
        }
    }

    //=======================================================================================================================
    //=======================================================================================================================
     @Override
    public String toString() {
        String retorno = 
        "\n=======================================\n" +
        "===============Resultado===============\n{"  + 
                           "\n" + algoritmo + 
                           "\n" + dataset + 
        "\n\n*********Avaliacao 1*********\n" + avaliacao[0] + 
        "\n\n*********Avaliacao 2*********\n" + avaliacao[1] + 
        "\n\n*********Avaliacao 3*********\n" + avaliacao[2] + 
        "\n}\n======================================="+
        "\n=======================================";
        return retorno;
    }

    //=======================================================================================================================
    //=======================================================================================================================
}
