public class Dataset {
    //criando atributos privados
    private int numExemplos;//quantidade de exemplos contida no conjunto de dados
    private int numAtributos;//quantidade de atributos contida no conjunto de dados
    private int numClasses;//quantidade de classes contida no conjunto de dados
    private String nome;// nome do conjunto de dados
    //metodo construtor
    public Dataset(int nExemplos, int nAtributos, int nClasses, String nome) {
       
        //Chamo o metodo para verificar se o parâmetro recebido é valido
        Experimento.verificadorDeParametrosString(nome);

        if (nExemplos < 0) {
            System.out.println("O numero de exemplos nao pode ser menor que 0");
            System.exit(0);
        }
        else if (nAtributos < 0) {
            System.out.println("O numero de atributos nao pode ser menor que 0");
            System.exit(0);
        }
        else if (nClasses < 0) {
            System.out.println("O numero de classes nao pode ser menor que 0");
            System.exit(0);
        }        
        else 
        {
            this.numExemplos = nExemplos;
            this.numAtributos = nAtributos;
            this.numClasses = nClasses;
            this.nome = nome;
        }
    }

   //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna a quatidade de exemplos utilizada no conjunto de dados
    public int getNumExemplos() {
        return this.numExemplos;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna a quantidade de atributos do conjunto de dados
    public int getNumAtributos() {
        return this.numAtributos;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna a quantidade de classes do conjunto de dados
    public int getNumClasses() {
        return this.numClasses;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna o nome do conjunto de dados
    public String getNome() {
        return this.nome;
    }

    //=======================================================================================================================
    //=======================================================================================================================

     //Sobrescreve com um método toString para poder retornar as informações do objeto
    @Override
    public String toString() {
        return "-----------Dataset-----------\n\t{" + "\n\tExemplos = " + numExemplos + " \n\tAtributos = " + numAtributos + " \n\tClasses = " + numClasses + " \n\tNome = " + nome + "\n\t}\n-----------------------------";
    } 
    
    //=======================================================================================================================
    //=======================================================================================================================   
}
