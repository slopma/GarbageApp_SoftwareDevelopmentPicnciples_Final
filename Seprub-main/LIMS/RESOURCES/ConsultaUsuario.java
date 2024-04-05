import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ConsultaUsuario extends JPanel {  
    String pathData = "LIMS/RESOURCES/data/";
    String archivoData = "usuario.csv"; 
 
    String tipoID;
    String numeroID;
    String nombre;
    String cuidad;
    String direccion;
    String estado;
    Object[][] dataUsuario ={};;
    String[] headerLista = 
            {   "Tipo ID",
                "Número ID",
                "Nombre",
                "Cuidad",
                "Dirección",
                "Estado"
            };

    private JComboBox<String> cbCuidad;
    private JTextField tfNombreUsuario;
        
    public ConsultaUsuario(){
        this.setVisible (false);
        ConsultarUsuarioGUI();
    }

    public void ConsultarUsuarioGUI() {
        JLabel lbCuidad;
        JLabel lbNombreUsuario;
        JButton btFiltrarUsuario;
        JButton btCerrar;
        

        //construct preComponents
        String[] cbCuidadItems = {"Medellín", "Envigado", "Itaguí"};

        JPanel jpUsuario = new JPanel(new GridLayout());
        jpUsuario.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Lista Usuario", TitledBorder.CENTER, TitledBorder.TOP));
        //JTable jtUsuario = new JTable(dataUsuario, headerLista);
        
        JTable jtUsuario = new JTable();
        JScrollPane jsUsuario = new JScrollPane(jtUsuario);   

        //construct components
        btCerrar = new JButton ("Cerrar");
        btFiltrarUsuario = new JButton ("Consultar");
        cbCuidad = new JComboBox<String> (cbCuidadItems);        
        lbCuidad = new JLabel ("Cuidad");
        tfNombreUsuario = new JTextField (5);
        lbNombreUsuario = new JLabel ("Nombre:"); 
          
        //set components properties
        tfNombreUsuario.setToolTipText ("Nombre del Usuario");
        cbCuidad.setToolTipText ("Cuidad");
        btFiltrarUsuario.setToolTipText ("Filtrar Usuario");
        btCerrar.setToolTipText ("Cerrar registro Usuario");

        //adjust size and set layout
        setLayout (null);
        TitledBorder blackline = BorderFactory.createTitledBorder("   Consulta de Usuario  ");
        EmptyBorder marginBorder = new EmptyBorder(20, 50, 620, 50);
        this.setBorder(new CompoundBorder(marginBorder, blackline)); 

        final DefaultTableModel dtmUsuario = (DefaultTableModel)jtUsuario.getModel();
        dtmUsuario.addColumn(headerLista[0]);              	 
		dtmUsuario.addColumn(headerLista[1]);					
		dtmUsuario.addColumn(headerLista[2]);
        dtmUsuario.addColumn(headerLista[3]);              	 
		dtmUsuario.addColumn(headerLista[4]);					
		dtmUsuario.addColumn(headerLista[5]);

        TableColumnModel columnModel = jtUsuario.getColumnModel();
        jtUsuario.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        columnModel.getColumn(0).setPreferredWidth(120);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(300);
        columnModel.getColumn(5).setPreferredWidth(100); 

        //add components
        jpUsuario.add(jsUsuario);    
        add (jpUsuario);     
        add (lbCuidad);
        add (tfNombreUsuario);
        add (lbNombreUsuario);
        add (cbCuidad);
        add (btFiltrarUsuario);
        add (btCerrar);
    
        //set component bounds (only needed by Absolute Positioning)
        jpUsuario.setBounds (50, 115, 960, 500);
        btCerrar.setBounds (890, 640, 100, 25);
        btFiltrarUsuario.setBounds (890, 60, 100, 25);
        lbNombreUsuario.setBounds (70, 60, 50, 25);
        tfNombreUsuario.setBounds (125, 60, 300, 25);
        lbCuidad.setBounds (435, 60, 50, 25);
        cbCuidad.setBounds (490, 60, 140, 25);  

        //Eventos
        btCerrar.addActionListener((event) -> this.Cerrar()) ;
        btFiltrarUsuario.addActionListener((event) -> this.Consultar(dtmUsuario));
    }

    public void Consultar(DefaultTableModel dtmUsuario){
        nombre = tfNombreUsuario.getText();
        cuidad = (String) cbCuidad.getSelectedItem();;
        estado = "ACTIVO";   

        String registro = "";  
        String splitBy = ";";  
        int row = 0;

        for (int i = dtmUsuario.getRowCount() -1; i >= 0; i--){      
            dtmUsuario.removeRow(i);
        }

        try
        {            
            BufferedReader brProd = new BufferedReader(new FileReader(pathData+archivoData));   
            brProd.readLine();

            while ((registro = brProd.readLine()) != null)     
            {  
                String[] productor = registro.split(splitBy);    //use punto y coma como separador 
                dtmUsuario.addRow(new Object[0]);
                dtmUsuario.setValueAt(productor[0], row,0);							
                dtmUsuario.setValueAt(productor[1], row,1);							
                dtmUsuario.setValueAt(productor[2], row,2); 
                dtmUsuario.setValueAt(productor[3], row,3);  
                dtmUsuario.setValueAt(productor[4], row,4);
                dtmUsuario.setValueAt(productor[5], row,5); 
                row++;                        
             }  

             brProd.close();
        }   
        catch (IOException e) {  
            e.printStackTrace();  
        }          
    }

    public void Cerrar(){
        //System.out.println("Cerrar Ventana");   
        this.setVisible (false);
    }   

    public void Iniciar(){   
        this.setVisible (true);   
    }
}