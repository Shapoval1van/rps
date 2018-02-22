package com.rockpaperscissors;

import com.rockpaperscissors.strategy.MoveStrategy;

public class Player {
    private final int RANG_INCREMENT = 1;
    private String alias;
    private int rang;
    private MoveStrategy moveStrategy;

    public Player(String alias, int rang, MoveStrategy moveStrategy) {
        this.alias = alias;
        this.rang = rang;
        this.moveStrategy = moveStrategy;
    }

    public Player() {
    }

    public Move doMove() {
        Move move = moveStrategy.getMove();
        StringBuilder notice = new StringBuilder()
                .append("Player: ")
                .append(alias)
                .append(" chose... ")
                .append(move);
        System.out.println(notice.toString());
        return move;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getRang() {
        return rang;
    }

    public void addRang() {
        rang += RANG_INCREMENT;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (rang != player.rang) return false;
        return alias != null ? alias.equals(player.alias) : player.alias == null;
    }

    @Override
    public int hashCode() {
        int result = alias != null ? alias.hashCode() : 0;
        result = 31 * result + rang;
        return result;
    }
}
