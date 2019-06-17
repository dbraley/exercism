// +build bonus

package robotname

import (
	"log"
	"testing"
)

func TestCollisions(t *testing.T) {
	log.Printf("Max nameToRobotMap: %v %v", maxNames, maxNames-600000)

	// Test uniqueness for new robots.
	for i := len(seen); i <= maxNames-600000; i++ {
		if i % 1000 == 0 {
			log.Printf("Generating %dth name, %v already used", i, len(serialToRobotMap))
		}
		_ = New().getName(t, false)
	}

	// Test that nameToRobotMap aren't recycled either.
	r := New()
	for i := len(seen); i < maxNames; i++ {
		if i % 1000 == 0 {
			log.Printf("Generating %dth name, %v already used", i, len(serialToRobotMap))
		}
		r.Reset()
		_ = r.getName(t, false)
	}

	log.Printf("total consumption: %v", len(serialToRobotMap))

	// Test that name exhaustion is handled more or less correctly.
	_, err := New().Name()
	if err == nil {
		t.Fatalf("should return error if namespace is exhausted")
	}
}
