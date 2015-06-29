package br.com.leitorarquivo.teste;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class NF40 {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {

		

		/*System.out.println("Commons\n");
		 * System.out.println(new Date(System.currentTimeMillis()).getTime());
		 * loadFileApacheCommons(new
		 * File("D:\\leitor_arquivo\\embasa\\teste.txt"));
		 * System.out.println(new Date(System.currentTimeMillis()).getTime());
		 * 
		 * System.out.println("----------------------------------------------\n")
		 * ;
		 */
		
		Path path = Paths.get("D:\\leitor_arquivo\\embasa\\teste.txt");
		try (Stream<String> stream = Files.lines(Paths.get("D:\\leitor_arquivo\\embasa\\teste.txt"))) {
		    stream
		        .filter(line -> line.contains("826200000154    879400478209    499036590713    590000000000"))
		        .map(String::trim)
		        .forEach(System.out::println);
		}
		
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    long countPrints = reader
		        .lines()
		        .filter(line -> line.contains("826200000154    879400478209    499036590713    590000000000"))
		        .count();
		    System.out.println(countPrints);
		}

		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(new Locale("pt", "br"))));

		System.out.println("NIO2\n");

		System.out.println(LocalDateTime.now());

		System.out.println(new Date(System.currentTimeMillis()).getTime());

		loadFileNio2("D:\\leitor_arquivo\\embasa\\teste.txt");

		System.out.println(LocalDateTime.now());

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

			Integer posicaoInicial = count - 86;

			Integer posicaoFinal = count + 6;

			count = count + 6;

			List<String> lines = new ArrayList<String>();

			for (Integer i = posicaoInicial; i < posicaoFinal; i++) {

				lines.add(linhas.get(i.intValue()));
			}

			Files.write(Paths.get("D:\\leitor_arquivo\\embasa\\" + System.nanoTime() + ".txt"), lines, Charset.defaultCharset());

			System.out.println(lines.toString());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void loadFileApacheCommons(File file) {

		LineIterator it = null;

		List<String> linhas = new ArrayList<String>();

		try {

			it = FileUtils.lineIterator(file);

			while (it.hasNext()) {

				String line = it.nextLine();

				linhas.add(line);

			}

			String codigoBarras = "826200000154    879400478209    499036590713    590000000000";

			Integer count = 0;

			for (String string : linhas) {
				
				count++;

				if (string.contains(codigoBarras)) {

					break;
				}

			}

			Integer posicaoInicial = count - 86;

			Integer posicaoFinal = count + 6;

			count = count + 6;

			List<String> lines = new ArrayList<String>();

			for (Integer i = posicaoInicial; i < posicaoFinal; i++) {

				lines.add(linhas.get(i.intValue()));
			}

			Files.write(Paths.get("D:\\leitor_arquivo\\embasa\\" + System.nanoTime() + ".txt"), lines, Charset.defaultCharset());

			System.out.println(lines.toString());

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			LineIterator.closeQuietly(it);
		}
	}

}
