import java.util.Scanner;

/**class Clemons_Kevin_Lab3  asks the user to enter six chess pieces and assumes the user will
 not repeat any entries. the program asks for the color and inital coordinates
 of each piece. The program then asks for target coordinates and checks if each
 of the six pieces can move to that position.
 @author Kevin Clemons
 @version 1.0
 @since 2020-11-10

  *******************************Change Log*********************************
  **2020-09-23, 2300, Clemons
 -added ChessPiece class
 -added classes for all chesspieces
 -added ChessBoard class
 -added enums color, piece, and column

  **2020-09-26, 1800, clemons
 -added promptChessPiece(), promptColor(), promptCol(), promptRow()
 -added main method (loops to store objects and manipulate data)
 -fixed bug in class Queen verifyTarget()
 */
public class Clemons_Kevin_Lab3{
    public static void main(String[] args){
        /** field for the number of pieces that can be checked*/8
        final int NUM_OF_PIECES = 6;

        //creates a chessboard to verify position
        ChessPiece pieces[] = new ChessPiece[NUM_OF_PIECES];
        Chessboard validator = new Chessboard();

        //stores object in array
        for(int i = 0; i < pieces.length; i++){
            pieces[i] = promptChessPiece();
        }

        //obtains target position, checks if its a valid move
        System.out.println("please input the target column for each piece: ");
        Column targetCol = promptColumn();

        System.out.println("please input the target row for each piece: ");
        int targetRow = promptRow();

        while(!validator.withinChessboard(targetRow, targetCol)){
            System.out.println("That is not a possible location on a chessboard!");
            System.out.println("please input the target column for each piece: ");
            targetCol = promptColumn();

            System.out.println("please input the target row for each piece: ");
            targetRow = promptRow();
        }

        //Checks if each Chesspiece object in container can move to target position
        for(int i = 0; i < pieces.length; i++){
            if(pieces[i].verifyTarget(targetRow, targetCol)){
                System.out.println(names[i] + " at " + pieces[i].getCol() + pieces[i].getRow() + " can move to " + targetCol + targetRow);
            }
            else{
                System.out.println(names[i] + " at " + pieces[i].getCol() + pieces[i].getRow() + " can NOT move to " + targetCol + targetRow);

            }
        }

    }

    /** field to store input for chess piece names entered by user and an a global accumulator*/
    static String names[] = new String[6];
    static int j = 0;

    /**
     * Prompts the user to enter the desired chesspiece and color
     * then prompts for the starting position of that piece and returns
     * a ChessPiece object
     *
     * @return ChessPiece currentPiece
     */
    public static ChessPiece promptChessPiece(){
        //obtains user info
        System.out.println("input desired chess piece, must be 'pawn', 'king', 'queen', 'bishop', 'rook', or 'knight' exactly: ");
        Scanner in = new Scanner(System.in);
        String piece = in.nextLine();
        piece.trim();
        piece.toLowerCase();
        Color color = promptColor();
        ChessPiece currentPiece = new Pawn();

        if(piece.equals(Piece.king.name()) || piece.equals(Piece.pawn.name()) || piece.equals(Piece.knight.name()) || piece.equals(Piece.queen.name())
                || piece.equals(Piece.bishop.name()) || piece.equals(Piece.rook.name())){

            switch(piece){
                case "pawn":
                    currentPiece = new Pawn(Piece.pawn, color);
                    names[j] = String.valueOf(Piece.pawn);
                    j++;
                    break;

                case "rook":
                    currentPiece = new Rook(Piece.rook, color);
                    names[j] = String.valueOf(Piece.rook);
                    j++;
                    break;

                case "king":
                    currentPiece = new King(Piece.king, color);
                    names[j] = String.valueOf(Piece.king);
                    j++;
                    break;

                case "queen":
                    currentPiece = new Queen(Piece.queen, color);
                    names[j] = String.valueOf(Piece.queen);
                    j++;
                    break;

                case "bishop":
                    currentPiece = new Bishop(Piece.bishop, color);
                    names[j] = String.valueOf(Piece.bishop);
                    j++;
                    break;

                case "knight":
                    currentPiece = new Knight(Piece.knight, color);
                    names[j] = String.valueOf(Piece.knight);
                    j++;
                    break;

            }
        }
        else{
            //if input does not match required format, inform user and restart
            System.out.println("This input must match 'pawn', 'king', 'queen', 'bishop', 'rook', or 'knight' exactly. \nplease input a correct chess piece: ");
            return promptChessPiece();
        }

        //obtains starting coordinates
        System.out.println("please input the Starting column: ");
        Column col = promptColumn();
        currentPiece.setCol(col);
        System.out.println("please input the Starting row: ");
        int row = promptRow();
        currentPiece.setRow(row);

        return currentPiece;

    }

