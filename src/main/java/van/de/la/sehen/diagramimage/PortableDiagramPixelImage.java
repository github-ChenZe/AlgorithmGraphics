package van.de.la.sehen.diagramimage;

import van.de.la.sehen.util.Util;
import van.de.la.sehen.warning.WarningStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PortableDiagramPixelImage {
    private BufferedImage bufferedImage;

    private PortableDiagramPixelImage next = null;

    public PortableDiagramPixelImage getNext() {
        return next;
    }

    private int getWidth() {
        return bufferedImage.getWidth();
    }

    private int getHeight() {
        return bufferedImage.getHeight();
    }

    public void setNext(PortableDiagramPixelImage next) {
        this.next = next;
    }

    public PortableDiagramPixelImage(Image image) {
        if (image instanceof BufferedImage) this.bufferedImage = (BufferedImage) image;
        else this.bufferedImage = Util.toBufferedImage(image);
    }

    public PortableDiagramPixelImage(Image image, PortableDiagramPixelImage next) {
        this(image);
        this.next = next;
    }

    public BufferedImage getBufferedImage() {
        if (bufferedImage == null) {
            WarningStream.putWarning("Null BufferedImage encountered.", this);
        }
        return bufferedImage;
    }

    private LinkedList<BufferedImage> getBufferedImageSequenceList() {
        if (next != null) {
            LinkedList<BufferedImage> list = next.getBufferedImageSequenceList();
            list.addFirst(getBufferedImage());
            return list;
        }
        LinkedList<BufferedImage> list = new LinkedList<>();
        list.addFirst(getBufferedImage());
        return list;
    }

    public List<BufferedImage> getBufferedImageSequence() {
        return getBufferedImageSequenceList();
    }

    private LinkedList<PortableDiagramPixelImage> getPortableImageSequenceList() {
        if (next != null) {
            LinkedList<PortableDiagramPixelImage> list = next.getPortableImageSequenceList();
            list.addFirst(this);
            return list;
        }
        LinkedList<PortableDiagramPixelImage> list = new LinkedList<>();
        list.addFirst(this);
        return list;
    }

    public List<PortableDiagramPixelImage> getPortableImageSequence() {
        return getPortableImageSequenceList();
    }

    public void exportToFile(String filename) {
        try {
            ImageIO.write(bufferedImage, "PNG", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
            WarningStream.putWarning("Failed to export image.", this);
        }
    }

    public void exportToFile(String filename, int count) {
        this.exportToFile(filename + count + ".png");
        if (next != null) next.exportToFile(filename, ++count);
    }

    public static PortableDiagramPixelImage emptyImage() {
        BufferedImage dimg = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        return new PortableDiagramPixelImage(dimg);
    }

    public PortableDiagramPixelImage rescale(double scale) {
        Image tmp = bufferedImage.getScaledInstance((int) (getWidth() * scale), (int) (getHeight() * scale), Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage((int) (getWidth() * scale), (int) (getHeight() * scale), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return new PortableDiagramPixelImage(dimg);
    }
}
