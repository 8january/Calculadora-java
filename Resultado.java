package zero;

import java.util.ArrayList;

public class Resultado {

	private ArrayList<Double> numeros = new ArrayList<Double>();
	private ArrayList<String> operacoes = new ArrayList<String>();

	private String contaMatematica;

	public String calcula(String contaMatematica) {

		this.contaMatematica = contaMatematica;
		preencherArraylist();

		return calcular();

	}

	private void preencherArraylist() {

		String numbers = "0";

		for (int i = 0; i < contaMatematica.length(); i++) {
			char c = contaMatematica.charAt(i);

			if (Character.isDigit(c) || c == ',') {
				numbers += c;
			} else {
				operacoes.add(String.valueOf(c));
				numeros.add(Double.parseDouble(numbers.replace(",", ".").replaceFirst("\\.", "")));

				numbers = "0";
			}
		}
		
		numeros.add(Double.parseDouble(numbers.replace(",", ".")));


	}

	private String calcular() {

		String resultadoFinal = "0";

		if (operacoes.contains("*") || operacoes.contains("/") || operacoes.contains("%"))
			calcularMultiplicacaoEDivisaoNaArraylist();

		if (operacoes.size() >= 2)
			resultadoFinal = calcularSomaESubtracaoNaArraylist();
		else if (numeros.size() == 1) {
			resultadoFinal = String.valueOf(numeros.get(0));
			operacoes.removeAll(operacoes);
			numeros.removeAll(numeros);
		}

		return String.format("%.2f", Double.parseDouble(resultadoFinal));

	}

	private int contaOperacoesDeMultiplicacaoEDivisao() {
		int contagem = 0;

		for (int i = 0; i < operacoes.size(); i++) {
			if (operacoes.get(i).equals("*") || operacoes.get(i).equals("/") || operacoes.get(i).equals("%"))
				contagem++;
		}

		return contagem;
	}

	private int[] contaPosicoesComMultiplicacaoEDivisao() {

		int[] posicoes = new int[contaOperacoesDeMultiplicacaoEDivisao()];
		int flag = 0;

		for (int i = 0; i < operacoes.size(); i++) {
			if (operacoes.get(i).equals("*") || operacoes.get(i).equals("/") || operacoes.get(i).equals("%")) {
				posicoes[flag] = i;
				flag++;
			}

		}

		return posicoes;

	}

	private void calcularMultiplicacaoEDivisaoNaArraylist() {
		try {
			int contagem = contaOperacoesDeMultiplicacaoEDivisao();
			int[] posicoes = new int[contagem];

			posicoes = contaPosicoesComMultiplicacaoEDivisao();

			for (int indice = 0; indice < contagem; indice++) {
				if (numeros.size() < 2) {
					break;
				}
				switch (operacoes.get(posicoes[indice])) {
				case "/":
					numeros.set(posicoes[indice], numeros.get(posicoes[indice]) / numeros.get(posicoes[indice] + 1));
					break;
				case "*":
					numeros.set(posicoes[indice], numeros.get(posicoes[indice]) * numeros.get(posicoes[indice] + 1));
					break;
				case "%":
					numeros.set(posicoes[indice],
							numeros.get(posicoes[indice]) / 100 * numeros.get(posicoes[indice] + 1));
					break;
				}

				removeOpAndNumber(posicoes[indice]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String calcularSomaESubtracaoNaArraylist() {

		double resultado = numeros.get(0);
		for (int i = 0; i < operacoes.size(); i++) {
			switch (operacoes.get(i)) {
			case "+":
				resultado += numeros.get(i + 1);
				break;
			case "-":
				resultado -= numeros.get(i + 1);
				break;
			}
		}

		operacoes.clear();
		numeros.clear();
		numeros.add(resultado);

		return String.valueOf(resultado);
	}

	private void removeOpAndNumber(int index) {
		operacoes.remove(index);
		if (numeros.size() > index + 1) {
			numeros.remove(index + 1);
		}
	}

}
