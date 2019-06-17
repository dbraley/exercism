// Package scrabble is used to calculate scores for words used in a game of scrabble
package scrabble

import (
	"unicode"
)

func Score(word string) int {
	score := 0
	for _, letter := range word {
		score += letterScore(letter)
	}
	return score
}

func letterScore(letter rune) int {
	switch unicode.ToUpper(letter) {
	case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T':
		return 1
	case 'D', 'G':
		return 2
	case 'B', 'C', 'M', 'P':
		return 3
	case 'F', 'H', 'V', 'W', 'Y':
		return 4
	case 'K':
		return 5
	case 'J', 'X':
		return 8
	case 'Q', 'Z':
		return 10
	}
	return 0
}
