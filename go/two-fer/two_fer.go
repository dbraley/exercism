// Package twofer includes utilities for declaring the sharing of resources
package twofer

// ShareWith returns a string suggesting a set of exactly two resources be split among an entity implied by the name
// passed in, and the callee itself
func ShareWith(s string) string {
	return "One for " + getName(s) + ", one for me."
}
func getName(s string) string {
	if len(s) > 0 {
		return s
	} else {
		return "you"
	}
}
