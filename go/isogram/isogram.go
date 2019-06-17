package isogram

import (
	"strings"
)

func IsIsogram(input string) bool {
	potentialIsogram := strings.ToLower(input)
	for _, rune := range potentialIsogram {
		if rune != '-' && rune != ' ' && strings.Count(potentialIsogram, string(rune)) > 1 {
			return false
		}
	}
	return true
}
