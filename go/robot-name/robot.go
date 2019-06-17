package robotname

import (
	"github.com/pkg/errors"
	"math/rand"
)

const max = 26*26*10*10*10

type Robot struct {
	myName string
}

var serialToRobotMap = map[int]*Robot{}

func (r *Robot) Name() (string, error) {
	if r.myName == "" {
		if len(serialToRobotMap) < max {
			startSerial := rand.Intn(max)
			for i := 0; i < max; i++ {
				finalSerial := (startSerial + i) % max
				if serialToRobotMap[finalSerial] == nil {
					var name= serialToName(finalSerial)
					r.myName = name
					serialToRobotMap[finalSerial] = r
					return r.myName, nil
				}
			}
		}
		return "", errors.Errorf("Consumed all %v robot nameToRobotMap!", len(serialToRobotMap))
	}
	return r.myName, nil
}

func serialToName(i int) string {
	nameRunes := make([]rune, 5)
	nameRunes[4], i = getDigit(i)
	nameRunes[3], i = getDigit(i)
	nameRunes[2], i = getDigit(i)
	nameRunes[1], i = getAlpha(i)
	nameRunes[0], i = getAlpha(i)
	return string(nameRunes)
}

func getDigit(remainder int) (rune, int) {
	return getRuneInRange(remainder, '0', 10)
}

func getAlpha(remainder int) (rune, int) {
	return getRuneInRange(remainder, 'A', 26)
}

func getRuneInRange(remainder int, start int, max int) (rune, int) {
	r := rune(start + remainder%max)
	return r, remainder / max
}

func (r *Robot) Reset() {
	r.myName = ""
}

