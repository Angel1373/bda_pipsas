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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author luiscarlosbeltran
 */
public class PantallaPrincipal extends JFrame {

    public PantallaPrincipal() {

        setTitle("pantalla inicio");
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

        //---- parte del centro con los 2 botones y donde deberia estar la imagen -----
        JPanel centro = new JPanel();
        //el box layout en el eje Y es para acomodar apilados, o sea vertical
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        //hace un espacio arriba para que los botones esten en el centro
        centro.add(Box.createVerticalStrut(250));


        //el boton de iniciar sesion
        JButton btnSesion = new JButton("Iniciar sesion");
        btnSesion.setBackground(new Color(0, 168, 49));
        btnSesion.setFont(new Font("Serif", Font.BOLD, 28));
        btnSesion.setMaximumSize(new Dimension(300, 60));
        btnSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        centro.add(btnSesion);

        centro.add(Box.createVerticalStrut(40));

        //el boton de pedido express
        JButton btnExpress = new JButton("Pedido Express");
        btnExpress.setBackground(new Color(242, 156, 29));
        btnExpress.setFont(new Font("Serif", Font.BOLD, 28));
        btnExpress.setMaximumSize(new Dimension(300, 60));
        btnExpress.setAlignmentX(Component.CENTER_ALIGNMENT);

        centro.add(btnExpress);
        
        
        add(centro, BorderLayout.CENTER);

        //------ parte de abajo ------
        JPanel abajo = new JPanel();
        abajo.setBackground(new Color(200, 200, 200));


        JLabel texto = new JLabel("Primero el moco, ahora la Pipsa 🦍🍕");
        texto.setFont(new Font("Serif", Font.PLAIN, 28));

        abajo.add(texto);

        add(abajo, BorderLayout.SOUTH);
        
        
        //----- action listener del btnSesion -------
        btnSesion.addActionListener(e -> {

        //abre la ventana de inicio de sesion
        InicioSesion inicio = new InicioSesion();
        inicio.setVisible(true);

        //cierra esta ventana principal
        dispose();

        });
        
        //----- action listener del btnExpress -------
        btnExpress.addActionListener(e -> {
            
            try{
                //abre la ventana menuEXP
                menuEXP mEX = new menuEXP();
                mEX.setVisible(true);

                //cierra esta ventana principal
                dispose();
        
            } catch (SQLException ex){
                System.out.println("Error al consultar pizzas disponibles" + ex.getMessage());
            }

        });
    }
}
