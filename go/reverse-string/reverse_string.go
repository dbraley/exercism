package reverse

// String reverses the input sting
func String(input string) string {
	runes := []rune(input)
	for front, back := 0, len(runes)-1; front < back ; front, back = front+1, back-1 {
		runes[front], runes[back] = runes[back], runes[front]
	}
	return string(runes)
}
