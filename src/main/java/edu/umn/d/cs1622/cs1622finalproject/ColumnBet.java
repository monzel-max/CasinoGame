package edu.umn.d.cs1622.cs1622finalproject;

public class ColumnBet extends RouletteBet {
    private int column; // 1, 2 or 3

    public ColumnBet(int amt, int column) {
        super(amt);
        if (column < 1 || column > 3) {
            throw new IllegalArgumentException("Column must be 1, 2 or 3");
        }
        this.column = column;
        this.description = column + getColumnDescription(column);
    }

    private String getColumnDescription(int column) {
        return switch (column) {
            case 1 -> "st Column";
            case 2 -> "nd Column";
            case 3 -> "rd Column";
            default -> "";
        };
    }

    @Override
    public boolean isWinner(int result) {
        if (result == 0)
            return false;
        return result % 3 == column % 3;
    }

    @Override
    public int getPayoutMultiplier() {
        return 2; // 2 to 1 payout
    }
}
