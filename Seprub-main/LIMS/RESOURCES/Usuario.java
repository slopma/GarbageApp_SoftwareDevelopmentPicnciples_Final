import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Usuario extends JPanel {    
    String pathData = "LIMS/resources/data/";
    String archivoData = "usuario.csv";
    String registroProd;  

    String tipoID;
    String numeroID;
    String nombre;
    String ciudad;
    String direccion;
    String estado;

    private JComboBox<String> cbTipoID;
    private JTextField tfNumeroID;
    private JComboBox<String> cbCiudad;
    private JTextField tfDireccionUsuario;
    private JTextField tfNombreUsuario;

    public Usuario(){
        this.setVisible (false);
        UsuarioGUI();
    }

    public void UsuarioGUI() {
        JButton btNuevoUsuario;
        JButton btRegistrarUsuario;
        JLabel lbCiudad;
        JLabel lbIDUsuario;
        JLabel lbNombreUsuario;
        JLabel lbDireccion;
        JButton btBuscarUsuario;
        JButton btCerrar;

        //construct preComponents
        String[] cbTipoIDItems = {"CEDULA", "CEDULA EXTRANGERIA", "NIT"};
        String[] cbCiudadItems = {"MEDELLÍN", "BOGOTÁ", "CALI"};

        //construct components
        btNuevoUsuario = new JButton ("Nuevo");
        btRegistrarUsuario = new JButton ("Registrar");
        btCerrar = new JButton ("Cerrar");
        btBuscarUsuario = new JButton ("Buscar");
        cbTipoID = new JComboBox<String> (cbTipoIDItems);
        cbCiudad = new JComboBox<String> (cbCiudadItems);        
        lbCiudad = new JLabel ("Ciudad ubicación:");
        tfNombreUsuario = new JTextField (5);
        tfNumeroID = new JTextField (5);
        lbIDUsuario = new JLabel ("ID Usuario:");
        lbNombreUsuario = new JLabel ("Nombre:"); 
        lbDireccion = new JLabel ("Dirección:");
        tfDireccionUsuario = new JTextField (5);
           
        //set components properties
        btNuevoUsuario.setToolTipText ("Nuevo Usuario");
        btRegistrarUsuario.setToolTipText ("Registrar el Usuario");
        cbTipoID.setToolTipText ("Tipo de Identificación");
        tfNombreUsuario.setToolTipText ("Nombre del Usuario");
        tfNumeroID.setToolTipText ("Número de Identificacion");
        lbNombreUsuario.setToolTipText ("Nombre Usuario");
        cbCiudad.setToolTipText ("Cuidad ubicación");
        lbDireccion.setToolTipText ("Dirección de ubicación");
        tfDireccionUsuario.setToolTipText ("Dirección del Usuario");
        btBuscarUsuario.setToolTipText ("Busque un Usuario");
        btCerrar.setToolTipText ("Cerrar registro Usuario");

        //adjust size and set layout
        setLayout (null);
        TitledBorder blackline = BorderFactory.createTitledBorder("   Registro de Usuario  ");
        EmptyBorder marginBorder = new EmptyBorder(50, 50, 200, 50);
        this.setBorder(new CompoundBorder(marginBorder, blackline));       

        //add components
        add (btNuevoUsuario);
        add (btRegistrarUsuario);
        add (cbTipoID);
        add (lbCiudad);
        add (tfNombreUsuario);
        add (tfNumeroID);
        add (lbIDUsuario);
        add (lbNombreUsuario);
        add (cbCiudad);
        add (lbDireccion);
        add (tfDireccionUsuario);
        add (btBuscarUsuario);
        add (btCerrar);

        //set component bounds (only needed by Absolute Positioning)

        btNuevoUsuario.setBounds (560, 495, 100, 25);
        btRegistrarUsuario.setBounds (665, 495, 100, 25);
        btCerrar.setBounds (875, 495, 100, 25);

        lbIDUsuario.setBounds (270, 250, 110, 25);
        cbTipoID.setBounds (385, 250, 160, 25);
        tfNumeroID.setBounds (550, 250, 110, 25);
        btBuscarUsuario.setBounds (665, 250, 100, 25);
        lbNombreUsuario.setBounds (270, 280, 110, 25);
        tfNombreUsuario.setBounds (385, 280, 380, 25);

        lbCiudad.setBounds (270, 310, 110, 25);
        cbCiudad.setBounds (385, 310, 140, 25);
        lbDireccion.setBounds (270, 340, 110, 25);
        tfDireccionUsuario.setBounds (385, 340, 380, 25);

        //Eventos
        btCerrar.addActionListener((event) -> this.Cerrar()) ;
        btRegistrarUsuario.addActionListener((event) -> this.GuardarUsuario()) ;
        btNuevoUsuario.addActionListener((event) -> this.NuevoUsuario()) ;  
    }

    public void GuardarUsuario(){
        tipoID = (String) cbTipoID.getSelectedItem();   
        numeroID = tfNumeroID.getText();
        nombre = tfNombreUsuario.getText();
        ciudad = (String) cbCiudad.getSelectedItem();;
        direccion = tfDireccionUsuario.getText();
        estado = "ACTIVO";    

        registroProd = tipoID+";"+numeroID+";"+nombre+";"+ ciudad +";"+direccion+";"+estado;

        FileWriter fichero = null;
        PrintWriter pw = null;
   
        try
        {
            File file = new File(pathData+archivoData);
            fichero = new FileWriter(pathData+archivoData, true);
            pw = new PrintWriter(fichero); 
 
            if (file.length() == 0) {
                pw.println("tipoID"+";"+"numeroID"+";"+"nombre"+";"+" ciudad"+";"+"direccion"+";"+"estado");
            } 

            pw.println(registroProd);   

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
                if (null != fichero)
                    fichero.close();
           } catch (Exception e2) {
                e2.printStackTrace();
           }
        }

        JOptionPane.showMessageDialog(this, "Registro Usuario Exitoso");
    }

    public void NuevoUsuario(){
        cbTipoID.setSelectedIndex(0);
        tfNumeroID.setText(null);
        cbCiudad.setSelectedIndex(0);
        tfDireccionUsuario.setText(null);
        tfNombreUsuario.setText(null);
    }

    public void SuspenderUsuario(){
        System.out.println("Suspender Usuario");
    }

    public void Cerrar(){
        this.setVisible (false);
    }   

    public void Iniciar(){      
        this.setVisible (true);   
    }

    public static String getNombreUsuario() {
        return null;
    }
}