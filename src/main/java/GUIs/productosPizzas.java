/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Negocio.BOs.IPizzaBO;
import Negocio.BOs.PizzaBO;
import Negocio.DTOs.PizzaDTO;
import Negocio.Excepciones.negocioException;
import fabricaClienteBO.pizzaCambioDisYPre;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import persistencia.DAOs.IPizzaDAO;
import persistencia.DAOs.PizzaDAO;
import persistencia.Dominio.PedidoActual;
import persistencia.Dominio.Pizza;
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
        tarjeta.setMaximumSize(new Dimension(900, 450));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

        tarjeta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        JLabel lblNombre = new JLabel(pizza.getNombre() + " - " + pizza.getTamano());
        lblNombre.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel lblDescripcion = new JLabel(pizza.getDescripcion());
        JLabel lblPrecio = new JLabel("Precio: $" + pizza.getPrecio());

        
        JButton cambiarDisponibilidadYPrecio = new JButton("Actualizar la pizza");
        cambiarDisponibilidadYPrecio.setBackground(new Color(100,200,100));

        tarjeta.add(lblNombre);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblDescripcion);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblPrecio);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(cambiarDisponibilidadYPrecio);
        
        
        cambiarDisponibilidadYPrecio.addActionListener(e-> {
         // Crear ventana modal
        JDialog ventana = new JDialog(this, "Modificar Pizza", true);
        ventana.setSize(600, 420);
        ventana.setLocationRelativeTo(this);
        ventana.setLayout(new BorderLayout(10, 10));

        JPanel panelCentro = new JPanel(new GridLayout(9, 2, 9, 9));

        // precio
        JLabel Precio = new JLabel("Ingrese el nuevo precio", SwingConstants.CENTER);
        JTextField campoNuevoPrecio = new JTextField();
        panelCentro.add(Precio);
        panelCentro.add(campoNuevoPrecio);
        
        // id de la pipsa
        JLabel pizzaID = new JLabel("Ingrese el id de la pizza");
        JTextField campoID = new JTextField();
        panelCentro.add(pizzaID);
        panelCentro.add(campoID);
        
        // id de la pipsa
        JLabel descripcionLB = new JLabel("Ingrese la nueva descripcion de la pizza");
        JTextField descripcion = new JTextField();
        panelCentro.add(descripcionLB);
        panelCentro.add(descripcion);

        // disponibilidad
        JLabel lblDisponibilidad = new JLabel("Seleccione la disponibilidad", SwingConstants.CENTER);
        String[] opciones = {"Disponible", "No disponible"};
        JComboBox<String> comboDisponibilidad = new JComboBox<>(opciones);
        panelCentro.add(lblDisponibilidad);
        panelCentro.add(comboDisponibilidad);
        
        // disponibilidad
        JLabel lbTamano = new JLabel("Seleccione el tamaño de la pizza");
        JTextField tamano = new JTextField();
        panelCentro.add(lbTamano);
        panelCentro.add(tamano);
        
        // disponibilidad
        JLabel lbNombre = new JLabel("Seleccione el nombre de la pizza");
        JTextField nombre = new JTextField();
        panelCentro.add(lbNombre);
        panelCentro.add(nombre);

        ventana.add(panelCentro, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        panelBotones.add(btnAceptar);
        ventana.add(panelBotones, BorderLayout.SOUTH);
        
        btnAceptar.addActionListener(ev -> {
               
            try {
                PizzaDTO pizzaDTO = new PizzaDTO();
                String textoPrecio = campoNuevoPrecio.getText();
                Double precio = Double.parseDouble(textoPrecio);
                pizzaDTO.setPrecio(precio);
                /*
                Aqui si selecciona el indice 0 sera true y le pondra disponible
                si selecciona el indice 1 sera false y le pondra no disponible
                */
                pizzaDTO.setDisponible(comboDisponibilidad.getSelectedIndex() == 0);
                String textoID = campoID.getText();
                Integer id = Integer.parseInt(textoID);
                pizzaDTO.setId_pizza(id);
                pizzaDTO.setDescripcion(descripcion.getText().trim());
                pizzaDTO.setTamano(tamano.getText());
                pizzaDTO.setNombre(nombre.getText());
                PizzaBO pizzaBO = pizzaCambioDisYPre.regresarBO();
                pizzaBO.actualizarPizza(pizzaDTO);
                JOptionPane.showMessageDialog(this, "Pizza editada con éxito");
            } catch (negocioException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            
            });
            ventana.setVisible(true);

        });
       
        
        return tarjeta; 
    }
    
}