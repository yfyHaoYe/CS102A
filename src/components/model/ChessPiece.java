package components.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 国际象棋棋子
 */
public class ChessPiece implements Serializable {

    private Integer kind;
    private Integer row;
    private Integer col;
    private ChessColor color;
    private Boolean selected;

    public ChessPiece() {
    }//用来将Component变成item加到list中

    public ChessPiece(Integer kind, Integer row, Integer col, ChessColor color) {
        this.kind = kind;
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public ChessColor getColor() {
        return color;
    }

    public void setColor(ChessColor color) {
        this.color = color;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }


    /**
     * 初始棋盘的list
     */
    public static List<ChessPiece> getAllChessPiece() {
        List<ChessPiece> list = new ArrayList<>();
        list.add(new ChessPiece(1,0, 0, ChessColor.BLACK));
        list.add(new ChessPiece(1,0, 7, ChessColor.BLACK));
        list.add(new ChessPiece(1,7, 0, ChessColor.WHITE));
        list.add(new ChessPiece(1,7, 7, ChessColor.WHITE));
        list.add(new ChessPiece(2,0, 3, ChessColor.BLACK));
        list.add(new ChessPiece(2,7, 3, ChessColor.WHITE));
        list.add(new ChessPiece(3,0, 4, ChessColor.BLACK));
        list.add(new ChessPiece(3,7, 4, ChessColor.WHITE));
        list.add(new ChessPiece(4,0, 2, ChessColor.BLACK));
        list.add(new ChessPiece(4,0, 5, ChessColor.BLACK));
        list.add(new ChessPiece(4,7, 2, ChessColor.WHITE));
        list.add(new ChessPiece(4,7, 5, ChessColor.WHITE));
        list.add(new ChessPiece(5,0, 1, ChessColor.BLACK));
        list.add(new ChessPiece(5,0, 6, ChessColor.BLACK));
        list.add(new ChessPiece(5,7, 1, ChessColor.WHITE));
        list.add(new ChessPiece(5,7, 6, ChessColor.WHITE));
        for (int i=0;i<8;i++){
            list.add(new ChessPiece(6,1, i, ChessColor.BLACK));
            list.add(new ChessPiece(6,6, i, ChessColor.WHITE));
        }
        return list;
    }

    /**
     * 将棋盘转换为list
     */
    public static List<ChessPiece> getListByComponent(ChessComponent[][] chessComponents) {
        List<ChessPiece> list = new ArrayList<>();
        for (ChessComponent[] component : chessComponents) {
            for (ChessComponent chessComponent : component) {
                if (!(chessComponent instanceof EmptySlotComponent)) {
                    ChessPiece item = new ChessPiece();
                    int kind = 0;
                    if (chessComponent instanceof RookChessComponent) {
                        kind = 1;
                    } else if (chessComponent instanceof KingChessComponent) {
                        kind = 2;
                    } else if (chessComponent instanceof QueenChessComponent) {
                        kind = 3;
                    } else if (chessComponent instanceof BishopChessComponent) {
                        kind = 4;
                    } else if (chessComponent instanceof KnightChessComponent) {
                        kind = 5;
                    } else if (chessComponent instanceof PawnChessComponent) {
                        kind = 6;
                    }
                    item.setKind(kind);
                    int row = chessComponent.getChessboardPoint().x(),
                            col = chessComponent.getChessboardPoint().y();
                    item.setRow(row);
                    item.setCol(col);
                    item.setColor(chessComponent.getChessColor());
                    item.setSelected(chessComponent.isSelected());
                    list.add(item);
                }
            }
        }
        return list;
    }
}
