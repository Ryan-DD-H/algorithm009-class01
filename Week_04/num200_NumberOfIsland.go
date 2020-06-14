func numIslands(grid [][]byte) int {
    
    if grid == nil || len(grid) == 0 {
        return 0
    }
    row, col, count := len(grid), len(grid[0]), 0
    
    for i := 0; i < row; i++ {
        
        for j := 0; j < col; j++ {
            if grid[i][j] == '1' {
                count++
                dfs(grid, i, j, row, col)
            }  
        } 
    }
    return count
}


func dfs(grid [][]byte, row int, col int, rowLen int, colLen int) {
    
    if row < 0 || row >= rowLen || col < 0 || col >= colLen || grid[row][col] == '0' {
        return
    }
    
    grid[row][col] = '0'
    var rowNum = [4]int{0, 0, 1, -1}
    var colNum = [4]int{-1, 1, 0, 0}
    
    for i := 0; i < 4; i++ {
        dfs(grid, row + rowNum[i], col + colNum[i], rowLen, colLen)
    } 
}