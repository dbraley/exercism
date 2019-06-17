package tree

import (
	"github.com/pkg/errors"
)

type Record struct {
	ID     int
	Parent int
}

type Node struct {
	ID       int
	Children []*Node
}

type NodeMap map[int]*Node

func Build(records []Record) (*Node, error) {
	nodeMap := NodeMap{}
	for _, r := range records {
		if r.ID == 0 && r.Parent != 0 {
			return nil, errors.Errorf("Illegal root node record %v", r)
		}
		if nodeMap[r.ID] != nil {
			return nil, errors.Errorf("Illegal duplicate node %v", r)
		}
		nodeMap[r.ID] = &Node{
			ID: r.ID,
		}
	}
	if len(nodeMap) > 0 && nodeMap[0] == nil {
		return nil, errors.Errorf("No root node record!")
	}
	for _, r := range records {
		if r.ID > r.Parent {
			parentNode := nodeMap[r.Parent]
			childNode := nodeMap[r.ID]
			parentNode.Children = insert(parentNode.Children, childNode)
		} else if r.ID != 0 {
			return nil, errors.Errorf("Illegal record %v has parent with larger id than self", r)
		}
	}
	for i := 0; i < len(nodeMap); i++ {
		if nodeMap[i] == nil {
			return nil, errors.Errorf("Missing record %v", i)
		}
	}
	return nodeMap[0], nil
}

func insert(nodes []*Node, newNode *Node) []*Node {
	pos := 0
	for i, node := range nodes {
		if node.ID < newNode.ID {
			pos = i+1
		} else {
			break
		}
	}
	return append(nodes[:pos], append([]*Node{newNode}, nodes[pos:]...)...)
}


