// Package gigasecond provides utilities for working with gigaseconds
package gigasecond

import "time"

// Represents 1 Gigasecond
const Gigasecond = time.Second * 1000000000

// AddGigasecond adds one Gigasecond to the provided time
func AddGigasecond(t time.Time) time.Time {
	return t.Add(Gigasecond)
}
