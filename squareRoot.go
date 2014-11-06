package main

import (
    "fmt"
    "math"
)

func Sqrt(x float64) float64 {
    z := x/2
    for i := 0; i < 10; i++ {
        // Newton's Method
        answerBefore := z
    	z = z - (z*z - x)/(2*z)
        diff := z - answerBefore
        if math.Abs(diff)  < float64(0.0000000001)  {
        	break;
        }
    }
    return z;
}

func main() {
    val := float64(99999)
    fmt.Println(Sqrt(val))
    fmt.Println(math.Sqrt(val))
}
