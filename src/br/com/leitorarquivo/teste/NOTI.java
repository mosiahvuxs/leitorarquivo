package br.com.leitorarquivo.teste;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class NOTI {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {

		loadFileNio2("D:\\leitor_arquivo\\noti\\teste.txt");

	}

	public static void loadFileNio2(String path) {

		Path txt = Paths.get(path);

		List<String> linhas;

		try {

			linhas = Files.readAllLines(txt, StandardCharsets.ISO_8859_1);

			System.out.println(Arrays.asList(linhas));

			String codigoBarras = "826700001173    685400478408    282935841605    615000000005 ";

			Integer count = 0;

			for (String string : linhas) {

				count++;

				if (string.contains(codigoBarras)) {

					break;
				}

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
