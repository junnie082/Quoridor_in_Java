package partition;

import java.util.Scanner;
import java.util.regex.*;

import board.*;
import bfs.BFS;
import players.Player;


public class Wood {
    // 판은 '-' 로 표현.
    Board board = new Board();
    private static int rowWood;
    private static int colWood; 
    private static String woodDirection;

    Scanner input = new Scanner(System.in); 

    public int getRowWood() {
        return this.rowWood;
    }

    public int getColWood() {
        return this.colWood;
    }

    public String getWoodDirection() {
        return this.woodDirection;
    }

    public boolean putWood(Player player1, Player player2) {
        boolean check = true;
        BFS bfs = new BFS(); 

        while (check) {
            changeRowToInt();
            boolean trueIfVertical = decideVerticalOrHorizontal();

            changeColToInt(trueIfVertical);
            woodDirection(trueIfVertical);

            check = board.putWoodOnBoard(trueIfVertical);
            if (check) continue;
            // check 가 true 면 이미 해당 방향에 장애물이 있다.
            check = bfs.isThereAtLeastOneWay(player1);
            
            if (check) {  // false 면 가능성 없음.
                System.out.println("플레이어가 상대 진영에 도달할 수 있는 경로가 최소 한 개 이상이어야 합니다. 다시 입력하세요. row col direction");
                Board.mainBoard[rowWood][colWood] = '*';
                resetBoardVal(); 
                return true;
            }

            check = bfs.isThereAtLeastOneWay(player2);

            if (check) {  // false 면 가능성 없음.
                System.out.println("플레이어가 상대 진영에 도달할 수 있는 경로가 최소 한 개 이상이어야 합니다. 다시 입력하세요. row col direction");
                Board.mainBoard[rowWood][colWood] = '*';
                resetBoardVal(); 
                return true;
            }

        }
        return false; 
    }

    private void resetBoardVal() {
        
        if (woodDirection.equals("w")) {
            Board.mainBoard[rowWood-1][colWood] = ' ';
            Board.mainBoard[rowWood-2][colWood] = '*';
        } 
        else if (woodDirection.equals("d")) {
            Board.mainBoard[rowWood][colWood+1] = ' ';
            Board.mainBoard[rowWood][colWood+2] = '*';
        }
        else if (woodDirection.equals("x")) {
            Board.mainBoard[rowWood+1][colWood] = ' ';
            Board.mainBoard[rowWood+2][colWood] = '*';
        }
        else if (woodDirection.equals("a")) {
            Board.mainBoard[rowWood][colWood-1] = ' ';
            Board.mainBoard[rowWood][colWood-2] = '*';
        }
        
        
    }

    private void woodDirection(boolean trueIfVertical) {
        System.out.println("해당 좌표로부터 나무 판자가 놓일 방향을 입력하세요. ");
        input.nextLine();
        while(true) {
            try {
                IllegalArgumentException e2 = new IllegalArgumentException();
                if (trueIfVertical) {
                    System.out.println("나무 판자는 세로로 놓이므로 '상 w 하 x' 만 입력할 수 있습니다. ");
                    woodDirection = input.nextLine();
                    if (woodDirection.equals("w")) {
                        board.checkBoundary(rowWood-2);
                    } else if (woodDirection.equals("x")) {
                        board.checkBoundary(rowWood+2);
                    } else {
                        throw e2;
                    }
                } else {
                    System.out.println("나무 판자는 가로로 놓이므로 '좌 a 우 d' 만 입력할 수 있습니다. ");
                    woodDirection = input.nextLine();

                    if (woodDirection.equals("a")) {
                        board.checkBoundary(colWood-2);
                    } else if (woodDirection.equals("d")) {
                        board.checkBoundary(colWood+2);
                    } else {
                        throw e2;
                    }
                }
                break;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("나무 판자가 놓일 방향을 다시 입력하세요. ");
            } catch(IllegalArgumentException e2) {
                System.out.println("나무 판자의 방향이 옳지 않습니다. 다시 입력하세요.");
            }
        }
        
    }

    private void changeRowToInt() {
        while (true) {
            System.out.println("나무 판자를 놓을 좌표의 row 값을 입력하세요: row");
            String row = input.next();

            Pattern pt1 = Pattern.compile("^[1-8]*$");
            String pt2 = "^[a-i]*$";

            Matcher matcher1 = pt1.matcher(row); 
            boolean regex2 = Pattern.matches(pt2, row); 

            if (matcher1.find()) {
                // 12345678
                rowWood = 2 * Integer.parseInt(row); 
            } else if (regex2) {
                // abcdefghi
                rowWood = 2 * ((int)row.charAt(0) - (int)'a') + 1;
            }
            if (rowWood >= 1 && rowWood <= 17) break;
            System.out.println("1부터 8까지의 숫자만 입력하세요.");
        }
    }

    private boolean decideVerticalOrHorizontal() {
        if (rowWood % 2 == 1) {
            // VERTICAL
            return true;
        } else {
            // HORIZONTAL
            return false; 
        }
    }

    private void changeColToInt(boolean trueIfVertical) {
        Wood wood =new Wood();  
        if (trueIfVertical) {
            // vertical
            wood.putVertically();                
        } else {
            // horizontal
            wood.putHorizontally();
        }   
    }

    private void putVertically() {
        Pattern pt1 = Pattern.compile("^[1-8]*$");
        System.out.println("나무 판자는 세로로 놓입니다. col 값을 입력하세요: (1,2,3,4,5,6,7,8) ");
        
        while (true) {
            String col = input.next();
            Matcher matcher1 = pt1.matcher(col); 
            if (matcher1.find()) {
                colWood = 2 * Integer.parseInt(col);
                if (colWood >= 1 && colWood <= 17) return; 
                System.out.println("1부터 8까지의 숫자나 a부터 i까지의 알파벳만 입력하세요.");
            }
            System.out.println("나무 판자는 세로로 놓입니다. col 값을 다시 입력하세요: ");
        }
    }

    private void putHorizontally(){
        String pt2 = "^[a-i]*$";
        System.out.println("나무 판자는 가로로 놓입니다. col 값을 입력하세요: (a,b,c,d,e,f,g,h,i) ");

        while (true) {
            String col = input.next();
            boolean regex2 = Pattern.matches(pt2, col); 
            if (regex2) {
                colWood = 2 * ((int)col.charAt(0) - (int)'a') + 1;
                if (colWood >= 1 && colWood <= 17) return;
                System.out.println("1부터 8까지의 숫자나 a부터 i까지의 알파벳만 입력하세요.");
            }
            System.out.println("나무 판자는 가로로 놓입니다. col 값을 다시 입력하세요: "); 
        }
    }
}
