package matrix

import (
	"fmt"
	"strconv"
	"strings"
)

type Matrix struct {
	cells	map[int]map[int]int
	maxRow	int
	maxCol	int
}

func (matrix Matrix) Rows() [][]int {
	return [][]int{}
}

func (matrix Matrix) Set(i int, i2 int, i3 int) bool {
	return false
}

func (matrix Matrix) Cols() [][]int {
	return [][]int{}
}

func New(s string) (*Matrix, error) {
	cells := map[int]map[int]int{}
	maxRow, maxCol := 0, 0
	rows := strings.Split(s, "\n")
	for r, row := range rows {
		cells[r] = map[int]int{}
		fmt.Printf("reading row: %q \n", row)
		cellsInRow := strings.Split(strings.TrimSpace(row), " ")
		for c, cell := range cellsInRow {
			i, err := strconv.Atoi(cell)
			if err != nil {
				return nil, err
			} else {
				cells[r][c] = i
			}
			if c > maxCol {
				maxCol = c
			}
		}
		maxRow = r
	}
	for r := 0 ; r <= maxRow ; r++ {
		for c := 0 ; c <= maxCol ; c ++ {
			if row, ok := cells[r]; ok {
				if _, ok := row[c]; ok {
					continue
				}
			}
			return nil, fmt.Errorf("illegal matrix: should have size %vx%v, no value at (%v,%v)", maxRow, maxCol, r, c)
		}
	}
	return &Matrix{cells:cells, maxRow:maxRow, maxCol:maxCol}, nil
}

