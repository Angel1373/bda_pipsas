/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Negocio.DTOs.PizzaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import persistencia.conexion.ConexionBD;

/**
 *
 * @author luiscarlosbeltran
 */
public class menuProgramado extends JFrame {

    private ConexionBD conexion;

    public menuProgramado() throws SQLException {
        conexion = new ConexionBD();
        

        setTitle("Menú de Pizzas");
        setSize(700, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
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
        abajo.setBackground(new Color(200, 200, 200));


        JButton verPedido = new JButton("Ver pedido");
        verPedido.setFont(new Font("Serif", Font.BOLD, 24));
        verPedido.setBackground(new Color(70, 150, 220));
        verPedido.setAlignmentX(Component.CENTER_ALIGNMENT);
        verPedido.setMaximumSize(new Dimension(450, 60));

        abajo.add(verPedido);

        add(abajo, BorderLayout.SOUTH);

        setVisible(true);
        
        //actionlisterer del boton para ver el pedido
        verPedido.addActionListener(e -> {
            new MiPedido();
            dispose();
        });
    }
    
    // metodo que selecciona solo pizzas disponibles 
    private void cargarPizzas(JPanel panel) throws SQLException {

        try (Connection con = conexion.CrearConexion()) {

            String sql = "SELECT * FROM pizzas WHERE disponible = 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PizzaDTO pizza = new PizzaDTO();
                pizza.setId_pizza(rs.getInt("id_pizza")); // 🔥 ESTA LINEA ES CLAVE
                pizza.setNombre(rs.getString("nombre"));
                pizza.setTamano(rs.getString("tamano"));
                pizza.setDescripcion(rs.getString("descripcion"));
                pizza.setPrecio(rs.getDouble("precio"));
                pizza.setDisponible(true);

                JPanel tarjeta = crearTarjeta(pizza);

                panel.add(tarjeta);
                panel.add(Box.createVerticalStrut(20));
            }

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

        //abre la ventana de detallespiza
        DetallePizza dp = new DetallePizza(pizza);
        dp.setVisible(true);

        //cierra esta ventana
        menuProgramado.this.dispose();
        });
        
        return tarjeta; 
    }
    
}
