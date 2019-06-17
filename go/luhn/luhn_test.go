package luhn

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestAssertEqualArray(t *testing.T) {
	a := []int{1}
	b := []int{1}
	assert.Equal(t, a, b)
}

func TestValid(t *testing.T) {
	for _, test := range testCases {
		if ok := Valid(test.input); ok != test.ok {
			t.Fatalf("Fail(%s): %s\n\t Expected: %t\n\t Got: %t", test.input, test.description, test.ok, ok)
		} else {
			t.Logf("Pass(%s): %s", test.input, test.description)
		}
	}
}

func TestDoubleLuhn(t *testing.T) {
	for _, test := range doubleLuhnTestCases {
		if actual := doubleLuhn(test.input); actual != test.expected {
			t.Errorf("Fail(%v) expected %v got %v", test.description, test.expected, actual)
		}
	}
}

func TestLuhnTotal(t *testing.T) {
	for _, test := range luhnTotalTestCases {
		if actual := total(test.input); actual != test.expected {
			t.Errorf("Fail(%v) expected %v got %v", test.input, test.expected, actual)
		}
	}
}

func TestToIntArrayEmptyString(t *testing.T) {
	input := ""
	intArray, _ := toIntArray(input)
	if len(intArray) != 0 {
		t.Fatalf("Fail(%s) Expected %v got %v", input, []int{}, intArray)
	}
}

func TestToIntArrayOf0(t *testing.T) {
	input := "0"
	intArray, _ := toIntArray(input)
	if len(intArray) != 1 || intArray[0] != 0 {
		t.Fatalf("Fail(%s) Expected %v got %v", input, []int{0}, intArray)
	}
}

func TestToIntArrayOf1(t *testing.T) {
	input := "1"
	intArray, _ := toIntArray(input)
	if len(intArray) != 1 || intArray[0] != 1 {
		t.Fatalf("Fail(%s) Expected %v got %v", input, []int{1}, intArray)
	}
}

func TestToIntArrayOf10(t *testing.T) {
	input := "10"
	intArray, _ := toIntArray(input)
	if len(intArray) != 2 || intArray[0] != 1 || intArray[1] != 0 {
		t.Fatalf("Fail(%s) Expected %v got %v", input, []int{1,0}, intArray)
	}
}

func TestToIntArrayOf1_9(t *testing.T) {
	input := "1 9"
	intArray, _ := toIntArray(input)
	if len(intArray) != 2 || intArray[0] != 1 || intArray[1] != 9 {
		t.Fatalf("Fail(%s) Expected %v got %v", input, []int{1,9}, intArray)
	}
}

func TestToIntArrayOf__1_9___6(t *testing.T) {
	input := "  1 9   6"
	intArray, _ := toIntArray(input)
	if len(intArray) != 3 || intArray[0] != 1 || intArray[1] != 9 || intArray[2] != 6 {
		t.Fatalf("Fail(%s) Expected %v got %v", input, []int{1,9,6}, intArray)
	}
}

func BenchmarkValid(b *testing.B) {
	for i := 0; i < b.N; i++ {
		Valid("2323 2005 7766 3554")
	}
}
