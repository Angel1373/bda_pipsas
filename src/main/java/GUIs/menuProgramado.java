/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
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

        // Panel principal donde se agregan pizzas
        JPanel panelPizzas = new JPanel();
        panelPizzas.setLayout(new BoxLayout(panelPizzas, BoxLayout.Y_AXIS));

        // Scroll
        JScrollPane scroll = new JScrollPane(panelPizzas);
        add(scroll, BorderLayout.CENTER);

        cargarPizzas(panelPizzas);

        setVisible(true);
    }

    private void cargarPizzas(JPanel panel) {

        try (Connection con = conexion.CrearConexion()) {

            String sql = "SELECT * FROM pizzas WHERE disponible = true";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String nombre = rs.getString("nombre");
                String tamano = rs.getString("tamano");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");

                panel.add(crearTarjetaPizza(nombre, tamano, descripcion, precio));
                panel.add(Box.createVerticalStrut(20));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel crearTarjetaPizza(String nombre, String tamano,
                                     String descripcion, double precio) {

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        tarjeta.setMaximumSize(new Dimension(600, 150));
        tarjeta.setBackground(Color.WHITE);

        JLabel lblNombre = new JLabel(nombre + " - " + tamano);
        lblNombre.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel lblDescripcion = new JLabel(descripcion);
        JLabel lblPrecio = new JLabel("Precio: $" + precio);

        JButton btn = new JButton("Agregar al pedido");

        tarjeta.add(lblNombre);
        tarjeta.add(lblDescripcion);
        tarjeta.add(lblPrecio);
        tarjeta.add(btn);

        return tarjeta;
    }

}
