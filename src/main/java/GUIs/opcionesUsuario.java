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
public class opcionesUsuario extends JFrame {
    public opcionesUsuario(){
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
        
        //rectangulo azul de bienvenida
        JButton bnv = new JButton("Bienvenid@ a Gorillaz Pipsa");
        bnv.setFont(new Font("Serif", Font.BOLD, 20));
        bnv.setBackground(new Color(0, 91, 163));
        bnv.setAlignmentX(Component.CENTER_ALIGNMENT);
        bnv.setMaximumSize(new Dimension(350, 60));
        centro.add(bnv);
        centro.add(Box.createVerticalStrut(50));
        
        //boton para realizar pedido (manda al menu de pipsas)
        JButton pedido = new JButton("Realizar Pedido");
        pedido.setFont(new Font("Serif", Font.BOLD, 20));
        pedido.setBackground(new Color(100, 200, 120));
        pedido.setAlignmentX(Component.CENTER_ALIGNMENT);
        pedido.setMaximumSize(new Dimension(350, 60));
        centro.add(pedido);
        centro.add(Box.createVerticalStrut(50));
        
        //boton para ver pedidos
        JButton verP = new JButton("Ver mis pedidos");
        verP.setFont(new Font("Serif", Font.BOLD, 20));
        verP.setBackground(new Color(100, 200, 120));
        verP.setAlignmentX(Component.CENTER_ALIGNMENT);
        verP.setMaximumSize(new Dimension(350, 60));
        centro.add(verP);
        centro.add(Box.createVerticalStrut(50));
        
        //boton para pantalla modificar datos
        //la pantalla de modificar datos es como la de registro de cuentas, pero el boton de abajo dice confirmar
        JButton modidatos = new JButton("Modificar mis datos");
        modidatos.setFont(new Font("Serif", Font.BOLD, 20));
        modidatos.setBackground(new Color(100, 200, 120));
        modidatos.setAlignmentX(Component.CENTER_ALIGNMENT);
        modidatos.setMaximumSize(new Dimension(350, 60));
        centro.add(modidatos);
        centro.add(Box.createVerticalStrut(50));
        
        //boton para hacer pedido express
        JButton xpr = new JButton("Pedido Express");
        xpr.setFont(new Font("Serif", Font.BOLD, 20));
        xpr.setBackground(new Color(242, 156, 29));
        xpr.setAlignmentX(Component.CENTER_ALIGNMENT);
        xpr.setMaximumSize(new Dimension(350, 60));
        centro.add(xpr);
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
        
        //action listener de realizar pedido
        
        pedido.addActionListener(e -> {

        //abre la ventana de menuProgramado
        try{
        menuProgramado mP = new menuProgramado();
        mP.setVisible(true);

        //cierra esta ventana
        dispose();
        } catch (SQLException ex){
            
        }
        });

        
        //action listener de modificar datos
        modidatos.addActionListener(e -> {

        //abre la ventana de modificar datos
        modificarDatos mD = new modificarDatos();
        mD.setVisible(true);

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
