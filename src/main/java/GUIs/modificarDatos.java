/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Negocio.BOs.ClienteBO;
import Negocio.BOs.UsuarioBO;
import Negocio.DTOs.ClienteCompletoDTO;
import Negocio.DTOs.UsuarioDTO;
import Negocio.Excepciones.negocioException;
import fabricaClienteBO.iniciarUsuario;
import fabricaClienteBO.registrarCliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import persistencia.Dominio.Cliente;
import persistencia.Dominio.Sesion;
import persistencia.Dominio.Usuario;
import persistencia.conexion.ConexionBD;
import persistencia.conexion.IConexionBD;

/**
 * pantalla para que el cliente entre y modifique sus datos
 * @author luiscarlosbeltran
 */
public class modificarDatos extends JFrame {
    
    /**
     * metodo que crea y muestra la pantalla modificarDatos
     */
    public modificarDatos() {
        
       IConexionBD conexion = new ConexionBD();

        // llamamos al metodo para crear el BO con los DAOs para no usarlos en el GUI
        ClienteBO clienteBO = registrarCliente.registrarCliente();
        //llamamos al metodo para poder usar el consultar usuario por usuario 
        UsuarioBO usuarioBO = iniciarUsuario.iniciarUsuario();

        setTitle("pantalla inicio sesion");
        setSize(700, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //---- parte de lo azul que va arriba ----
        JPanel arriba = new JPanel();
        arriba.setBackground(new Color(70, 150, 220)); //el color azul;

        //el texto
        JLabel textoArriba = new JLabel("Gorillaz Pipsa");
        textoArriba.setFont(new Font("Serif", Font.BOLD, 50));
        
        //mete texto a la parte de arriba y mete la parte de arriba a la ventana
        arriba.add(textoArriba);
        add(arriba, BorderLayout.NORTH);
        

        
        //parte del centro

        JPanel centro = new JPanel(new GridLayout(0, 2, 15, 15));

        //para meter nombres
        JLabel n = new JLabel("Nombres");
        n.setFont(new Font("Serif", Font.BOLD, 20));
        n.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(n);
        
        JTextField nombres = new JTextField("");
        nombres.setFont(new Font("Serif", Font.BOLD, 20));
        nombres.setMaximumSize(new Dimension(400, 50));
        nombres.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(nombres);

        //para meter apellido paterno
        JLabel ap = new JLabel("Apellido Paterno");
        ap.setFont(new Font("Serif", Font.BOLD, 20));
        ap.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(ap);
        
        JTextField aPaterno = new JTextField("");
        aPaterno.setFont(new Font("Serif", Font.BOLD, 20));
        aPaterno.setMaximumSize(new Dimension(400, 50));
        aPaterno.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(aPaterno);

        //para meter apellido materno (opcional)
        JLabel am = new JLabel("Apellido Materno");
        am.setFont(new Font("Serif", Font.BOLD, 20));
        am.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(am);
        
        JTextField aMaterno = new JTextField("");
        aMaterno.setFont(new Font("Serif", Font.BOLD, 20));
        aMaterno.setMaximumSize(new Dimension(400, 50));
        aMaterno.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(aMaterno);

        //para la fecha de nacimiento
        JLabel fechanacimientoLabel = new JLabel("Fecha de Nacimiento YYYY-MM-DD");
        fechanacimientoLabel.setFont(new Font("Serif", Font.BOLD, 20));
        JTextField fechanacimiento = new JTextField();
        fechanacimiento.setFont(new Font("Serif", Font.BOLD, 20));

        centro.add(fechanacimientoLabel);
        centro.add(fechanacimiento);

        //para la edad
        JLabel edadLabel = new JLabel("Edad");
        edadLabel.setFont(new Font("Serif", Font.BOLD, 20));
        JTextField edad = new JTextField();
        edad.setFont(new Font("Serif", Font.BOLD, 20));

        centro.add(edadLabel);
        centro.add(edad);

        //para meter el telefono(s)
        JLabel cphone = new JLabel("Telefono(s)");
        cphone.setFont(new Font("Serif", Font.BOLD, 20));
        cphone.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(cphone);
        
        JTextField telefono = new JTextField("");
        telefono.setFont(new Font("Serif", Font.BOLD, 20));
        telefono.setMaximumSize(new Dimension(400, 50));
        telefono.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(telefono);

        //para poner la etiqueta de dl telefono
        JLabel etiquetaTelefonoLabel = new JLabel("Etiqueta de Teléfono");
        etiquetaTelefonoLabel.setFont(new Font("Serif", Font.BOLD, 20));
        JTextField etiquetaTelefono = new JTextField();
        etiquetaTelefono.setFont(new Font("Serif", Font.BOLD, 20));

        centro.add(etiquetaTelefonoLabel);
        centro.add(etiquetaTelefono);

        //calle (opcional)
        JLabel calleLabel = new JLabel("Calle (opcional)");
        calleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        calleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(calleLabel);
        centro.add(calleLabel);
        
        JTextField calle = new JTextField("");
        calle.setFont(new Font("Serif", Font.BOLD, 20));
        calle.setMaximumSize(new Dimension(400, 50));
        calle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(calle);

        //colonia (opcional)
        JLabel coloniaLabel = new JLabel("Colonia (opcional)");
        coloniaLabel.setFont(new Font("Serif", Font.BOLD, 20));
        coloniaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(coloniaLabel);
        
        JTextField colonia = new JTextField("");
        colonia.setFont(new Font("Serif", Font.BOLD, 20));
        colonia.setMaximumSize(new Dimension(400, 50));
        colonia.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(colonia);

        //numero (opcional)
        JLabel numeroLabel = new JLabel("Numero (opcional)");
        numeroLabel.setFont(new Font("Serif", Font.BOLD, 20));
        numeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(numeroLabel);
        centro.add(numeroLabel);
        
        JTextField numero = new JTextField("");
        numero.setFont(new Font("Serif", Font.BOLD, 20));
        numero.setMaximumSize(new Dimension(400, 50));
        numero.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(numero);

        //para nombre de usuario
        JLabel nu = new JLabel("Nombre de Usuario");
        nu.setFont(new Font("Serif", Font.BOLD, 20));
        nu.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(nu);
        
        JTextField nombreU = new JTextField("");
        nombreU.setFont(new Font("Serif", Font.BOLD, 20));
        nombreU.setMaximumSize(new Dimension(400, 50));
        nombreU.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(nombreU);

        //para la contraseña
        JLabel contra = new JLabel("Contraseña");
        contra.setFont(new Font("Serif", Font.BOLD, 20));
        contra.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(contra);
        
        JPasswordField contraseña = new JPasswordField();
        contraseña.setFont(new Font("Serif", Font.BOLD, 24));
        contraseña.setMaximumSize(new Dimension(400, 50));
        contraseña.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(contraseña);
        
        //para poner estado
        JLabel estadoLabel = new JLabel("Estado");
        estadoLabel.setFont(new Font("Serif", Font.BOLD, 20));
        JTextField estado = new JTextField();
        estado.setFont(new Font("Serif", Font.BOLD, 20));

        centro.add(estadoLabel);
        centro.add(estado);

        add(centro, BorderLayout.CENTER);
      
        //------ parte de abajo ------
        JPanel abajo = new JPanel();
        abajo.setBackground(new Color(200, 200, 200));


        JButton modificarCliente = new JButton("Confirmar");
        modificarCliente.setFont(new Font("Serif", Font.BOLD, 24));
        modificarCliente.setBackground(new Color(70, 150, 220));
        modificarCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
        modificarCliente.setMaximumSize(new Dimension(450, 60));

        abajo.add(modificarCliente);

        add(abajo, BorderLayout.SOUTH);
        
         
        //action listener que lleva a la pantalla de opciones cliente
        modificarCliente.addActionListener(e -> {
        try{
            
        
        ClienteCompletoDTO clienteModificar = new ClienteCompletoDTO();
        clienteModificar.setIdCliente(Sesion.getIdCliente());
        clienteModificar.setNombres(nombres.getText().trim());
        clienteModificar.setApellidoPaterno(aPaterno.getText().trim());
        clienteModificar.setApellidoMaterno(aMaterno.getText().trim());
        // el texto de la fecha lo hacemos LocalDate
         LocalDate fecha = LocalDate.parse(fechanacimiento.getText());
         clienteModificar.setFechaNacimiento(fecha);
         // recibimos la edad en texto y la hacemos integer
         String edadTexto = edad.getText().trim();
         int edadInteger = Integer.parseInt(edadTexto);
         clienteModificar.setEdad(edadInteger);
         clienteModificar.setCalle(calle.getText().trim());
         clienteModificar.setColonia(colonia.getText().trim());
         clienteModificar.setNumeroCasa(numero.getText().trim());
         
         //obtenemos el estado quitamos espacios y lo hacemos minusculas por que asi lo hice en la clase cliente
         // en el value of busca un estado igual al que el cliente puso y lo convierte a enum
         Cliente.EstadoCliente estadoNuevo = Cliente.EstadoCliente.valueOf(estado.getText().trim().toUpperCase());
         clienteModificar.setEstado(estadoNuevo);
         clienteModificar.setNumeroTelefono(telefono.getText().trim());
         clienteModificar.setContrasena(contraseña.getText().trim());
         clienteModificar.setUsuario(nombreU.getText().trim());
         clienteModificar.setEtiqueta(etiquetaTelefono.getText().trim());
         
         Cliente clienteModificado = clienteBO.actualizarCliente(clienteModificar);
         //le damos el id del cliente a la sesion mediante el modificado
         Sesion.setIdCliente(clienteModificado.getIdCliente());
         JOptionPane.showMessageDialog(this, "Modificacion con éxito ");
         //abre la ventana de opciones cliente
         opcionesUsuario oU = new opcionesUsuario();
         oU.setVisible(true);

         //cierra esta ventana
         dispose();
         
         }catch (negocioException ex) {
                Logger.getLogger("Error al insertar al cliente");
                JOptionPane.showMessageDialog(this, ex.getMessage(),  "Error al actualizar ",JOptionPane.ERROR_MESSAGE );
              }
        

        });
    }
}
