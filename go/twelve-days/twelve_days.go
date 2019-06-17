package twelve

import (
	"fmt"
	"strings"
)

func Song() string {
	verses := make([]string, 12)
	for i := 0; i < 12; i++ {
		verses[i] = Verse(i+1)
	}
	return strings.Join(verses, "\n") + "\n"
}

func Verse(day int) string {
	allPresentsForDay := make([]string, 0)
	for i := 0; i < day; i++ {
		allPresentsForDay = append(allPresentsForDay, presents(day - i, day > 1))
	}
	verseStart := fmt.Sprintf("On the %v day of Christmas my true love gave to me:", intToAdj(day))
	return verseStart + strings.Join(allPresentsForDay, ",")
}

func intToAdj(day int) string {
	switch day {
	case 1: return "first"
	case 2: return "second"
	case 3: return "third"
	case 4: return "fourth"
	case 5: return "fifth"
	case 6: return "sixth"
	case 7: return "seventh"
	case 8: return "eighth"
	case 9: return "ninth"
	case 10: return "tenth"
	case 11: return "eleventh"
	case 12: return "twelfth"
	default:
		return fmt.Sprintf("%dth", day)
	}
}

func presents(day int, lastOfMany bool) string {
	switch day {
	case 1: if lastOfMany {
			return " and a Partridge in a Pear Tree."
		}
		return " a Partridge in a Pear Tree."
	case 2: return " two Turtle Doves"
	case 3: return " three French Hens"
	case 4: return " four Calling Birds"
	case 5: return " five Gold Rings"
	case 6: return " six Geese-a-Laying"
	case 7: return " seven Swans-a-Swimming"
	case 8: return " eight Maids-a-Milking"
	case 9: return " nine Ladies Dancing"
	case 10: return " ten Lords-a-Leaping"
	case 11: return " eleven Pipers Piping"
	case 12: return " twelve Drummers Drumming"
	default: return ""
	}
}
