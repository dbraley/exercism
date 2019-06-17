package luhn

// Source: exercism/problem-specifications
// Commit: 4a80663 luhn: non-digit at end is invalid
// Problem Specifications Version: 1.4.0

var testCases = []struct {
	description string
	input       string
	ok          bool
}{
	{
		"single digit strings can not be valid",
		"1",
		false,
	},
	{
		"a single zero is invalid",
		"0",
		false,
	},
	{
		"a simple valid SIN that remains valid if reversed",
		"059",
		true,
	},
	{
		"a simple valid SIN that becomes invalid if reversed",
		"59",
		true,
	},
	{
		"a valid Canadian SIN",
		"055 444 285",
		true,
	},
	{
		"invalid Canadian SIN",
		"055 444 286",
		false,
	},
	{
		"invalid credit card",
		"8273 1232 7352 0569",
		false,
	},
	{
		"valid number with an even number of digits",
		"095 245 88",
		true,
	},
	{
		"valid strings with a non-digit included become invalid",
		"055a 444 285",
		false,
	},
	{
		"valid strings with a non-digit added at the end become invalid",
		"059a",
		false,
	},
	{
		"valid strings with punctuation included become invalid",
		"055-444-285",
		false,
	},
	{
		"valid strings with symbols included become invalid",
		"055£ 444$ 285",
		false,
	},
	{
		"single zero with space is invalid",
		" 0",
		false,
	},
	{
		"more than a single zero is valid",
		"0000 0",
		true,
	},
	{
		"input digit 9 is correctly converted to output digit 9",
		"091",
		true,
	},
	{
		"strings with non-digits is invalid",
		":9",
		false,
	},
}

var doubleLuhnTestCases = []struct {
	description string
	input       int
	expected    int
}{
	{"0",0, 0},
	{"1",1, 2},
	{"2",2, 4},
	{"3",3, 6},
	{"4",4, 8},
	{"5",5, 1},
	{"6",6, 3},
	{"7",7, 5},
	{"8",8, 7},
	{"9",9, 9},
}

var luhnTotalTestCases = []struct {
	input 		[]int
	expected 	int
}{
	{[]int{}, 0},
	{[]int{1}, 1},
	{[]int{0,1}, 1},
	{[]int{1,0,1}, 2},
	{[]int{1,1,1}, 4},
}
