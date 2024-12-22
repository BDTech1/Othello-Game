package othello.cli;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int[] getMoveInput() {
        int[] move = new int[2];

        System.out.print("Enter your move (row and column): ");
        move[0] = scanner.nextInt();
        move[1] = scanner.nextInt();

        return move;
    }

    public void closeScanner() {
        scanner.close();
    }
}
