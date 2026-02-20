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
public class InicioSesion extends JFrame {
    public InicioSesion() {

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
        
        centro.add(Box.createVerticalStrut(40));
        
        //el titulo que dice inicio de sesion
        JLabel titulo = new JLabel("Inicio de Sesión");
        titulo.setFont(new Font("Serif", Font.BOLD, 60));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(titulo);
        centro.add(Box.createVerticalStrut(30));
        
        //rectangulo verde que dice introduzca sus datos
        JButton textointro = new JButton("Introduzca sus datos");
        textointro.setFont(new Font("Serif", Font.BOLD, 28));
        textointro.setBackground(new Color(100, 200, 120));
        textointro.setAlignmentX(Component.CENTER_ALIGNMENT);
        textointro.setMaximumSize(new Dimension(350, 60));
        centro.add(textointro);
        centro.add(Box.createVerticalStrut(50));
        
        //para meter el telefono (hay que ver si usaremos nombres de usuario)
        JLabel cphone = new JLabel("Telefono");
        cphone.setFont(new Font("Serif", Font.BOLD, 20));
        cphone.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(cphone);
        
        
        JTextField telefono = new JTextField("");
        telefono.setFont(new Font("Serif", Font.BOLD, 20));
        telefono.setMaximumSize(new Dimension(400, 50));
        telefono.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(telefono);
        centro.add(Box.createVerticalStrut(40));
        
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
        centro.add(Box.createVerticalStrut(40));
        
        //para iniciar sesion
        JButton iniciar = new JButton("Iniciar Sesion");
        iniciar.setFont(new Font("Serif", Font.BOLD, 32));
        iniciar.setBackground(new Color(100, 200, 120));
        iniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        iniciar.setMaximumSize(new Dimension(350, 60));
        centro.add(iniciar);
        centro.add(Box.createVerticalStrut(60));
        
        
        add(centro, BorderLayout.CENTER);
        
        //------ parte de abajo ------
        JPanel abajo = new JPanel();
        abajo.setBackground(new Color(200, 200, 200));


        JButton registrarse = new JButton("No tienes cuenta? Registrate Aqui");
        registrarse.setFont(new Font("Serif", Font.BOLD, 24));
        registrarse.setBackground(new Color(70, 150, 220));
        registrarse.setAlignmentX(Component.CENTER_ALIGNMENT);
        registrarse.setMaximumSize(new Dimension(450, 60));

        abajo.add(registrarse);

        add(abajo, BorderLayout.SOUTH);
        
        
        //action listener que lleva a la pantalla de crear cuenta
        registrarse.addActionListener(e -> {

        //abre la ventana de inicio de sesion
        registroClientes reg = new registroClientes();
        reg.setVisible(true);

        //cierra esta ventana
        dispose();

        });
    }
}
