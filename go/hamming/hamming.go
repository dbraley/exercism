// Package hamming contains utilities for calculating hamming information about DNA
package hamming

import (
	"errors"
	"fmt"
)

// Distance calculates the hamming distance between two DNA Strands
func Distance(a, b string) (int, error) {
	if len(a) == len(b) {
		aRunes := []rune(a)
		bRunes := []rune(b)
		hamming := 0
		for i, v := range aRunes {
			if v != bRunes[i] {
				hamming++
			}
		}
		return hamming, nil
	}
	return -1, errors.New(fmt.Sprintf("The two strands of DNA do not have the same length: (%v):(%d)!= (%v):(%d)",
		a, len(a), b, len(b)))
}
