package audio.sources;

import audio.Audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Son {
    private NomsSon nom;
    private AudioInputStream audioInputStream;
    private URL url;
    private Clip clip;

    public Son(NomsSon nom, String nomFicher , Clip clip ){
        this.nom = nom;
        this.url = Audio.class.getResource(nomFicher);
        try {
            audioInputStream = AudioSystem.getAudioInputStream(url);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        this.clip = clip;
        try {
            clip.open(this.getAudioInputStream());
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public NomsSon getNom() {
        return nom;
    }

    private AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    public void play(){
            clip.setFramePosition(0);
            clip.start();
    }

    public URL getUrl() {
        return url;
    }

}
