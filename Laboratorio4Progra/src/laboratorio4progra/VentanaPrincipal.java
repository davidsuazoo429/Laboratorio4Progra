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
    private PanelHorca panelHorca;
    
    public VentanaPrincipal() {
        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al inicializar la interfaz: " + e.getMessage());
        }
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
            try {
                if (iniciarNuevaPartida()) {
                    cardLayout.show(panelContenedor, "JUEGO");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al iniciar partida: " + ex.getMessage());
            }
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
        btnVolver.addActionListener(e -> {
            try {
                cardLayout.show(panelContenedor, "MENU");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cambiar de vista: " + ex.getMessage());
            }
        });
        panelTop.add(btnVolver);
        panel.add(panelTop, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentro.setOpaque(false);

        panelHorca = new PanelHorca();
        panelCentro.add(panelHorca);

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
        try {
            panelTeclado.removeAll();
            char[] letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".toCharArray();
            for (char letra : letras) {
                JButton btn = new JButton(String.valueOf(letra));
                btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
                btn.setFocusable(false);

                if (juegoActual != null && juegoActual.getLetrasIngresadas() != null && juegoActual.getLetrasIngresadas().contains(letra)) {
                    btn.setEnabled(false);
                }

                btn.addActionListener(e -> {
                    try {
                        if (juegoActual != null) {
                            juegoActual.jugarPartida(letra);
                            btn.setEnabled(false);
                            
                            if (juegoActual.getPalabraMostrada() != null) {
                                lblGuiones.setText(juegoActual.getPalabraMostrada().replace("", " ").trim());
                            }
                            
                            if (panelHorca != null) {
                                panelHorca.repaint();
                            }

                            if (juegoActual.determinarGanador()) {
                                JOptionPane.showMessageDialog(this, "¡Felicidades, ganaste!");
                                cardLayout.show(panelContenedor, "MENU");
                            } else if (juegoActual.determinarPerdedor()) {
                                JOptionPane.showMessageDialog(this, "¡Perdiste! La palabra era: " + juegoActual.getPalabraSecreta());
                                cardLayout.show(panelContenedor, "MENU");
                            }
                        }
                    } catch (CaracterInvalidoException | LetraDuplicadaException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error durante la ejecución del turno: " + ex.getMessage());
                    }
                });

                panelTeclado.add(btn);
            }
            panelTeclado.revalidate();
            panelTeclado.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el teclado: " + e.getMessage());
        }
    }

    private boolean iniciarNuevaPartida() {
        try {
            String[] opciones = {"Palabra al Azar", "Palabra Fija"};
            int seleccion = JOptionPane.showOptionDialog(
                this, "Seleccione la modalidad de juego:", "Modo de Juego",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]
            );

            if (seleccion == JOptionPane.CLOSED_OPTION) {
                return false;
            }

            if (seleccion == 0) {
                juegoActual = new PalabraAlAzar(administrador);
            } else if (seleccion == 1) {
                String palabraFija = JOptionPane.showInputDialog(this, "Ingrese la palabra secreta:");
                
                if (palabraFija == null) {
                    return false;
                }
                
                palabraFija = palabraFija.trim();
                
                if (palabraFija.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar una palabra válida.");
                    return false;
                }
                
                boolean soloLetras = true;
                for (char c : palabraFija.toCharArray()) {
                    if (!Character.isLetter(c) && c != ' ') {
                        soloLetras = false;
                        break;
                    }
                }
                
                if (!soloLetras) {
                    JOptionPane.showMessageDialog(this, "La palabra solo debe contener letras del abecedario.");
                    return false;
                }
                
                juegoActual = new PalabraFija(palabraFija);
            } else {
                return false;
            }

            if (juegoActual != null && juegoActual.getPalabraMostrada() != null) {
                lblGuiones.setText(juegoActual.getPalabraMostrada().replace("", " ").trim());
            }
            
            if (panelHorca != null) {
                panelHorca.repaint();
            }
            
            actualizarTeclado();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al configurar la partida: " + e.getMessage());
            return false;
        }
    }

    private class PanelHorca extends JPanel {
        public PanelHorca() {
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setStroke(new BasicStroke(3));
                g2.setColor(new Color(44, 62, 80));

                int w = getWidth();
                int h = getHeight();

                g2.drawLine(20, h - 30, w - 20, h - 30); 
                g2.drawLine(60, h - 30, 60, 30);        
                g2.drawLine(60, 30, w / 2, 30);        
                g2.drawLine(w / 2, 30, w / 2, 60);    

                if (juegoActual != null) {
                    int fallos = 6 - juegoActual.getIntentosRestantes();
                    int centroX = w / 2;

                    if (fallos >= 1) {
                        g2.drawOval(centroX - 20, 60, 40, 40);
                    }
                    if (fallos >= 2) {
                        g2.drawLine(centroX, 100, centroX, 170);
                    }
                    if (fallos >= 3) {
                        g2.drawLine(centroX, 120, centroX - 30, 150);
                    }
                    if (fallos >= 4) {
                        g2.drawLine(centroX, 120, centroX + 30, 150);
                    }
                    if (fallos >= 5) {
                        g2.drawLine(centroX, 170, centroX - 30, 210);
                    }
                    if (fallos >= 6) {
                        g2.drawLine(centroX, 170, centroX + 30, 210);
                    }
                }
            } catch (Exception e) {
                
            }
        }
    }
}