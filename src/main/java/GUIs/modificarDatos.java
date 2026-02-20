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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author luiscarlosbeltran
 */
public class modificarDatos extends JFrame {
    
    public modificarDatos() {

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
        

        
        //--- parte del centro ---
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        
        
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
        centro.add(Box.createVerticalStrut(15));
        
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
        centro.add(Box.createVerticalStrut(15));
        
        //para meter apellido materno (opcional)
        JLabel am = new JLabel("Apellido Materno (Opcional)");
        am.setFont(new Font("Serif", Font.BOLD, 20));
        am.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(am);
        
        JTextField aMaterno = new JTextField("");
        aMaterno.setFont(new Font("Serif", Font.BOLD, 20));
        aMaterno.setMaximumSize(new Dimension(400, 50));
        aMaterno.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(aMaterno);
        centro.add(Box.createVerticalStrut(15));
        
        //esta parte sera especial, para meter datos de la direccion de envio (opcionales)
        //primero va calle (opcional)
        JLabel calleLabel = new JLabel("Calle (opcional)");
        calleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        calleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(calleLabel);

        JTextField calle = new JTextField("");
        calle.setFont(new Font("Serif", Font.BOLD, 20));
        calle.setMaximumSize(new Dimension(400, 50));
        calle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(calle);
        centro.add(Box.createVerticalStrut(15));

        //numero (opcional)
        JLabel numeroLabel = new JLabel("Numero (opcional)");
        numeroLabel.setFont(new Font("Serif", Font.BOLD, 20));
        numeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(numeroLabel);

        JTextField numero = new JTextField("");
        numero.setFont(new Font("Serif", Font.BOLD, 20));
        numero.setMaximumSize(new Dimension(400, 50));
        numero.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(numero);
        centro.add(Box.createVerticalStrut(15));

        //colonia (opcional)
        JLabel coloniaLabel = new JLabel("Colonia (opcional)");
        coloniaLabel.setFont(new Font("Serif", Font.BOLD, 20));
        coloniaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(coloniaLabel);

        JTextField colonia = new JTextField("");
        colonia.setFont(new Font("Serif", Font.BOLD, 20));
        colonia.setMaximumSize(new Dimension(400, 50));
        colonia.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(colonia);
        centro.add(Box.createVerticalStrut(15));
        
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
        centro.add(Box.createVerticalStrut(15));
        
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
        centro.add(Box.createVerticalStrut(15));
        
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
        
        add(centro, BorderLayout.CENTER);
        
        //------ parte de abajo ------
        JPanel abajo = new JPanel();
        abajo.setBackground(new Color(200, 200, 200));


        JButton confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("Serif", Font.BOLD, 24));
        confirmar.setBackground(new Color(70, 150, 220));
        confirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmar.setMaximumSize(new Dimension(450, 60));

        abajo.add(confirmar);

        add(abajo, BorderLayout.SOUTH);
        
        
         
        //action listener que lleva a la pantalla de opciones cliente
        confirmar.addActionListener(e -> {

        //abre la ventana de opciones cliente
        opcionesUsuario oU = new opcionesUsuario();
        oU.setVisible(true);

        //cierra esta ventana
        dispose();

        });
    }
}
