/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Negocio.BOs.IPizzaBO;
import Negocio.BOs.PizzaBO;
import Negocio.DTOs.PizzaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import persistencia.DAOs.IPizzaDAO;
import persistencia.DAOs.PizzaDAO;
import persistencia.Dominio.PedidoActual;
import persistencia.conexion.ConexionBD;

/**
 *
 * @author luiscarlosbeltran
 */
public class productosPizzas extends JFrame {

    private ConexionBD conexion;

    public productosPizzas() throws SQLException {
        conexion = new ConexionBD();
        

        setTitle("Menu Express");
        setSize(700, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //---- parte de lo azul que va arriba ----
        JPanel arriba = new JPanel();
        arriba.setBackground(new Color(70, 150, 220)); //el color azul;

        //el texto
        JLabel textoArriba = new JLabel("Gorillaz Pipsa");
        textoArriba.setFont(new Font("Serif", Font.BOLD, 50));
        
        //mete texto a la parte de arriba y mete la parte de arriba a la ventana
        arriba.add(textoArriba);
        add(arriba, BorderLayout.NORTH);

        // Panel donde se agregan las pizzas
        JPanel panelPizzas = new JPanel();
        panelPizzas.setLayout(new BoxLayout(panelPizzas, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(panelPizzas);
        add(scroll, BorderLayout.CENTER);

        cargarPizzas(panelPizzas);
        
        //------ parte de abajo ------
        JPanel abajo = new JPanel();
        abajo.setLayout(new GridLayout(1,2));
        abajo.setBackground(new Color(200, 200, 200));


        JButton volver = new JButton("Volver");
        volver.setFont(new Font("Serif", Font.BOLD, 24));
        volver.setBackground(new Color(255, 0, 0));
        volver.setAlignmentX(Component.CENTER_ALIGNMENT);
        volver.setMaximumSize(new Dimension(450, 60));
        abajo.add(volver);
        
        add(abajo, BorderLayout.SOUTH);

        setVisible(true);
        
        //actionlistener del boton para volver,abre opcionesempleados de nuevo
        volver.addActionListener(e -> {
            opcionesEmpleado oe = new opcionesEmpleado();
            oe.setVisible(true);

            dispose();
        });
    }
    
    // metodo que selecciona todas las pizzas disponibles o no
    private void cargarPizzas(JPanel panel) {

        try {

            IPizzaDAO pizzaDAO = new PizzaDAO(conexion);
            IPizzaBO pizzaBO = new PizzaBO(pizzaDAO);

            List<PizzaDTO> pizzas = pizzaBO.obtenerTodasPizzas();

            for (PizzaDTO pizza : pizzas) {
                JPanel tarjeta = crearTarjeta(pizza);
                panel.add(tarjeta);
                panel.add(Box.createVerticalStrut(20));
            }

        } catch (Exception e) {
            System.out.println("Error al cargar pizzas" + e.getMessage());
        }
    }
    
    //crea una tarjetita, apartado para mostrar informacion de una pizza
    private JPanel crearTarjeta(PizzaDTO pizza) {

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setMaximumSize(new Dimension(600, 150));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

        tarjeta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        JLabel lblNombre = new JLabel(pizza.getNombre() + " - " + pizza.getTamano());
        lblNombre.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel lblDescripcion = new JLabel(pizza.getDescripcion());
        JLabel lblPrecio = new JLabel("Precio: $" + pizza.getPrecio());

        JButton cambiarPrecio = new JButton("Cambiar precio");
        cambiarPrecio.setBackground(new Color(100, 200, 120));
        
        JButton cambiarDisp = new JButton("Cambiar disponibilidad");
        cambiarDisp.setBackground(new Color(100, 200, 120));

        tarjeta.add(lblNombre);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblDescripcion);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblPrecio);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(cambiarPrecio);
        tarjeta.add(cambiarDisp);
        
        //actionlistener del boton cambiarprecio, abre ventana emergente 
        cambiarPrecio.addActionListener(e -> {
            JDialog ventanaPrecio = new JDialog(this, "Cambiar Precio", true);
            ventanaPrecio.setSize(350, 180);
            ventanaPrecio.setLocationRelativeTo(this);
            ventanaPrecio.setLayout(new BorderLayout(10, 10));
            
            //centro
            JPanel panelCentro = new JPanel(new GridLayout(2, 1, 5, 5));
            JLabel mensaje = new JLabel("Ingrese el nuevo precio", SwingConstants.CENTER);
            JTextField campoNuevoPrecio = new JTextField();

            panelCentro.add(mensaje);
            panelCentro.add(campoNuevoPrecio);

            ventanaPrecio.add(panelCentro, BorderLayout.CENTER);

            // Panel botones
            JPanel panelBotones = new JPanel();
            JButton btnAceptar = new JButton("Aceptar");

            panelBotones.add(btnAceptar);

            ventanaPrecio.add(panelBotones, BorderLayout.SOUTH);
            
            ventanaPrecio.setVisible(true);

            //actionlistener del boton aceptar para aceptar cambiar el precio
            btnAceptar.addActionListener(ev -> {
                //meter la logica del boton aceptar aqui, que modifique el precio y cierre esta ventana
            });
        });
        
        
        //action listenrer del boton para cambiar disponibiliddad
        cambiarDisp.addActionListener(e -> {

            JDialog ventanaDisp = new JDialog(this, "Cambiar Disponibilidad", true);
            ventanaDisp.setSize(350, 180);
            ventanaDisp.setLocationRelativeTo(this);
            ventanaDisp.setLayout(new BorderLayout(10, 10));

            // Panel central
            JPanel panelCentro = new JPanel(new GridLayout(2, 1, 5, 5));

            JLabel mensaje = new JLabel("Seleccione la disponibilidad", SwingConstants.CENTER);

            String[] opciones = {"Disponible", "No disponible"};
            JComboBox<String> comboDisponibilidad = new JComboBox<>(opciones);

            panelCentro.add(mensaje);
            panelCentro.add(comboDisponibilidad);

            ventanaDisp.add(panelCentro, BorderLayout.CENTER);

            //boton acepatr
            JPanel panelBotones = new JPanel();
            JButton btnAceptar = new JButton("Aceptar");

            panelBotones.add(btnAceptar);

            ventanaDisp.add(panelBotones, BorderLayout.SOUTH);

            //boton de aceptar, meter la logica aqui para cambiar el estado
            btnAceptar.addActionListener(ev -> {
                //logica
            });
            
            ventanaDisp.setVisible(true);
        });
        
        return tarjeta; 
    }
    
}