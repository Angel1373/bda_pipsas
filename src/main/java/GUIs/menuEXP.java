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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import persistencia.DAOs.IPizzaDAO;
import persistencia.DAOs.PizzaDAO;
import persistencia.Dominio.PedidoActual;
import persistencia.conexion.ConexionBD;

/**
 * pantalla de menu EXPRESS que muestra las pizzas disponibles para que seleccionar
 * @author luiscarlosbeltran
 */
public class menuEXP extends JFrame {

    private ConexionBD conexion;

    /**
     * metodo que crea y muestra la pantalla menuEXP
     * @throws SQLException 
     */
    public menuEXP() throws SQLException {
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


        JButton volver = new JButton("Volver al inicio");
        volver.setFont(new Font("Serif", Font.BOLD, 24));
        volver.setBackground(new Color(255, 0, 0));
        volver.setAlignmentX(Component.CENTER_ALIGNMENT);
        volver.setMaximumSize(new Dimension(450, 60));
        abajo.add(volver);
        
        JButton verPedido = new JButton("Ver pedido");
        verPedido.setFont(new Font("Serif", Font.BOLD, 24));
        verPedido.setBackground(new Color(70, 150, 220));
        verPedido.setAlignmentX(Component.CENTER_ALIGNMENT);
        verPedido.setMaximumSize(new Dimension(450, 60));
        abajo.add(verPedido);

        add(abajo, BorderLayout.SOUTH);

        setVisible(true);
        
        //actionlistener del boton para volver, y borra el "carrito"
        volver.addActionListener(e -> {
            PantallaPrincipal principal = new PantallaPrincipal();
            principal.setVisible(true);
            PedidoActual.limpiar();
            dispose();
        });
        
        //actionlisterer del boton para ver el pedido
        verPedido.addActionListener(e -> {
            new MiPedidoEXP();
            dispose();
        });
    }
    
    // metodo que selecciona solo pizzas disponibles 
    private void cargarPizzas(JPanel panel) {

        try {

            IPizzaDAO pizzaDAO = new PizzaDAO(conexion);
            IPizzaBO pizzaBO = new PizzaBO(pizzaDAO);

            List<PizzaDTO> pizzas = pizzaBO.obtenerPizzasDisponibles();

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

        JButton verDetalles = new JButton("Ver detalles");
        verDetalles.setBackground(new Color(100, 200, 120));

        tarjeta.add(lblNombre);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblDescripcion);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblPrecio);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(verDetalles);
        
        //actionlistener del boton de ver detalles pizza, adentro de esta misma tarjeta
        verDetalles.addActionListener(e -> {

        //abre la ventana de detalleEXP
        DetalleEXP dp = new DetalleEXP(pizza);
        dp.setVisible(true);

        //cierra esta ventana
        menuEXP.this.dispose();
        });
        
        return tarjeta; 
    }
    
}