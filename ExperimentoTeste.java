/*
	Sistema de apoio a pesquisadores na área de Aprendizado de Maquina (AM)
	Autores:
	-Guilherme Ribeiro Correa Garcia
	-Demetrius Moreira Panovitch
	-Natália de Mello Alves
	-Alvaro Elias Abdalla Barbosa

	Engenharia de Software - Facom UFMS - 2019
*/

public class ExperimentoTeste
{	
	//Aqui serão testadas todas funcionalidades de cada classe
	public static void main(String[] args) 
	{	
		//=================================================================================================
		//=================================================================================================
		System.out.println("\n---------------------------------------------------------------------------\n");
		System.out.println("+ Resultados sao separados por duas linhas de \"=\" de tamanho Medio;\n"+
						   "+ Resumo de Experimento eh separado por duas linhas de \"=\" de tamanho Longo;\n"+
						   "+ Avaliacoes sao separadas por uma linha de  \"*\" de tamanho Curto;\n"+
						   "+ ValorAvaliacao, MedidaAvaliacao, Algoritmo, Dataset sao separados por uma linha de \"-\" de tamanho Curto;\n"+
						   "+ Os testes das funcionalidades de cada classe sao separados por duas linhas de \"#\" de tamanho Longo;\n"+
						   "+ Cada {} determina a abertura e fechamento de um bloco de parametros.");
		System.out.println("\n---------------------------------------------------------------------------\n");		
		//=================================================================================================
		//=================================================================================================


		float [] parametros = {1,2,3};
		//=================================================================================================
		//==================================TestesVálidosAlgoritmo=========================================
		System.out.println("\n###########################################################################");
		System.out.println("###########################################################################\n");

		Algoritmo objetoAlgoritmo1 = new Algoritmo("Algoritmo1", parametros);
		Algoritmo objetoAlgoritmo2 = new Algoritmo("Algoritmo2", parametros);	
		System.out.println(objetoAlgoritmo1.getNome());
		for (int contador = 0; contador < 3; contador++) 
		{
			System.out.println(objetoAlgoritmo1.getParametros()[contador]);
		}

		//Total = 2 Métodos

		//=================================================================================================
		//====================================TestesVálidosDataset=========================================
		System.out.println("\n###########################################################################");
		System.out.println("###########################################################################\n");

		Dataset objetoDataset1 = new Dataset(1,2,3,"Dataset1");
		Dataset objetoDataset2 = new Dataset(1,2,3,"Dataset2");	
		System.out.println(objetoDataset1.getNumExemplos());		
		System.out.println(objetoDataset1.getNumAtributos());		
		System.out.println(objetoDataset1.getNumClasses());		
		System.out.println(objetoDataset1.getNome());	

		//Total = 4 Métodos

		//=================================================================================================
		//================================TestesVálidosMedidaAvaliacao=====================================
		System.out.println("\n###########################################################################");
		System.out.println("###########################################################################\n");

		MedidaAvaliacao objetoMedida1 = new MedidaAvaliacao("Medida1", 1, 10);
		MedidaAvaliacao objetoMedida2 = new MedidaAvaliacao("Medida2", 1, 10);
		MedidaAvaliacao objetoMedida3 = new MedidaAvaliacao("Medida3", 1, 10);	
		System.out.println(objetoMedida1.getNome());
		System.out.println(objetoMedida1.getMenorValor());
		System.out.println(objetoMedida1.getMaiorValor());

		//Total = 3 Métodos

		//=================================================================================================
		//=================================TestesVálidosValorAvaliacao=====================================	
		System.out.println("\n###########################################################################");
		System.out.println("###########################################################################\n");

		ValorAvaliacao valor1 = new ValorAvaliacao(objetoMedida1);
		ValorAvaliacao valor2 = new ValorAvaliacao(objetoMedida2);
		ValorAvaliacao valor3 = new ValorAvaliacao(objetoMedida3);
		ValorAvaliacao valor4 = new ValorAvaliacao(objetoMedida1);
		//O valor 4 foi inserido apenas para que haja distinção das medias para os valores
		valor4.setValor(10);			
		valor1.setValor(1);
		valor2.setValor(2);
		valor3.setValor(3);	
		System.out.println(valor1.getValor());	
		System.out.println(valor1.getMedida());	
		System.out.println(valor1.getNomeDaMedida());

		//Total = 4 Métodos(3 obrigatórios)

		//=================================================================================================
		//=================================TestesVálidosValorResultado=====================================
		System.out.println("\n###########################################################################");
		System.out.println("###########################################################################\n");

		Resultado resultado1 = new Resultado(objetoAlgoritmo1, objetoDataset1);
		Resultado resultado2 = new Resultado(objetoAlgoritmo1, objetoDataset1);
		Resultado resultado3 = new Resultado(objetoAlgoritmo2, objetoDataset1);
		Resultado resultado4 = new Resultado(objetoAlgoritmo1, objetoDataset2);
		Resultado resultado5 = new Resultado(objetoAlgoritmo2, objetoDataset2);		
		resultado1.addAvaliacao(valor2);
		resultado1.addAvaliacao(valor3); 
		resultado2.addAvaliacao(valor1);
		resultado2.addAvaliacao(valor2);
		resultado2.addAvaliacao(valor3);
		resultado3.addAvaliacao(valor4);
		resultado3.addAvaliacao(valor2);
		resultado3.addAvaliacao(valor3); 
		resultado4.addAvaliacao(valor1);
		resultado4.addAvaliacao(valor2);
		resultado4.addAvaliacao(valor3); 
		resultado5.addAvaliacao(valor1);
		resultado5.addAvaliacao(valor2);
		resultado5.addAvaliacao(valor3);
		System.out.println(resultado1.addAvaliacao(valor1)); 		
		for (int contador = 0; contador < 3; contador++) 
		{
			System.out.println(resultado1.getAvaliacoes()[contador]);
		}	
		System.out.println(resultado1.getAlgoritmo());
		System.out.println(resultado1.getDataset());
		System.out.println(resultado1.getNomeDoAlgoritmo());
		System.out.println(resultado1.getNomeDoDataset());
		System.out.println(resultado1.getAvaliacaoPorMedida("Medida2"));

		//Total = 7 Métodos

		//=================================================================================================
		//==================================TestesVálidosExperimento=======================================
		System.out.println("\n###########################################################################");
		System.out.println("###########################################################################\n");

		//Observe o resumo experimento e verifique pelo resumo se os outros métodos estão corretos
		Experimento experimento1 = new Experimento(11,06,2019);
		experimento1.addResultado(resultado1);
		experimento1.addResultado(resultado2);
		experimento1.addResultado(resultado3);
		experimento1.addResultado(resultado4);
		System.out.println(experimento1.addResultado(resultado5)); 
		experimento1.imprimeResumoExperimento();	
		System.out.println(experimento1.getMediaResultados("Medida1"));
		for (int contador = 0; contador < 10; contador++) 
		{
			System.out.println(experimento1.getValoresResultados("Medida1")[contador]);	
		}
		System.out.println(experimento1.getMediaResultadosPorAlgoritmo("Algoritmo1", "Medida1"));
		System.out.println(experimento1.getMediaResultadosPorDataset("Dataset1", "Medida1"));
		System.out.println(experimento1.getMelhorResultado("Medida1"));
		System.out.println(experimento1.getMelhorResultadoPorAlgoritmo("Algoritmo1", "Medida1"));
		System.out.println(experimento1.getMelhorResultadoPorDataset("Dataset1", "Medida1"));
		System.out.println(experimento1.getPiorResultado("Medida1"));
		System.out.println(experimento1.getPiorResultadoPorAlgoritmo("Algoritmo1", "Medida1"));
		System.out.println(experimento1.getPiorResultadoPorDataset("Dataset1", "Medida1"));
		System.out.println(experimento1.getDatasetMelhorResultado("Medida2"));		
		System.out.println(experimento1.getDatasetMelhorResultadoAlgoritmo("Algoritmo1", "Medida1"));	
		System.out.println(experimento1.getAlgoritmoMelhorResultado("Medida1"));	
		System.out.println(experimento1.getAlgoritmoMelhorResuladoDataset("Dataset1", "Medida1"));	
		for (int contador = 0; contador < 10; contador++) 
		{
			System.out.println(experimento1.getNomesAlgoritmosUtilizados()[contador]);
		}		

		//Total = 17 Métodos

		System.out.println("\n###########################################################################");
		System.out.println("###########################################################################\n");
		//=================================================================================================
		//=================================================================================================		
	}
}