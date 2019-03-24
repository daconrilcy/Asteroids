package board;

public class RepaintTheBoard implements Runnable {

    Board board;
    private Thread thread;



    public RepaintTheBoard(Board board){
        this.board = board;
    }

    @Override
    public void run() {
        this.board.repaint();
    }

    public synchronized void start(){

    }
}
