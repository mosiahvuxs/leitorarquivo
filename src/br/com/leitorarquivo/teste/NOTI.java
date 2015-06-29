package br.com.leitorarquivo.teste;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

			String[] parts = linhas.toString().split("[\\W][ ]");

			System.out.println(Arrays.asList(parts));

			String codigoBarras = "826700000175    989800478400    282919301600    615000000005";

			Integer count = 0;

			boolean encontrou = false;

			for (String string : linhas) {

				count++;

				if (string.contains(codigoBarras)) {

					encontrou = true;

					break;
				}

			}

			if (encontrou) {

				Integer posicaoFinal = count;

				List<String> lines = new ArrayList<String>();

				for (Integer i = posicaoFinal; i > 0; i--) {

					lines.add(linhas.get(i.intValue()));

					if (linhas.get(i.intValue()).trim().equals("0")) {

						break;

					}

				}

				Collections.reverse(lines);

				for (Integer i = posicaoFinal; i < linhas.size(); i++) {

					lines.add(linhas.get(i.intValue()));

					if (linhas.get(i.intValue()).trim().equals("0")) {

						break;

					}

				}

				Files.write(Paths.get("D:\\leitor_arquivo\\noti\\" + System.nanoTime() + ".txt"), lines, Charset.defaultCharset());

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
