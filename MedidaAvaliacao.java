public class MedidaAvaliacao {
    //criando atributos privados
    private String nome;//nome da medida de avaliação
    private float menorValor;//menor valor do intervalo válido para uma avaliação
    private float maiorValor;//maior valor do intervalo válido para uma avaliação

    //metodo construtor
    public MedidaAvaliacao(String nome, float menorValor, float maiorValor) {
        
        //Chamo o metodo para verificar se o parâmetro recebido é valido
        Experimento.verificadorDeParametrosString(nome);

        if (menorValor >= maiorValor){
            System.out.println("O menor valor nao pode ser maior que o maior valor.");
            System.exit(0);
        }
        else if (menorValor < 0){
            System.out.println("O menor valor nao pode ser menor que 0");
            System.exit(0);
        }
        else if (maiorValor < 0){
            System.out.println("O maior valor nao pode ser menor que 0");
            System.exit(0);
        }
        else {
            this.nome = nome;
            this.menorValor = menorValor;
            this.maiorValor = maiorValor;
        }
    }

    //=======================================================================================================================
    //=======================================================================================================================
    
    //esse método retorna o nome da medida de avaliação
    public String getNome() {
        return this.nome;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna o menor valor do intervalo válido para uma avaliação
    public float getMenorValor() {
        return this.menorValor;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse método retorna o maior valor do intervalo válido para uma avaliação
    public float getMaiorValor() {
        return this.maiorValor;
    }

    //=======================================================================================================================
    //=======================================================================================================================    

     @Override
    public String toString() {
        return "-------MedidaAvaliacao-------\n\t{" + "\n\tNome = " + nome + " \n\tMenorValor = " + menorValor + " \n\tMaiorValor = " + maiorValor + "\n\t}\n-----------------------------";
    }

    //=======================================================================================================================
    //=======================================================================================================================
}
