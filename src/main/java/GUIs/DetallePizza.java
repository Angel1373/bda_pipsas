/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Negocio.DTOs.DetallePedidoDTO;
import Negocio.DTOs.PizzaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import persistencia.Dominio.PedidoActual;

/**
 *
 * @author luiscarlosbeltran
 */
public class DetallePizza extends JFrame {

    public DetallePizza(String nombre, String tamano, String descripcion, double precio) {

        setTitle("Detalle Pizza");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //la parte de arriba
        JPanel arriba = new JPanel();
        arriba.setBackground(new Color(70, 150, 220));

        JLabel titulo = new JLabel("Gorillaz Pipsa");
        titulo.setFont(new Font("Serif", Font.BOLD, 40));

        arriba.add(titulo);
        add(arriba, BorderLayout.NORTH);

        //parte del centro
        JPanel gridCentral = new JPanel();
        gridCentral.setSize(300, 300);
        gridCentral.setLayout(new GridLayout(2, 2));
        
        //pone el nombre de la pizza, arriba a la izquierda
        gridCentral.add(new JLabel(nombre, SwingConstants.CENTER));
        
        
        //poner tamaño y descripcion arriba a la derecha
        //tanto chorizote porque un JLabel no soporta \n bruh
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel textoTamano = new JLabel("Tamaño: " + tamano);
        JLabel textoDescripcion = new JLabel("Descripcion: " + descripcion);
        panel.add(textoTamano);
        panel.add(Box.createVerticalStrut(5)); // pequeño espacio
        panel.add(textoDescripcion);
        gridCentral.add(panel); 
        
        //area para que el cliente ponga sus notas
        gridCentral.add(new JTextArea("Escriba sus notas extras aqui (opcional)"));
        
        //area para aumentar o disminuir cantidad
        JPanel panelCantidad = new JPanel(new BorderLayout());

        //el texto que dice cantidad
        JLabel lblCantidad = new JLabel("Cantidad", SwingConstants.CENTER);
        panelCantidad.add(lblCantidad, BorderLayout.NORTH);

        //el area que sube o baja segun la cantidad, no baja de 1
        JTextField txtCantidad = new JTextField("1");
        txtCantidad.setHorizontalAlignment(JTextField.CENTER);
        txtCantidad.setEditable(false);
        panelCantidad.add(txtCantidad, BorderLayout.CENTER);

        //el boton de +
        JButton btnMas = new JButton("+");
        btnMas.setBackground(Color.GREEN);
        btnMas.setForeground(Color.BLACK);
        panelCantidad.add(btnMas, BorderLayout.WEST);

        //el boton de -
        JButton btnMenos = new JButton("-");
        btnMenos.setBackground(Color.RED);
        btnMenos.setForeground(Color.BLACK);
        panelCantidad.add(btnMenos, BorderLayout.EAST);

        //actionlistener del boton +
        btnMas.addActionListener(e -> {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            cantidad++;
            txtCantidad.setText(String.valueOf(cantidad));
        });
        
        //actionlistener del boton -
        btnMenos.addActionListener(e -> {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            if (cantidad > 1) {
                cantidad--;
                txtCantidad.setText(String.valueOf(cantidad));
            }
        });
        gridCentral.add(panelCantidad);
        
        add(gridCentral, BorderLayout.CENTER);
        

        //------ parte de abajo ------
        JPanel abajo = new JPanel();
        abajo.setBackground(new Color(200, 200, 200));
        abajo.setLayout(new GridLayout(1, 2));


        JButton volver = new JButton("Volver");
        volver.setFont(new Font("Serif", Font.BOLD, 24));
        volver.setBackground(new Color(204, 40, 0));
        volver.setAlignmentX(Component.LEFT_ALIGNMENT);
        volver.setMaximumSize(new Dimension(350, 60));
        abajo.add(volver);
        
        JButton agregar = new JButton("Agregar al pedido");
        agregar.setFont(new Font("Serif", Font.BOLD, 24));
        agregar.setBackground(new Color(70, 150, 220));
        agregar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        agregar.setMaximumSize(new Dimension(350, 60));
        abajo.add(agregar);

        add(abajo, BorderLayout.SOUTH);

        setVisible(true);
        
        //action listenr del boton de volver
        volver.addActionListener(e -> {

        //abre la ventana de menuProgramado
        try{
        menuProgramado mP = new menuProgramado();
        mP.setVisible(true);

        //cierra esta ventana
        dispose();
        } catch (SQLException ex){
            
        }
        });
        
        //actionlistener del boton agregar
        agregar.addActionListener(e -> {

            int cantidad = Integer.parseInt(txtCantidad.getText());

            JTextArea areaNotas = (JTextArea) gridCentral.getComponent(2);
            String notas = areaNotas.getText();

            PizzaDTO pizzaDTO = new PizzaDTO();
            pizzaDTO.setNombre(nombre);
            pizzaDTO.setTamano(tamano);
            pizzaDTO.setDescripcion(descripcion);
            pizzaDTO.setPrecio(precio);
            pizzaDTO.setDisponible(true);

            DetallePedidoDTO detalle = new DetallePedidoDTO();
            detalle.setPizza(pizzaDTO);
            detalle.setCantidad(cantidad);
            detalle.setNotas(notas);

            PedidoActual.agregarDetalle(detalle);

            JOptionPane.showMessageDialog(this, "Pizza agregada al pedido");

            //
            try {
                new menuProgramado().setVisible(true);
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}