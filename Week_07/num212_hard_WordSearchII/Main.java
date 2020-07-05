package question_bank.num212_hard_WordSearchII;

import java.util.*;

public class Main {


  public static void main(String [] args) {


    char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
    };

    String [] words = {"oath","pea","eat","rain"};

    //Solution solution = new Solution();
    Solution01 solution01 = new Solution01();

    //System.out.println(solution.findWords(board,words));
    System.out.println(solution01.findWords(board,words));
  }
}


/**
 *给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 示例:
 *
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 *
 * 提示:
 *
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */

/**
 * num208与num79相结合
 * */


/**
 * 失败的
 * */
class Solution {

  Trie trie = new Trie();
  public List<String> findWords(char[][] board, String[] words) {

    List<String> list = new ArrayList<>();

    for (String str : words) {

      if(exist(board,str))
        list.add(str);
    }
    return list;
  }


  public boolean exist(char[][] board, String word) {
    trie.insert(word);

    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        if(dfs(board,new boolean[m][n],i,j,""+board[i][j]))
          return true;
      }
    }
    return false;

  }

  public boolean dfs (char[][] board, boolean [][] isVisited, int i, int j, String str){

    if (trie.search(str))
      return true;
    if (trie.startsWith(str)){
      isVisited[i][j] = true;

      if( i < board.length - 1 && !isVisited[i+1][j] && dfs(board,isVisited,i+1,j,str+board[i+1][j]))
        return true;
      if( i > 0 && !isVisited[i-1][j] && dfs(board,isVisited,i-1,j,str+board[i-1][j]))
        return true;
      if( j < board[i].length - 1 && !isVisited[i][j+1] && dfs(board,isVisited,i,j+1,str+board[i][j+1]))
        return true;
      if( j > 0 && !isVisited[i][j-1] && dfs(board,isVisited,i,j-1,str+board[i][j-1]))
        return true;

      isVisited[i][j] = false;
    }
    return false;
  }
}

class TrieNode {

  public boolean haveChild;
  public boolean isEnd = false;
  public TrieNode [] childNode = new TrieNode[26];


}

class Trie {

  public TrieNode trieNode = new TrieNode();

  public void insert(String word) {

    TrieNode child = this.trieNode;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if(child.childNode[ch - 'a'] == null)
        child.childNode[ch - 'a'] = new TrieNode();
      child = child.childNode[ch - 'a'];
      child.haveChild = true;
    }
    child.isEnd = true;

  }

  public boolean search(String word) {
    TrieNode child = findLastNode(word);
    return child != null && child.isEnd;
  }

  public boolean startsWith(String prefix) {
    TrieNode child = findLastNode(prefix);
    return child != null && child.haveChild;
  }

  public TrieNode findLastNode (String str) {
    TrieNode child = this.trieNode;
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      child = child.childNode[ch - 'a'];
      if (child == null)
        return null;
    }
    return child;
  }
}







class Solution01 {
  public List<String> findWords(char[][] board, String[] words) {
    //构建字典树
    wordTrie myTrie=new wordTrie();
    trieNode root=myTrie.root;
    for(String s:words)
      myTrie.insert(s);

    //使用set防止重复
    Set<String> result =new HashSet<>();
    int m=board.length;
    int n=board[0].length;
    boolean [][]visited=new boolean[m][n];
    //遍历整个二维数组
    for(int i=0;i<board.length; i++){
      for (int j = 0; j < board [0].length; j++){
        find(board,visited,i,j,m,n,result,root);
      }
    }
    return new LinkedList<>(result);
  }
  private void find(char [] [] board, boolean [][]visited,int i,int j,int m,int n,Set<String> result,trieNode cur){
    //边界以及是否已经访问判断
    if(i<0||i>=m||j<0||j>=n||visited[i][j])
      return;
    cur=cur.child[board[i][j]-'a'];
    visited[i][j]=true;
    if(cur==null)
    {
      //如果单词不匹配，回退
      visited[i][j]=false;
      return;
    }
    //找到单词加入
    if(cur.isLeaf)
    {
      result.add(cur.val);
      //找到单词后不能回退，因为可能是“ad” “addd”这样的单词得继续回溯
//            visited[i][j]=false;
//            return;
    }
    find(board,visited,i+1,j,m,n,result,cur);
    find(board,visited,i,j+1,m,n,result,cur);
    find(board,visited,i,j-1,m,n,result,cur);
    find(board,visited,i-1,j,m,n,result,cur);
    //最后要回退，因为下一个起点可能会用到上一个起点的字符
    visited[i][j]=false;
  }


}

//字典树
class wordTrie{
  public trieNode root=new trieNode();
  public void insert(String s){
    trieNode cur=root;
    for(char c:s.toCharArray()){
      if(cur.child[c-'a']==null){
        cur.child [c-'a'] = new trieNode();
        cur=cur.child[c-'a'];
      }else
        cur=cur.child [c-'a'];
    }
    cur.isLeaf=true;
    cur.val=s;
  }
}
//字典树结点
class trieNode{
  public String val;
  public trieNode[] child=new trieNode[26];
  public boolean isLeaf=false;
  trieNode(){
  }
}

