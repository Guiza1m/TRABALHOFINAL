public class Algoritmo 
{
    //criando atributos privados
    //nome do algoritmo
    private String nome;
    //parametros que sao utilizados nesse algoritmo
    private float[] parametros = new float [3];

    //=======================================================================================================================
    //=======================================================================================================================

    //metodo construtor, que receberá uma String (nome do algoritmo) e um vetor de floats (parametros do algoritmo)
    public Algoritmo(String nome, float[] parametros) 
    {
        //Chamo o metodo para verificar se o parâmetro recebido é valido
        Experimento.verificadorDeParametrosString(nome);
        
        if(parametros == null)
        {
            System.out.println("Parametros invalidos!.");
            System.exit(0);
        }

        //Atribuição do atributo nome dessa classe como nome passado por parâmetro
        this.nome = nome;
        
        //Se os vetor parametros passados for menor que o tamanho do vetor this.parametros ele atribui os valores do vetor parametros do metodo chamador ao this.parametros 
        if (parametros.length < this.parametros.length) {

            //Atribui os valores ao this.parametros
            for (int i = 0; i < parametros.length; i++) {
                this.parametros[i] = parametros[i];
            }

            //Os espaços que não foram preenchidos em this.parametros são preenchidos com MAX_VALUE
            for (int i = 1; i <= this.parametros.length - parametros.length; i++) {
                this.parametros[this.parametros.length - i] = Float.MAX_VALUE;
            }
        }
        //Caso os vetores tenham tamanhos iguais ele apenas atribui o mesmo local na memória
        else {
            this.parametros = parametros;
        }
    }

    //=======================================================================================================================
    //=======================================================================================================================

    public String getNome() {
        return this.nome;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    public float[] getParametros() {
        return this.parametros;
    }
    //=======================================================================================================================
    //=======================================================================================================================

    //Sobrescreve com um método toString para poder retornar as informações do objeto
   @Override
    public String toString() {
        return "----------Algoritmo----------\n\t{" + "\n\tNome = " + nome + " \n\tParametro 1 = " + parametros[0] + " \n\tParametro 2 = " + parametros[1] + " \n\tParametro 3 = " + parametros[2] + "\n\t}\n-----------------------------";
    }

    //=======================================================================================================================
    //=======================================================================================================================
}
