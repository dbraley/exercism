package luhn

import "github.com/pkg/errors"

func Valid(cc string) bool {
	digits, err := toIntArray(cc)
	if err != nil || len(digits) < 2 {
		return false
	}
	sum := total(digits)
	return sum % 10 == 0
}

func toIntArray(input string) ([]int, error) {
	intDigits := make([]int, len(input))
	numIndex := 0
	for _, digit := range input {
		i := toInt(digit)
		if i >= 0 && i <= 9 {
			intDigits[numIndex] = i
			numIndex++
		} else if i != -16 {
			return nil, errors.New("illegal char")
		}
	}
	return intDigits[:numIndex], nil
}

func total(input []int) int {
	sum := 0
	for i, n := range input {
		if shouldDouble(input, i) {
			sum += doubleLuhn(n)
		} else {
			sum += n
		}
	}
	return sum
}

func shouldDouble(input []int, index int) bool {
	return (len(input)-index)%2 == 0
}

func doubleLuhn(i int) int {
	d := i * 2
	if d > 9 {
		d = d - 9
	}
	return d
}

func toInt(digit rune) int {
	return int(digit - '0')
}
