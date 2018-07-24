package controller;


import java.io.File;
import model.User;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

 /**
 * Classe de Testes da classe ControllerFile.
 * 
 * @author Matheus Nascimento e Elvis Serafim
 * @since Jul 2018
 * @version 1.0
 **/
public class ControllerFileTest {
    
    ControllerFile controleFile;
    String diretorio;
    User usuario01, usuario02;
    

    @Before
    public void setUp() {
        controleFile = new ControllerFile();
        diretorio = "Resources/teste.teste";
        usuario01 = new User("Jose", "jose@gmail", "jose123", "88223344", "10122000", "Feira de santana", "JoseSantos");
        usuario02 = new User("Antonio", "antonio@gmail", "antonio123", "99223344", "23041999", "Feira de santana", "AntonioFerreira");
    }

    @Test
    public void salvarArquivo() throws Exception {
        controleFile.save(usuario01, diretorio);
        File arquivo = new File(diretorio);
        assertEquals(true, arquivo.exists());
        arquivo.delete();
    }

    @Test
    public void lerArquivo() throws Exception {
        controleFile.save(usuario02, diretorio);
        User usuario = (User) ControllerFile.readDate(diretorio);
        assertEquals(true ,usuario.equals(usuario02));
        assertEquals(false, usuario.equals(usuario01));
        File arquivo = new File(diretorio);
        arquivo.delete();
    }
}
