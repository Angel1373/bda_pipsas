/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Negocio.BOs.ClienteBO;
import Negocio.DTOs.ClienteCompletoDTO;
import Negocio.Excepciones.negocioException;
import fabricaClienteBO.registrarCliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.logging.Logger;
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

/**
 *
 * @author luiscarlosbeltran
 */
public class registroClientes extends JFrame {
    public registroClientes() {
        
       
        // llamamos al metodo para crear el BO con los DAOs para no usarlos en el GUI
        ClienteBO clienteBO = registrarCliente.registrarCliente();
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
        JLabel calleLabel = new JLabel("Calle");
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
        JLabel coloniaLabel = new JLabel("Colonia");
        coloniaLabel.setFont(new Font("Serif", Font.BOLD, 20));
        coloniaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(coloniaLabel);
        
        JTextField colonia = new JTextField("");
        colonia.setFont(new Font("Serif", Font.BOLD, 20));
        colonia.setMaximumSize(new Dimension(400, 50));
        colonia.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(colonia);

        //numero (opcional)
        JLabel numeroLabel = new JLabel("Numero");
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


        JButton crearCuenta = new JButton("Crear Cuenta");
        crearCuenta.setFont(new Font("Serif", Font.BOLD, 24));
        crearCuenta.setBackground(new Color(70, 150, 220));
        crearCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        crearCuenta.setMaximumSize(new Dimension(450, 60));

        abajo.add(crearCuenta);

        add(abajo, BorderLayout.SOUTH);
        
         
        //action listener que lleva a la pantalla de opciones cliente
        crearCuenta.addActionListener(e -> {
          try{
              
         ClienteCompletoDTO nuevoCliente = new ClienteCompletoDTO();
         nuevoCliente.setUsuario(nombreU.getText().trim());
         nuevoCliente.setContrasena(contraseña.getText().trim());
         nuevoCliente.setNombres(nombres.getText().trim());
         nuevoCliente.setApellidoPaterno(aPaterno.getText().trim());
         nuevoCliente.setApellidoMaterno(aMaterno.getText().trim());
         // hacemos localdate el string
         LocalDate fecha = LocalDate.parse(fechanacimiento.getText());
         nuevoCliente.setFechaNacimiento(fecha);
         // recibimos la edad en texto y la hacemos integer
         String edadTexto = edad.getText().trim();
         int edadInteger = Integer.parseInt(edadTexto);
         nuevoCliente.setEdad(edadInteger);
         //obtenemos el estado quitamos espacios y lo hacemos minusculas por que asi lo hice en la clase cliente
         // en el value of busca un estado igual al que el cliente puso y lo convierte a enum
         Cliente.EstadoCliente estadoNuevo = Cliente.EstadoCliente.valueOf(estado.getText().trim().toUpperCase());
         nuevoCliente.setEstado(estadoNuevo);
         
         nuevoCliente.setNumeroTelefono(telefono.getText().trim());
         nuevoCliente.setEtiqueta(etiquetaTelefono.getText().trim());
         nuevoCliente.setCalle(calle.getText().trim());
         nuevoCliente.setColonia(colonia.getText().trim());
         nuevoCliente.setNumeroCasa(numero.getText().trim());
         Cliente clienteInsertado = clienteBO.insertarCliente(nuevoCliente);
          JOptionPane.showMessageDialog(this, "Cuenta creada con éxito");
        // en esta parte guardamos la sesion del usuario para poder usarlo en las demas ventanas
        // como insertamos el cliente le damos el id del cliente creado
        Usuario usuarioSesion = new Usuario();
        usuarioSesion.setId_cliente(clienteInsertado.getIdCliente());
        Sesion.setUsuarioActual(usuarioSesion);
         
         
        opcionesUsuario oU = new opcionesUsuario();
        oU.setVisible(true);
        dispose();
                
            } catch (negocioException ex) {
                Logger.getLogger("Error al insertar al cliente");
              
            }
        });
    }
}
