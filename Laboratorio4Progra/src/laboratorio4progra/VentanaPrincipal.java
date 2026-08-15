/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio4progra;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JPanel panelContenedor;
    private CardLayout cardLayout;
    private AdministrarPalabras administrador;
    private AhorcadoBase juegoActual;
    private JLabel lblGuiones;
    private JPanel panelTeclado;
    
    public VentanaPrincipal() {
        setTitle("El Ahorcado");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        administrador = new AdministrarPalabras();
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        panelContenedor.add(crearPanelMenu(), "MENU");
        panelContenedor.add(crearPanelJuego(), "JUEGO");

        add(panelContenedor);
    }


    private JPanel crearPanelMenu() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(35, 43, 56));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;

        JLabel lblTitulo = new JLabel("EL AHORCADO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblTitulo.setForeground(Color.WHITE);
        gbc.gridy = 0;
        panel.add(lblTitulo, gbc);

        JButton btnEmpezar = new JButton("EMPEZAR");
        btnEmpezar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnEmpezar.setBackground(new Color(46, 204, 113));
        btnEmpezar.setForeground(Color.WHITE);
        btnEmpezar.setPreferredSize(new Dimension(240, 45));
        gbc.gridy = 1;
        panel.add(btnEmpezar, gbc);

        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSalir.setBackground(new Color(231, 76, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setPreferredSize(new Dimension(240, 45));
        gbc.gridy = 2;
        panel.add(btnSalir, gbc);

  
        btnEmpezar.addActionListener(e -> {
                iniciarNuevaPartida();
                cardLayout.show(panelContenedor, "JUEGO"); 
        });
                btnSalir.addActionListener(e -> System.exit(0));

        return panel;
    }

 
    private JPanel crearPanelJuego() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTop.setOpaque(false);
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> cardLayout.show(panelContenedor, "MENU"));
        panelTop.add(btnVolver);
        panel.add(panelTop, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentro.setOpaque(false);

        panelCentro.add(new PanelHorca());

        JPanel panelPalabra = new JPanel(new GridBagLayout());
        panelPalabra.setOpaque(false);
        
        lblGuiones = new JLabel("_ _ _ _ _ _ _");
        lblGuiones.setFont(new Font("Monospaced", Font.BOLD, 36));
        lblGuiones.setForeground(new Color(41, 128, 185));
        panelPalabra.add(lblGuiones);

        panelCentro.add(panelPalabra);
        panel.add(panelCentro, BorderLayout.CENTER);

        panelTeclado = new JPanel(new GridLayout(3, 9, 5, 5));
        panelTeclado.setOpaque(false);
        

        panel.add(panelTeclado, BorderLayout.SOUTH);

        return panel;
    }
    private void actualizarTeclado() {
        panelTeclado.removeAll();
        char[] letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".toCharArray();
        for (char letra : letras) {
            JButton btn = new JButton(String.valueOf(letra));
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.setFocusable(false);

            if (juegoActual != null && juegoActual.getLetrasIngresadas().contains(letra)) {
                btn.setEnabled(false);
            }

            btn.addActionListener(e -> {
                try {
                    juegoActual.jugarPartida(letra);
                    btn.setEnabled(false);
                    lblGuiones.setText(juegoActual.getPalabraMostrada().replace("", " ").trim());

                    if (juegoActual.determinarGanador()) {
                        JOptionPane.showMessageDialog(this, "¡Felicidades, ganaste!");
                        cardLayout.show(panelContenedor, "MENU");
                    } else if (juegoActual.determinarPerdedor()) {
                        JOptionPane.showMessageDialog(this, "¡Perdiste! La palabra era: " + juegoActual.getPalabraSecreta());
                        cardLayout.show(panelContenedor, "MENU");
                    }
                } catch (CaracterInvalidoException | LetraDuplicadaException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            });

            panelTeclado.add(btn);
        }
        panelTeclado.revalidate();
        panelTeclado.repaint();
    }
    private void iniciarNuevaPartida() {
        String[] opciones = {"Palabra al Azar", "Palabra Fija"};
        int seleccion = JOptionPane.showOptionDialog(
            this, "Seleccione la modalidad de juego:", "Modo de Juego",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]
        );

        if (seleccion == 0) {
            juegoActual = new PalabraAlAzar(administrador);
        } else {
            String palabraFija = JOptionPane.showInputDialog(this, "Ingrese la palabra secreta:");
            if (palabraFija == null || palabraFija.trim().isEmpty()) {
                palabraFija = "JAVA";
            }
            juegoActual = new PalabraFija(palabraFija);
        }

        lblGuiones.setText(juegoActual.getPalabraMostrada().replace("", " ").trim());
        actualizarTeclado();
    }
    private static class PanelHorca extends JPanel {
        public PanelHorca() {
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.setColor(new Color(44, 62, 80));

            int w = getWidth();
            int h = getHeight();

            // Dibujo únicamente de la estructura de la horca y la cuerda
            g2.drawLine(20, h - 30, w - 20, h - 30); // Base
            g2.drawLine(60, h - 30, 60, 30);        // Poste vertical
            g2.drawLine(60, 30, w / 2, 30);         // Barra superior
            g2.drawLine(w / 2, 30, w / 2, 60);      // Cuerda
        }
    }

   
}