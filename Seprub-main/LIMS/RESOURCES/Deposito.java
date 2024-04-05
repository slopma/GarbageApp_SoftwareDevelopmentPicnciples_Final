import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;

public class Deposito extends JPanel {    
    String pathResources = "LIMS\\RESOURCES\\";
    String archivoData = "BaseDatos.csv";
    String reporte;  
    int cantidad;
    String TipoDesecho;
    String nombre;

     JTextField tfnombre;
     JTextField tfcantidad;

    public Deposito(){
        this.setVisible (false);
    }

    public void DepositoGUI() {
        JButton btConsultarDeposito;
        JButton btGuardarReporte;
        JLabel lbnombreDesecho;
        JTextField tfdesecho;
        

        //construct components
        JButton ConsultarTipo = new JButton ("TIPO");
        btConsultarDeposito= new JButton ("DEPÓSITO");
        btGuardarReporte =new JButton("Guardar");
        tfcantidad = new JTextField (4);

        lbnombreDesecho= new JLabel ("Nombre desecho: ");
        tfdesecho = new JTextField ("ingrese un desecho");
           
        //set components properties
        ConsultarTipo.setToolTipText ("consulte el tipo de desecho");
        btConsultarDeposito.setToolTipText ("consulte el deposito");
        btGuardarReporte.setToolTipText ("guarda la cantidad del desecho");
        

        //adjust size and set layout
        setLayout (null);
        TitledBorder blackline = BorderFactory.createTitledBorder(" Consulta de desechos ");
        EmptyBorder marginBorder = new EmptyBorder(50, 50, 200, 50);
        this.setBorder(new CompoundBorder(marginBorder, blackline));       

        //add components
        add (ConsultarTipo);
        add (btConsultarDeposito);
        add (lbnombreDesecho);
        add(btGuardarReporte);
        
        add (tfdesecho);
        add(tfcantidad);

        //set component bounds (only needed by Absolute Positioning)

        ConsultarTipo.setBounds (400, 250, 100, 25);
        btConsultarDeposito.setBounds (600, 250, 100, 25);
        lbnombreDesecho.setBounds (240, 200, 150, 25);
        tfdesecho.setBounds (350, 200, 380, 25);
        tfcantidad.setBounds (700, 200, 100, 25);
        btGuardarReporte.setBounds (800, 200, 120, 25);
        //guardar los datos de tftexto
        
        //Eventos
        ConsultarTipo.addActionListener((event) -> {
            try {
                this.nombre = tfdesecho.getText();
                buscarTipo(this.nombre);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        
        btConsultarDeposito.addActionListener((event) -> {
           try {
                this.nombre = tfdesecho.getText();
                buscarDeposito(this.nombre);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btGuardarReporte.addActionListener((event) -> {

            this.nombre = tfdesecho.getText();
            new Reporte(this);

        });
}
    public String getNombre(){
        return this.nombre;
    }

    public void guardar(){
        this.cantidad = Integer.parseInt(tfcantidad.getText());  
        this.nombre = tfnombre.getText();
        this.reporte = this.nombre+";"+this.cantidad+";";
    }

    public void Cerrar(){
        this.setVisible (false);
    }   
    public void Iniciar(){      
        this.setVisible (true);
    }

    
    public void buscarTipo(String nombre) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader (pathResources + "BaseDatos.csv"));
        this.setVisible (true);
        JFrame ventana = new JFrame("Información del desecho");
        ventana.setLayout(new FlowLayout());
      
        while (bufferedReader.ready()) {
          String line = bufferedReader.readLine();
      
          String[] datos = line.split(";");
      
          nombre = nombre.toLowerCase();
      
          if (datos[0].toLowerCase().equals(nombre)) {
            JLabel lbNombre = new JLabel("Nombre: ");
            JLabel lbTipo = new JLabel("Tipo: ");
      
            JTextField tfNombre = new JTextField(datos[0]);
            JTextField tfTipo = new JTextField(datos[1]);
      
            ventana.add(lbNombre);
            ventana.add(tfNombre);
            ventana.add(lbTipo);
            ventana.add(tfTipo);
      
            ventana.setSize(300, 100);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
          }
        }
      
        bufferedReader.close();
      }

    public void buscarDeposito(String nombre) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathResources + "BaseDatos.csv"));
        this.setVisible (true);
        JFrame ventana = new JFrame("Información del desecho");
        ventana.setLayout(new FlowLayout());
      
        while (bufferedReader.ready()) {
          String line = bufferedReader.readLine();
      
          String[] datos = line.split(";");
      
          nombre = nombre.toLowerCase();
      
          if (datos[0].toLowerCase().equals(nombre)) {
            JLabel lbNombre = new JLabel("Nombre: ");
            JLabel lbTipo = new JLabel("Deposito: ");
      
            JTextField tfNombre = new JTextField(datos[0]);
            JTextField tfTipo = new JTextField(datos[2]);
      
            ventana.add(lbNombre);
            ventana.add(tfNombre);
            ventana.add(lbTipo);
            ventana.add(tfTipo);
      
            ventana.setSize(300, 100);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
          }
        }
         bufferedReader.close();
      }

   



}