    /**
     asks the user which color, checks if its valid then returns Color enum

     @return Color color
     */
    public static Color promptColor(){
        System.out.println("please input the chosen color, must be black or white: ");
        Scanner sc = new Scanner(System.in);
        String info = sc.nextLine();
        info.trim();
        info.toLowerCase();
        Color color = Color.white;

        if(info.equals(Color.black.name()) ^ info.equals(Color.white.name())){
            switch(info){
                case "white":
                    color = Color.white;
                    break;

                case "black":
                    color = Color.black;
                    break;
            }
        }
        else{
            //if input does not match required format, inform user and restart
            System.out.println("This input must be either black or white. \n please input a correct color: ");
            return promptColor();
        }
        return color;

    }

    /**
     asks the user the x axis , checks if its valid then returns corresponding Column object

     @return Column Tcol
     */
    public static Column promptColumn(){
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        temp.trim();
        temp = temp.toUpperCase();
        char tempCol = temp.charAt(0);
        if(tempCol == 'A' ^ tempCol =='B' ^ tempCol =='C' ^ tempCol =='D'
                ^ tempCol =='E' ^ tempCol =='F' ^ tempCol =='G' ^ tempCol =='H'){
            Column Tcol = Column.B;
            switch(tempCol){
                case 'A':
                    Tcol = Column.A;
                    break;

                case 'B':
                    Tcol = Column.B;
                    break;

                case 'C':
                    Tcol = Column.C;
                    break;

                case 'D':
                    Tcol = Column.D;
                    break;

                case 'E':
                    Tcol = Column.E;
                    break;

                case 'F':
                    Tcol = Column.F;
                    break;

                case 'G':
                    Tcol = Column.G;
                    break;

                case 'H':
                    Tcol = Column.H;
                    break;
            }

            return Tcol;

        }
        else{
            //if input does not match required format, inform user and restart
            System.out.println("This input must be one letter from A - H. \n please input a correct column: ");
            return promptColumn();
        }

    }

    /**
     asks the user y axis, checks if its valid then returns int

     @return int row
     */
    public static int promptRow(){
        Scanner sc = new Scanner(System.in);
        String tempRow = sc.nextLine();
        tempRow.trim();
        int row = Integer.parseInt(tempRow);
        if(row == 1 || row == 2 || row == 3 || row == 4 || row == 5 || row == 6 || row == 7 || row == 8){
            return row;
        }
        else{
            //if input does not match required format, inform user and restart
            System.out.println("This input must be a number betweeen 1-8!");
            return promptRow();
        }
    }


}


/**class Chesspiece creates and stores information on a Chesspiece object
 and verifies its movements are valid
 @author  Kevin Clemons
 @version 1.0
 @since   2020-11-10 */
abstract class ChessPiece{
    /** fields for managing piece attributes: name, color, x-axis, y-axis*/
    private  Piece pieceName;
    protected  Color color;
    protected  int row;
    protected  Column col;

    /** Empty Constructor
     sets all fields value to a default value */
    ChessPiece(){
        this.pieceName = Piece.pawn;
        this.color = Color.white;
        this.row = 1;
        this.col = Column.A;
    }

    /** Constructor sets all fields value to a default value
     @param pieceName sets piece name
     @param color sets piece color
     */
    ChessPiece(Piece pieceName, Color color){
        this.pieceName = pieceName;
        this.color = color;
    }

    /**method getColor returns the piece color
     @return color
     */
    public String getColor(){
        return String.valueOf(color);
    }

    /**method getRow returns the piece row position
     @return row
     */
    public int getRow(){
        return row;
    }

    /**method getCol returns the piece column position
     @return col
     */
    public Column getCol(){
        return col;
    }

    /**method setRow sets the piece row position
     @param row
     */
    public void setRow(int row){
        this.row = row;
    }

    /**method setCol sets the piece column position
     @param col
     */
    public void setCol(Column col){
        this.col = col;
    }

    /**
     takes in an x and y axis and the current chess piece and
     determines the elibility of moving to that position from
     the set, or initial, x and y axis based on the rules for
     the current chess piece.

     @param int row: the y axis
     @param Column col: the x axis

     @return boolean
     */
    abstract boolean verifyTarget(int row, Column col);

}

/**class Pawn creates and stores information on a Pawn chess piece
 and verifies its movements are valid
 @author  Kevin Clemons
 @version 1.0
 @since   2020-11-10 */
class Pawn extends ChessPiece{

    /** Empty Constructor
     sets all fields value to a default value
     */
    Pawn(){
        super();
    }

