package diffsquares

func SquareOfSum(x int) int {
	// >> 1 is a slightly more performant way to execute /2
	sum := x * (x + 1) >> 1
	return sum * sum
}

func SumOfSquares(x int) int {
	// << 1 is a slightly more performant way to execut *2
	return x * (x + 1) * (x << 1 + 1) / 6
}

func Difference(input int) int {
	return SquareOfSum(input) - SumOfSquares(input)
}