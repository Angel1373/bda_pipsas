/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Negocio.BOs.ClienteBO;
import Negocio.DTOs.ClienteCompletoDTO;
import Negocio.Excepciones.negocioException;
import fabricaClienteBO.registrarCliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import persistencia.Dominio.Cliente;
import persistencia.Dominio.Sesion;
import persistencia.conexion.ConexionBD;
import persistencia.conexion.IConexionBD;

/**
 * pantalla para insertar una pizza
 * @author luiscarlosbeltran
 */
public class insertarPizza extends JFrame {
    
    /**
     * metodo que crea y muestra la pantalla insertarPizza
     */
    public insertarPizza() {

        setTitle("pantalla insertar pizza");
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
        

        
        //parte del centro

        JPanel centro = new JPanel(new GridLayout(0, 2, 15, 15));

        //para meter nombres
        JLabel n = new JLabel("Nombre de la Pizza");
        n.setFont(new Font("Serif", Font.BOLD, 20));
        n.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(n);
        
        JTextField nombres = new JTextField("");
        nombres.setFont(new Font("Serif", Font.BOLD, 20));
        nombres.setMaximumSize(new Dimension(400, 50));
        nombres.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(nombres);

        //para meter tamaño
        JLabel tam = new JLabel("Tamaño");
        tam.setFont(new Font("Serif", Font.BOLD, 20));
        tam.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(tam);
        
        JTextField tamano = new JTextField("");
        tamano.setFont(new Font("Serif", Font.BOLD, 20));
        tamano.setMaximumSize(new Dimension(400, 50));
        tamano.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(tamano);

        //para meter descripcion
        JLabel desc = new JLabel("Descripcion");
        desc.setFont(new Font("Serif", Font.BOLD, 20));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(desc);
        
        JTextField descripcion = new JTextField("");
        descripcion.setFont(new Font("Serif", Font.BOLD, 20));
        descripcion.setMaximumSize(new Dimension(400, 50));
        descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(descripcion);

        //para meter precio
        JLabel moni = new JLabel("Precio");
        moni.setFont(new Font("Serif", Font.BOLD, 20));
        moni.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(moni);
        
        JTextField precio = new JTextField("");
        precio.setFont(new Font("Serif", Font.BOLD, 20));
        precio.setMaximumSize(new Dimension(400, 50));
        precio.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(precio);

        //combobox para seleccionar disponibilidad
        JLabel disp = new JLabel("Disponibilidad");
        disp.setFont(new Font("Serif", Font.BOLD, 20));
        disp.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(disp);
        
        String[] opciones = {"Disponible", "No disponible"};
        JComboBox<String> combopciones = new JComboBox<>(opciones);
        centro.add(combopciones);

        
        
        add(centro, BorderLayout.CENTER);
        
        //------ parte de abajo ------
        JPanel abajo = new JPanel();
        abajo.setBackground(new Color(200, 200, 200));
        
        
        JButton volver = new JButton("Volver");
        volver.setFont(new Font("Serif", Font.BOLD, 24));
        volver.setBackground(new Color(70, 150, 220));
        volver.setAlignmentX(Component.CENTER_ALIGNMENT);
        volver.setMaximumSize(new Dimension(450, 60));
        
        JButton insertarPipsa = new JButton("Insertar Pizza");
        insertarPipsa.setFont(new Font("Serif", Font.BOLD, 24));
        insertarPipsa.setBackground(new Color(70, 150, 220));
        insertarPipsa.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertarPipsa.setMaximumSize(new Dimension(450, 60));
        
        abajo.add(volver);
        abajo.add(insertarPipsa);
        
        add(abajo, BorderLayout.SOUTH);
        
         
        //action listener que lleva a la pantalla de opciones empelado
        volver.addActionListener(e -> {
            opcionesEmpleado oe = new opcionesEmpleado();
            oe.setVisible(true);
            dispose();
        });
        
        //action listener que registra la pipsa
        insertarPipsa.addActionListener(e -> {
            
        });
    }
}