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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author luiscarlosbeltran
 */
public class opcionesEmpleado extends JFrame {
    public opcionesEmpleado(){
        setTitle("pantalla opciones usuario");
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
        
        
        //---- parte centro -------
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        
        centro.add(Box.createVerticalStrut(40));
        
        //boton para ver productos, o sea ver las pipzsassasasa
        JButton apro = new JButton("Administrar Productos");
        apro.setFont(new Font("Serif", Font.BOLD, 20));
        apro.setBackground(new Color(100, 200, 120));
        apro.setAlignmentX(Component.CENTER_ALIGNMENT);
        apro.setMaximumSize(new Dimension(350, 60));
        centro.add(apro);
        centro.add(Box.createVerticalStrut(50));
        
        //boton para ver pedidos
        JButton ape = new JButton("Administrar Pedidos");
        ape.setFont(new Font("Serif", Font.BOLD, 20));
        ape.setBackground(new Color(100, 200, 120));
        ape.setAlignmentX(Component.CENTER_ALIGNMENT);
        ape.setMaximumSize(new Dimension(350, 60));
        centro.add(ape);
        centro.add(Box.createVerticalStrut(50));
        
        add(centro, BorderLayout.CENTER);
        
        //------ parte de abajo ------
        JPanel abajo = new JPanel();
        abajo.setBackground(new Color(200, 200, 200));

        //boton cerrar sesion
        JButton cS = new JButton("Cerrar Sesion");
        cS.setFont(new Font("Serif", Font.BOLD, 24));
        cS.setBackground(new Color(209, 8, 8));
        cS.setAlignmentX(Component.CENTER_ALIGNMENT);
        cS.setMaximumSize(new Dimension(450, 60));

        abajo.add(cS);

        add(abajo, BorderLayout.SOUTH);
        
        //action listener de  prodcutos pizzas sisisisissi (tengo sueño llevo 8 dias sin dormir bien)
        apro.addActionListener(e -> {

        //abre la ventana que muestra productos
        productosPizzas pp;
            try {
                pp = new productosPizzas();
            
                pp.setVisible(true);
                //cierra esta ventana
                dispose();
            } catch (SQLException ex) {
                System.out.println("No se pudieron obtener las pizzas" + ex.getMessage());
            }
        });
        
        //action listener de modificar datos
        ape.addActionListener(e -> {

        //abre la ventana de gestionarpedidos
        
        //cierra esta ventana
        dispose();

        });
        
        //action listener de cerrar sesion que lleva a la pantalla principal
        cS.addActionListener(e -> {

        //abre la ventana principal
        PantallaPrincipal pp = new PantallaPrincipal();
        pp.setVisible(true);

        //cierra esta ventana
        dispose();

        });
        
    }
}
