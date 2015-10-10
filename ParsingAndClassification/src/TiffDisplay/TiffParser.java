package TiffDisplay;

import com.sun.media.jai.codec.ByteArraySeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;

import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TiffParser {
  static Image load(byte[] data) throws Exception{
    SeekableStream stream = new ByteArraySeekableStream(data);
    String[] names = ImageCodec.getDecoderNames(stream);
    ImageDecoder dec = ImageCodec.createImageDecoder(names[0], stream, null);
    RenderedImage im = dec.decodeAsRenderedImage();
    return PlanarImage.wrapRenderedImage(im).getAsBufferedImage();
  }

  public static void main(String[] args) throws Exception{
    String path;
    if (args.length == 0) {
      path = JOptionPane.showInputDialog(null, "Image Path", "resources/CCITT_1.TIF");
    }
    else {
      path = args[0];
    }

    FileInputStream in = new FileInputStream(path);
    FileChannel channel = in.getChannel();

    ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
    channel.read(buffer);
    Image image = load(buffer.array());
    Image imageScaled = image.getScaledInstance(500, -1,  Image.SCALE_SMOOTH);
    JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon( imageScaled )));
  }}
