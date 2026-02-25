/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

import Negocio.BOs.IPedidoBO;
import Negocio.BOs.PedidoBO;
import Negocio.DTOs.DetallePedidoDTO;
import Negocio.Excepciones.negocioException;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import persistencia.DAOs.PedidoDAO;
import persistencia.DAOs.PizzaEnPedidoDAO;
import persistencia.Dominio.Pedido;
import persistencia.Dominio.PedidoActual;
import persistencia.Dominio.Sesion;
import persistencia.conexion.ConexionBD;
import persistencia.conexion.IConexionBD;

/**
 * Pantalla (DE PEDIDO PROGRAMADO) en la que el usuario ve su pedido actual. Como si fuera un "carrito de compras"
 * @author luiscarlosbeltran
 */
public class MiPedido extends JFrame {

    private JLabel lblTotal;
    private JPanel panelTarjetas;

    /**
     * metodo para crear y mostrar MiPedido
     */
    public MiPedido() {

        setTitle("Mi Pedido");
        setSize(700, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //la parte de arriba de siempre
        JPanel arriba = new JPanel();
        arriba.setBackground(new Color(70,150,220));

        JLabel titulo = new JLabel("Gorillaz Pipsa");
        titulo.setFont(new Font("Serif", Font.BOLD, 50));

        arriba.add(titulo);
        add(arriba, BorderLayout.NORTH);
        
        //parte del centro, boxlayout para meter 2 cosas: el total dinamico y el scroll con las pizzas metidas al pedido
        JPanel centroPrincipal = new JPanel();
        centroPrincipal.setLayout(new BoxLayout(centroPrincipal, BoxLayout.Y_AXIS));

        //parte del total dinamico
        lblTotal = new JLabel("", SwingConstants.CENTER);
        lblTotal.setFont(new Font("Serif", Font.BOLD, 24));
        lblTotal.setOpaque(true);
        lblTotal.setBackground(new Color(255,170,80));
        lblTotal.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        lblTotal.setAlignmentX(Component.CENTER_ALIGNMENT);

        centroPrincipal.add(lblTotal);
        centroPrincipal.add(Box.createVerticalStrut(15));

        //las tarjetas de las pizzas que se metieron al pedido
        panelTarjetas = new JPanel();
        panelTarjetas.setLayout(new BoxLayout(panelTarjetas, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(panelTarjetas);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);

        centroPrincipal.add(scroll);

        add(centroPrincipal, BorderLayout.CENTER);

        cargarTarjetas();
        actualizarTotal();
        
        JPanel panelCupon = new JPanel(new BorderLayout());
        panelCupon.setBorder(BorderFactory.createTitledBorder("Cupon (opcional)"));

        JTextField areacupon = new JTextField();
        panelCupon.add(areacupon, BorderLayout.CENTER);

        centroPrincipal.add(Box.createVerticalStrut(10));
        centroPrincipal.add(panelCupon);
        
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
        
        JButton confirmar = new JButton("Confirmar Pedido");
        confirmar.setFont(new Font("Serif", Font.BOLD, 24));
        confirmar.setBackground(new Color(70, 150, 220));
        confirmar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        confirmar.setMaximumSize(new Dimension(350, 60));
        abajo.add(confirmar);

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
        
        //aqui va uno largo... action listener del boton confirmar pedido
        confirmar.addActionListener(e -> {

    try {

        if (PedidoActual.getDetalles().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en el pedido");
            return;
        }

        Integer idCliente = Sesion.getIdCliente();

        if (idCliente == null) {
            JOptionPane.showMessageDialog(this, "Debe iniciar sesión");
            return;
        }

        String codigoCupon = areacupon.getText().trim();
        double total = PedidoActual.calcularTotal();

        IConexionBD conexion = new ConexionBD();
        PedidoDAO pedidoDAO = new PedidoDAO(conexion);
        PizzaEnPedidoDAO detalleDAO = new PizzaEnPedidoDAO(conexion);

        Pedido pedido = new Pedido();
        pedido.setEstado(Pedido.estadoPedido.PENDIENTE);
        pedido.setNotas("Pedido Programado");
        pedido.setCosto(total);

        int idPedido;

        if (!codigoCupon.isEmpty()) {
            idPedido = pedidoDAO.crearPedidoProgramadoConCupon(
                    idCliente, codigoCupon, pedido.getNotas(), total);
        } else {
            idPedido = pedidoDAO.crearPedidoProgramadoSinCupon(idCliente, pedido);
        }

        for (DetallePedidoDTO detalle : PedidoActual.getDetalles()) {
            detalleDAO.insertarPizzaEnPedido(
                    idPedido,
                    detalle.getPizza().getId_pizza(),
                    detalle.getCantidad(),
                    detalle.getNotas()
            );
        }

        JOptionPane.showMessageDialog(this, "Pedido confirmado correctamente");

        PedidoActual.limpiar();
        dispose();
        new menuProgramado().setVisible(true);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
});
    }

    /**
     * Metodo para enseñar las tarjetitas de cada pipsa creada
     */
    private void cargarTarjetas() {

        panelTarjetas.removeAll();

        for (DetallePedidoDTO detalle : PedidoActual.getDetalles()) {

            JPanel tarjeta = crearTarjeta(detalle);
            panelTarjetas.add(tarjeta);
            panelTarjetas.add(Box.createVerticalStrut(15));
        }

        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }

    /**
     * crea las tarjetitas de cada pipsa que selecciono el ususario
     * @param detalle es el "producto" que se mete al carrito
     * @return un JPanel que es la tarjetita de cada pipsa
     */
    private JPanel crearTarjeta(DetallePedidoDTO detalle) {

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setMaximumSize(new Dimension(600,150));
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        //izuqiersa, el nombre de la pipsa
        JLabel nombre = new JLabel(detalle.getPizza().getNombre());
        nombre.setFont(new Font("Serif", Font.BOLD, 20));
        tarjeta.add(nombre, BorderLayout.WEST);

        //centro, detalles y cosas
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        JLabel tamano = new JLabel("Tamaño: " + detalle.getPizza().getTamano());
        JLabel cantidad = new JLabel("Cantidad: " + detalle.getCantidad());
        JLabel costo = new JLabel("Costo: $" + detalle.getSubtotal());

        centro.add(tamano);
        centro.add(cantidad);
        centro.add(costo);

        tarjeta.add(centro, BorderLayout.CENTER);

        //derecha, para elimnar lapipsa
        JButton eliminar = new JButton("Eliminar");
        eliminar.setBackground(Color.RED);
        eliminar.setForeground(Color.BLACK);

        eliminar.addActionListener(e -> {
            PedidoActual.getDetalles().remove(detalle);
            cargarTarjetas();
            actualizarTotal();
        });

        tarjeta.add(eliminar, BorderLayout.EAST);

        return tarjeta;
    }

    /**
     * pone el texto del label que muestra el total, es dinamico, o sae se actualiza con los precios de las pipsas seleccionadas
     */
    private void actualizarTotal() {
        lblTotal.setText("Pedido ----- Total: $" + PedidoActual.calcularTotal());
    }
}