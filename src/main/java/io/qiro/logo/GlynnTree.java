package io.qiro.logo;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import org.apache.commons.math3.complex.Complex;

public class GlynnTree {

    private static double julia(Complex zz, int maxIteration) {
        int i = 0;
        Complex z = zz;
        while (i < maxIteration && z.abs() < 1.0) {
            z = z.pow(1.5).subtract(0.2);
            i += 1;
        }
        return i;
    }

    private static BufferedImage generate(
        double xcenter,
        double xrange,
        double ycenter,
        double yrange,
        int maxIteration,
        double whiteThreshold,
        int size
    ) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        double xmin = xcenter - xrange / 2;
        double xmax = xcenter + xrange / 2;
        double ymin = ycenter - yrange / 2;
        double ymax = ycenter + yrange / 2;

        double[][] buffer = new double[size][size];
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double x = (double) i / size * xrange + xmin;
                double y = ymax - (double) j / size * yrange;
                double z = julia(new Complex(-x, y), maxIteration);
                buffer[i][j] = z;
                min = Math.min(min, z);
                max = Math.max(max, z);
            }
        }

        double range = max - min;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double z = buffer[i][j];
                z = (z - min) / range;
                if (z < whiteThreshold) {
                    image.setRGB(size - j - 1, size - i - 1, new Color(255, 255, 255, 0).getRGB());
                } else {
                    z = Math.pow(z, 5);
                    int rgb = (int) (255 * z);
                    image.setRGB(size - j - 1, size - i - 1, new Color(rgb, rgb, rgb).getRGB());
                }
            }
        }

        return image;
    }

    public static void show(
        double xcenter,
        double xrange,
        double ycenter,
        double yrange,
        int maxIteration,
        double whiteThreshold,
        int size
    ) throws IOException {
        BufferedImage image = generate(
            xcenter, xrange,
            ycenter, yrange,
            maxIteration,
            whiteThreshold,
            size);

        JFrame f = new JFrame("Julia Example") {
            @Override
            public void paint(java.awt.Graphics g) {
                g.drawImage(image, 0, 0, null);
            }
        };
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(size, size);
        f.repaint();
        f.setVisible(true);
    }

    public static void generateImage(
        double xcenter,
        double xrange,
        double ycenter,
        double yrange,
        int maxIteration,
        double whiteThreshold,
        int size,
        File output
    ) throws IOException {
        BufferedImage image = generate(
            xcenter, xrange,
            ycenter, yrange,
            maxIteration,
            whiteThreshold,
            size);
        ImageIO.write(image, "PNG", output);
    }

    public static void main(String[] args) throws IOException {
        double xcenter = 0.54;
        double xrange = 0.35;
        double ycenter = 0.0;
        double yrange = 0.4;
        int maxIteration = 105;
        double whiteThreshold = 0.35;
        int size = 4096;

//        GlynnTree.show(xcenter, xrange, ycenter, yrange, maxIteration, whiteThreshold, size);
        File output = new File("qiro-logo_" + size + "x" + size + ".png");
        GlynnTree.generateImage(
            xcenter, xrange,
            ycenter, yrange,
            maxIteration,
            whiteThreshold,
            size,
            output
        );
    }
}
