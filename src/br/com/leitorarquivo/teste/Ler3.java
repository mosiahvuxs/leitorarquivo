package br.com.leitorarquivo.teste;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class Ler3 {

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("Commons\n");

		System.out.println(new Date(System.currentTimeMillis()).getTime());
		loadFileApacheCommons(new File("D:\\leitor_arquivo\\embasa\\teste.txt"));
		System.out.println(new Date(System.currentTimeMillis()).getTime());

		System.out.println("----------------------------------------------\n");

		System.out.println("NIO2\n");
		System.out.println(new Date(System.currentTimeMillis()).getTime());
		loadFileNio2("D:\\leitor_arquivo\\embasa\\teste.txt");
		System.out.println(new Date(System.currentTimeMillis()).getTime());

	}

	public static void loadFileNio2(String path) {

		Path txt = Paths.get(path);

		List<String> linhas;

		try {

			linhas = Files.readAllLines(txt, StandardCharsets.ISO_8859_1);

			String codigoBarras = "826200000154    879400478209    499036590713    590000000000";

			Integer count = 0;

			for (String string : linhas) {

				count++;

				if (string.contains(codigoBarras)) {

					break;
				}

			}

			Integer posicaoInicial = count - 85;

			count = count + 6;

			int countAux = 1;

			StringBuilder textoParaCopiar = new StringBuilder();
			
			List<String> lines = new ArrayList<String>();

			for (String texto : linhas) {

				if (posicaoInicial.intValue() <= countAux || count.intValue() <= countAux) {

					if (textoParaCopiar.toString().trim() != null && textoParaCopiar.toString().length() > 0) {

						textoParaCopiar.append("\n" + texto);

					} else {

						textoParaCopiar.append(texto);
					}
					
					lines.add(texto);

				}

				if (count.intValue() == countAux) {

					break;
				}

				countAux++;

			}
			
			Files.write(Paths.get("D:\\leitor_arquivo\\embasa\\" + System.nanoTime() + ".txt"), lines, Charset.defaultCharset());

			System.out.println(textoParaCopiar);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void loadFileApacheCommons(File file) {

		LineIterator it = null;

		List<String> textoLido = new ArrayList<String>();

		try {

			it = FileUtils.lineIterator(file);

			while (it.hasNext()) {

				String line = it.nextLine();

				textoLido.add(line);

			}

			String codigoBarras = "826200000154    879400478209    499036590713    590000000000";

			Integer count = 0;

			for (String string : textoLido) {

				count++;

				if (string.contains(codigoBarras)) {

					break;
				}
			}

			Integer posicaoInicial = count - 85;
			count = count + 6;

			int countAux = 1;

			StringBuilder textoParaCopiar = new StringBuilder();

			for (String texto : textoLido) {

				if (posicaoInicial.intValue() <= countAux || count.intValue() <= countAux) {

					if (textoParaCopiar.toString().trim() != null && textoParaCopiar.toString().length() > 0) {

						textoParaCopiar.append("\n" + texto);

					} else {

						textoParaCopiar.append(texto);
					}

				}

				if (count.intValue() == countAux) {

					break;
				}

				countAux++;

			}

			System.out.println(textoParaCopiar);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			LineIterator.closeQuietly(it);
		}
	}

}
