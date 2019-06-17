package grains

import "github.com/pkg/errors"

func Square(input int) (uint64, error) {
	if input < 1 || input > 64 {
		return 0, errors.New("invalid input")
	}
	return 1 << (uint64(input) -1), nil
}

func Total() uint64  {
	sum := uint64(0)
	for n := 1; n <=64; n++ {
		grains, _ := Square(n)
		sum += grains
	}
	return sum
}
