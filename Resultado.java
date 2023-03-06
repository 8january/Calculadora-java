package zero;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class Resultado {

	private ArrayList<Double> numeros = new ArrayList<Double>();
	private ArrayList<String> operacoes = new ArrayList<String>();

	private String contaMatematica;

	public String calcula(String contaMatematica) {

		this.contaMatematica = contaMatematica.replace(" ", "");
		if (contaMatematica.equals("=") || naoPossuiNumeros(contaMatematica))
			return "";

		preencherArraylist();
		return calcular();

	}

	public String formatarNumero(double numero) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');

		DecimalFormat formato = new DecimalFormat("###,###.##", simbolos);
		return formato.format(numero);
	}

	public boolean naoPossuiNumeros(String stringContaMatematica) {

		for (int i = 0; i < stringContaMatematica.length(); i++) {
			char c = stringContaMatematica.charAt(i);
			if (Character.isDigit(c))
				return false;
		}

		return true;
	}

	private void preencherArraylist() {

		String numbers = "";

		for (int i = 0; i < contaMatematica.length(); i++) {
			char c = contaMatematica.charAt(i);

			if (Character.isDigit(c) || c == ',') {
				numbers += c;
			} else {
				operacoes.add(String.valueOf(c));
				numeros.add(Double.parseDouble(numbers.replace(",", ".")));

				numbers = "";
			}
		}

	}

	private String calcular() {
		String resultadoFinal = "";

		if ((operacoes.contains("×") || operacoes.contains("÷") || operacoes.contains("%")))
			calcularMultiplicacaoEDivisaoNaArraylist();

		if (operacoes.size() > 1)
			resultadoFinal = calcularSomaESubtracaoNaArraylist();
		else {
			resultadoFinal = String.valueOf(numeros.get(0));
			operacoes.removeAll(operacoes);
			numeros.removeAll(numeros);
		}

		return formatarNumero(Double.parseDouble(resultadoFinal));
	}

	private void calcularMultiplicacaoEDivisaoNaArraylist() {
		try {
			while (operacoes.contains("×") || operacoes.contains("÷") || operacoes.contains("%")) {
				int index = -1;
				for (int i = 0; i < operacoes.size(); i++) {
					if (operacoes.get(i).equals("×") || operacoes.get(i).equals("÷") || operacoes.get(i).equals("%")) {
						index = i;
						break;
					}
				}
				if (index != -1) {
					switch (operacoes.get(index)) {
					case "÷":
						numeros.set(index, numeros.get(index) / numeros.get(index + 1));
						break;
					case "×":
						numeros.set(index, numeros.get(index) * numeros.get(index + 1));
						break;
					case "%":
						numeros.set(index, numeros.get(index) / 100 * numeros.get(index + 1));
						break;
					}
					removeOpAndNumber(index);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String calcularSomaESubtracaoNaArraylist() {

		for (int i = 0; i < operacoes.size(); i++) {

			switch (operacoes.get(0)) {
			case "+":
				numeros.set(0, numeros.get(0) + numeros.get(1));
				break;
			case "-":
				numeros.set(0, numeros.get(0) - numeros.get(1));
				break;
			}

			removeOpAndNumber(0);
		}

		String result = String.valueOf(numeros.get(0));
		operacoes.removeAll(operacoes);
		numeros.removeAll(numeros);

		return result;

	}

	private void removeOpAndNumber(int index) {
		operacoes.remove(index);
		numeros.remove(index + 1);
	}

}