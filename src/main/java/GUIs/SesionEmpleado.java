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
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import persistencia.Dominio.empleado;

/**
 * La pantalla para inicar sesion como empelado
 * @author luiscarlosbeltran
 */
public class SesionEmpleado extends JFrame {
    
    //una lista sencilla de objetos tipo empleado, nomas para validar inicios de sesion
    private ArrayList<empleado> listaEmpleados = new ArrayList<>();
    private JTextField usuarioEMP;
    private JPasswordField contraseña;
    
    
    
    /**
     * Metodo que crea y muestra la pantalla SesionEmpleado
     */
    public SesionEmpleado() {
        
        //crea unos empleados nomas para hacer el inicio de sesion, nos e van a la base de datos
        //nota: los borrare cada vez que salga de esta pantalla para no crear registros repetidos cada vez que se abre esta pantalla
        listaEmpleados.add(new empleado("Papichulo666", "lolxd666"));
        listaEmpleados.add(new empleado("brochacho", "chad0809"));
        
        
        
        
        setTitle("pantalla inicio sesion empleado");
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
        
        //para meter el nombre de usuario
        JLabel nU = new JLabel("Usuario");
        nU.setFont(new Font("Serif", Font.BOLD, 20));
        nU.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(nU);
        
        
        usuarioEMP = new JTextField("");
        usuarioEMP.setFont(new Font("Serif", Font.BOLD, 20));
        usuarioEMP.setMaximumSize(new Dimension(400, 50));
        usuarioEMP.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(usuarioEMP);
        centro.add(Box.createVerticalStrut(40));
        
        //para la contraseña
        JLabel contra = new JLabel("Contraseña");
        contra.setFont(new Font("Serif", Font.BOLD, 20));
        contra.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(contra);
        
        contraseña = new JPasswordField("");
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


        JButton volver = new JButton("Volver");
        volver.setFont(new Font("Serif", Font.BOLD, 24));
        volver.setBackground(new Color(255, 0, 0));
        volver.setAlignmentX(Component.CENTER_ALIGNMENT);
        volver.setMaximumSize(new Dimension(450, 60));

        abajo.add(volver);

        add(abajo, BorderLayout.SOUTH);
        
        
        //action listener de volver, para volver (xd)
        volver.addActionListener(e -> {
            PantallaPrincipal principal = new PantallaPrincipal();
            principal.setVisible(true);
            dispose();
        });
        
        //action listener que lleva a la pantalla de opciones cliente
        iniciar.addActionListener(e -> {

            if (confirmarSesionEmpleado() == true) {
        
            // abre la pantalla opcionesEmpleado, cierra esta
            opcionesEmpleado oe = new opcionesEmpleado();
            oe.setVisible(true);
            dispose();
        
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        });
    }
    
    /**
     * Metodo simple para validar los datos de un empleado y dejarlo iniciar sesion o no
     * no se mete en la BD porque no hay tabla de empleado, por eso los declare arriba (xddd)
     * @return true si los datos coinciden, falso en caso contrario
     */
    public boolean confirmarSesionEmpleado() {
        String usuarioIngresado = usuarioEMP.getText();
        String contraseniaIngresada = new String(contraseña.getPassword());

        for (empleado emp : listaEmpleados) {
            if (usuarioIngresado.equals(emp.getUsuarioE()) && contraseniaIngresada.equals(emp.getContraseniaE())) {
                return true;
            }
        }
        return false;
    }
    
}