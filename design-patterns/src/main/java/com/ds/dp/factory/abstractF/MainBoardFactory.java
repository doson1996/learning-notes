package com.ds.dp.factory.abstractF;

/**
 * @Author ds
 * @Date 2021/3/12 9:37
 * @Description 主板简单工厂
 */
public class MainBoardFactory {

    /**
     * 创建主板
     *
     * @param type
     * @return
     */
    public static MainBoardApi createMainBoard(int type) {
        MainBoardApi mainBoard = null;

        if (type == 1) {
            mainBoard = new GaMainBoard(10);
        }

        if (type == 2) {
            mainBoard = new MsiMainBoard(20);
        }

        return mainBoard;
    }


}
