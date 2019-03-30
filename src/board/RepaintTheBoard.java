package board;

public class RepaintTheBoard implements Runnable {

    private Board board;

    RepaintTheBoard(Board board){
        this.board = board;
    }

    @Override
    public void run() {
        this.board.repaint();
    }
}
