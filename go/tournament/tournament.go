package tournament

import (
	"encoding/csv"
	"fmt"
	"io"
	"sort"
	"strings"
)

type TeamTally struct {
	Name	string
	Win		int
	Draw 	int
	Loss	int
}

type FullTally map[string]*TeamTally

func Tally(reader io.Reader, buffer io.Writer) error {

	var err error
	fullTally := FullTally{}
	tallies := make([]*TeamTally, 0)

	csvReader := csv.NewReader(reader)
	csvReader.Comma  = ';'
	csvReader.FieldsPerRecord = 3

	for ;; {
		line, err := csvReader.Read()

		if err == io.EOF {
			break
		}
		if err != nil {
			switch err.(type) {
			case *csv.ParseError:
				switch len(line) {
				case 1:
					if !strings.HasPrefix(line[0], "#") {
						return err
					}
				default:
					return err
				}
			default:
				return err
			}
			continue
		} else {
			team1 := line[0]
			tallies = initTeamTally(fullTally, team1, tallies)
			team2 := line[1]
			tallies = initTeamTally(fullTally, team2, tallies)
			status := line[2]

			switch status {
			case "win":
				fullTally[team1].Win += 1
				fullTally[team2].Loss += 1
			case "loss":
				fullTally[team1].Loss += 1
				fullTally[team2].Win += 1
			case "draw":
				fullTally[team1].Draw += 1
				fullTally[team2].Draw += 1
			default:
				return fmt.Errorf("Illegal status %q", status)
			}
		}
	}

	sort.Slice(tallies, func(i, j int) bool {
		return isHigherInList(*tallies[i], *tallies[j])
	})

	_, err = buffer.Write([]byte("Team                           | MP |  W |  D |  L |  P\n"))
	if err != nil {
		return err
	}
	for _, tally := range tallies {
		_, err = writeTeamTally(*tally, buffer)
		if err != nil {
			return err
		}
	}
	return nil
}

func isHigherInList(first TeamTally, second TeamTally) bool {
	firstPoints := first.totalPoints()
	secondPoints := second.totalPoints()
	if firstPoints == secondPoints {
		return first.Name < second.Name
	}
	return firstPoints > secondPoints
}

func initTeamTally(fullTally FullTally, team1 string, tallies []*TeamTally) []*TeamTally {
	if fullTally[team1] == nil {
		tally := TeamTally{team1, 0, 0, 0}
		fullTally[team1] = &tally
		tallies = append(tallies, &tally)
	}
	return tallies
}

func writeTeamTally(teamTally TeamTally, buffer io.Writer) (int, error) {
	mp := teamTally.Win + teamTally.Draw + teamTally.Loss
	p := teamTally.totalPoints()
	ccTallyString := fmt.Sprintf("%-30v | %2d | %2d | %2d | %2d | %2d\n", teamTally.Name, mp, teamTally.Win, teamTally.Draw, teamTally.Loss, p)
	return buffer.Write([]byte(ccTallyString))
}

func (t TeamTally) totalPoints() int {
	return 3*t.Win + t.Draw
}

