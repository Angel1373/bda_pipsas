/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Negocio.BOs.IPedidoBO;
import Negocio.BOs.IPizzaBO;
import Negocio.BOs.PedidoBO;
import Negocio.BOs.PizzaBO;
import Negocio.DTOs.PedidoDTO;
import Negocio.DTOs.PizzaDTO;
import Negocio.Excepciones.negocioException;
import fabricaClienteBO.pizzaCambioDisYPre;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import persistencia.DAOs.IPedidoDAO;
import persistencia.DAOs.IPizzaDAO;
import persistencia.DAOs.PedidoDAO;
import persistencia.DAOs.PizzaDAO;
import persistencia.conexion.ConexionBD;

/**
 *
 * @author luiscarlosbeltran
 */
public class estadosPedidos extends JFrame {

    private ConexionBD conexion;

    public estadosPedidos() throws SQLException {
        conexion = new ConexionBD();
             

        setTitle("pantalla empleado cambiar estados pedidos");
        setSize(700, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //---- parte de lo azul que va arriba ----
        JPanel arriba = new JPanel();
        arriba.setBackground(new Color(70, 150, 220)); //el color azul;

        //el texto
        JLabel textoArriba = new JLabel("Gorillaz Pipsa");
        textoArriba.setFont(new Font("Serif", Font.BOLD, 50));
        
        //mete texto a la parte de arriba y mete la parte de arriba a la ventana
        arriba.add(textoArriba);
        add(arriba, BorderLayout.NORTH);

        // Panel donde se agregan los pedidos
        JPanel panelPedidos = new JPanel();
        panelPedidos.setLayout(new BoxLayout(panelPedidos, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(panelPedidos);
        add(scroll, BorderLayout.CENTER);

        cargarPedidos(panelPedidos);
        
        //------ parte de abajo ------
        JPanel abajo = new JPanel();
        abajo.setLayout(new GridLayout(1,2));
        abajo.setBackground(new Color(200, 200, 200));


        JButton volver = new JButton("Volver");
        volver.setFont(new Font("Serif", Font.BOLD, 24));
        volver.setBackground(new Color(255, 0, 0));
        volver.setAlignmentX(Component.CENTER_ALIGNMENT);
        volver.setMaximumSize(new Dimension(450, 60));
        abajo.add(volver);
        
        add(abajo, BorderLayout.SOUTH);

        setVisible(true);
        
        //actionlistener del boton para volver,abre opcionesempleados de nuevo
        volver.addActionListener(e -> {
            opcionesEmpleado oe = new opcionesEmpleado();
            oe.setVisible(true);

            dispose();
        });
    }
    
    // metodo que selecciona todos los pedidos 
    private void cargarPedidos(JPanel panel) {

        try {

            IPedidoDAO pedidoDAO = new PedidoDAO(conexion);
            IPedidoBO pedidoBO = new PedidoBO(pedidoDAO);

            List<PedidoDTO> pedidos = pedidoBO.obtenerPedidos();

            for (PedidoDTO pedido : pedidos) {
                JPanel tarjeta = crearTarjeta(pedido);
                panel.add(tarjeta);
                panel.add(Box.createVerticalStrut(20));
            }

        } catch (Exception e) {
            System.out.println("Error al cargar pedidos" + e.getMessage());
        }
    }
    
    //crea una tarjetita, apartado para mostrar informacion de una pizza
    private JPanel crearTarjeta(PedidoDTO pedido) {

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setMaximumSize(new Dimension(600, 150));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

        tarjeta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        JLabel idpedidplbl= new JLabel("id del pedido: " + pedido.getIdPedido());
        idpedidplbl.setFont(new Font("Serif", Font.BOLD, 20));
        
        JLabel lblEstado= new JLabel("Estado: " + pedido.getEstado());
        lblEstado.setFont(new Font("Serif", Font.BOLD, 20));


        JLabel lblPrecio = new JLabel("Costo: $" + pedido.getCosto());
        
        JButton cambiarEstado = new JButton("Cambiar estado");
        cambiarEstado.setBackground(new Color(100,200,100));

        tarjeta.add(idpedidplbl);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblEstado);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblPrecio);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(cambiarEstado);
        
        
        cambiarEstado.addActionListener(e-> {
         //crea una ventan
        JDialog ventana = new JDialog(this, "Cambiar estado", true);
        ventana.setSize(350, 220);
        ventana.setLocationRelativeTo(this);
        ventana.setLayout(new BorderLayout(10, 10));

        JPanel panelCentro = new JPanel(new GridLayout(5, 1, 5, 5));

        JLabel lblEstadoCambio = new JLabel("Seleccione el estado", SwingConstants.CENTER);
        String[] opciones = {"Pendiente", "Listo", "Entregado", "No reclamado", "Cancelado"};
        JComboBox<String> comboboxEstados = new JComboBox<>(opciones);
        panelCentro.add(lblEstadoCambio);
        panelCentro.add(comboboxEstados);
        
        ventana.add(panelCentro, BorderLayout.CENTER);

        // panel para poner boton acpetar abajo
        JPanel panelBotones = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        panelBotones.add(btnAceptar);
        ventana.add(panelBotones, BorderLayout.SOUTH);
        
        btnAceptar.addActionListener(ev -> {
            try{ 
            // Obtiene el estado seleccionado del ComboBox
            String nuevoEstado = (String) comboboxEstados.getSelectedItem();
            // Se crea un DTO para enviar la informacion al BO
            PedidoDTO pedidoActualizar = new PedidoDTO();
            // se asignar el ID del pedido seleccionado
            pedidoActualizar.setIdPedido(pedido.getIdPedido());
            // se asignar el nuevo estado elegido por el usuario
            pedidoActualizar.setEstado(nuevoEstado);
            // se crea el DAO
            IPedidoDAO pedidoDAO = new PedidoDAO(conexion);
            // se crea el BO pasando el DAO
            IPedidoBO pedidoBO = new PedidoBO(pedidoDAO);
            // se manda actualizar el estado
            pedidoBO.actualizarEstadoPedido(pedidoActualizar);
            // mensaje de existo al usuario de que si se pudo actualizar
            JOptionPane.showMessageDialog(this, "Estado actualizado correctamente");
            // cierra la ventana actual
            ventana.dispose();
            // refresca la pantalla de pedidos
            dispose(); 
            new estadosPedidos().setVisible(true);
            // error controlado por el negocio
            }catch(negocioException ex){
                JOptionPane.showConfirmDialog(this, ex.getMessage());
            // error por si pasa algo inesperado
            }catch(Exception ex){
                JOptionPane.showConfirmDialog(this, "Error al actualizar el estado");
                ex.printStackTrace();
            }
            });
            ventana.setVisible(true);

        });
       
        
        return tarjeta; 
    }
    
}