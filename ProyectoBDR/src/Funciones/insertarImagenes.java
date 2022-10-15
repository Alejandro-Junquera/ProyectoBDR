package Funciones;

import java.awt.Image;
import java.io.File;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class insertarImagenes {
	public static ImageIcon ResizableImage(String path, JLabel imgLabel) {
		ImageIcon myimagen = new ImageIcon(path);
		Image img = myimagen.getImage();
		Image newimg = img.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newimg);
		return image;
	}
	public static String generarRutaImg(String relativa,JLabel imgLabel) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter soloImg = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "jpeg");
		fileChooser.setFileFilter(soloImg);
		fileChooser.showSaveDialog(null);
		relativa=".\\";
		if (fileChooser.getSelectedFile() != null) {
			File selectedFile = fileChooser.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			
			for (int i = path.indexOf("Imagenes"); i < path.length(); i++) {
				relativa += path.charAt(i);
			}
			imgLabel.setIcon(ResizableImage(relativa, imgLabel));
			return relativa;
		}
		return relativa;
		
	}

}