    /** Constructor sets all fields value to a default value
     @param pieceName sets piece name
     @param color sets piece color
     */
    Pawn(Piece pieceName, Color color){
        super(pieceName, color);
    }


    /**
     takes in an x and y axis and the current chess piece and
     determines the elibility of moving to that position from
     the set, or initial, x and y axis based on the rules for
     the current chess piece.

     @param int row: the y axis
     @param Column col: the x axis

     @return boolean
     */
    public boolean verifyTarget(int row, Column col){
        boolean possibleMove = false;
        String tempTarget = String.valueOf(col);
        int num = tempTarget.charAt(0);

        String tempDest = String.valueOf(this.col);
        int num2 = tempDest.charAt(0);
        // y-axis can only have a difference of one x-axis must not change.
        //if color is black, it can only move in the -y direction. vice versa
        if(color.equals("black")){
            if((num == num2)  && (row - this.row == -1)){
                possibleMove = true;
            }
            else{
                possibleMove = false;
            }
        }
        else{
            if((num == num2)  && (row - this.row == 1)){
                possibleMove = true;
            }
            else{
                possibleMove = false;
            }
        }
        return possibleMove;
    }
}

/**class Rook creates and stores information on a Rook chess piece
 and verifies its movements are valid
 @author  Kevin Clemons
 @version 1.0
 @since   2020-11-10 */
class Rook extends ChessPiece{

    /** Empty Constructor
     sets all fields value to a default value
     */
    Rook(){
        super();
    }

    /** Constructor sets all fields value to a default value
     @param pieceName sets piece name
     @param color sets piece color
     */
    Rook(Piece pieceName, Color color){
        super(pieceName, color);
    }

    /**
     takes in an x and y axis and the current chess piece and
     determines the elibility of moving to that position from
     the set, or initial, x and y axis based on the rules for
     the current chess piece.

     @param int row: the y axis
     @param Column col: the x axis

     @return boolean
     */
    public boolean verifyTarget(int row, Column col){
        boolean possibleMove = false;
        String tempTarget = String.valueOf(col);
        int num = tempTarget.charAt(0);

        String tempDest = String.valueOf(this.col);
        int num2 = tempDest.charAt(0);

        if(((Math.abs(num - num2) <= 8) && (row - this.row == 0))
                || ((num - num2 == 0) && (Math.abs(row - this.row) <= 8))){
            possibleMove = true;
        }
        else{
            possibleMove = false;
        }
        return possibleMove;
    }
}

/**class Bishop creates and stores information on a Bishop chess piece
 and verifies its movements are valid
 @author  Kevin Clemons
 @version 1.0
 @since   2020-11-10 */
class Bishop extends ChessPiece{

    /** Empty Constructor
     sets all fields value to a default value
     */
    Bishop(){
        super();
    }

    /** Constructor sets all fields value to a default value
     @param pieceName sets piece name
     @param color sets piece color
     */
    Bishop(Piece pieceName, Color color){
        super(pieceName, color);
    }

    /**
     takes in an x and y axis and the current chess piece and
     determines the elibility of moving to that position from
     the set, or initial, x and y axis based on the rules for
     the current chess piece.

     @param int row: the y axis
     @param Column col: the x axis

     @return boolean
     */
    public boolean verifyTarget(int row, Column col){
        boolean possibleMove = false;
        String tempTarget = String.valueOf(col);
        int num = tempTarget.charAt(0);

        String tempDest = String.valueOf(this.col);
        int num2 = tempDest.charAt(0);

        if((Math.abs(num - num2) == Math.abs(row - this.row))){
            possibleMove = true;
        }
        else{
            possibleMove = false;
        }
        return possibleMove;
    }
}

/**class Knight creates and stores information on a Knight chess piece
 and verifies its movements are valid
 @author  Kevin Clemons
 @version 1.0
 @since   2020-11-10 */
class Knight extends ChessPiece{

    /** Empty Constructor
     sets all fields value to a default value
     */
    Knight(){
        super();
    }

    /** Constructor sets all fields value to a default value
     @param pieceName sets piece name
     @param color sets piece color
     */
    Knight(Piece pieceName, Color color){
        super(pieceName, color);
    }

    /**
     takes in an x and y axis and the current chess piece and
     determines the elibility of moving to that position from
     the set, or initial, x and y axis based on the rules for
     the current chess piece.

     @param int row: the y axis
     @param Column col: the x axis

     @return boolean
     */
    public boolean verifyTarget(int row, Column col){
        boolean possibleMove = false;
        String tempTarget = String.valueOf(col);
        int num = tempTarget.charAt(0);

        String tempDest = String.valueOf(this.col);
        int num2 = tempDest.charAt(0);

        if(((Math.abs(num - num2) == 2) && (Math.abs(row - this.row) == 1)) ||
                ((Math.abs(num - num2) == 1) && (Math.abs(row - this.row) == 2))){
            possibleMove = true;
        }
        else{
            possibleMove = false;
        }
        return possibleMove;
    }
}

