public class ValorAvaliacao {
    //criando atributos privados
    private float valor;
    private MedidaAvaliacao medida;
    //metodo construtor
    public ValorAvaliacao(MedidaAvaliacao medida) {
        if (medida == null) {
            System.out.println("Nao foi possivel identificar a medida de avaliacao");
            System.exit(0);
        }
        else 
        {
            this.medida = medida;
        }
    }
    //=======================================================================================================================
    //=======================================================================================================================
    //esse metodo verifica e, se estiver dentro do intervalo limitado pela medida, atribui o valor do parametro à variavel valor,
    // além de retornar o valor boolean
    public boolean setValor(float valor) {
        if (valor > medida.getMaiorValor() || valor < medida.getMenorValor()) {
            System.out.println("Impossivel atribuir o valor especificado");
            return false;
        } else {
            this.valor = valor;
            return true;
        }
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna o valor atribuido a variável valor
    public float getValor() {
        return this.valor;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //esse metodo retorna a medida utilizada na avaliação
    public MedidaAvaliacao getMedida() {
        return this.medida;
    }

    //=======================================================================================================================
    //=======================================================================================================================

    /*
    esse metodo eu fiz pra usar na classe Resultado, no metodo getAvaliacaoPorMedida, pra facilitar na hora de comparar os nomes
    (comparar o nome da medida que ta em uma das posiçoes do vetor com o nome que o usuario colocou como parametro).
    */
    public String getNomeDaMedida() {
        return medida.getNome();
    }

    //=======================================================================================================================
    //=======================================================================================================================

    //Sobrescreve com um método toString para poder retornar as informações do objeto
    @Override
    public String toString() {
        return "-------ValorAvaliacao--------\n\t{" + "\n\tValor = " + valor + " \n" + medida + "\n\t}\n-----------------------------";
    }

    //=======================================================================================================================
    //=======================================================================================================================
}
