/*
 *              Hewlett Packard Brazil - R&D Brazil
 *       Information Routing and Integration System - IRIS
 *                   All rights reserved.
 *
 * @author Roberto Argenta Coutinho <roberto.coutinho@hp.com>
 * @Date 
 * @Description
 */
package com.rac.recognum;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/**
 *

 */
public class DrawingArea extends JPanel {

    private ArrayList<Point> points = new ArrayList<Point>();

    public DrawingArea() {
        setBackground(Color.WHITE);
        MyMouseListener ml = new MyMouseListener();
        addMouseListener(ml);
        addMouseMotionListener(ml);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color foreground = g.getColor();
        g.setColor(Color.BLACK);
        for (Point p : points) {
            //g.drawRect(p.x, p.y, 4, 4);
            g.fillRect(p.x, p.y, 4, 4);
        }
    }

    public void clear() {
        points.clear();
        repaint();
    }

    class MyMouseListener extends MouseInputAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            points.add(e.getPoint());
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

    public double[] getPixels() {
        this.repaint();
        Dimension size = this.getSize();
        int imageType = BufferedImage.TYPE_INT_ARGB;

        Graphics2D g = (Graphics2D) this.getGraphics();
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        this.paint(g2d);
        int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        double[] doub = new double[pixels.length];

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pixels.length; i++) {
            double d = Double.valueOf(String.valueOf(pixels[i]));            
            doub[i] = d;
            builder.append(d).append(",");
        }
        //System.out.print(builder.toString());
        return doub;
    }
}