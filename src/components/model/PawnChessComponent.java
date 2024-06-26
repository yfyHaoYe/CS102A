package components.model;

import components.view.Chessboard;
import components.view.ChessboardPoint;
import components.controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * 这个类表示国际象棋里面的兵
 */
public class PawnChessComponent extends ChessComponent {
    /**
     * 黑兵和白兵的图片，static使得其可以被所有并对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;

    /**
     * 兵棋子对象自身的图片，是上面两种中的一种
     */
    private Image pawnImage;





    /**
     * 读取加载兵棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("src/assets/images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("src/assets/images/pawn-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定pawnImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateRookImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, Chessboard chessboard) {
        super(chessboardPoint, location, color, listener, size,chessboard);
        initiateRookImage(color);
    }

    /**
     * 兵棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 兵棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (chessColor==ChessColor.WHITE) {
            //向前一格或两格,目的地需要为空
            if (destination.y()==source.y()&&
                chessComponents[destination.x()][destination.y()] instanceof EmptySlotComponent) {
                //向前两格，路上没有棋子挡着
                if (source.x()==6&&destination.x()==4 &&
                chessComponents[destination.x()+1][destination.y()] instanceof EmptySlotComponent) {
                    return true;
                }
                //向前一格
                else if (destination.x()-source.x()==-1){
                    return true;
                }
            }
            //向斜前方吃棋子
            else if (Math.abs(destination.y()-source.y())==1&&destination.x()-source.x()==-1&&
                    !(chessComponents[destination.x()][destination.y()] instanceof EmptySlotComponent)
                    &&chessComponents[destination.x()][destination.y()].getChessColor()!=chessColor){
                return true;
            }
        }
        else if (chessColor==ChessColor.BLACK) {
            //向前一格或两格,目的地需要为空
            if (destination.y()==source.y()&&
                    chessComponents[destination.x()][destination.y()] instanceof EmptySlotComponent) {
                //向前两格，路上没有棋子挡着
                if (source.x()==1&&destination.x()==3 &&
                        chessComponents[destination.x()-1][destination.y()] instanceof EmptySlotComponent) {
                    return true;
                }
                //向前一格
                else if (destination.x()-source.x()==1){
                    return true;
                }
            }
            //向斜前方吃棋子
            else if (Math.abs(destination.y()-source.y())==1&&destination.x()-source.x()==1&&
                    !(chessComponents[destination.x()][destination.y()] instanceof EmptySlotComponent)
                    &&chessComponents[destination.x()][destination.y()].getChessColor()!=chessColor){
                return true;
            }
        }
        return false;
    }

    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(bishopImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the components.model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
