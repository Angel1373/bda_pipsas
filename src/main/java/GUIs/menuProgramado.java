/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public menuProgramado() {
        conexion = new ConexionBD();
        

        setTitle("Menú de Pizzas");
        setSize(700, 900);
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
    }

    private void cargarPizzas(JPanel panel) {

        try (Connection con = conexion.CrearConexion()) {

            String sql = "SELECT * FROM pizzas WHERE disponible = 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String nombre = rs.getString("nombre");
                String tamano = rs.getString("tamano");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");

                JPanel tarjeta = crearTarjeta(nombre, tamano, descripcion, precio);

                panel.add(tarjeta);
                panel.add(Box.createVerticalStrut(20));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel crearTarjeta(String nombre, String tamano,
                                String descripcion, double precio) {

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setMaximumSize(new Dimension(600, 150));
        tarjeta.setBackground(Color.WHITE);

        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblNombre = new JLabel(nombre + " - " + tamano);
        lblNombre.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel lblDescripcion = new JLabel(descripcion);
        JLabel lblPrecio = new JLabel("Precio: $" + precio);

        JButton agregar = new JButton("Agregar al pedido");
        agregar.setBackground(new Color(100, 200, 120));

        tarjeta.add(lblNombre);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblDescripcion);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblPrecio);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(agregar);

        return tarjeta;
        
        
    }
}
