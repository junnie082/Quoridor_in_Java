package players;

import java.util.Scanner;
import players.*;

public class Move {

    Scanner input = new Scanner(System.in); 
    public void selectDirection(Player player) {
        Move move = new Move();

        System.out.println("상 w, 좌 a, 하 x, 우 d");
        String direction = input.nextLine();
        
        if (direction.equals("w")) {
            move.moveToUp(player);
        } else if (direction.equals("a")) {
            move.moveToLeft(player);
        } else if (direction.equals("x")) {
            move.moveToDown(player);
        } else if (direction.equals("d")) {
            move.moveToRight(player);
        }
    }

    private void moveToUp(Player player) {
        int pos = player.getRowPos();
        pos -= 2;
        
        try {
            checkBoundary(pos);        
            player.setRowPos(pos);
        }  catch (IndexOutOfBoundsException e) {
            System.out.println("경계를 넘습니다. 다시 입력하세요.");
        }
    
    }

    private void moveToLeft(Player player) {
        int pos = player.getColPos();
        pos -= 2;
        try {
            checkBoundary(pos);        
            player.setColPos(pos);
        }  catch (IndexOutOfBoundsException e) {
            System.out.println("경계를 넘습니다. 다시 입력하세요.");
        }
    }

    private void moveToDown(Player player) {
        int pos = player.getRowPos();
        pos += 2;

        try {
            checkBoundary(pos);        
            player.setRowPos(pos);
        }  catch (IndexOutOfBoundsException e) {
            System.out.println("경계를 넘습니다. 다시 입력하세요.");
        }
    }

    private void moveToRight(Player player) {
        int pos = player.getColPos();
        pos += 2;

        try {
            checkBoundary(pos);        
            player.setColPos(pos);
        }  catch (IndexOutOfBoundsException e) {
            System.out.println("경계를 넘습니다. 다시 입력하세요.");
        }
    }

    private void checkBoundary(int pos) {
        if (pos < 1 || pos > 17) 
            throw new IndexOutOfBoundsException();
    }
}
