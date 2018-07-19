package controller;

/**
 *
 * @author Usuário 01
 */
import java.io.BufferedReader;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControllerFileChooser {
    
    public void buscarFoto(JLabel label){
        FileNameExtensionFilter fileFiltro = new FileNameExtensionFilter("jpg","png");
        JFileChooser filechoose = new JFileChooser();
        filechoose.setFileFilter(fileFiltro);
        filechoose.setDialogTitle("Escolhendo foto");
        int resposta = filechoose.showOpenDialog(null);
        
        if (resposta == JFileChooser.APPROVE_OPTION){
            label.setIcon(new ImageIcon (filechoose.getSelectedFile().getPath()));
        }
    }
    public void buscarVideo(){
        FileNameExtensionFilter fileFiltro = new FileNameExtensionFilter("mp4");
        JFileChooser filechoose = new JFileChooser();
        filechoose.setFileFilter(fileFiltro);
        filechoose.setDialogTitle("Escolhendo video");
        int resposta = filechoose.showOpenDialog(null);
        
        if (resposta == JFileChooser.APPROVE_OPTION){
            
        }
    }
        
    public void buscarTexto(JLabel label){
         FileNameExtensionFilter fileFiltro = new FileNameExtensionFilter("Doc", "pdf", "docx");
        JFileChooser filechoose = new JFileChooser();
        filechoose.setFileFilter(fileFiltro);
        filechoose.setDialogTitle("Escolhendo video");
        int resposta = filechoose.showOpenDialog(null);
        
        if (resposta == JFileChooser.APPROVE_OPTION){
            
        }
    }
}
