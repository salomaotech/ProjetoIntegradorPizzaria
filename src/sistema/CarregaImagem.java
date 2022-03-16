/*

Carrega uma imagem informando sua localização e retorna um imageicon

 */
package sistema;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class CarregaImagem {

    private ImageIcon imagem;
    int altura_original, largura_original;

    // construtor
    public CarregaImagem(String url_servidor, int altura, int largura) {

        // try para tratar erros!
        try {

            // cria nova url
            URL url = new URL(url_servidor);

            // imagem
            ImageIcon imagemIcone = new ImageIcon(ImageIO.read(url));

            // escala de imagem
            Image image = imagemIcone.getImage();
            Image newimg = image.getScaledInstance(largura, altura, java.awt.Image.SCALE_DEFAULT);
            imagem = new ImageIcon(newimg);

            // seta as dimensoes
            altura_original = imagemIcone.getIconHeight();
            largura_original = imagemIcone.getIconWidth();

        } catch (MalformedURLException ex) {

            // mensagem de erro
            System.out.println("Url inválida!" + " " + ex);

        } catch (IOException ex) {

            // mensagem de erro
            System.out.println("Arquivo não carregado!" + " " + ex);

        } catch (java.lang.NullPointerException ex) {

            System.out.println("Arquivo não pode ser aberto!" + " " + ex);

        }

    }

    // retorna imagem
    public ImageIcon getImagem() {

        return imagem;

    }

}
