package aed;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author enzof
 */
public class telaprincipal extends JFrame {
    private JMapViewer map;
    private List<Lugar> lugares = new ArrayList<>();
    private List<Rota> rotas = new ArrayList<>();

    public telaprincipal() {
        setTitle("Mapa - Cadastro de Lugares e Rotas (JMapViewer)");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        map = new JMapViewer();
        map.setZoom(5);
        map.setDisplayPosition(new Coordinate(-15.7801, -47.9292), 4); // Centraliza no Brasil

        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    Point p = e.getPoint();
                    Coordinate coord = (Coordinate) map.getPosition(p);
                    JOptionPane.showMessageDialog(telaprincipal.this,
                            "Coordenadas: " + coord.getLat() + ", " + coord.getLon());
                    cadastrarLugar(coord);
                }
            }
        });

        JButton rotaBtn = new JButton("Cadastrar Rota");
        rotaBtn.addActionListener(e -> cadastrarRota());

        add(map, BorderLayout.CENTER);
        add(rotaBtn, BorderLayout.SOUTH);
    }

    private void cadastrarLugar(Coordinate coord) {
        String nome = JOptionPane.showInputDialog(this, "Nome do lugar:");
        if (nome == null || nome.isEmpty()) return;

        String[] tipos = {"Lugar 1", "Lugar 2", "Lugar 3"};
        String tipo = (String) JOptionPane.showInputDialog(
                this, "Selecione o tipo:",
                "Tipo do lugar",
                JOptionPane.PLAIN_MESSAGE,
                null,
                tipos,
                tipos[0]
        );

        Lugar lugar = new Lugar(nome, tipo, coord);
        lugares.add(lugar);

        // Marcador no mapa
        map.addMapMarker(new MapMarkerDot(nome, coord));

        JOptionPane.showMessageDialog(this,
                "Lugar cadastrado:\n" + lugar);
    }

    private void cadastrarRota() {
        if (lugares.size() < 2) {
            JOptionPane.showMessageDialog(this,
                    "É necessário pelo menos 2 lugares cadastrados.");
            return;
        }

        Lugar origem = (Lugar) JOptionPane.showInputDialog(
                this, "Selecione o lugar de origem:",
                "Origem",
                JOptionPane.PLAIN_MESSAGE,
                null,
                lugares.toArray(),
                lugares.get(0)
        );

        Lugar destino = (Lugar) JOptionPane.showInputDialog(
                this, "Selecione o lugar de destino:",
                "Destino",
                JOptionPane.PLAIN_MESSAGE,
                null,
                lugares.toArray(),
                lugares.get(0)
        );

        if (origem == null || destino == null || origem.equals(destino)) {
            JOptionPane.showMessageDialog(this, "Seleção inválida.");
            return;
        }

        double distancia = calcularDistancia(origem.getCoord(), destino.getCoord());
        Rota rota = new Rota(origem, destino, distancia);
        rotas.add(rota);

        // Desenhar a rota no mapa
        List<Coordinate> coords = new ArrayList<>();
        coords.add(origem.getCoord());
        coords.add(destino.getCoord());
        map.addMapPolygon(new MapPolygonImpl(coords));

        JOptionPane.showMessageDialog(this,
                "Rota cadastrada:\n" + rota);
    }

    // Fórmula de Haversine para calcular distância em km
    private double calcularDistancia(Coordinate c1, Coordinate c2) {
        final double R = 6371.0; // raio da Terra em km
        double lat1 = Math.toRadians(c1.getLat());
        double lon1 = Math.toRadians(c1.getLon());
        double lat2 = Math.toRadians(c2.getLat());
        double lon2 = Math.toRadians(c2.getLon());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new telaprincipal().setVisible(true);
        });
    }
}




