// Package raindrops contains utilities for converting numbers to raindrop sounds
package raindrops

import "strconv"

// Convert generates a rain sound based on the number, or returns that number
func Convert(num int) string {
	sound := ""
	if num%3 == 0 {
		sound += "Pling"
	}
	if num%5 == 0 {
		sound += "Plang"
	}
	if num%7 == 0 {
		sound += "Plong"
	}
	if len(sound) > 0 {
		return sound
	}
	return strconv.Itoa(num)
}
