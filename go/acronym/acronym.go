// Package acronym contains utilities for working with Acronyms
package acronym

import (
	"strings"
)

// Abbreviate creates an abbreviation of the provided phrase
func Abbreviate(input string) string {
	noDash := strings.Replace(input, "-", " ", -1)
	myStrings := strings.Split(noDash, " ")
	abbreviated := ""
	for i := 0 ; i < len(myStrings) ; i++ {
		runes := []rune(myStrings[i])
		abbreviated += string(runes[:1])
	}
	return strings.ToUpper(abbreviated)
}
