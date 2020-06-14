func lemonadeChange(bills []int) bool {
   five, ten := 0, 0

   for _, bill := range(bills) {

        switch {
            case bill == 5:
                five++
            case bill == 10:
                five, ten = five - 1, ten + 1
            case ten > 0:
                five, ten = five - 1, ten - 1
            default:
                five -= 3
        }
       if five < 0 {
           return false
       }
   } 
   return true
}