package question_bank.num37_hard_SudokuSolver;

import java.util.Arrays;

public class Main {

  public static void main(String [] args){


    Solution01 solution01 = new Solution01();
    //Solution solution01 = new Solution();

    char [][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'},
    };

    solution01.solveSudoku(board);
    for(int i = 0; i < board.length ; i++) {
      System.out.println(Arrays.toString(board[i]));
    }
  }
}

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 *
 *
 * 一个数独。
 *
 *
 *
 * 答案被标成红色。
 *
 * Note:
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */


class Solution {
  public void solveSudoku(char[][] board) {
    // 三个布尔数组 表明 行, 列, 还有 3*3 的方格的数字是否被使用过
    boolean[][] rowUsed = new boolean[9][10];
    boolean[][] colUsed = new boolean[9][10];
    boolean[][][] boxUsed = new boolean[3][3][10];
    // 初始化
    for(int row = 0; row < board.length; row++){
      for(int col = 0; col < board[0].length; col++) {
        int num = board[row][col] - '0';
        if(1 <= num && num <= 9){
          rowUsed[row][num] = true;
          colUsed[col][num] = true;
          boxUsed[row/3][col/3][num] = true;
        }
      }
    }
    // 递归尝试填充数组
    recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, 0, 0);
  }

  private boolean recusiveSolveSudoku(char[][]board, boolean[][]rowUsed, boolean[][]colUsed, boolean[][][]boxUsed, int row, int col){
    // 边界校验, 如果已经填充完成, 返回true, 表示一切结束
    if(col == board[0].length){
      col = 0;
      row++;
      if(row == board.length){
        return true;
      }
    }
    // 是空则尝试填充, 否则跳过继续尝试填充下一个位置
    if(board[row][col] == '.') {
      // 尝试填充1~9
      for(int num = 1; num <= 9; num++){
        boolean canUsed = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row/3][col/3][num]);
        if(canUsed){
          rowUsed[row][num] = true;
          colUsed[col][num] = true;
          boxUsed[row/3][col/3][num] = true;

          board[row][col] = (char)('0' + num);
          if(recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1)){
            return true;
          }
          board[row][col] = '.';

          rowUsed[row][num] = false;
          colUsed[col][num] = false;
          boxUsed[row/3][col/3][num] = false;
        }
      }
    } else {
      return recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1);
    }
    return false;
  }
}



/**
 * 我自己写的
 * */
class Solution01 {
  public void solveSudoku(char[][] board) {
    boolean rowNum[][] = new boolean[9][10];
    boolean colNum[][] = new boolean[9][10];
    boolean boxNum[][][] = new boolean[3][3][10];
    for (int row = 0; row < board.length; row++) {


      for (int col = 0; col < board[row].length; col++){

        int num = board[row][col] - '0';
        if (num >= 1 && num <= 9){
          rowNum[row][num] = true;
          colNum[col][num] = true;
          boxNum[row/3][col/3][num] = true;

        }
      }
    }
    fillSudoku(board,rowNum,colNum,boxNum,0,0);
  }


  public boolean fillSudoku (char [][] board, boolean [][] rowNum, boolean [][] colNum, boolean [][][] boxNum, int row, int col) {

    if(row == board.length) {
      row = 0;
      col++;
      if (col == board[0].length)
        return true;
    }

    if(board[row][col] == '.'){
      for(int num = 1; num <= 9; num++) {
        if (! (rowNum[row][num] || colNum[col][num] || boxNum[row/3][col/3][num])) {
          board[row][col] = (char)(num + '0');
          rowNum[row][num] = true;
          colNum[col][num] = true;
          boxNum[row/3][col/3][num] = true;
          if (fillSudoku(board, rowNum, colNum, boxNum, row+1, col))
            return true;
          board[row][col] = '.';
          rowNum[row][num] = false;
          colNum[col][num] = false;
          boxNum[row / 3][col / 3][num] = false;
        }
      }
    }else
      return fillSudoku(board, rowNum, colNum, boxNum, row+1, col);
    return false;
  }
}