// Package space contains utilities for calculating relative orbital times
package space

const earthYearsPerMercuryYear = 0.2408467
const earthYearsPerVenusYear = 0.61519726
const earthYearsPerMarsYear = 1.8808158
const earthYearsPerJupiterYear = 11.862615
const earthYearsPerSaturnYear = 29.447498
const earthYearsPerUranusYear = 84.016846
const earthYearsPerNeptuneYear = 164.79132

const secondsPerEarthYear = 31557600

var planets = map[Planet]float64{
	"Mercury": secondsPerEarthYear * earthYearsPerMercuryYear,
	"Venus":   secondsPerEarthYear * earthYearsPerVenusYear,
	"Earth":   secondsPerEarthYear,
	"Mars":    secondsPerEarthYear * earthYearsPerMarsYear,
	"Jupiter": secondsPerEarthYear * earthYearsPerJupiterYear,
	"Saturn":  secondsPerEarthYear * earthYearsPerSaturnYear,
	"Uranus":  secondsPerEarthYear * earthYearsPerUranusYear,
	"Neptune": secondsPerEarthYear * earthYearsPerNeptuneYear,
}

// Age calculates the number of years that have passed on the provided Planet for the given number of seconds
func Age(seconds float64, planet Planet) float64 {
	secondsPerPlanetsYear, found := planets[planet]
	if found {
		return seconds / secondsPerPlanetsYear
	}
	return 0
}

type Planet string