/**class Queen creates and stores information on a Queen chess piece
 and verifies its movements are valid
 @author  Kevin Clemons
 @version 1.0
 @since   2020-11-10 */
class Queen extends ChessPiece{

    /** Empty Constructor
     sets all fields value to a default value
     */
    Queen(){
        super();
    }

    /** Constructor sets all fields value to a default value
     @param pieceName sets piece name
     @param color sets piece color
     */
    Queen(Piece pieceName, Color color){
        super(pieceName, color);
    }

    /**
     takes in an x and y axis and the current chess piece and
     determines the elibility of moving to that position from
     the set, or initial, x and y axis based on the rules for
     the current chess piece.

     @param int row: the y axis
     @param Column col: the x axis

     @return boolean
     */

    public boolean verifyTarget(int row, Column col){
        boolean possibleMove = false;
        String tempTarget = String.valueOf(col);
        int num = tempTarget.charAt(0);

        String tempDest = String.valueOf(this.col);
        int num2 = tempDest.charAt(0);

        if((((Math.abs(num - num2) <= 8) && (row - this.row == 0))
                || ((num - num2) == 0) && (Math.abs(row - this.row) <= 8))
                ^ (Math.abs(num - num2) == Math.abs(row - this.row))){

            possibleMove = true;
        }
        else{
            possibleMove = false;
        }
        return possibleMove;
    }
}

/**class King creates and stores information on a King chess piece
 and verifies its movements are valid
 @author  Kevin Clemons
 @version 1.0
 @since   2020-11-10 */
class King extends ChessPiece{

    /** Empty Constructor
     sets all fields value to a default value
     */
    King(){
        super();
    }

    /** Constructor sets all fields value to a default value
     @param pieceName sets piece name
     @param color sets piece color
     */
    King(Piece pieceName, Color color){
        super(pieceName, color);
    }

    /**
     takes in an x and y axis and the current chess piece and
     determines the elibility of moving to that position from
     the set, or initial, x and y axis based on the rules for
     the current chess piece.

     @param int row: the y axis
     @param Column col: the x axis

     @return boolean
     */
    public boolean verifyTarget(int row, Column col){
        boolean possibleMove = false;
        String tempTarget = String.valueOf(col);
        int num = tempTarget.charAt(0);

        String tempDest = String.valueOf(this.col);
        int num2 = tempDest.charAt(0);

        if(Math.abs(num - num2) <= 1 && Math.abs(row - this.row) <= 1){
            possibleMove = true;
        }
        else{
            possibleMove = false;
        }
        return possibleMove;
    }
}

/**class Chessboard creates a private 2d array containg all
 possible coordinates then searches that array
 @author  Kevin Clemons
 @version 1.0
 @since   2020-11-10 */
class Chessboard{
    /**fields for creating and editing a 2d array Chessboard*/
    private final int MAX_ROW = 8;
    private final int MIN_ROW = 0;
    private Column col;
    private String[][] board_coordinates = new String[MAX_ROW][MAX_ROW];
    private StringBuilder sb = new StringBuilder();

    /** Empty Constructor
     sets all fields value to a default value
     integers to be converted to Chars
     creates 2d array with all possible chess move positions
     */
    Chessboard(){
        int board_columns = 65;
        int board_row = 49;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board_coordinates[i][j] = String.valueOf((char)(board_columns + j));
                board_coordinates[i][j] += String.valueOf((char)(board_row + i));
            }
        }
    }

    /**
     takes in an x and y axis and determines if that move is even possible.

     @param int row: the y axis
     @param Column col: the x axis

     @return boolean
     */
    public boolean withinChessboard(int row, Column col){
        String temp = String.valueOf(col);
        char targetCol = temp.charAt(0);
        char targetRow = (char)(row + 48);
        sb.append(targetCol).append(targetRow);
        String coord = sb.toString();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board_coordinates[i][j].equals(coord)){
                    return true;
                }

            }
        }
        return false;
    }
}

/**enum Piece containing chess pieces and all possible attributes
 */
enum Piece{
    pawn, queen, king, bishop, rook, knight
}

/**enum Column for all possible x axis
 */
enum Column{
    A, B, C, D, E, F, G, H;

}

/**enum Color contains color objects
 */
enum Color{
    black, white;
}