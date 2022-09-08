import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Pelicula{

	String titulo;
	String beneficios;
	int beneficiosInt;
	
	public static Set<Pelicula> listaPeliculas = new HashSet<>();
	public static Set<Integer> listaBeneficios = new TreeSet<>();
	
	public Pelicula(String titulo, String beneficios) {
		this.titulo = titulo;
		this.beneficios = beneficios;
		listaPeliculas.add(this);
		listaBeneficios.add(this.calcularBeneficios());
	}
	
	private int calcularBeneficios() {
		String string = beneficios;
		string = string.replace("$", "");
		string = string.replace(",", "");
		int number = Integer.parseUnsignedInt((String) string.subSequence(0, 4));
		beneficiosInt = number;
		return number;
	}
	
	public static ArrayList<Pelicula> ordenar() {
		LinkedHashSet<Pelicula> peliculasOrdenadas = new LinkedHashSet<>();
		for (Integer integer : listaBeneficios) {
			for (Pelicula pelicula : listaPeliculas) {
				if (pelicula.beneficiosInt == integer) {
					peliculasOrdenadas.add(pelicula);
				}
			}
		}
		//https://stackoverflow.com/questions/49963235/how-to-reverse-order-of-values-in-setstring
		ArrayList<Pelicula> list = new ArrayList<>(peliculasOrdenadas);
		Collections.reverse(list);
		return list;
	}
	
	@Override
	public String toString() {
		return titulo + " 		" + beneficios;
	}
	
}


public class AA13 {
	
	public static void main (String [] args) 
	{
		new Pelicula("Avatar", "$2,847,397,339");
		new Pelicula("Avengers: Endgame", "$2,797,501,328");
		new Pelicula("Titanic", "$2,201,647,264");
		new Pelicula("Star Wars: Episode VII - The Force Awakens", "$2,069,521,700");
		new Pelicula("Avengers: Infinity War", "$2,048,359,754");
		new Pelicula("Spider-Man: No Way Home", "$1,910,675,428");
		new Pelicula("Jurassic World", "$1,671,537,444");
		new Pelicula("The Lion King", "$1,663,250,487");
		new Pelicula("The Avengers", "$1,518,815,515");
		new Pelicula("Furious 7", "$1,515,341,399");
		File documento = new File("Peliculas_11_20.txt");
		try {
			Scanner sc = new Scanner(documento);
			int i = 0;
			while (sc.hasNextLine())
			{
				String line = sc.nextLine();
				//System.out.println(line);
				if (++i > 1 && line != "") {
					//https://stackoverflow.com/questions/2168831/why-cant-i-split-a-string-with-the-dollar-sign
					String[] parts = line.split("\\$");
					String part1 = parts[0]; // 123
					String part2 = "$" + parts[1]; // 654321
					new Pelicula(part1, part2);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		List<String> lineas = new ArrayList<String>();
		//list is now in reverse order of the keySet
		for (Pelicula pelicula : Pelicula.ordenar()) {
			String string = ++i + ".- " + pelicula.toString();
			lineas.add(string);
			if (i <= 10) {
				System.out.println(string);
			}
		}
		try
		{
			String nombreArchivo = "top20_mejores_peliculas.txt";
			Path file = Paths.get(nombreArchivo);
			Files.write(file, lineas, StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			System.out.println("Ocurrió un error");
			e.printStackTrace();
		}
		try
		{
			String nombreArchivo = "Jenkinsfile";
			Path file = Paths.get(nombreArchivo);
			lineas = new ArrayList<String>();
			lineas.add("def date = new Date()");
			lineas.add("");
			lineas.add("pipeline {");
			lineas.add("	agent any");
			lineas.add("	stages {");
			lineas.add("		stage ('HolaMundo') {");
			lineas.add("			steps {");
			lineas.add("				echo \"Hola Mundo! EL día de hoy es elDia \" + date.format('dd-MM-yyyy') + \". Este curso me hizo programar mas de lo que me hubiese gustado\"");
			lineas.add("			}");
			lineas.add("		}");
			lineas.add("	}");
			lineas.add("}");
			Files.write(file, lineas, StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			System.out.println("Ocurrió un error");
			e.printStackTrace();
		}
	}

}
