package clock

import "fmt"

type Clock struct {
	Hour	int
	Minute	int
}

func New(hour int, minute int) Clock {
	hc, m := normalizeMinutes(minute)
	_, h := normalizeHours(hour + hc)
	return Clock {h, m}
}

func normalizeMinutes(minute int) (int, int) {
	return normalize(minute, 60)
}

func normalizeHours(hour int) (int, int) {
	return normalize(hour, 24)
}

func normalize(val int, max int) (int, int) {
	cary := val / max
	m := val % max
	if m < 0 {
		cary --
		m += max
	}
	return cary, m
}

func (clock Clock) Add(i int) Clock {
	return New(clock.Hour, clock.Minute + i)
}

func (clock Clock) Subtract(i int) Clock {
	return New(clock.Hour, clock.Minute - i)
}

func (clock Clock) String() string {
	return fmt.Sprintf("%02d:%02d", clock.Hour, clock.Minute)
}
