package letter

// FreqMap records the frequency of each rune in a given text.
type FreqMap map[rune]int

// Frequency counts the frequency of each rune in a given text and returns this
// data as a FreqMap.
func Frequency(s string) FreqMap {
	m := FreqMap{}
	for _, r := range s {
		m[r]++
	}
	return m
}

func ConcurrentFrequency(strings []string) FreqMap {
	result := FreqMap{}
	size := len(strings)
	frequencyMapChannel := make(chan FreqMap, size)

	for _, s := range strings {
		go func(s string) {
			frequencyMapChannel <- Frequency(s)
		}(s)
	}
	for i := 0; i < size; i++ {
		freqMap := <-frequencyMapChannel
		for l, c := range freqMap {
			result[l] += c
		}
	}
	return result
}