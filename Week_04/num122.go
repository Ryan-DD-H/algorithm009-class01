func maxProfit(prices []int) int {
    pre := -1
    max := pre - prices[0]
    for _, now := range(prices) {
        if now > pre {
            max += now - pre
        }
        pre = now
    }
    return max
}