
import java.util.*;

public class BranchSolve {
    public  static  int horizontalGridSize = 12;
    public static  int verticalGridSize = 5;
    public static char[] input;


    public static void randInput(char[] input) {
        System.out.println("The program will now shuffle the order of the pentominoes.");
        Random rand = new Random();
        for (int i = input.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            char temp = input[i];
            input[i] = input[j];
            input[j] = temp;
        }
        System.out.print("The order of the pentominoes is : ");
        for (int i = 0; i < input.length; i++) {
            if (i < input.length - 1) {
                System.out.print(input[i] + ", ");
            } else {
                System.out.print(input[i] + ".\n");
            }

        }
    }

    private static int characterToID(char character) {
        int pentID = -1;
        if (character == 'X') {
            pentID = 0;
        } else if (character == 'I') {
            pentID = 1;
        } else if (character == 'Z') {
            pentID = 2;
        } else if (character == 'T') {
            pentID = 3;
        } else if (character == 'U') {
            pentID = 4;
        } else if (character == 'V') {
            pentID = 5;
        } else if (character == 'W') {
            pentID = 6;
        } else if (character == 'Y') {
            pentID = 7;
        } else if (character == 'L') {
            pentID = 8;
        } else if (character == 'P') {
            pentID = 9;
        } else if (character == 'N') {
            pentID = 10;
        } else if (character == 'F') {
            pentID = 11;
        }
        return pentID;
    }

    private static  boolean solve (int[][] field, int pentominoIndex, char[] input, UI ui) throws InterruptedException {
        if (pentominoIndex == input.length) {
            ui.setState(field);
            return true;
        }
        if(!hasHoleSizeMultipleOf5(field)) {
            return false;
        }

        int pentID = characterToID(input[pentominoIndex]);

                for (int mutation = 0; mutation < PentominoDatabase.data[pentID].length; mutation++) {
                    int[][] pieceToPlace = PentominoDatabase.data[pentID][mutation];
        for (int x = 0; x <= field.length - pieceToPlace.length; x++) {
            for (int y = 0; y <= field[x].length - pieceToPlace[0].length; y++) {
                    if (canPlacePiece(field, pieceToPlace, x, y)) {
                        addPiece(field, pieceToPlace, pentID, x, y);
                        if(solve(field, pentominoIndex + 1, input, ui)){
                            return true;
                        }
                        removePiece(field, pieceToPlace, x, y);
                    }

                     }
                }
            }
        return false;
    }

    public static boolean hasHoleSizeMultipleOf5(int[][] field) {

        List<Integer> holeSizes = findHoleSizes(field);
        for (int holeSize : holeSizes) {
            if (holeSize % 5 != 0) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> findHoleSizes(int[][] field) {
        int rows = field.length;
        int columns = field[0].length;
        boolean[][] visited = new boolean[rows][columns];
        List<Integer> holeSizes = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (field[row][col] == -1 && !visited[row][col]) {
                    int holeSize = measureHole(field, visited, row, col);
                    if (holeSize > 0) {
                        holeSizes.add(holeSize);
                    }
                }
            }
        }

        return holeSizes;
    }


    private static int measureHole(int[][] field, boolean[][] visited, int row, int col) {
        int rows = field.length;
        int columns = field[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= columns || field[row][col] != -1 || visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;
        int holeSize = 1;

        holeSize += measureHole(field, visited, row - 1, col); // Up
        holeSize += measureHole(field, visited, row + 1, col); // Down
        holeSize += measureHole(field, visited, row, col - 1); // Left
        holeSize += measureHole(field, visited, row, col + 1); // Right

        return holeSize;
    }


    private static boolean canPlacePiece(int[][] field, int[][] piece, int x, int y) {

        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    int newX = x + i;
                    int newY = y + j;
                    if (newX < 0 || newX >= field.length || newY < 0 || newY >= field[i].length || field[newX][newY] != -1) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static void removePiece(int[][] field, int[][] piece, int x, int y) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    field[x + i][y + j] = -1;
                }
            }
        }
    }

    public static void addPiece(int[][] field, int[][] piece, int pieceID, int x, int y) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    field[x+i][y+j] = pieceID;
                }
            }
        }
    }

    public static void branchSolve(char[] input, UI ui) throws InterruptedException {
        int[][] field = new int[horizontalGridSize][verticalGridSize];
        for (int i = 0; i < field.length; i++) {
            Arrays.fill(field[i], -1);
        }
        ui.setState(field);
        boolean solved = solve(field,0, input, ui);
        if(solved){
            System.out.println("Solution found");
        }
        else {
            System.out.println("No solution found");
        }
        }

        public static void waitForEnter(){
        Scanner s = new Scanner(System.in);
        String b= s.nextLine();
        }
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type 'Start' if you want to start or type 'Exit' to quit the program.");
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("START")) {
                System.out.println("Do you want to change the standard grid size from 5*12? \nY/N?");
                String c1 = scanner.nextLine().toUpperCase();
                switch (c1){
                    case "Y":
                        String validPentominoes = "XYLZPNFITWVU";
                        System.out.println("How many lines?");
                        verticalGridSize= scanner.nextInt();
                        System.out.println("How many columns?");
                        horizontalGridSize= scanner.nextInt();
                        UI ui1 = new UI(horizontalGridSize, verticalGridSize, 50);
                        scanner.nextLine();
                        System.out.println("Valid pentominoes: X Y L Z P N F I T W V U.");
                        boolean ok;
                        do {
                            ok=true;
                            System.out.println("Input what pentomino shapes you would like to use, one after the other with no spaces separating them. " +
                                    "\nYou may use the same pentomino more than once.");
                            String pInput = scanner.nextLine();
                             input = pInput.toCharArray();
                            for (int i = 0; i < pInput.length(); i++) {
                                char b = input[i];
                                if(validPentominoes.indexOf(b)==-1){
                                    ok=false;
                                }
                            }
                            if(pInput.isEmpty()){
                                ok=false;
                                System.out.println("No pentominoes were selected for use.\n" +
                                        "Program will not find any solutions, please input pentominoes.");
                            }
                            else if(!ok){
                                System.out.println("Invalid pentomino sequence.");
                            }
                        }while(!ok);
                        randInput(input);
                        System.out.println("Program started.");
                        long start = System.currentTimeMillis();
                        branchSolve(input, ui1);
                        long finish = System.currentTimeMillis();
                        long elapsed = finish - start;
                        System.out.println("Time elapsed is " + elapsed + "ms.");
                        System.out.println("After pressing Enter the program will quit.");
                        waitForEnter();
                        System.exit(0);
                        break;
                    case "N":
                        input= new char[]{'X', 'Y', 'L', 'Z', 'P', 'N', 'F', 'I', 'T', 'W', 'V', 'U'};
                         UI ui2 = new UI(horizontalGridSize, verticalGridSize, 50);
                        System.out.println("The program will proceed with all the pentomino shapes.");
                        randInput(input);
                        System.out.println("Program started.");
                        long start1 = System.currentTimeMillis();
                        branchSolve(input, ui2);
                        long finish1 = System.currentTimeMillis();
                        long elapsed1= finish1 - start1;
                        System.out.println("Time elapsed is " + elapsed1 + "ms.");
                        System.out.println("After pressing Enter the program will quit.");
                        waitForEnter();
                        System.exit(0);
                        break;

                }


            } else if (choice.equals("EXIT")) {
                System.out.println("Have a good day!");
                System.exit(0);
            } else {
                System.out.println("Not a valid choice.");
            }
        }
    }


}
