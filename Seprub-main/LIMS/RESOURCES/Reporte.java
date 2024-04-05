import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Reporte {
    private String nombre;
    private String fechaString;
    private LocalDate fechaActual;
    private Deposito deposito;

    public Reporte(Deposito deposito) {
        System.out.println("¡Se ha creado correctamente el reporte!");
        System.out.println(" - El nombre de deposito de entrada es " + deposito.getNombre());
        this.deposito = deposito;
        this.fechaActual = LocalDate.now();
        this.fechaString = fechaActual.toString();
        System.out.println(" - La fecha actual es " + this.fechaString);
        this.nombre = deposito.getNombre();
        System.out.println(" - El nombre para el reporte es " + this.nombre);
        this.guardarReporteCSV();
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fechaString;
    }

    public void guardarReporteCSV(){

        File file = new File ("Reporte.csv");
        PrintWriter output = null;
        try {

            output = new PrintWriter(new FileWriter(file, true));

            output.println(this.nombre + ";" + this.fechaString);

            System.out.println(" Reporte guardado correctamente ");

        } catch (Exception e){System.out.println(e.getMessage());} finally {if (output != null ){output.close();}}

    }



}