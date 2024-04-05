import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Lims extends JFrame {
    private JPanel panelMensajeBienvenida;
    private Usuario rUsuario;
    private ConsultaUsuario cUsuario;
    private Deposito verDeposito;


    String pathImages = "LIMS/RESOURCES/images/";

    public Lims() {
        initAppUI();

         // Create the welcome panel
        crearPanelMensajeBienvenida();

        // Show the welcome message as a popup
        JOptionPane.showMessageDialog(
            this,
            panelMensajeBienvenida,
            "Bienvenida ",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void initAppUI() {
        createMenuBar();

        setTitle("LIMS");
        setSize(1080, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        java.awt.Image icono = Toolkit.getDefaultToolkit().getImage(pathImages+"lims.png");
        setIconImage(icono);        

    }

    private void crearPanelMensajeBienvenida() {
        panelMensajeBienvenida = new JPanel();
        panelMensajeBienvenida.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelMensajeBienvenida.setBackground(Color.WHITE);
        panelMensajeBienvenida.setPreferredSize(new Dimension(500, 100));

        JLabel mensajeBienvenida = new JLabel("Bienvenido a ¡LIMS!");
        mensajeBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeBienvenida.setVerticalAlignment(SwingConstants.CENTER);
        mensajeBienvenida.setFont(new Font("Arial", Font.BOLD, 30));
        panelMensajeBienvenida.add(mensajeBienvenida);
    }

    private void createMenuBar() {
        
        JMenuBar menuBar = new JMenuBar();
        
         /*Creación de la opción Indicadores y sus opciones */
        ImageIcon depositoIcon = new ImageIcon(pathImages+"usuario.png");
        JMenu depositoMenu = new JMenu("Deposito");
        depositoMenu.setMnemonic(KeyEvent.VK_I);

        JMenuItem eMenuItemdeposito = new JMenuItem("Deposito", depositoIcon);     
        eMenuItemdeposito.setMnemonic(KeyEvent.VK_R);
        eMenuItemdeposito.setToolTipText("Consultar Deposito");
        eMenuItemdeposito.addActionListener((event) -> Deposito());         
        
        /*Creación de la opción Productores y sus opciones */
        ImageIcon usuarioIcon = new ImageIcon(pathImages+"usuario.png");
        JMenu UsuarioMenu = new JMenu("Usuario");
        UsuarioMenu.setMnemonic(KeyEvent.VK_P);

        JMenuItem eMenuItemRegUsuario = new JMenuItem("Registrar", usuarioIcon);     
        eMenuItemRegUsuario.setMnemonic(KeyEvent.VK_R);
        eMenuItemRegUsuario.setToolTipText("Registrar Usuario");
        eMenuItemRegUsuario.addActionListener((event) -> registrarUsuario()); 

        JMenuItem eMenuItemListUsuario = new JMenuItem("Lista Usuarios", usuarioIcon);     
        eMenuItemListUsuario.setMnemonic(KeyEvent.VK_R);
        eMenuItemListUsuario.setToolTipText("Listar Usuarios");
        eMenuItemListUsuario.addActionListener((event) -> consultarUsuario()); 

        /*Creación de la opción Depósito y sus opciones */
        JMenu DepositoMenu = new JMenu("Deposito");
        DepositoMenu.setMnemonic(KeyEvent.VK_D);

       
       /*Creación de la opción configuración y sus opciones */
        JMenu configApp = new JMenu("Configuración");
        configApp.setMnemonic(KeyEvent.VK_F);      

        JMenuItem eMenuItemSalir = new JMenuItem("Salir", new ImageIcon(pathImages+"exit.png"));     
        eMenuItemSalir.setMnemonic(KeyEvent.VK_S);
        eMenuItemSalir.setToolTipText("Salir de Aplicación");
        eMenuItemSalir.addActionListener((event) -> System.exit(0));

        configApp.add(eMenuItemSalir);
        UsuarioMenu.add(eMenuItemRegUsuario);
        UsuarioMenu.add(eMenuItemListUsuario);   
        depositoMenu.add(eMenuItemdeposito);                

        menuBar.add(depositoMenu);
        menuBar.add(UsuarioMenu);
        menuBar.add(configApp);

        setJMenuBar(menuBar);
    }

    public void registrarUsuario(){
        rUsuario = new Usuario();
        this.add(rUsuario);      
        rUsuario.Iniciar();
    }    
 
    public void consultarUsuario(){
        cUsuario = new ConsultaUsuario();
        this.add(cUsuario);        
        cUsuario.Iniciar();
    }

    public void Deposito(){
        Deposito nuevoDeposito = new Deposito();
        this.add(nuevoDeposito);
        this.verDeposito = nuevoDeposito;
        this.verDeposito.DepositoGUI();
        this.verDeposito.Iniciar();
        try{
        this.verDeposito.buscarTipo(verDeposito.getNombre());
        } catch (Exception e){System.out.println(e.getMessage());}
    }          

     public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Lims ex = new Lims(); 
            ex.setVisible(true);
        });
    }
}