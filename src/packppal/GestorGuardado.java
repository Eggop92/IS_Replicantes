package packppal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URL;
import java.util.Scanner;

/**
 * Esta clase es una MAE
 * 
 * Esta clase se encarga de la interaccion con ficheros para cargar y guardar una partida en curso
 * 
 * @author Alex
 *
 */
public class GestorGuardado
{

	private static GestorGuardado miGestorGuardado; //Unica instancia de esta clase. Patron Singleton.
	private final String NOMBRE_FICHERO_GUARDADO = "/packrecursos/partidaGuardada.txt";
	private final String NOMBRE_FICHERO_CARGA = "/packrecursos/partidaGuardada.txt";
	
	private GestorGuardado()
	{
		
	}
	
	/**
	 * Getter estatico para hacerse con la unica instancia de esta clase
	 * @return La unica instancia de GestorGuardado
	 */
	public static GestorGuardado getMiGestorGuardado()
	{
		if(miGestorGuardado == null)
		{
			miGestorGuardado = new GestorGuardado();
		}
		
		return miGestorGuardado;
	}
	
	public void guardarPartida()
	{		
		// Variables e inicializaciones para cosas que tienen que ver con
		// escritura del fichero
		URL direccionFichero = null;
		File fichero = null;
		FileWriter escritorFichero = null;
		
		// Creamos URL del fichero
		direccionFichero = getClass().getResource(NOMBRE_FICHERO_GUARDADO);
		
		try
		{
			// Ahora el objeto que maneja el fichero
			fichero = new File(direccionFichero.getPath());
			
			// Creamos aqui el objeto FileWriter
			escritorFichero = new FileWriter(fichero);
			
			String partidaCodif = ListaJugadores.getMiListaJugadores().toString() + Tablero.getMiTablero().toString();
			
			escritorFichero.write(partidaCodif);
			escritorFichero.flush();
			
			escritorFichero.close();
		}
		catch (Exception e)
		{
			System.out.println("Ha fallado la carga del fichero de guardado. Para solucionar este problema sigue los siguientes pasos:");
			System.out.println("Asegurate que existe una carpeta llamada 'packrecursos' en la carpeta del programa");
			System.out.println("Asegurate que dentro de esa carpeta exista un archivo llamado 'partidaGuardada.txt'");
			System.out.println("Vuelve a intentarlo ahora");
			e.printStackTrace();
		}
		
	}
	
	public void cargarPartida()
	{
		//Variables para andar con el fichero
		Scanner lectorFichero = null;
		URL direccionFichero = null;
		File fichero = null;
		
		String[] stringCargada = new String[4];		
		
		
		//Crear URL al fichero
		direccionFichero = getClass().getResource(NOMBRE_FICHERO_CARGA);
	
		//Crear objeto para manejar el fichero
		fichero = new File(direccionFichero.getPath());
			
		try
		{
			lectorFichero = new Scanner(fichero);
			
			//Para cada linea del fichero
			while (lectorFichero.hasNextLine())
			{
				//Extraigo una linea
				stringCargada = lectorFichero.nextLine().split("%");
			}
			
			int numJugadores = Integer.parseInt(stringCargada[0]);
			
			ListaJugadores.getMiListaJugadores().nuevaPartida(numJugadores, stringCargada[1]);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("El fichero de carga no se ha podido abrir, revisa que existe");
			e.printStackTrace();
		}
		catch (NumberFormatException e)
		{
			System.out.println("Algo salio mal al cargar el fichero. Probablemente este corrupto y hayas perdido la partida.");
			e.printStackTrace();
		}		
		
	}	
	
}
