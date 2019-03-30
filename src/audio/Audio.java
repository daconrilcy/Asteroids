package audio;




import audio.sources.NomsSon;
import audio.sources.Son;
import javax.sound.sampled.*;
import java.util.ArrayList;


public class Audio {

    private ArrayList<Clip> clips;
    private Son lose;
    private ArrayList<Son> ticks;
    private Son bound;

    public Audio() {
        clips = new ArrayList<>();
        ticks = new ArrayList<>();
        try {

            Mixer.Info[] mixInfo = AudioSystem.getMixerInfo();
            Mixer mixer = AudioSystem.getMixer(mixInfo[0]);
            for (Mixer.Info info : mixInfo) {
                String minf = info.getDescription();
                if (minf.equalsIgnoreCase("Direct Audio Device: DirectSound Playback")) {
                    mixer = AudioSystem.getMixer(info);
                    break;
                }
            }

            DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
           for (int i = 0 ; i<20 ; i++){
               clips.add((Clip) mixer.getLine(dataInfo));
           }
           creerSons();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

    }

    private void creerSons(){
        lose = new Son(NomsSon.lose, "sources/lose.wav", clips.get(0));
        for (int i = 0 ; i < 10 ;i++){
            int n = i+1;
            String nomFichier = "sources/toc" + n + ".wav";
            ticks.add(new Son(NomsSon.tick, nomFichier , clips.get(n)));
        }
        bound = new Son(NomsSon.bound , "sources/bound.wav", clips.get(11));

    }

    public void play(NomsSon nomsSon){

        switch (nomsSon){
            case lose:
                lose.play();
                break;
            case tick:
                playTic();
                break;
            case bound:
                bound.play();
                break;
        }
        //boucle necessaire pour pas stopper le programme tant que l'audio n'est lu complétement
        /*do {
            try {
                Thread.sleep(5);
            }catch (Exception e){

            }
        }while (clip.isActive());*/
    }

    private void playTic(){
      int i = (int)(Math.random()*9);

      ticks.get(i).play();
    }

}
