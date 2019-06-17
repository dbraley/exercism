// Package bob provides functionality for interacting with 'bob', a lackadaisical teenager
package bob

import "strings"

// Hey responds with a response based upon a provided greeting
func Hey(remark string) string {
	switch {
	case isShouting(remark) && isQuestion(remark):
		return "Calm down, I know what I'm doing!"
	case isShouting(remark):
		return "Whoa, chill out!"
	case isQuestion(remark):
		return "Sure."
	case isSilence(remark):
		return "Fine. Be that way!"
	default:
		return "Whatever."
	}
}
func isSilence(remark string) bool {
	return strings.TrimSpace(remark) == ""
}
func isQuestion(remark string) bool {
	return strings.HasSuffix(strings.TrimSpace(remark), "?")
}
func isShouting(remark string) bool {
	// Kind of a ugly way to check the case is all caps, not just entirely composed of characters lacking a letter-case
	return remark == strings.ToUpper(remark) && remark != strings.ToLower(remark)
}
