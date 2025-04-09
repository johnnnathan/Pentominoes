public class BruteForce {
    public static final int horizontalGridSize = 12;
    public static final int verticalGridSize = 5;
    private static boolean solutionFound = false; // Added flag to track if a solution is found


    public static final char[] input = {'U', 'I', 'Z', 'T', 'X', 'V', 'W', 'Y', 'L', 'P', 'N', 'F'};
    public static UI ui = new UI(horizontalGridSize, verticalGridSize, 50);

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

    private static void bruteForceSearch(int[][] field, int pentominoIndex) {
        if (solutionFound) {
            return;
        }

        if (pentominoIndex == input.length) {

            boolean solution = true;
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if (field[i][j] == -1) {
                        solution = false;
                        break;
                    }
                }
                if (!solution) {
                    break;
                }
            }
            if (solution) {

                ui.setState(field);
                System.out.println("Solution found");
                solutionFound = true;
            }
            return;
        }

        int pentID = characterToID(input[pentominoIndex]);

        // Try different mutations (rotations/flips) of the pentomino
        for (int mutation = 0; mutation < PentominoDatabase.data[pentID].length; mutation++) {
            int[][] pieceToPlace = PentominoDatabase.data[pentID][mutation];

            for (int x = 0; x < field.length; x++) {
                for (int y = 0; y < field[x].length; y++) {
                    if (canPlacePiece(field, pieceToPlace, x, y)) {
                        addPiece(field, pieceToPlace, pentID, x, y);
                        bruteForceSearch(field, pentominoIndex + 1);
                        removePiece(field, pieceToPlace, x, y);

                    }
                }
            }
        }
    }

    private static boolean canPlacePiece(int[][] field, int[][] piece, int x, int y) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] == 1) {
                    int newX = x + i;
                    int newY = y + j;
                    if (newX < 0 || newX >= field.length || newY < 0 || newY >= field[newX].length || field[newX][newY] != -1) {
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
                    field[x + i][y + j] = pieceID;
                }
            }
        }
    }

    public static void bruteForceSearch() {
        int[][] field = new int[horizontalGridSize][verticalGridSize];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = -1;
            }
        }

        bruteForceSearch(field, 0);
    }

    public static void main(String[] args) {
        bruteForceSearch();
    }

}
